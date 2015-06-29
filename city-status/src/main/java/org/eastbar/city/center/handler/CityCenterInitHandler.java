package org.eastbar.city.center.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import org.eastbar.city.CityCenter;
import org.eastbar.codec.CenterInitReq;
import org.eastbar.codec.SiteReport;
import org.eastbar.codec.SocketMsg;
import org.eastbar.codec.TermReport;

import java.util.List;
import java.util.Map;

/**
 * Created by andyliang on 6/29/15.
 */
public class CityCenterInitHandler extends ChannelInboundHandlerAdapter {
    private final CityCenter center;

    public CityCenterInitHandler(CityCenter center) {
        this.center = center;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        Map<SiteReport, List<TermReport>> reportListMap= center.getSiteTermReports();
        CenterInitReq initReq = new CenterInitReq(reportListMap);
        ctx.channel().writeAndFlush(initReq);
    }

}
