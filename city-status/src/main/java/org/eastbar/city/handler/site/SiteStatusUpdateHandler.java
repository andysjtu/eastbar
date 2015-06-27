package org.eastbar.city.handler.site;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.eastbar.city.Center;
import org.eastbar.codec.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by AndySJTU on 2015/5/30.
 */
public class SiteStatusUpdateHandler extends SimpleChannelInboundHandler<SocketMsg> {

    private final Center center;
    public final static Logger logger = LoggerFactory.getLogger(SiteStatusUpdateHandler.class);

    public SiteStatusUpdateHandler(Center center) {
        this.center = center;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        short type = msg.getMessageType();
        if (type == SiteMsgType.TERM_STATUS.shortValue()) {
            TermReportMsg reportMsg = new TermReportMsg(msg);
            center.updateTermStatus(reportMsg.getReport());
        } else if (type == SiteMsgType.POLICY_STATUS.shortValue()) {
            SiteReportMsg siteReportMsg = new SiteReportMsg(msg);
            center.updateSitePolicyStatus(siteReportMsg.getReport());
        } else {
            ctx.fireChannelRead(ReferenceCountUtil.retain(msg));
        }
    }
}
