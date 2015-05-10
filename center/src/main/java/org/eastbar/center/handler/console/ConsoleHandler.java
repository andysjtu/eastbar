package org.eastbar.center.handler.console;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.eastbar.center.CenterServer;

/**
 * Created by andysjtu on 2015/5/10.
 */
public class ConsoleHandler extends SimpleChannelInboundHandler<String> {
    private final CenterServer centerServer;

    public ConsoleHandler(CenterServer centerServer) {
        this.centerServer = centerServer;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //TODO
    }
}
