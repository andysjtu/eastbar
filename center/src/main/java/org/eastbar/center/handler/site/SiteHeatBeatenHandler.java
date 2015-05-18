package org.eastbar.center.handler.site;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
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
}
