package org.eastbar.site;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by andysjtu on 2015/5/9.
 */
@Component
public class CenterConnector implements Connector {
    public static final  Logger logger = LoggerFactory.getLogger(CenterConnector.class);
    @Value("${managerCenterIp}")
    private String remoteAddr;
    @Value("${managerCenterPort}")
    private int remotePort;
    private int localPort=19999;

    private NioEventLoopGroup workerGroup = new NioEventLoopGroup();
    private ScheduledExecutorService service = Executors.newScheduledThreadPool(1);

    private volatile Channel remoteChannel;
    private Bootstrap bootstrap;


    @PostConstruct
    private void configBootstrap() {
        bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .localAddress(localPort)
                .remoteAddress(remoteAddr, remotePort)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
//                        pipeline.addLast(new LoggingHandler(LogLevel.ERROR));
                    }
                });
    }


    public Channel channel(){
        return this.remoteChannel;
    }

    public boolean isConnected(){
        if(remoteChannel!=null&&remoteChannel.isActive()){
            return true;
        }
        return false;
    }


    @Override
    public void connect() {
        ChannelFuture future = bootstrap.connect();
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    logger.info("成功连接上Center管理端");
                    remoteChannel = future.channel();
                    remoteChannel.closeFuture().addListener(new ChannelFutureListener() {
                        @Override
                        public void operationComplete(ChannelFuture future) throws Exception {
                            remoteChannel=null;
                            scheduleNextConnect();
                        }
                    });
                } else {
                    scheduleNextConnect();
                }
            }
        });
    }

    private void scheduleNextConnect() {
        service.schedule(new Runnable() {
            @Override
            public void run() {
                connect();
            }
        }, 2, TimeUnit.SECONDS);
    }

    @Override
    public void disconnect() {
        if(remoteChannel.isActive()){
            remoteChannel.close();
            workerGroup.shutdownGracefully();
        }
        else{
            service.shutdownNow();
            workerGroup.shutdownGracefully();
        }
    }
}
