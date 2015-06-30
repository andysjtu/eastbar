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
//        return LoginUtil.mockLogin("林超", "410107197902025432", "192.168.9.155", "201506291023");
        return LoginUtil.mockLogin("林超", "410107197902025432", "192.168.9.155", "201506291023");
    }



}
