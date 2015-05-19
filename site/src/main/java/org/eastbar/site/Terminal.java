package org.eastbar.site;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import org.eastbar.codec.*;
import org.eastbar.site.biz.CustomerLoginEvent;
import org.eastbar.site.biz.CustomerLogoutEvent;
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

    private UsedInfo usedInfo;

    private TerminalInfo termInfo;

    private volatile boolean isLock = false;

    public Terminal(String ip) {
        this.ip = ip;
    }

    public void operate(final Channel respTargetChannel) {
        final SocketMsg msg = new ClientCmd.LockClientCmd();
        if (termChannel != null && termChannel.isActive()) {
            termChannel.writeAndFlush(msg).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (!future.isSuccess()) {
                        sendErrorResponse(respTargetChannel, msg.getMessageId(), msg.getMessageType());
                    }
                }
            });
        } else {
            sendErrorResponse(respTargetChannel, msg.getMessageId(), msg.getMessageType());
        }
    }

    private void sendErrorResponse(Channel respTargetChannel, short messageId, short messageType) {
        GenResp resp = new GenResp.S2CenterGenResp(messageId, messageType, GenResp.Status.Failure);
        respTargetChannel.writeAndFlush(respTargetChannel);
    }


    public void loginCustomer(CustomerLoginEvent loginEvent) {

        if (!(status == Status.DOWN || status == Status.LOGOUT)) {
            logger.warn("状况变化出现异常，可能是退出登录通知没有收到");
            if (termChannel != null) {
                termChannel.close();
            }
        }
        usedInfo = new UsedInfo();
        usedInfo.setAccount(loginEvent.getAccount());
        usedInfo.setAuthOrg(loginEvent.getAuthOrg());
        usedInfo.setId(loginEvent.getId());
        usedInfo.setIdType(loginEvent.getIdType());
        usedInfo.setLoginTime(loginEvent.getLoginTime());
        usedInfo.setName(loginEvent.getName());
        status = Status.LOGIN;
    }

    public void logoutCustomer(CustomerLogoutEvent logoutEvent) {
        if (status == Status.ONLINE || status == Status.OFFLINE) {
            usedInfo.setLogoutTime(logoutEvent.getLogoutTime());
        } else {
            logger.warn("状况变化出现异常，可能是Login登录通知没有收到");
            usedInfo = new UsedInfo();
            usedInfo.setAccount(logoutEvent.getAccount());
            usedInfo.setAuthOrg(logoutEvent.getAuthOrg());
            usedInfo.setId(logoutEvent.getId());
            usedInfo.setIdType(logoutEvent.getIdType());
            usedInfo.setLoginTime(logoutEvent.getLoginTime());
            usedInfo.setLogoutTime(logoutEvent.getLogoutTime());
            usedInfo.setName(logoutEvent.getName());

        }
        status = Status.LOGOUT;
    }

    public void active(ClientInitReq initReq, final Channel channel) {
        this.termChannel = channel;
        this.status = Status.ONLINE;
        termInfo = new TerminalInfo();
        ClientAuthScheme authScheme = initReq.getAuthSchme();
        termInfo.setClientVersion(authScheme.getVersion());
        termInfo.setIpAddress(authScheme.getIpAddress());
        termInfo.setMacAddress(authScheme.getMacAddress());
        termInfo.setOs(authScheme.getOs());

        ClientAuthResp authResp = new ClientAuthResp();
        if (usedInfo != null) {
            authResp.setVersion(authScheme.getVersion());
            authResp.setIdType(usedInfo.getIdType());
            authResp.setUserId(usedInfo.getId());
            authResp.setUserName(usedInfo.getName());
            authResp.setUserAccount(usedInfo.getAccount());
        } else {
            logger.warn("没有收到业务系统的登录信息，将向客户端推送模拟测试信息");
            authResp.setIdType("1");
            authResp.setVersion("1");
            authResp.setUserId("310101197902026432");
            authResp.setUserName("张三");
            authResp.setUserAccount("testAccount");
        }

        ClientInitResp resp = new ClientInitResp(initReq.getMessageId(), authResp);


        channel.closeFuture().addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    termChannel = null;
                    status = Status.OFFLINE;
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

        if (status == Status.LOGIN) {
            //TODO
        } else {
            logger.warn("无法知道用户的准确身份");
            //TODO
        }
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


}
