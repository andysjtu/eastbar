package org.eastbar.codec.console;

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
import org.eastbar.net.Listener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by andysjtu on 2015/6/30.
 */
public class ConsoleListener implements Listener {
    public static final Logger logger = LoggerFactory.getLogger(ConsoleListener.class);


    private NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
    private NioEventLoopGroup workerGroup = new NioEventLoopGroup(2);
    private volatile Channel serverChannel;

    private int listenPort=7777;

    @Override
    public void listen() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_BACKLOG, 2)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        configHandler(ch);
                    }
                });
        ChannelFuture future = bootstrap.bind(listenPort);
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    logger.info("开启侦听终端命令服务成功");
                    serverChannel = future.channel();

                } else {
                    logger.error("开启侦听终端命令服务失败");
                    bossGroup.shutdownGracefully();
                    workerGroup.shutdownGracefully();
                }
            }
        });
    }

    protected void configHandler(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("logHandler", new LoggingHandler("连接命令窗口通道", LogLevel.INFO));
        pipeline.addLast("eastFrameDecoder", new EastbarFrameDecoder());
        pipeline.addLast("socketMsgDecoder", new SocketMsgDecoder());
        pipeline.addLast("socketMsgEncoder", new SocketMsgEncoder());
    }

    @Override
    public void stopListen() {
        if(serverChannel!=null) {
            ChannelFuture future = serverChannel.close();
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if(future.isSuccess()){
                        logger.info("关闭侦听终端命令服务成功");
                    }
                }
            });
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


    public int getListenPort() {
        return listenPort;
    }

    public void setListenPort(int listenPort) {
        this.listenPort = listenPort;
    }
}
