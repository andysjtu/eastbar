package org.eastbar.logd;

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
import org.eastbar.comm.Listener;
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
public class LogListener implements Listener {
    public final static Logger logger = LoggerFactory.getLogger(LogListener.class);

    private NioEventLoopGroup bossGroup = new NioEventLoopGroup(2);
    private NioEventLoopGroup workerGroup = new NioEventLoopGroup(1000);

//    @Autowired
//    private LogSaver logSaver;

    private volatile Channel serverChannel;
    @Value("${log.port}")
    private int listenPort = 9100;

    @Autowired
    private PrgLogSender logSender;

    @Override
    public void listen() {
        if (listenPort < 1) {
            throw new IllegalArgumentException("listenPort must be set ");
        }
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childOption(ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT)
                .option(ChannelOption.SO_BACKLOG, 2000)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("log", new LoggingHandler("日志接受通道", LogLevel.INFO));
                        pipeline.addLast("readTimeout", new ReadTimeoutHandler(120, TimeUnit.SECONDS));
                        pipeline.addLast("gzipDecoder", ZlibCodecFactory.newZlibDecoder());
                        pipeline.addLast("gzipEncoder", ZlibCodecFactory.newZlibEncoder(3));
                        pipeline.addLast("readTimeoutHandler", new ReadTimeoutHandler(2, TimeUnit.MINUTES));
                        pipeline.addLast("eastFrameDecoder", new EastbarFrameDecoder());
                        pipeline.addLast("sockMsgDecoder", new SocketMsgDecoder());
                        pipeline.addLast("socketMsgEncoder", new SocketMsgEncoder());

                        pipeline.addLast("bizyLogHandler", new LogHandler(null, logSender));
                    }
                });
        ChannelFuture future = bootstrap.bind(listenPort);
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    serverChannel = future.channel();
                    logger.info("开启侦听日志服务成功");
                } else {
                    logger.error("开启侦听日志服务失败", future.cause());
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
                        logger.info("关闭侦听日志服务成功");
                    }
                }
            });
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
