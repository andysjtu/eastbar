package org.eastbar.site.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.ReadTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by AndySJTU on 2015/5/11.
 */
public class ExceptionHandler extends ChannelInboundHandlerAdapter {
    public final static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        logger.info("场所通道关闭：" + ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (cause instanceof ReadTimeoutException) {
            logger.warn("来自{}的连接出现长时间接收数据", ctx.channel().remoteAddress());
        } else {
            logger.warn("来自{}的连接出现异常:{}", ctx.channel().remoteAddress(), cause.getMessage());
        }
        ctx.channel().close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.warn("出现异常,出现没有处理报文 ： " + ctx.channel().remoteAddress());
    }
}
