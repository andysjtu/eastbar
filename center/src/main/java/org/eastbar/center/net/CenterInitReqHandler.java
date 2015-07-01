package org.eastbar.center.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.eastbar.codec.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by AndySJTU on 2015/6/1.
 */
public class CenterInitReqHandler extends SimpleChannelInboundHandler<SocketMsg> {
    public final static Logger logger= LoggerFactory.getLogger(CenterInitReqHandler.class);

    private final CenterHub hub;

    public CenterInitReqHandler(CenterHub hub) {
        this.hub = hub;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        short type = msg.getMessageType();
        logger.info("收到type 类型是 : "+Integer.toHexString(type));
        if (type == SiteMsgType.CENTER_INIT_CONN.shortValue()) {
            CenterInitReq initReq = new CenterInitReq(msg);
            hub.registerCenter(ctx.channel(), initReq);
        } else if (type == SiteMsgType.SITE_INIT_CONN.shortValue()) {
            SiteInitReq req = new SiteInitReq(msg);
            hub.updateSiteStatus(req, ctx.channel());
        }
        else if (type == SiteMsgType.SITE_DISC_CONN.shortValue()) {
            SiteDiscReq req = new SiteDiscReq(msg);
            hub.unregisterSite(req, ctx.channel());
        }
        else if (type == SiteMsgType.TERM_STATUS.shortValue()) {

            TermReportMsg termReportMsg = new TermReportMsg(msg);
            hub.updateTermStatus(termReportMsg.getReport(), ctx.channel());
        } else {
            ctx.fireChannelRead(ReferenceCountUtil.retain(msg));
        }
    }
}
