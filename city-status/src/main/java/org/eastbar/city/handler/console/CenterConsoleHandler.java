package org.eastbar.city.handler.console;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.eastbar.city.CenterServer;
import org.eastbar.city.VSite;
import org.eastbar.codec.GenResp;
import org.eastbar.codec.GenRespUtil;
import org.eastbar.codec.SiteMsgType;
import org.eastbar.codec.SocketMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by andysjtu on 2015/5/10.
 */
public class CenterConsoleHandler extends SimpleChannelInboundHandler<SocketMsg> {
    public final static Logger logger = LoggerFactory.getLogger(CenterConsoleHandler.class);

    private final CenterServer centerServer;

    public CenterConsoleHandler(CenterServer centerServer) {
        this.centerServer = centerServer;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        short type = msg.getMessageType();
        if (type == SiteMsgType.STATUS.shortValue()) {
            ByteBuf buf = Unpooled.buffer();
            buf.writeBytes(centerServer.getCenter().getReportString().getBytes(Charsets.UTF_8));
            SocketMsg nm = new SocketMsg(msg);
            nm.data(buf);
            ctx.channel().writeAndFlush(nm);
            return;
        } else {
            ByteBuf buf = msg.data().content();
            String siteCode = buf.toString(Charsets.UTF_8);
            logger.info("receive SiteCode is {}", siteCode);
            SocketMsg newMsg = new SocketMsg(msg);
            newMsg.setData(null);
            VSite vSite = centerServer.getCenter().getVSite(siteCode);
            if (vSite != null && vSite.isConnected()) {
                vSite.redirect(newMsg, ctx.channel());
                return;
            }

        }
        sendErrorResp(ctx, msg);
    }

    private void sendErrorResp(ChannelHandlerContext ctx, SocketMsg msg) {
        GenResp resp = GenRespUtil.createS2ClientFailResp(msg.getMessageId(), msg.getMessageType());
        ctx.channel().writeAndFlush(resp);
    }
}
