package org.eastbar.site;

import com.google.common.collect.Sets;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.eastbar.codec.*;
import org.eastbar.comm.Listener;
import org.eastbar.site.handler.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * Created by andysjtu on 2015/5/9.
 */
@Component
public class ClientListener implements Listener {
    public static final Logger logger = LoggerFactory.getLogger(ClientListener.class);

    private NioEventLoopGroup bossGroup = new NioEventLoopGroup(2);
    private NioEventLoopGroup workerGroup = new NioEventLoopGroup(500);
    private volatile Channel serverChannel;

    private String localIp;
    @Value("${ClientToManagerPort}")
    private int listenPort;

    @Autowired
    private Site site;

    public void setLocalIp(String localIp) {
        this.localIp = localIp;
    }

    public void setListenPort(int listenPort) {
        this.listenPort = listenPort;
    }

    private ChannelFutureListener closeListener = new ChannelFutureListener() {
        @Override
        public void operationComplete(ChannelFuture future) throws Exception {
            serverChannel = null;
        }
    };

    @Autowired
    private ClientProxyChannelHandler proxyChannelHandler;

    @Override
    public void listen() {
        proxyChannelHandler.registerkeys(Sets.newHashSet(ClientMsgType.GEN_RESP.shortValue(),
                ClientMsgType.QUERY_CLIENT_MODULE.shortValue(),
                ClientMsgType.QUERY_CLIENT_PROCESS.shortValue(),
                ClientMsgType.CAPTURE_CLIENT.shortValue()));
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1000)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .handler(new ServerChannelInitilizer())
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("logHandler", new LoggingHandler(LogLevel.INFO));
                        pipeline.addLast("eastFrameDecoder", new EastbarFrameDecoder());
                        pipeline.addLast("skmsgDecoder", new SocketMsgDecoder());
                        pipeline.addLast("skmsgEncoder", new SocketMsgEncoder());
                        pipeline.addLast("initHandler", new ClientInitReqHandler(site));
                        pipeline.addLast("beatenHandler", new ClientBeatenHandler());
                        pipeline.addLast("clientLogHandler", new ClientLogHandler());
                        pipeline.addLast("clientAlertHandler", new ClientAlertHandler());
                        pipeline.addLast("clientCmdRespHandler", proxyChannelHandler);
                        pipeline.addLast("generalHandler", new ClientHandler());

                    }
                });
        ChannelFuture future = bootstrap.bind(listenPort);
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    logger.info("开启侦听客户端服务成功");
                    serverChannel = future.channel();
                    serverChannel.closeFuture().addListener(closeListener);

                } else {
                    logger.error("开启侦听客户端服务失败");
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
                        serverChannel = null;
                        logger.info("关闭侦听客户端服务成功");
                    }
                }
            });
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static class ServerChannelInitilizer extends ChannelInitializer<ServerSocketChannel> {

        @Override
        protected void initChannel(ServerSocketChannel ch) throws Exception {
            ChannelPipeline pipeline = ch.pipeline();
            pipeline.addLast("logHandler", new LoggingHandler(LogLevel.DEBUG));
        }
    }

}
