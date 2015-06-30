package org.eastbar.city.site;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.eastbar.city.CityCenter;
import org.eastbar.city.site.handler.SiteHeatBeatenHandler;
import org.eastbar.city.site.handler.SiteInitReqHandler;
import org.eastbar.city.site.handler.SiteStatusUpdateHandler;
import org.eastbar.codec.*;
import org.eastbar.net.Listener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by AndySJTU on 2015/4/15.
 */
@Component
public class SiteListener implements Listener {
    public final static Logger logger= LoggerFactory.getLogger(SiteListener.class);

    @Value("${managerCenterServerPort}")
    private int listenPort=0;
    private NioEventLoopGroup bossGroup = new NioEventLoopGroup(2);
    private NioEventLoopGroup workerGroup = new NioEventLoopGroup(1000);

    private volatile Channel serverChannel;

    @Autowired
    private CityCenter center;

    @Override
    public void listen() {
        if(listenPort<1){
            throw new IllegalArgumentException("listenPort must be set ");
        }
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .option(ChannelOption.SO_BACKLOG, 1000)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {

                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("log",new LoggingHandler("连接场所端通道",LogLevel.INFO));
                        pipeline.addLast("idle", new IdleStateHandler(0,0,60, TimeUnit.SECONDS));
                        pipeline.addLast("eastFrameDecoder",new EastbarFrameDecoder());
                        pipeline.addLast("sockMsgDecoder",new SocketMsgDecoder());
                        pipeline.addLast("socketMsgEncoder",new SocketMsgEncoder());
                        pipeline.addLast("siteInitHandler",new SiteInitReqHandler(center));
                        pipeline.addLast("siteHeatbeaten",new HeartBeatenHandler());
                        pipeline.addLast("siteStatusUpdater",new SiteStatusUpdateHandler(center));
                        pipeline.addLast(ProxyChannelHandler.HANDLER_NAME, new ProxyChannelHandler());
                    }
                });
        ChannelFuture future = bootstrap.bind(listenPort);
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    serverChannel = future.channel();
                    logger.info("开启侦听场所端服务成功");
                } else {
                    logger.error("开启侦听客户端服务失败:",future.cause());
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
                    if(future.isSuccess()){
                        logger.info("关闭侦听场所端服务成功");
                    }
                }
            });
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
