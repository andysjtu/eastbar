package org.eastbar.site;

import com.google.common.collect.Lists;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import org.eastbar.codec.ClientAuthScheme;
import org.eastbar.site.biz.CustomerLoginEvent;
import org.eastbar.site.biz.CustomerLogoutEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by AndySJTU on 2015/5/11.
 */
public class Terminal {
    public final static Logger logger = LoggerFactory.getLogger(Terminal.class);


    private String ip;
    private Status status = Status.DOWN;
    private volatile Channel termChannel;

    private UsedInfo usedInfo;

    private TerminalInfo termInfo;


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

    public void active(ClientAuthScheme authScheme, final Channel channel) {
        this.termChannel = channel;
        this.status = Status.ONLINE;
        termInfo = new TerminalInfo();
        termInfo.setClientVersion(authScheme.getVersion());
        termInfo.setIpAddress(authScheme.getIpAddress());
        termInfo.setMacAddress(authScheme.getMacAddress());
        termInfo.setOs(authScheme.getOs());
        channel.closeFuture().addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    termChannel = null;
                    status = Status.OFFLINE;
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


    public static enum Status {
        DOWN, LOGIN, ONLINE, OFFLINE, LOGOUT;
    }
}
