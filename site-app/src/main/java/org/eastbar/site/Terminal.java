package org.eastbar.site;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import org.eastbar.codec.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by AndySJTU on 2015/5/11.
 */
public class Terminal {
    public final static Logger logger = LoggerFactory.getLogger(Terminal.class);


    private final String ip;

    private volatile Channel termChannel;

    private UserInfo userInfo;

    private TerminalInfo termInfo;

    private volatile boolean connected = false;
    private volatile boolean online = false;
    private final String siteCode;

    private final Site site;

    private volatile boolean isSpecail =false;

    public Terminal(Site site, String ip) {
        this.siteCode = site.getSiteCode();
        this.site = site;
        this.ip = ip;
        userInfo = new UserInfo();
        userInfo.setIp(ip);
        termInfo = new TerminalInfo();
        termInfo.setIp(ip);
    }

    public void redirect(final SocketMsg msg, final Channel respChannel) {
        logger.debug("Term 当前状态是 con : {},onlie : {}",connected,online);
        if (termChannel != null && termChannel.isActive()) {
            termChannel.writeAndFlush(msg).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (!future.isSuccess()) {
                        logger.warn("发送命令失败", future.cause());
                        sendErrorResponse(respChannel, msg.getMessageId(), msg.getMessageType());
                    }
                }
            });
            ProxyChannelHandler handler = (ProxyChannelHandler) termChannel.pipeline().get(ProxyChannelHandler.HANDLER_NAME);
            if (handler != null) {
                handler.registerTarget(msg.getMessageId(), msg.getMessageType(), respChannel);
            } else {
                logger.warn("没有找到ProxyHandler，请检查");
            }
        } else {
            try {
                logger.warn("客户通道没有建立，返回失败操作");
                sendErrorResponse(respChannel, msg.getMessageId(), msg.getMessageType());
            } finally {
                msg.release();
            }
        }
    }


    private void sendErrorResponse(Channel respTargetChannel, short messageId, short messageType) {
        GenResp resp = new GenResp.S2CenterGenResp(messageId, messageType, GenResp.Status.Failure);
        respTargetChannel.writeAndFlush(resp);
    }


    public void loginCustomer(UserInfo loginEvent,boolean isSpecail) {
        this.isSpecail = isSpecail;
        if (loginEvent.isSameLoginInfo(userInfo)) {
            return;
        }
        online = true;
        userInfo = new UserInfo(loginEvent);
        notifyStatusUpdate();

//        if (connected) {//如果客户端还保持连接，可能是比业务信息早到，可能是业务信息中断
//            if (termChannel != null) {
//                termChannel.close();
//            }
//        }

    }

    private void notifyStatusUpdate() {
        site.notifyEvent(getReport());
    }

    public void logoutCustomer(UserInfo logoutEvent) {
        this.isSpecail = false;
        if (logoutEvent.isSameLogoutInfo(userInfo)) {
            return;
        }
        online = false;
        if (isSameCustomer(logoutEvent)) {//same one
            DozerUtil.copyProperties(logoutEvent, userInfo);
            userInfo.setLogoutTime(logoutEvent.getLogoutTime());
        } else {//not same one
            logger.warn("不是同一个人上下机，可能出现信息中断,人工产生上一个人退出登录事件");
            generateLogoutEvent(logoutEvent);
            DozerUtil.copyProperties(logoutEvent, userInfo);
        }

        if (connected) {
            termChannel.close();
        }
        notifyStatusUpdate();

    }

    private void generateLogoutEvent(UserInfo logoutEvent) {
        TermReport report = getReport();
        report.setLogoutTime(logoutEvent.getLoginTime());
        site.notifyEvent(report);
    }

    private boolean isSameCustomer(UserInfo logoutEvent) {
        if (userInfo.getId() != null) {
            return logoutEvent.getId().equals(userInfo.getId());
        }
        return true;
    }

    public void active(ClientInitReq initReq, final Channel channel) {
        if (online) {
            this.termChannel = channel;
            this.connected = true;
            ClientAuthScheme authScheme = initReq.getAuthSchme();
            DozerUtil.copyProperties(authScheme, termInfo);

            ClientAuthResp authResp = new ClientAuthResp();
            logger.debug("向客户端推送客户信息,推送地址是:{}", channel.remoteAddress());

            authResp.setVersion(authScheme.getVersion());
            authResp.setIdType(userInfo.getIdType());
            authResp.setId(userInfo.getId());
            authResp.setName(userInfo.getName());
            authResp.setAccount(userInfo.getAccount());
            if(isSpecail){
                authResp.setSpecialMode(true);
            }

            channel.closeFuture().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        termChannel = null;
                        connected = false;
                        notifyStatusUpdate();
                    }
                }
            });

            ClientInitResp resp = new ClientInitResp(initReq.getMessageId(), authResp);

            channel.writeAndFlush(resp).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (!future.isSuccess()) {
                        logger.warn("向客户端推送注册信息失败，请检查");
                        future.channel().close();
                    }
                }
            });
            notifyStatusUpdate();
        } else {
            logger.warn("没有收到业务系统的登录信息，将关闭通道:{}", channel.remoteAddress());
            channel.close();
        }


//        this.termChannel = channel;
//        this.connected = true;
//        ClientAuthScheme authScheme = initReq.getAuthSchme();
//        DozerUtil.copyProperties(authScheme, termInfo);
//
//        ClientAuthResp authResp = new ClientAuthResp();

    }

    public Channel channel() {
        return termChannel;
    }

    public String getIp() {
        return ip;
    }


    public TerminalInfo getTermInfo() {
        return termInfo;
    }

    public void setTermInfo(TerminalInfo termInfo) {
        this.termInfo = termInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public TermReport getReport() {
        TermReport report = new TermReport();
        DozerUtil.copyProperties(userInfo, report);
        DozerUtil.copyProperties(termInfo, report);
        report.setOnline(online);
        report.setConnected(connected);
        report.setSiteCode(siteCode);
        report.setIp(getIp());
        return report;

    }
}
