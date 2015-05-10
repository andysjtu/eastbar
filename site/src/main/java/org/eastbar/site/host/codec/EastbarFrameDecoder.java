package org.eastbar.site.host.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * divide the stream into frame
 * Created by andysjtu on 2015/5/7.
 */
public class EastbarFrameDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        Object buf = decode(channelHandlerContext,byteBuf);
        if(buf!=null){
            list.add(buf);
        }
    }

    protected Object decode(ChannelHandlerContext ctx,ByteBuf byteBuf){
        if (byteBuf.readableBytes() < 14) {
            return null;
        }
        int bodyLength = getBodyLength(byteBuf);
        int frameLenth =14+bodyLength;
        if(byteBuf.readableBytes()<frameLenth){
            return null;
        }
        ByteBuf frame = ctx.alloc().buffer(frameLenth);
        frame.writeBytes(byteBuf,byteBuf.readerIndex(),frameLenth);
        byteBuf.readerIndex(byteBuf.readerIndex()+frameLenth);
        return frame;
    }
    private int getBodyLength(ByteBuf buf) {
        int readIndex = buf.readerIndex();
        int length = buf.getInt(readIndex + 10);
        return length;
    }
}
