package org.eastbar.bizclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by AndySJTU on 2015/6/17.
 */
public class BizLoginClient extends AbstractClient{


    public static void main(String[] args) {
        BizLoginClient bizClient = new BizLoginClient();
        bizClient.connect();
    }

    @Override
    protected void registerHandler(ChannelPipeline pipeline) {
        pipeline.addLast(new MockBizHandler());
    }
}
