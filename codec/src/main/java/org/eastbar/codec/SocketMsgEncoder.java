package org.eastbar.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by andysjtu on 2015/5/9.
 */
public class SocketMsgEncoder extends MessageToByteEncoder<SocketMsg> {
    @Override
    protected void encode(ChannelHandlerContext ctx, SocketMsg msg, ByteBuf out) throws Exception {
        msg.encodeAsByteBuf(out);
    }
}
