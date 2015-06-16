package org.eastbar.site.bizproxy;

import io.netty.channel.ChannelHandlerContext;
import org.assertj.core.util.Maps;
import org.eastbar.codec.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by AndySJTU on 2015/6/15.
 */
public class BizProxyServer {
    private Map<String, UserInfo> ipHost = Maps.newConcurrentHashMap();

    private volatile ChannelHandlerContext innerContext;


    public UserInfo getUserInfo(String ip) {
        return ipHost.get(ip);
    }

    public void registerCustomerLogin(UserInfo userInfo) {
        ipHost.put(userInfo.getIp(), userInfo);
    }

    public void registerCustomerLogout(UserInfo userInfo) {
        ipHost.put(userInfo.getIp(), userInfo);
    }


    public List<UserInfo> getAllUserInfos() {
        List<UserInfo>
    }
}
