package org.eastbar.center;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.eastbar.center.rmi.CenterHub;
import org.eastbar.codec.SocketMsg;

/**
 * Created by AndySJTU on 2015/5/27.
 */
public class CenterInitHandler extends SimpleChannelInboundHandler<SocketMsg> {
    private final CenterHub centerHub;

    public CenterInitHandler(CenterHub centerHub) {
        this.centerHub = centerHub;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
         //TODO
    }
}
