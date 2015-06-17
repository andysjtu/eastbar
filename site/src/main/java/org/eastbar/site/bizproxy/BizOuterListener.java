package org.eastbar.site.bizproxy;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.eastbar.site.biz.BizHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by AndySJTU on 2015/6/15.
 */
public class BizOuterListener {
    public final static Logger logger = LoggerFactory.getLogger(BizOuterListener.class);

    private NioEventLoopGroup bossGroup = new NioEventLoopGroup();
    private NioEventLoopGroup workerGroup = new NioEventLoopGroup();
    private int listenPort = 3004;

    private final BizProxyServer server;

    public BizOuterListener(BizProxyServer server) {
        this.server = server;
    }

    public void doListen() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("logHandler", new LoggingHandler("CONN TO BIZ-SERVER", LogLevel.DEBUG));
                        pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65536, 0, 4, 0, 4));
                        pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                        pipeline.addLast("bizHandler", new BizOuterHandler(server));
                    }
                });
        ChannelFuture future = bootstrap.bind(listenPort);
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    logger.info("LISTEN FOR BIZ-SERVER SUCCESS....");

                } else {
                    logger.error("LISTEN FOR BIZ-SERVER FAIL....");
                    logger.error("CAUSE IS ", future.cause());
                    bossGroup.shutdownGracefully();
                    workerGroup.shutdownGracefully();
                }
            }
        });
    }
}
