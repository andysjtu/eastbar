package org.eastbar.city.center;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.eastbar.city.CityCenter;
import org.eastbar.city.center.handler.Center2ClientCmdHandler;
import org.eastbar.city.center.handler.CityCenterInitHandler;
import org.eastbar.codec.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by AndySJTU on 2015/5/27.
 */
@Component
public class HubConnector {
    public static final Logger logger = LoggerFactory.getLogger(HubConnector.class);

    @Value("${rmi.center.ip}")
    private String remoteAddr;
    @Value("${rmi.center.port}")
    private int remotePort = 6888;

    private int localPort = 2999;


    private NioEventLoopGroup workerGroup = new NioEventLoopGroup();

    private volatile Channel remoteChannel;
    private Bootstrap bootstrap;

    @Autowired
    private CityCenter center;


    @PostConstruct
    public void configBootstrap() {
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
                        pipeline.addLast("logHandler", new LoggingHandler("连接中心Hub端", LogLevel.DEBUG));
                        pipeline.addLast("idleHandler", new IdleStateHandler(0, 0, 60, TimeUnit.SECONDS));
                        pipeline.addLast("soketMsgEncoder", new SocketMsgEncoder());
                        pipeline.addLast("eastframeDecoder", new EastbarFrameDecoder());
                        pipeline.addLast("socketMsgDecoder", new SocketMsgDecoder());
                        pipeline.addLast("heartBeaten", new HeartBeatenHandler());
                        pipeline.addLast("initReqHandler",new CityCenterInitHandler(center));
                        pipeline.addLast("cmdRespHandler",new Center2ClientCmdHandler(center));
//                        pipeline.addLast(CenterCmdRespHandler.DEFAULT_HANDLER_NAME, new CenterCmdRespHandler());


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
                    logger.info("成功连接上CenterHub端");
                    remoteChannel = future.channel();
                    remoteChannel.closeFuture().addListener(new ChannelFutureListener() {
                        @Override
                        public void operationComplete(ChannelFuture future) throws Exception {
                            if (future.isSuccess()) {
                                remoteChannel = null;
                                scheduleNextConnect();
                            }
                        }
                    });
                } else {
                    scheduleNextConnect();
                }
            }
        });
    }



    private void scheduleNextConnect() {
        workerGroup.schedule(new Runnable() {
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
            workerGroup.shutdownGracefully();
        }
    }


    public int getLocalPort() {
        return localPort;
    }

    public void setLocalPort(int localPort) {
        this.localPort = localPort;
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
