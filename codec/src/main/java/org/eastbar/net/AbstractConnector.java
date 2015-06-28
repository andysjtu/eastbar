package org.eastbar.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by AndySJTU on 2015/6/17.
 */
public abstract class AbstractConnector {
    public static final Logger logger = LoggerFactory.getLogger(AbstractConnector.class);

    protected String remoteAddress;
    protected int remotePort;
    protected int localPort;

    protected NioEventLoopGroup workerGroup = new NioEventLoopGroup();

    private volatile Channel remoteChannel;
    private Bootstrap bootstrap;

    private volatile ChannelFuture future;

    public AbstractConnector() {
    }

    public AbstractConnector(String remoteAddress, int remotePort, int localPort) {
        this.remoteAddress = remoteAddress;
        this.remotePort = remotePort;
        this.localPort = localPort;
    }


    private void configBootstrap() {
        checkParameter();

        bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class);
        configOptions(bootstrap).
                localAddress(localPort)
                .remoteAddress(remoteAddress, remotePort)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        registerHandler(pipeline);
                    }
                });
    }

    protected Bootstrap configOptions(Bootstrap bootstrap) {
        return bootstrap.option(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
                .option(ChannelOption.TCP_NODELAY, Boolean.TRUE)
                .option(ChannelOption.SO_REUSEADDR, Boolean.TRUE);
    }

    private void checkParameter() {
        if (StringUtils.trimToNull(remoteAddress) == null) {
            throw new IllegalArgumentException("remoteAddress cannot be null");
        }
        if (remotePort == 0) throw new IllegalArgumentException("remotePort cannot be null");
    }


    protected abstract void registerHandler(ChannelPipeline pipeline);


    public Channel channel() {
        return this.remoteChannel;
    }

    public boolean isConnected() {
        if (remoteChannel != null && remoteChannel.isActive()) {
            return true;
        }
        return false;
    }


    public ChannelFuture connect() {
        if (bootstrap == null) {
            synchronized (this) {
                if (bootstrap == null) {
                    configBootstrap();
                }
            }
        }

        future = bootstrap.connect();
        future.addListener(new ChannelFutureListener() {
                               @Override
                               public void operationComplete(ChannelFuture future) throws Exception {
                                   if (future.isSuccess()) {
                                       logger.info("连接远端服务器成功: {}/{}", remoteAddress, remotePort);
                                       remoteChannel = future.channel();
                                       doSuccessConnect(future);
                                       remoteChannel.closeFuture().addListener(new ChannelFutureListener() {
                                           @Override
                                           public void operationComplete(ChannelFuture future) throws Exception {
                                               if (future.isSuccess()) {
                                                   remoteChannel = null;
                                                   doFailConnect(future);
                                                   scheduleNextConnect();

                                               }
                                           }
                                       });
                                   } else {
                                       doFailConnect(future);
                                       scheduleNextConnect();
                                   }
                               }
                           }

        );
        return future;
    }

    protected void doSuccessConnect(ChannelFuture future) {
        //do nothing
    }


    protected void doFailConnect(ChannelFuture future) {
       logger.debug("connect fail");
    }


    protected void scheduleNextConnect() {
        workerGroup.schedule(new Runnable() {
            @Override
            public void run() {
                connect();
            }
        }, 60, TimeUnit.SECONDS);
    }

    public void disconnect() {
        if (future != null) {
            if (!future.isDone()) future.cancel(true);
            else {
                if (remoteChannel.isActive()) {
                    remoteChannel.close();
                }
            }
        }
        workerGroup.shutdownGracefully();

    }


    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public int getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(int remotePort) {
        this.remotePort = remotePort;
    }

    public void setLocalPort(int localPort) {
        this.localPort = localPort;
    }
}
