package org.eastbar.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by andysjtu on 2015/5/9.
 */
public class SocketMsgDecoderTest {
    @Test
     public void testDecoder(){
         ByteBuf buf = Unpooled.buffer();
         buf.writeByte(new MsgAttrBuilder().type(MsgAttr.Type.SITE_TO_CLIENT).buildMsgAttr().byteValue());
         buf.writeByte(ProtocolVersion.Version.byteValue());
         buf.writeShort(0x1);
         buf.writeShort(0x1);
         buf.writeBytes(IpV4.localIpBytes());
         buf.writeInt(4);
         buf.writeInt(4);

//         SocketMsg socketMsg = new SocketMsg();
//         socketMsg.readByteBuf(buf);

         EmbeddedChannel channel = new EmbeddedChannel(new EastbarFrameDecoder(),new SocketMsgDecoder());

         assertTrue(channel.writeInbound(buf));
         assertTrue(channel.finish());

         SocketMsg msg = (SocketMsg)channel.readInbound();
        assertNull(channel.readInbound());

        ByteBufHolder holder = msg.data();

        holder.release();



     }
}