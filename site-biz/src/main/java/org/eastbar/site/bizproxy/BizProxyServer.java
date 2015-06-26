package org.eastbar.site.bizproxy;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.internal.ConcurrentSet;
import org.eastbar.codec.SocketMsg;
import org.eastbar.codec.UserInfo;
import org.eastbar.codec.biz.CustomLoginMsg;
import org.eastbar.codec.biz.CustomLogoutMsg;
import org.eastbar.codec.biz.UserInfoMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by AndySJTU on 2015/6/15.
 */
public class BizProxyServer {
    public final static Logger logger = LoggerFactory.getLogger(BizProxyServer.class);

    private Map<String, UserInfo> ipHost = Maps.newConcurrentMap();

    private ConcurrentSet<Channel> sets = new ConcurrentSet<>();


    public UserInfo getUserInfo(String ip) {
        return ipHost.get(ip);
    }

    public void registerCustomerLogin(UserInfo userInfo) {
        ipHost.put(userInfo.getIp(), userInfo);
        notifySite(new CustomLoginMsg(userInfo));
        printSlef();
    }

    private void printSlef() {
        logger.info("iphost is {}", ipHost);
    }

    public void registerCustomerLogout(UserInfo userInfo) {
//        System.out.println("key is : "+userInfo.getIp());
//        System.out.println("map is : "+ipHost);
        UserInfo info = ipHost.remove(userInfo.getIp());
//        System.out.println("info is : "+info);
        notifySite(new CustomLogoutMsg(userInfo));
        printSlef();
    }

    private void notifySite(SocketMsg msg) {
//        SocketMsg msg = new UserInfoMsg(Lists.newArrayList(userInfo));
        for (Channel channel : sets) {
            channel.writeAndFlush(msg);
        }
    }


    public List<UserInfo> getAllUserInfos() {
        List<UserInfo> list = Lists.newArrayList(ipHost.values());
        return list;
    }

    public void registerSiteChannelContext(ChannelHandlerContext ctx) {
        List<UserInfo> userInfo = getAllUserInfos();
        SocketMsg msg = new UserInfoMsg(userInfo);
        ctx.channel().writeAndFlush(msg);
        sets.add(ctx.channel());
        ctx.channel().closeFuture().addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if(future.isSuccess()) {
                    sets.remove(future.channel());
                }
            }
        });
    }
}
