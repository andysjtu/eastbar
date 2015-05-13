package org.eastbar.site.handler.center;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.eastbar.codec.SocketMsg;

/**
 * Created by andysjtu on 2015/5/12.
 */
public class CenterCmdHandler extends SimpleChannelInboundHandler<SocketMsg> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        
    }
}
