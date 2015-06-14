package org.eastbar.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by andysjtu on 2015/5/9.
 */
public class SocketMsgDecoder extends MessageToMessageDecoder<ByteBuf> {
    public final static Logger logger= LoggerFactory.getLogger(SocketMsgDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        SocketMsg socketMsg = new SocketMsg();
        socketMsg.readByteBuf(msg);
        out.add(socketMsg);

    }

}
