package org.eastbar.site.client;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.eastbar.net.Listener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * just for download policies
 * Created by andysjtu on 2015/5/10.
 */
@Component
public class PolicyDldListener implements Listener {
    public final static Logger logger = LoggerFactory.getLogger(PolicyDldListener.class);

    private NioEventLoopGroup bossGroup = new NioEventLoopGroup(2);
    private NioEventLoopGroup workerGroup = new NioEventLoopGroup(100);
    private volatile Channel serverChannel;

    private String localIp;
    private int listenPort = 6176;

    @Autowired
    private PolicyManager manager;

    private ChannelFutureListener closeListener = new ChannelFutureListener() {
        @Override
        public void operationComplete(ChannelFuture future) throws Exception {
            serverChannel = null;
        }
    };

    @Override
    public void listen() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup).
                channel(NioServerSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("loggingHandler",new LoggingHandler("下载策略通道",LogLevel.INFO));
                        pipeline.addLast("http-decoder", new HttpRequestDecoder());
                        pipeline.addLast("http-aggregator", new HttpObjectAggregator(65536));
                        pipeline.addLast("http-encoder", new HttpResponseEncoder());
                        pipeline.addLast("http-chunked", new ChunkedWriteHandler());
                        pipeline.addLast("policyHandler", new PolicyDldHandler(manager));

                    }
                });

        ChannelFuture future = bootstrap.bind(listenPort).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    logger.info("启动下载服务开启成功");
                    serverChannel = future.channel();
                    serverChannel.closeFuture().addListener(closeListener);
                } else {
                    logger.info("启动下载服务开启失败");
                    logger.warn("原因是:", future.cause());
                    System.out.println("listening port is : " + listenPort);
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
                        logger.info("关闭侦听客户端服务成功");
                        serverChannel = null;
                    }
                }
            });

        }
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
