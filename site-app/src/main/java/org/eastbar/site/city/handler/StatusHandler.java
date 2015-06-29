package org.eastbar.site.city.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.eastbar.codec.*;
import org.eastbar.site.Site;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        SiteReport siteReport = site.getSiteReport();
        List<TermReport> termReports = site.getTermReportList();
        SiteInitReq msg = new SiteInitReq(siteReport,termReports);
        ctx.channel().writeAndFlush(msg);

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        ctx.fireChannelRead(ReferenceCountUtil.retain(msg));
    }
}
