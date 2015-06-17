package org.eastbar.codec;

import com.google.common.collect.Sets;
import io.netty.channel.*;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Created by AndySJTU on 2015/5/15.
 */
public class HeartBeatenHandler extends SimpleChannelInboundHandler<SocketMsg> {

    public final static Logger logger = LoggerFactory.getLogger(HeartBeatenHandler.class);

    private Set<Short> messageIds = Sets.newConcurrentHashSet();

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            BeatenEvent event = new BeatenEvent();
            final short id = event.getMessageId();
            messageIds.add(id);
            ChannelFuture future = ctx.writeAndFlush(event);
            future.addListener(
                    ChannelFutureListener.CLOSE_ON_FAILURE);
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (!future.isSuccess()) {
                        messageIds.remove(id);
                    }
                }
            });

        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        short value = msg.getMessageType();
        if (value == SiteMsgType.GEN_RESP.shortValue()) {
            GenResp resp = new GenResp(msg);
            short recMessageId = resp.getRecMessageId();
            short recMessageType = resp.getRecMessageType();
            logger.debug("通用答复 recMessageId is {},recMessageType is {}", recMessageId, recMessageType);
            if (messageIds.contains(recMessageId) && recMessageType == SiteMsgType.BEATEN.shortValue()) {
                logger.debug("receive beaten reply...");
                messageIds.remove(recMessageId);
                return;
            }
        } else if (value == SiteMsgType.BEATEN.shortValue()) {
            logger.debug("收到对方的心跳信息");
            GenResp resp = GenRespUtil.createCenter2SiteSuccessResp(msg.getMessageId(), msg.getMessageType());
            ctx.channel().writeAndFlush(resp);
            return;
        }
        ctx.fireChannelRead(ReferenceCountUtil.retain(msg));

    }
}
