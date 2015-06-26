package org.eastbar.site;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.netty.channel.Channel;
import org.eastbar.codec.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public final static Logger logger= LoggerFactory.getLogger(TerminalManager.class);

    @Autowired
    private Site site;

    private Map<String, Terminal> terminalMap = Maps.newConcurrentMap();

    public Terminal getTerminalOrCreated(String ip) {
        Terminal terminal = terminalMap.get(ip);
        if (terminal == null) {
            terminal = new Terminal(site, ip);
            terminalMap.put(ip, terminal);
        }
        return terminal;
    }

    public Map<String, Terminal> getTerminalMap() {
        return terminalMap;
    }

    public void setTerminalMap(Map<String, Terminal> terminalMap) {
        this.terminalMap = terminalMap;
    }

    public Terminal getTerminalWithoutCreate(String ip) {
        return terminalMap.get(ip);
    }

    public void customerLogin(UserInfo loginEvent) {
        String eip = loginEvent.getIp();
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
            Terminal terminal = new Terminal(site, ip);
            terminalMap.put(ip, terminal);
            terminal.loginCustomer(loginEvent);
        }
    }

    public void customerLogout(UserInfo logoutEvent) {
        String eip = logoutEvent.getIp();
        String ip = null;
        try {
            ip = IpV4.convertIPV4(eip).toRegularIpFormat();
        } catch (IPFormatException e) {
            e.printStackTrace();
            ip = eip;
        }
        if (terminalMap.containsKey(ip)) {
//            logger.info("找到该设备{}",ip);
            Terminal terminal = terminalMap.get(ip);
            terminal.logoutCustomer(logoutEvent);
        } else {
//            logger.info("没有找到该设备{}",ip);
            Terminal terminal = new Terminal(site, ip);
            terminalMap.put(ip, terminal);
            terminal.logoutCustomer(logoutEvent);
        }


    }

    public void monitorActive(ClientInitReq initReq, Channel channel) {
        String ip = initReq.getAuthSchme().getIp();
        Terminal terminal = terminalMap.get(ip);
        if (terminal != null) {
            terminal.active(initReq, channel);
            InetSocketAddress address = (InetSocketAddress) channel.remoteAddress();
        } else {
            terminal = new Terminal(site, ip);
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
            builder.append(" : online->");
            builder.append(terminal.isOnline());
            builder.append(":connected->");
            builder.append(terminal.isConnected());
            results.add(builder.toString());
        }
        return results;
    }

    public List<TermReport> getTerminalReport() {
        List<TermReport> reporst = Lists.newArrayList();
        Collection<Terminal> terminals = terminalMap.values();
        for (Terminal t : terminals) {

            reporst.add(t.getReport());
        }


        return reporst;
    }

    public void closeAll() {
        Collection<Terminal> terminals = Collections.unmodifiableCollection(terminalMap.values());
        for (Terminal terminal : terminals) {
            Channel channel = terminal.channel();
            if (channel != null && channel.isActive()) {
                channel.close();
            }
        }
    }
}
