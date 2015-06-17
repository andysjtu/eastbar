package org.eastbar.site.biz;

import ch.qos.logback.core.net.SyslogOutputStream;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.eastbar.codec.EastbarFrameDecoder;
import org.eastbar.codec.HeartBeatenHandler;
import org.eastbar.codec.SocketMsgDecoder;
import org.eastbar.codec.SocketMsgEncoder;
import org.eastbar.comm.AbstractConnector;
import org.eastbar.site.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by AndySJTU on 2015/6/17.
 */
@Component
public class BizProxyConnector extends AbstractConnector {

    public static String DEFALT_REMOTE_ADDRESS = "127.0.0.1";
    public static int DEFAULT_REMOTE_PORT = 3005;
    public static int DEFAULT_LOCAL_PORT = 3006;


    @Autowired
    private Site site;


    @Override
    protected void registerHandler(ChannelPipeline pipeline) {
        pipeline.addLast("logHandler", new LoggingHandler("CON-TO-BIZ-PROXY", LogLevel.INFO));
        pipeline.addLast("idleHandler", new IdleStateHandler(0, 0, 60));
        pipeline.addLast("eastFrameDecoder", new EastbarFrameDecoder());
        pipeline.addLast("sockMsgDecoder", new SocketMsgDecoder());
        pipeline.addLast("socketMsgEncoder", new SocketMsgEncoder());
        pipeline.addLast("beaten", new HeartBeatenHandler());
        pipeline.addLast("bizHandler", new BizHandler(site));
    }


}
