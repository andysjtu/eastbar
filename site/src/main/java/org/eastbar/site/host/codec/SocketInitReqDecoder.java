package org.eastbar.site.host.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by AndySJTU on 2015/5/6.
 */
public class SocketInitReqDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int readableLength = byteBuf.readableBytes();
        if (readableLength < 14) {
            return;
        }
        int bodyLength = getBodyLength(byteBuf);
        if(readableLength<bodyLength){
            return;
        }

        MsgAttr attr = new MsgAttr(byteBuf.readByte());
        ProtocolVersion version = ProtocolVersion.valueOf(byteBuf.readByte());
        short type = byteBuf.readShort();
        short messageId= byteBuf.readShort();
        int ipvalue = byteBuf.readInt();
        byteBuf.readInt();



    }

    private int getBodyLength(ByteBuf buf) {
        int readIndex = buf.readerIndex();
        int length = buf.getInt(readIndex + 6);
        return length;
    }
}
