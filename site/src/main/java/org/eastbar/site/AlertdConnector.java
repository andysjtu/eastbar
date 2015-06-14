package org.eastbar.site;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.eastbar.codec.EastbarFrameDecoder;
import org.eastbar.codec.SocketMsgDecoder;
import org.eastbar.codec.SocketMsgEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by AndySJTU on 2015/6/11.
 */
public class AlertdConnector {
    public static final Logger logger = LoggerFactory.getLogger(AlertdConnector.class);
    @Value("${alert.server.ip}")
    private String remoteAddr;
    @Value("${alert.server.port}")
    private int remotePort;
    private int localPort = 19979;





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
                        pipeline.addLast("logHandler", new LoggingHandler("连接告警汇报端", LogLevel.DEBUG));
                        pipeline.addLast("soketMsgEncoder", new SocketMsgEncoder());
                        pipeline.addLast("eastframeDecoder", new EastbarFrameDecoder());
                        pipeline.addLast("socketMsgDecoder", new SocketMsgDecoder());
                    }
                });
    }


    public Channel channel() {
        return this.remoteChannel;
    }

    public boolean isConnected() {
        if (remoteChannel != null && remoteChannel.isActive()) {
            return true;
        }
        return false;
    }



    public void connect() {
        ChannelFuture future = bootstrap.connect();
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    logger.info("成功连接告警汇报端");
                    remoteChannel = future.channel();
                    remoteChannel.closeFuture().addListener(new ChannelFutureListener() {
                        @Override
                        public void operationComplete(ChannelFuture future) throws Exception {
                            remoteChannel = null;
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

    public void disconnect() {
        if (remoteChannel.isActive()) {
            remoteChannel.close();
            workerGroup.shutdownGracefully();
        } else {
            service.shutdownNow();
            workerGroup.shutdownGracefully();
        }
    }



    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public int getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(int remotePort) {
        this.remotePort = remotePort;
    }
}
