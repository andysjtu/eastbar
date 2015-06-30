package org.eastbar.site.biz;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.eastbar.codec.SiteMsgType;
import org.eastbar.codec.SocketMsg;
import org.eastbar.codec.UserInfo;
import org.eastbar.codec.biz.CustomLoginMsg;
import org.eastbar.codec.biz.CustomLogoutMsg;
import org.eastbar.codec.biz.UserInfoMsg;
import org.eastbar.site.Site;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by AndySJTU on 2015/5/20.
 */
public class BizHandler extends SimpleChannelInboundHandler<SocketMsg> {
    public final static Logger logger = LoggerFactory.getLogger(BizHandler.class);


    private final Site site;

    public BizHandler(Site site) {
        this.site = site;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        short type = msg.getMessageType();
        if (type == SiteMsgType.CUSTOMER_LOGIN.shortValue()) {
            CustomLoginMsg userInfoMsg = new CustomLoginMsg(msg);
            UserInfo userInfo = userInfoMsg.getUserInfo();
            logger.debug("收到注册信息 : " + userInfo);
            site.registerCustomerLogin(userInfo);

        } else if (type == SiteMsgType.CUSTOMER_LOGOUT.shortValue()) {
            CustomLogoutMsg userInfoMsg = new CustomLogoutMsg(msg);
            UserInfo userInfo = userInfoMsg.getUserInfo();
            logger.debug("收到注销信息 : " + userInfo);
            site.registerCustomerLogout(userInfo);
        } else if (type == SiteMsgType.USER_INFO_MSG.shortValue()) {
            UserInfoMsg userInfoMsg = new UserInfoMsg(msg);
            List<UserInfo> userInfoList = userInfoMsg.getUserInfos();
            logger.debug("收到全部信息 : "+userInfoList);
            site.initTermInfos(userInfoList);
        } else {
            logger.warn("receive some package: {} that is not recognized,please check ", type);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        System.out.println("--------------------------------------------------");
        logger.warn("Unexpected exception from downstream.........", cause);
    }

}
