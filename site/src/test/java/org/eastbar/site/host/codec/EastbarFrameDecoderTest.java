package org.eastbar.site.host.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by andysjtu on 2015/5/7.
 */
public class EastbarFrameDecoderTest {
    @Test
    public void testEastbarFrameDecode(){
        ByteBuf buf = Unpooled.buffer();

        buf.writeByte(new MsgAttrBuilder().type(MsgAttr.Type.SITE_TO_CLIENT).buildMsgAttr().byteValue());
        buf.writeByte(ProtocolVersion.Version.byteValue());
        buf.writeShort(0x1);
        buf.writeShort(0x1);
        buf.writeBytes(IpV4.localIpBytes());
        buf.writeInt(0);

        buf.writeByte(new MsgAttrBuilder().type(MsgAttr.Type.SITE_TO_CLIENT).buildMsgAttr().byteValue());
        buf.writeByte(ProtocolVersion.Version.byteValue());
        buf.writeShort(0x1);
        buf.writeShort(0x1);
        buf.writeBytes(IpV4.localIpBytes());
        buf.writeInt(0);

        ByteBuf input = buf.duplicate().retain();

        EmbeddedChannel channel = new EmbeddedChannel(new EastbarFrameDecoder());

        Assert.assertTrue(channel.writeInbound(input));
        Assert.assertTrue(channel.finish());

        Assert.assertEquals(buf.readBytes(14), channel.readInbound());
        Assert.assertEquals(buf.readBytes(14), channel.readInbound());
        Assert.assertNull(channel.readInbound());


    }
}
