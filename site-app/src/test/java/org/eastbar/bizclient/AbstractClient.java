package org.eastbar.bizclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by AndySJTU on 2015/6/17.
 */
public abstract class AbstractClient {
    private String remoteAddr = "192.168.9.144";
    private int remotePort=3004;
    private NioEventLoopGroup workerGroup = new NioEventLoopGroup();
    private Bootstrap bootstrap;
    private void config(){
        bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .remoteAddress(remoteAddr, remotePort)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("logHandler", new LoggingHandler("模拟测试", LogLevel.INFO));
                        pipeline.addLast("frameLengthHandler", new LengthFieldPrepender(4));
//                        pipeline.addLast("bizHandler", new MockBizHandler());
                        registerHandler(pipeline);
                    }
                });

    }

    protected abstract void registerHandler(ChannelPipeline pipeline);

    public void connect(){
        if(bootstrap==null){
            config();
        }
        bootstrap.connect().addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    future.channel().closeFuture().addListener(new ChannelFutureListener() {
                        @Override
                        public void operationComplete(ChannelFuture future) throws Exception {
                            shutdown();
                        }
                    });
                }
            }
        });
    }

    private void shutdown() {
        workerGroup.shutdownGracefully();
    }
}
