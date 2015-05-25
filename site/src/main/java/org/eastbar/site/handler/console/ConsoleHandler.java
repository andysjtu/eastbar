package org.eastbar.site.handler.console;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import org.eastbar.codec.*;
import org.eastbar.site.Terminal;
import org.eastbar.site.handler.client.ClientProxyChannelHandler;
import org.eastbar.site.Site;
import org.eastbar.site.SiteServer;
import org.eastbar.site.policy.PolicyVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Created by andysjtu on 2015/5/10.
 */
public class ConsoleHandler extends SimpleChannelInboundHandler<SocketMsg> {

    public final static Logger logger = LoggerFactory.getLogger(ConsoleHandler.class);


    private final ClientProxyChannelHandler proxyChannelHandler;


    private final SiteServer siteServer;

    public ConsoleHandler(ClientProxyChannelHandler proxyChannelHandler, SiteServer siteServer) {
        this.proxyChannelHandler = proxyChannelHandler;
        this.siteServer = siteServer;
    }


    private Channel findTerminalChannel(String host) {
        Site site = siteServer.getSite();
        Channel channel = site.getTerminalChannel(host);
        return channel;
    }


    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, final SocketMsg msg) throws Exception {
        MsgAttr attr = new MsgAttr(msg.getMsgAttr());

        if (msg.getMessageType() == SiteMsgType.LIST.shortValue()) {
            Set<String> hosts = siteServer.getSite().getHosts();
            SocketMsg newMsg = new SocketMsg(msg);
            newMsg.setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
            ByteBuf buf = Unpooled.buffer();
            if (hosts.size() == 0) {
                buf.writeBytes("没有终端连上\n".getBytes(Charsets.UTF_8));
            } else {
                for (String host : hosts) {
                    buf.writeBytes(host.getBytes(Charsets.UTF_8));
                    buf.writeBytes("\n".getBytes());
                }
            }
            newMsg.data(buf);
            ctx.writeAndFlush(newMsg);
        } else if (msg.getMessageType() == SiteMsgType.STATUS.shortValue()) {
            Set<String> hosts = siteServer.getSite().getHosts();
            SocketMsg newMsg = new SocketMsg(msg);
            newMsg.setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
            ByteBuf buf = Unpooled.buffer();
            PolicyVersion version = siteServer.getSite().getVersion();
            buf.writeBytes(JsonUtil.toJson(version).getBytes(Charsets.UTF_8));
            newMsg.data(buf);
            ctx.writeAndFlush(newMsg);
        } else {
            String ip = msg.getHost().toRegularIpFormat();
            Terminal terminal = siteServer.getSite().getTerminalManager().getTerminalWithoutCreate(ip);
            if (terminal != null) {
                msg.changeSiteToClientAttr();
                terminal.redirect(msg.retain(), ctx.channel());
            } else {
                GenResp resp = new GenResp.S2CenterGenResp(msg.getMessageId(), msg.getMessageType(), GenResp.Status.Failure);
                ctx.channel().writeAndFlush(resp);
            }

        }
    }


}
