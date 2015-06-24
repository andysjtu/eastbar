package org.eastbar.site;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.eastbar.codec.EastbarFrameDecoder;
import org.eastbar.codec.SocketMsgDecoder;
import org.eastbar.codec.SocketMsgEncoder;
import org.eastbar.comm.Listener;
import org.eastbar.site.handler.console.ConsoleHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andysjtu on 2015/5/10.
 */
@Component
public class ConsoleListener implements Listener {
    
    public static final  Logger logger = LoggerFactory.getLogger(ConsoleListener.class);

    @Autowired
    private SiteServer siteServer;

    public void setSiteServer(SiteServer siteServer) {
        this.siteServer = siteServer;
    }

    private NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
    private NioEventLoopGroup workerGroup = new NioEventLoopGroup(2);
    private volatile Channel serverChannel;



    private int listenPort=9999;

    @Override
    public void listen() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_BACKLOG, 2)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("logHandler", new LoggingHandler("CONN CONSOLE-CHANNEL",LogLevel.DEBUG));
                        pipeline.addLast("eastFrameDecoder", new EastbarFrameDecoder());
                        pipeline.addLast("socketMsgDecoder", new SocketMsgDecoder());
                        pipeline.addLast("socketMsgEncoder", new SocketMsgEncoder());
                        pipeline.addLast("cmdDecoder",new ConsoleHandler(siteServer));
                    }
                });
        ChannelFuture future = bootstrap.bind(listenPort);
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    logger.info("START LISTEN TO CONSOLE CMD SUCCESS");
                    serverChannel = future.channel();

                } else {
                    logger.error("START LISTEN TO CONSOLE CMD SUCCESS");
                    bossGroup.shutdownGracefully();
                    workerGroup.shutdownGracefully();
                }
            }
        });
    }

    @Override
    public void stopListen() {
        if(serverChannel!=null) {
            ChannelFuture future = serverChannel.close();
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        logger.info("STOP LISTENING TO CONSOLE CMD SUCCESS");
                    }
                }
            });

        }
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
