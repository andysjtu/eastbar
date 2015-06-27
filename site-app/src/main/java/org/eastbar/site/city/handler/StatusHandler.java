package org.eastbar.site.city.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.eastbar.codec.SocketMsg;
import org.eastbar.site.Site;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by AndySJTU on 2015/5/20.
 */
public class StatusHandler extends SimpleChannelInboundHandler<SocketMsg> {
    public final static Logger logger = LoggerFactory.getLogger(StatusHandler.class);

    private final Site site;

    public StatusHandler(Site site) {
        this.site = site;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        short value = msg.getMessageType();
        logger.warn("不知道是否生效。。。。");
        ctx.fireChannelRead(ReferenceCountUtil.retain(msg));


    }
}
