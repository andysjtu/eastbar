package org.eastbar.site.handler.center;

import com.google.common.collect.Sets;
import io.netty.channel.*;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import org.eastbar.codec.GenResp;
import org.eastbar.codec.SiteBeatenEvent;
import org.eastbar.codec.SiteMsgType;
import org.eastbar.codec.SocketMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Created by AndySJTU on 2015/5/15.
 */
public class BeatenHandler extends SimpleChannelInboundHandler<SocketMsg> {

    public final static Logger logger = LoggerFactory.getLogger(BeatenHandler.class);

    private Set<Short> messageIds = Sets.newConcurrentHashSet();

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            SiteBeatenEvent event = new SiteBeatenEvent();
            final short id = event.getMessageId();
            messageIds.add(id);
            ChannelFuture future = ctx.writeAndFlush(event);
            future.addListener(
                    ChannelFutureListener.CLOSE_ON_FAILURE);
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if(!future.isSuccess()) {
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
        logger.info("sets is : "+messageIds);
        if (value == SiteMsgType.GEN_RESP.shortValue()) {
            GenResp resp = new GenResp(msg);
            short recMessageId = resp.getRecMessageId();
            short recMessageType = resp.getRecMessageType();
            logger.info("genrely recMessageId is {},recMessageType is {}", recMessageId, recMessageType);
            if (messageIds.contains(recMessageId) && recMessageType == SiteMsgType.BEATEN.shortValue()) {
                logger.info("receive beaten reply...");
                messageIds.remove(recMessageId);
                return;
            }
        }
        ctx.fireChannelRead(ReferenceCountUtil.retain(msg));
    }
}
