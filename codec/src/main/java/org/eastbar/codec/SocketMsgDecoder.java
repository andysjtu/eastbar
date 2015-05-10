package org.eastbar.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * Created by andysjtu on 2015/5/9.
 */
public class SocketMsgDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        SocketMsg socketMsg = new SocketMsg();
        socketMsg.readByteBuf(msg);
        out.add(socketMsg);
    }
}
