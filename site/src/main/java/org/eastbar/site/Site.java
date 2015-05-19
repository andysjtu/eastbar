package org.eastbar.site;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sun.xml.internal.xsom.impl.Ref;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import org.eastbar.codec.ClientAuthResp;
import org.eastbar.codec.ClientAuthScheme;
import org.eastbar.codec.ClientInitReq;
import org.eastbar.codec.LoginEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Map;
import java.util.Set;

/**
 * Created by andysjtu on 2015/5/9.
 */
@Component
public class Site {
    public final static Logger logger= LoggerFactory.getLogger(Site.class);


    private String siteCode;


    @Autowired
    private TerminalManager terminalManager;

    private volatile Channel centerChannel;


    public void setCenterChannel(Channel centerChannel){
        this.centerChannel = centerChannel;
    }


    public TerminalManager getTerminalManager() {
        return terminalManager;
    }

    public void setTerminalManager(TerminalManager terminalManager) {
        this.terminalManager = terminalManager;
    }

    public void disconnectAll() {

    }

    public void registerCustomerLogin(){
        //TODO
    }

    public void registerCustomerLogout(){
        //TODO
    }

    public void registerChannel(final Channel channel, ClientInitReq initReq) {
        ClientAuthScheme authScheme = initReq.getAuthSchme();
        logger.info("authScheme is : " + authScheme);

        String host = getAddress(channel);
        logger.info("上传通道地址是 : "+getAddress(channel));
        String reportHost = authScheme.getIpAddress();

        if(!host.equalsIgnoreCase(reportHost)){
            logger.warn("上传IP信息{}和Socket通道信息{}不一致，以上传IP信息为准 ",reportHost,host);
        }

        terminalManager.monitorActive(initReq,channel);

//        if(channelMap.containsKey(reportHost)){
//            logger.warn("重复登录不允许, 地址是 : " + reportHost);
//            return null;
//        }

//        addClientChannel(channel);
//
//        if(terminalMap.containsKey(reportHost)){
//            Terminal terminal = terminalMap.get(reportHost);
//            terminal.setClientVersion(authScheme.getVersion());
//            terminal.setOs(authScheme.getOs());
//            terminal.setMacAddress(authScheme.getMacAddress());
//
//            ClientAuthResp resp = new ClientAuthResp();
//            resp.setIdType(terminal.getIdType());
//            resp.setVersion(authScheme.getVersion());
//            resp.setUserId(terminal.getId());
//            resp.setUserName(terminal.getUsername());
//            resp.setUserAccount(terminal.getUserAccount());
//            return resp;
//        }
//
//        return mockResponse();

    }

//    private ClientAuthResp mockResponse() {
//        ClientAuthResp resp = new ClientAuthResp();
//        resp.setIdType("1");
//        resp.setVersion("1");
//        resp.setUserId("310107197902026432");
//        resp.setUserName("张三");
//        resp.setUserAccount("testAccount");
//        return resp;
//    }

//    private void addClientChannel(Channel channel) {
//        channelMap.put(getAddress(channel), channel);
//        channel.closeFuture().addListener(new ChannelFutureListener() {
//            @Override
//            public void operationComplete(ChannelFuture future) throws Exception {
//                Channel ch = future.channel();
//                String host = getAddress(ch);
//                channelMap.remove(host);
//            }
//        });
//    }

    private String getAddress(Channel channel){
        InetSocketAddress add = (InetSocketAddress)channel.remoteAddress();
        String host = add.getHostString();
        return host;
    }

    public Set<String> getHosts(){
        return Sets.newHashSet(terminalManager.getHostStatus());
    }

    public Channel getTerminalChannel(String hostIp) {
        Terminal terminal = terminalManager.getTerminal(hostIp);
        return terminal.channel();
//        return channelMap.get(hostIp);
    }

    public void reportOnLine(){

    }

    public void reportOffline(){

    }


}
