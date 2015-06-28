package org.eastbar.city.site.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.ReadTimeoutException;
import io.netty.util.ReferenceCountUtil;
import org.eastbar.codec.GenRespUtil;
import org.eastbar.codec.SiteMsgType;
import org.eastbar.codec.SocketMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by AndySJTU on 2015/5/15.
 */
public class SiteHeatBeatenHandler extends SimpleChannelInboundHandler<SocketMsg> {
    public final static Logger logger = LoggerFactory.getLogger(SiteHeatBeatenHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        short value = msg.getMessageType();
        if (value == SiteMsgType.BEATEN.shortValue()) {
            logger.info("收到{}的心跳信息", ctx.channel().remoteAddress());
            ctx.channel().writeAndFlush(GenRespUtil.createCenter2SiteSuccessResp(
                    msg.getMessageId(), msg.getMessageType()));
        } else {
            ctx.fireChannelRead(ReferenceCountUtil.retain(msg));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (cause instanceof ReadTimeoutException) {
            logger.warn("来自{}的网络长时间没有数据到达,可能出现问题，将关闭", ctx.channel().remoteAddress());
        } else {
            logger.warn("来自{}的网络出现异常，异常是:{}", ctx.channel().remoteAddress(), cause.getMessage());
        }
        ctx.close();
    }
}
