package org.eastbar.site;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.eastbar.codec.*;
import org.eastbar.site.handler.center.Center2ClientCmdHandler;
import org.eastbar.site.handler.center.PolicyUpdateHandler;
import org.eastbar.site.handler.center.StatusHandler;
import org.eastbar.site.policy.PolicySaver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by AndySJTU on 2015/6/10.
 */
@Component
public class CaptureConnector implements Connector {
    public static final Logger logger = LoggerFactory.getLogger(CenterConnector.class);
    @Value("${capture.server.ip}")
    private String remoteAddr;
    @Value("${capture.server.port}")
    private int remotePort;
    private int localPort = 19997;

    @Autowired
    private Site site;


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
                .option(ChannelOption.RCVBUF_ALLOCATOR,new FixedRecvByteBufAllocator(1024*20))
                .localAddress(localPort)
                .remoteAddress(remoteAddr, remotePort)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("logHandler", new LoggingHandler("连接截图服务器", LogLevel.DEBUG));
                        pipeline.addLast("idleHandler", new IdleStateHandler(0, 0, 60, TimeUnit.SECONDS));
                        pipeline.addLast("soketMsgEncoder", new SocketMsgEncoder());
                        pipeline.addLast("eastframeDecoder", new EastbarFrameDecoder());
                        pipeline.addLast("socketMsgDecoder", new SocketMsgDecoder());
                        pipeline.addLast("bentenHandler", new HeartBeatenHandler());
                        pipeline.addLast("centerCmdHandler", new Center2ClientCmdHandler(site));

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


    @Override
    public void connect() {
        ChannelFuture future = bootstrap.connect();
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    logger.info("成功连接上截图服务器");
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
        }, 10, TimeUnit.SECONDS);
    }

    @Override
    public void disconnect() {
        if (remoteChannel.isActive()) {
            remoteChannel.close();
            workerGroup.shutdownGracefully();
        } else {
            service.shutdownNow();
            workerGroup.shutdownGracefully();
        }
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
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
