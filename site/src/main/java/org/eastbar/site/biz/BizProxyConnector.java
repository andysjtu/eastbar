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
import org.eastbar.site.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by AndySJTU on 2015/6/17.
 */
@Component
public class BizProxyConnector {
    private String remoteAddr = "127.0.0.1";
    private int remotePort = 3005;
    private int localPort = 3006;
    private NioEventLoopGroup workerGroup = new NioEventLoopGroup();
    private Bootstrap bootstrap;

    @Autowired
    private  Site site;



    private void config() {
        bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .remoteAddress(remoteAddr, remotePort)
                .localAddress(localPort)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("logHandler", new LoggingHandler("CON-TO-BIZ-PROXY", LogLevel.INFO));
                        pipeline.addLast("idleHandler",new IdleStateHandler(0,0,60));
                        pipeline.addLast("eastFrameDecoder", new EastbarFrameDecoder());
                        pipeline.addLast("sockMsgDecoder", new SocketMsgDecoder());
                        pipeline.addLast("socketMsgEncoder", new SocketMsgEncoder());
                        pipeline.addLast("beaten",new HeartBeatenHandler());
                        pipeline.addLast("bizHandler", new BizHandler(site));
                    }
                });

    }


    public void connect() {
        if (bootstrap == null) {
            config();
        }
        bootstrap.connect().addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    future.channel().closeFuture().addListener(new ChannelFutureListener() {
                        @Override
                        public void operationComplete(ChannelFuture future) throws Exception {
                            if (future.isSuccess()) {
                                scheduleNext();
                            }
                        }
                    });
                } else {
                    scheduleNext();
                }
            }
        });
    }

    private void scheduleNext() {
        workerGroup.schedule(new Runnable() {
            @Override
            public void run() {
                connect();
            }
        }, 2, TimeUnit.SECONDS);
    }

}
