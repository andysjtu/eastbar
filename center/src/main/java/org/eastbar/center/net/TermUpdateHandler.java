package org.eastbar.center.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.eastbar.codec.SiteMsgType;
import org.eastbar.codec.SocketMsg;
import org.eastbar.codec.TermReport;
import org.eastbar.codec.TermReportMsg;

import java.util.List;

/**
 * Created by AndySJTU on 2015/6/2.
 */
public class TermUpdateHandler extends SimpleChannelInboundHandler<SocketMsg> {
    private final CenterHub centerHub;

    public TermUpdateHandler(CenterHub centerHub) {
        this.centerHub = centerHub;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        short type = msg.getMessageType();
        if (type == SiteMsgType.TERM_STATUS.shortValue()) {
            TermReportMsg termReportMsg = new TermReportMsg(msg);
            List<TermReport> termReportList = termReportMsg.getReport();
            centerHub.updateTermStatus(termReportList, ctx.channel());
        } else {
            ctx.fireChannelRead(ReferenceCountUtil.retain(msg));
        }
    }
}
