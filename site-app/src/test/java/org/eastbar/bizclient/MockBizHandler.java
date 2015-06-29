package org.eastbar.bizclient;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

/**
 * Created by AndySJTU on 2015/6/17.
 */
public class MockBizHandler extends ChannelInboundHandlerAdapter {
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
        buf.writeBytes(new BASE64Encoder().encode("林超".getBytes("GBK")).getBytes());
        buf.writeByte('\0');
        buf.writeBytes("1".getBytes());
        buf.writeByte('\0');
        buf.writeBytes(new BASE64Encoder().encode("410107197902026432".getBytes("GBK")).getBytes());
        buf.writeByte('\0');
        buf.writeBytes(new BASE64Encoder().encode("徐汇公安局".getBytes("GBK")).getBytes());
        buf.writeByte('\0');
        buf.writeBytes("192.168.9.100".getBytes());
        buf.writeByte('\0');
        buf.writeBytes("201506291340".getBytes());
        buf.writeByte('\0');

        return buf;
    }
    /**
     * C1101 c1101 = new C1101();
     c1101.setVersion(login[0]);
     c1101.setSessionId(login[1]);
     c1101.setAccount(login[2]);
     c1101.setName(new String(
     new BASE64Decoder().decodeBuffer(login[3]), "GBK"));
     c1101.setIdType(login[4]);
     c1101.setId(new String(new BASE64Decoder()
     .decodeBuffer(login[5]), "GBK"));
     c1101.setAuthOrg(new String(new BASE64Decoder()
     .decodeBuffer(login[6]), "GBK"));
     c1101.setIp(login[7]);
     c1101.setLoginTime(login[8]);
     */
}
