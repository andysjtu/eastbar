package org.eastbar.site.console.handler;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import org.eastbar.codec.*;
import org.eastbar.codec.console.ConsoleCmdMsg;
import org.eastbar.site.Terminal;
import org.eastbar.site.Site;
import org.eastbar.site.SiteServer;
import org.eastbar.site.policy.entity.SitePolicyVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.Templates;
import java.util.List;
import java.util.Set;

/**
 * Created by andysjtu on 2015/5/10.
 */
public class ConsoleHandler extends SimpleChannelInboundHandler<SocketMsg> {

    public final static Logger logger = LoggerFactory.getLogger(ConsoleHandler.class);


    private final SiteServer siteServer;

    public ConsoleHandler(SiteServer siteServer) {
        this.siteServer = siteServer;
    }

    private Channel findTerminalChannel(String host) {
        Site site = siteServer.getSite();
        Channel channel = site.getTerminalChannel(host);
        return channel;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        siteServer.getSite().getReportManager().registerChannel(ctx.channel());
    }

    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, final SocketMsg msg) throws Exception {
//        MsgAttr attr = new MsgAttr(msg.getMsgAttr());
//
//        if (msg.getMessageType() == SiteMsgType.LIST.shortValue()) {
//            Set<String> hosts = siteServer.getSite().getHosts();
//            SocketMsg newMsg = new SocketMsg(msg);
//            newMsg.setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
//            ByteBuf buf = Unpooled.buffer();
//            if (hosts.size() == 0) {
//                buf.writeBytes("没有终端连上\n".getBytes(Charsets.UTF_8));
//            } else {
//                for (String host : hosts) {
//                    buf.writeBytes(host.getBytes(Charsets.UTF_8));
//                    buf.writeBytes("\n".getBytes());
//                }
//            }
//            newMsg.data(buf);
//            ctx.writeAndFlush(newMsg);
//        } else if (msg.getMessageType() == SiteMsgType.STATUS.shortValue()) {
//            Set<String> hosts = siteServer.getSite().getHosts();
//            SocketMsg newMsg = new SocketMsg(msg);
//            newMsg.setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
//            ByteBuf buf = Unpooled.buffer();
//            SitePolicyVersion version = siteServer.getSite().getVersion();
//            buf.writeBytes(JsonUtil.toJson(version).getBytes(Charsets.UTF_8));
//            newMsg.data(buf);
//            ctx.writeAndFlush(newMsg);
//        } else {
//            String ip = msg.getHost().toRegularIpFormat();
//            Terminal terminal = siteServer.getSite().getTerminalManager().getTerminalWithoutCreate(ip);
//            if (terminal != null) {
//                msg.changeSiteToClientAttr();
//                terminal.redirect(msg.retain(), ctx.channel());
//            } else {
//                logger.warn("找不到指定IP->{}设备:",ip);
//                logger.warn("当前通道和ip映射："+siteServer.getSite().getTerminalManager().getTerminalMap());
//                GenResp resp = new GenResp.S2CenterGenResp(msg.getMessageId(), msg.getMessageType(), GenResp.Status.Failure);
//                ctx.channel().writeAndFlush(resp);
//            }
//
//        }
        short type = msg.getMessageType();
        if (type == SiteMsgType.CONSOLE_CMD_MSG.shortValue()) {
            ConsoleCmdMsg cmdMsg = new ConsoleCmdMsg(msg);
            String cmd = cmdMsg.getCmdStr();
            if ("list".equals(cmd)) {
                SiteReport siteReport = siteServer.getSite().getSiteReport();
                StringBuilder builder = new StringBuilder();
                builder.append("siteReport : ");
                builder.append(siteReport);
                builder.append("\n");

                List<TermReport> termReportList = siteServer.getSite().getTermReportList();
                builder.append("Term Size is :").append(termReportList.size()).append("\n");
                for (TermReport termReport : termReportList) {
                    builder.append("tempReport is ").append(termReport).append("\n");
                }

                ConsoleCmdMsg respMsg = new ConsoleCmdMsg(builder.toString());
                ctx.channel().writeAndFlush(respMsg);
            } else if (cmd.startsWith("lock")) {
                String ip = cmd.replace("lock", "").trim();
                ClientCmd actioncmd = new ClientCmd.LockClientCmd();
                Channel channel = siteServer.getSite().getTerminalChannel(ip);
                siteServer.getSite().findTerminal(ip).redirect(actioncmd, ctx.channel());
            } else {
                ConsoleCmdMsg respMsg = new ConsoleCmdMsg("未知命令，请检查 " + cmd);
                ctx.channel().writeAndFlush(respMsg);
            }
        }
    }


}
