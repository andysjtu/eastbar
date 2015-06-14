package org.eastbar.hub;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.eastbar.codec.*;

import java.util.List;
import java.util.Map;

/**
 * Created by AndySJTU on 2015/6/1.
 */
public class CenterInitReqHandler extends SimpleChannelInboundHandler<SocketMsg> {
    private final CenterHub hub;

    public CenterInitReqHandler(CenterHub hub) {
        this.hub = hub;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        short type = msg.getMessageType();
        if (type == SiteMsgType.CENTER_INIT_CONN.shortValue()) {
            CenterInitReq initReq = new CenterInitReq(msg);
            hub.registerCenter(ctx.channel(), initReq);
        } else if (type == SiteMsgType.SITE_INIT_CONN.shortValue()) {
            SiteInitReq req = new SiteInitReq(msg);
            hub.updateSiteStatus(req, ctx.channel());
        } else if (type == SiteMsgType.TERM_STATUS.shortValue()) {
            TermReportMsg termReportMsg = new TermReportMsg(msg);
            hub.updateTermStatus(termReportMsg.getReport(), ctx.channel());
        } else {
            ctx.fireChannelRead(ReferenceCountUtil.retain(msg));
        }
    }
}
