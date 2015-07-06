package org.eastbar.alert2mq;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.compression.ZlibCodecFactory;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.eastbar.codec.EastbarFrameDecoder;
import org.eastbar.codec.SocketMsgDecoder;
import org.eastbar.codec.SocketMsgEncoder;
import org.eastbar.net.Listener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by AndySJTU on 2015/6/4.
 */
@Component
public class AlertListener implements Listener {
    public final static Logger logger = LoggerFactory.getLogger(AlertListener.class);

    private NioEventLoopGroup bossGroup = new NioEventLoopGroup(2);
    private NioEventLoopGroup workerGroup = new NioEventLoopGroup(1000);

    @Autowired
    private JmsAlertSender alertSender;

    private volatile Channel serverChannel;
    @Value("${alert.port}")
    private int listenPort=9001;

    @Override
    public void listen() {
        if (listenPort < 1) {
            throw new IllegalArgumentException("listenPort must be set ");
        }
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT)
                .option(ChannelOption.SO_BACKLOG, 2000)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("log", new LoggingHandler("告警接受通道", LogLevel.DEBUG));
                        pipeline.addLast("readTimeout", new ReadTimeoutHandler(180, TimeUnit.SECONDS));
                        pipeline.addLast("gzipDecoder", ZlibCodecFactory.newZlibDecoder());
                        pipeline.addLast("gzipEncoder", ZlibCodecFactory.newZlibEncoder(3));
                        pipeline.addLast("eastFrameDecoder", new EastbarFrameDecoder());
                        pipeline.addLast("sockMsgDecoder", new SocketMsgDecoder());
                        pipeline.addLast("socketMsgEncoder", new SocketMsgEncoder());
                        pipeline.addLast("bizyAlertHandler", new AlertHandler(alertSender));
                    }
                });
        ChannelFuture future = bootstrap.bind(listenPort);
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    serverChannel = future.channel();
                    logger.info("开启侦听告警服务成功");
                } else {
                    logger.error("开启侦听告警服务失败", future.cause());
                    bossGroup.shutdownGracefully();
                    workerGroup.shutdownGracefully();
                }
            }
        });
    }

    @Override
    public void stopListen() {
        if (serverChannel != null) {
            ChannelFuture future = serverChannel.close();
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        logger.info("关闭侦听告警服务成功");
                    }
                }
            });
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
