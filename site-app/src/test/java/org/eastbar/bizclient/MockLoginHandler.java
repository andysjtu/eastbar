package org.eastbar.bizclient;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

/**
 * Created by andysjtu on 2015/6/30.
 */
public class MockLoginHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        ctx.channel().writeAndFlush(buildConnect()).addListener(ChannelFutureListener.CLOSE);

    }

    public ByteBuf buildConnect() throws UnsupportedEncodingException {
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(1101);
        buf.writeBytes("1.0".getBytes());
        buf.writeByte('\0');
        buf.writeBytes("netbar".getBytes());
        buf.writeByte('\0');
        buf.writeBytes("1234567".getBytes());
        buf.writeByte('\0');
        buf.writeBytes(new BASE64Encoder().encode("李朝阳".getBytes("GBK")).getBytes());
        buf.writeByte('\0');
        buf.writeBytes("1".getBytes());
        buf.writeByte('\0');
        buf.writeBytes(new BASE64Encoder().encode("620107197902026432".getBytes("GBK")).getBytes());
        buf.writeByte('\0');
        buf.writeBytes(new BASE64Encoder().encode("徐汇公安局".getBytes("GBK")).getBytes());
        buf.writeByte('\0');
        buf.writeBytes("192.168.9.100".getBytes());
        buf.writeByte('\0');
        buf.writeBytes("20150703113845".getBytes());
        buf.writeByte('\0');

        return buf;
    }
}
