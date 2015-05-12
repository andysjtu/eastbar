package org.eastbar.site;

import com.google.common.collect.Maps;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import org.eastbar.codec.ClientAuthResp;
import org.eastbar.codec.ClientAuthScheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Map<String,Channel> channelMap = Maps.newConcurrentMap();

    private Map<String,Terminal> terminalMap = Maps.newConcurrentMap();


    public void disconnectAll() {

    }

    public ClientAuthResp registerChannel(final Channel channel, ClientAuthScheme authScheme) {
        logger.info("authScheme is : " + authScheme);

        String host = getAddress(channel);
        logger.info("上传通道地址是 : "+getAddress(channel));
        String repostHost = authScheme.getIpAddress();

        if(!host.equalsIgnoreCase(repostHost)){
            logger.warn("上传IP信息{}和Socket通道信息{}不一致，以Socket通道信息为准 ",repostHost,host);
        }

        if(channelMap.containsKey(host)){
            logger.warn("重复登录不允许, 地址是 : " + host);
            return null;
        }

        addClientChannel(channel);

        if(terminalMap.containsKey(host)){
            Terminal terminal = terminalMap.get(host);
            terminal.setClientVersion(authScheme.getVersion());
            terminal.setOs(authScheme.getOs());
            terminal.setMacAddress(authScheme.getMacAddress());

            ClientAuthResp resp = new ClientAuthResp();
            resp.setIdType(terminal.getIdType());
            resp.setVersion(authScheme.getVersion());
            resp.setUserId(terminal.getId());
            resp.setUserName(terminal.getUsername());
            resp.setUserAccount(terminal.getUserAccount());
            return resp;
        }

        return mockResponse();

    }

    private ClientAuthResp mockResponse() {
        ClientAuthResp resp = new ClientAuthResp();
        resp.setIdType("1");
        resp.setVersion("1");
        resp.setUserId("310107197902026432");
        resp.setUserName("张三");
        resp.setUserAccount("testAccount");
        return resp;
    }

    private void addClientChannel(Channel channel) {
        channelMap.put(getAddress(channel), channel);
        channel.closeFuture().addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                Channel ch = future.channel();
                String host = getAddress(ch);
                channelMap.remove(host);
            }
        });
    }

    private String getAddress(Channel channel){
        InetSocketAddress add = (InetSocketAddress)channel.remoteAddress();
        String host = add.getHostString();
        return host;
    }

    public Set<String> getHosts(){
        return channelMap.keySet();
    }

    public Channel getTerminalChannel(String hostIp) {
        return channelMap.get(hostIp);
    }
}
