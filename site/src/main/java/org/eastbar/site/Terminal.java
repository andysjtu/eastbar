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
    private volatile Status status = Status.DOWN;
    private volatile Channel termChannel;

    private volatile UserInfo userInfo;

    private volatile TerminalInfo termInfo;

    private volatile boolean active = false;

    public Terminal(String ip) {
        this.ip = ip;
    }

    public void redirect(final SocketMsg msg, final Channel respChannel) {
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


    public void loginCustomer(UserInfo loginEvent) {

        if (!(status == Status.DOWN || status == Status.LOGOUT)) {
            logger.warn("状况变化出现异常，可能是退出登录通知没有收到");
            if (termChannel != null) {
                termChannel.close();
            }
        }
        userInfo = new UserInfo(loginEvent);
        status = Status.LOGIN;
    }

    public void logoutCustomer(UserInfo logoutEvent) {
        if (status == Status.ONLINE || status == Status.OFFLINE) {
            userInfo.setLogoutTime(logoutEvent.getLogoutTime());
        } else {
            logger.warn("状况变化出现异常，可能是Login登录通知没有收到");
            userInfo = new UserInfo(logoutEvent);

        }
        status = Status.LOGOUT;
    }

    public void active(ClientInitReq initReq, final Channel channel) {
        this.termChannel = channel;
        this.status = Status.ONLINE;
        this.active = true;
        termInfo = new TerminalInfo();
        ClientAuthScheme authScheme = initReq.getAuthSchme();
        DozerUtil.copyProperties(authScheme, termInfo);

        ClientAuthResp authResp = new ClientAuthResp();
        if (userInfo != null) {
            authResp.setVersion(authScheme.getVersion());
            authResp.setIdType(userInfo.getIdType());
            authResp.setId(userInfo.getId());
            authResp.setName(userInfo.getName());
            authResp.setAccount(userInfo.getAccount());
        } else {
            logger.warn("没有收到业务系统的登录信息，将向客户端推送模拟测试信息");
            authResp.setIdType("1");
            authResp.setVersion("1");
            authResp.setId("310101197902026432");
            authResp.setName("张三");
            authResp.setAccount("testAccount");
        }

        ClientInitResp resp = new ClientInitResp(initReq.getMessageId(), authResp);


        channel.closeFuture().addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    termChannel = null;
                    status = Status.OFFLINE;
                    active = false;
                }
            }
        });

        channel.writeAndFlush(resp).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (!future.isSuccess()) {
                    logger.warn("向客户端推送注册信息失败，请检查");
                    future.channel().close();
                }
            }
        });
    }

    public Channel channel() {
        return termChannel;
    }

    public String getIp() {
        return ip;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


}
