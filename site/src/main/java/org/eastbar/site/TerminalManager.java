package org.eastbar.site;

import com.google.common.collect.Maps;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import org.eastbar.codec.ClientAuthScheme;
import org.eastbar.codec.IPFormatException;
import org.eastbar.codec.IpV4;
import org.eastbar.site.biz.CustomerLoginEvent;
import org.eastbar.site.biz.CustomerLogoutEvent;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.util.Map;

/**
 * Created by andysjtu on 2015/5/18.
 */
@Component
public class TerminalManager {

    private Map<String, Terminal> terminalMap = Maps.newConcurrentMap();
    private Map<InetSocketAddress, Channel> terminalChannels = Maps.newConcurrentMap();
    private Map<String,InetSocketAddress> ipSocketMaps = Maps.newConcurrentMap();

    public void customerLogin(CustomerLoginEvent loginEvent) {
        String eip = loginEvent.getIpAddress();
        String ip = null;
        try {
            ip = IpV4.convertIPV4(eip).toRegularIpFormat();
        } catch (IPFormatException e) {
            e.printStackTrace();
            ip = eip;
        }
        if (terminalMap.containsKey(ip)) {
            Terminal terminal = terminalMap.get(ip);
            terminal.loginCustomer(loginEvent);
        } else {
            Terminal terminal = new Terminal();
            terminalMap.put(ip, terminal);
            terminal.loginCustomer(loginEvent);
        }
    }

    public void customerLogout(CustomerLogoutEvent logoutEvent) {
        String eip = logoutEvent.getIpAddress();
        String ip = null;
        try {
            ip = IpV4.convertIPV4(eip).toRegularIpFormat();
        } catch (IPFormatException e) {
            e.printStackTrace();
            ip = eip;
        }
        if (terminalMap.containsKey(ip)) {
            Terminal terminal = terminalMap.get(ip);
            terminal.logoutCustomer(logoutEvent);
        } else {
            Terminal terminal = new Terminal();
            terminalMap.put(ip, terminal);
            terminal.logoutCustomer(logoutEvent);
        }

        InetSocketAddress address = ipSocketMaps.remove(ip);
        if(address!=null){
            Channel channel = terminalChannels.remove(address);
            if(channel!=null){
                channel.close();
            }
        }
    }

    public void monitorActive(ClientAuthScheme authScheme,Channel  channel) {
        String ip = authScheme.getIpAddress();
        Terminal terminal = terminalMap.get(ip);
        if(terminal!=null){
           terminal.active(authScheme);
            InetSocketAddress address = (InetSocketAddress)channel.remoteAddress();
            ipSocketMaps.put(ip,address);
            terminalChannels.put(address,channel);
            channel.closeFuture().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    Channel closeChannel = future.channel();
                    InetSocketAddress closeAddress = (InetSocketAddress)closeChannel.remoteAddress();

                    terminalChannels.remove(closeAddress);
                    //TODO
                }
            });
        }else{
            //没有收到BIZ信息
        }
    }

    public void monitorInacive() {

    }
}
