package org.eastbar.city.site.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.eastbar.city.CityCenter;
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

    private final CityCenter center;

    public SiteInitReqHandler(CityCenter center) {
        this.center = center;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        short value = msg.getMessageType();
        if (value == SiteMsgType.SITE_INIT_CONN.shortValue()) {
            SiteInitReq initReq = new SiteInitReq(msg);
            center.initSite(initReq, ctx.channel());
        } else {

            ctx.fireChannelRead(ReferenceCountUtil.retain(msg));
        }
    }
}
