package org.eastbar.center.handler.site;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.eastbar.center.Center;
import org.eastbar.codec.SiteInitReq;
import org.eastbar.codec.SiteMsgType;
import org.eastbar.codec.SocketMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by AndySJTU on 2015/5/20.
 */
public class SiteInitReqHandler extends SimpleChannelInboundHandler<SocketMsg> {
    public final static Logger logger = LoggerFactory.getLogger(SiteInitReqHandler.class);

    private final Center center;

    public SiteInitReqHandler(Center center) {
        this.center = center;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        short value = msg.getMessageType();
        if (value == SiteMsgType.INIT_CONN.shortValue()) {
            SiteInitReq initReq = new SiteInitReq(msg);
            center.initSite(initReq, ctx.channel());
            ctx.channel().pipeline().remove(this);
        } else {
            logger.warn("消息格式有错误，请检查，关闭site通道");
            ctx.channel().close();
        }
    }
}
