package org.eastbar.site;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.netty.channel.Channel;
import org.eastbar.codec.ClientInitReq;
import org.eastbar.codec.IPFormatException;
import org.eastbar.codec.IpV4;
import org.eastbar.site.biz.CustomerLoginEvent;
import org.eastbar.site.biz.CustomerLogoutEvent;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by andysjtu on 2015/5/18.
 */
@Component
public class TerminalManager {

    private Map<String, Terminal> terminalMap = Maps.newConcurrentMap();
//    private Map<InetSocketAddress, Channel> terminalChannels = Maps.newConcurrentMap();
//    private Map<String,InetSocketAddress> ipSocketMaps = Maps.newConcurrentMap();


    public Terminal getTerminal(String ip) {
        Terminal terminal = terminalMap.get(ip);
        if (terminal == null) {
            terminal = new Terminal(ip);
            terminalMap.put(ip, terminal);
        }
        return terminal;
    }

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
            Terminal terminal = new Terminal(ip);
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
            Terminal terminal = new Terminal(ip);
            terminalMap.put(ip, terminal);
            terminal.logoutCustomer(logoutEvent);
        }

//        InetSocketAddress address = ipSocketMaps.remove(ip);
//        if(address!=null){
//            Channel channel = terminalChannels.remove(address);
//            if(channel!=null){
//                channel.close();
//            }
//        }
    }

    public void monitorActive(ClientInitReq initReq, Channel channel) {
        String ip = initReq.getAuthSchme().getIpAddress();
        Terminal terminal = terminalMap.get(ip);
        if (terminal != null) {
            terminal.active(initReq, channel);
            InetSocketAddress address = (InetSocketAddress) channel.remoteAddress();
//            ipSocketMaps.put(ip,address);
//            terminalChannels.put(address,channel);
//            channel.closeFuture().addListener(new ChannelFutureListener() {
//                @Override
//                public void operationComplete(ChannelFuture future) throws Exception {
//                    Channel closeChannel = future.channel();
//                    InetSocketAddress closeAddress = (InetSocketAddress)closeChannel.remoteAddress();
//
//                    terminalChannels.remove(closeAddress);
//                    //TODO
//                }
//            });
        } else {
            terminal = new Terminal(ip);
            terminalMap.put(ip, terminal);
            terminal.active(initReq, channel);
        }
    }

    public List<String> getHostStatus() {
        List<String> results = Lists.newArrayList();
        Collection<Terminal> terminals = Collections.unmodifiableCollection(terminalMap.values());
        for (Terminal terminal : terminals) {
            StringBuilder builder = new StringBuilder();
            builder.append(terminal.getIp());
            builder.append(" : ");
            builder.append(terminal.getStatus());
            results.add(builder.toString());
        }
        return results;
    }

}
