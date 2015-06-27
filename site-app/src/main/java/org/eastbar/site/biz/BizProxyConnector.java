package org.eastbar.site.biz;

import io.netty.channel.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.eastbar.codec.EastbarFrameDecoder;
import org.eastbar.codec.HeartBeatenHandler;
import org.eastbar.codec.SocketMsgDecoder;
import org.eastbar.codec.SocketMsgEncoder;
import org.eastbar.net.AbstractConnector;
import org.eastbar.site.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by AndySJTU on 2015/6/17.
 */
@Component
public class BizProxyConnector extends AbstractConnector {

    public static String DEFALT_REMOTE_ADDRESS = "127.0.0.1";
    public static int DEFAULT_LOCAL_PORT = 3005;


    @Autowired
    private Site site;

    @PostConstruct
    public void init(){
        remoteAddress=DEFALT_REMOTE_ADDRESS;
        remotePort=DEFAULT_LOCAL_PORT;
        localPort=3006;
    }


    @Override
    protected void registerHandler(ChannelPipeline pipeline) {
        pipeline.addLast("logHandler", new LoggingHandler("CON-TO-BIZ-PROXY", LogLevel.DEBUG));
        pipeline.addLast("idleHandler", new IdleStateHandler(0, 0, 60));
        pipeline.addLast("eastFrameDecoder", new EastbarFrameDecoder());
        pipeline.addLast("sockMsgDecoder", new SocketMsgDecoder());
        pipeline.addLast("socketMsgEncoder", new SocketMsgEncoder());
        pipeline.addLast("beaten", new HeartBeatenHandler());
        pipeline.addLast("bizHandler", new BizHandler(site));
    }


}
