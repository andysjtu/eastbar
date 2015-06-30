package org.eastbar.city.console;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.eastbar.city.CityCenterServer;
import org.eastbar.codec.*;
import org.eastbar.codec.console.ConsoleCmdMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by andysjtu on 2015/5/10.
 */
public class CityConsoleHandler extends SimpleChannelInboundHandler<SocketMsg> {
    public final static Logger logger = LoggerFactory.getLogger(CityConsoleHandler.class);

    private final CityCenterServer centerServer;

    public CityConsoleHandler(CityCenterServer centerServer) {
        this.centerServer = centerServer;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        short type = msg.getMessageType();
        if (type == SiteMsgType.CONSOLE_CMD_MSG.shortValue()) {
            ConsoleCmdMsg cmdMsg = new ConsoleCmdMsg(msg);
            String cmd = cmdMsg.getCmdStr();
            String cmdResp = null;
            if ("list".equals(cmd)) {
                Map<SiteReport, List<TermReport>> siteReportListMap = centerServer.getCenter().getSiteTermReports();
                StringBuilder builder = new StringBuilder();
                builder.append("Site个数是 " + siteReportListMap.size());
                Set<SiteReport> keySets = siteReportListMap.keySet();
                for (SiteReport siteReport : keySets) {
                    builder.append("SiteReport is :").append(siteReport).append("\n");
                    List<TermReport> termReports = siteReportListMap.get(siteReport);
                    for (TermReport termReport : termReports) {
                        builder.append("termReport is :" + termReport);
                    }
                }
                cmdResp = builder.toString();
                ConsoleCmdMsg respMsg = new ConsoleCmdMsg(cmdResp);
                ctx.channel().writeAndFlush(respMsg);
            } else if (cmd.startsWith("lock")) {
                String siteCodeAndIP = cmd.replace("lock", "").trim();
                String siteCode = siteCodeAndIP.split(":")[0];
                String ip = siteCodeAndIP.split(":")[1];
                SiteCmd actionCmd = new SiteCmd.LockClientCmd(ip);
                centerServer.getCenter().getVSite(siteCode).redirect(actionCmd, ctx.channel());
            } else if (cmd.startsWith("unlock")) {
                String siteCodeAndIP = cmd.replace("unlock", "").trim();
                String siteCode = siteCodeAndIP.split(":")[0];
                String ip = siteCodeAndIP.split(":")[1];
                SiteCmd actionCmd = new SiteCmd.UnlockClientCmd(ip);
                centerServer.getCenter().getVSite(siteCode).redirect(actionCmd, ctx.channel());
            } else if (cmd.startsWith("shutdown")) {
                String siteCodeAndIP = cmd.replace("shutdown", "").trim();
                String siteCode = siteCodeAndIP.split(":")[0];
                String ip = siteCodeAndIP.split(":")[1];
                SiteCmd actionCmd = new SiteCmd.ShutdownClientCmd(ip);
                centerServer.getCenter().getVSite(siteCode).redirect(actionCmd, ctx.channel());
            } else if (cmd.startsWith("restart")) {
                String siteCodeAndIP = cmd.replace("restart", "").trim();
                String siteCode = siteCodeAndIP.split(":")[0];
                String ip = siteCodeAndIP.split(":")[1];
                SiteCmd actionCmd = new SiteCmd.RestartClientCmd(ip);
                centerServer.getCenter().getVSite(siteCode).redirect(actionCmd, ctx.channel());
            } else if (cmd.startsWith("ps")) {
                String siteCodeAndIP = cmd.replace("ps", "").trim();
                String siteCode = siteCodeAndIP.split(":")[0];
                String ip = siteCodeAndIP.split(":")[1];
                SiteCmd actionCmd = new SiteCmd.QueryClientProcessCmd(ip);
                centerServer.getCenter().getVSite(siteCode).redirect(actionCmd, ctx.channel());
            } else if (cmd.startsWith("pm")) {
                String siteCodeAndIP = cmd.replace("pm", "").trim();
                String siteCode = siteCodeAndIP.split(":")[0];
                String ip = siteCodeAndIP.split(":")[1];
                SiteCmd actionCmd = new SiteCmd.QueryClientModuleCmd(ip);
                centerServer.getCenter().getVSite(siteCode).redirect(actionCmd, ctx.channel());
            } else if (cmd.startsWith("capt")) {
                String siteCodeAndIP = cmd.replace("capt", "").trim();
                String siteCode = siteCodeAndIP.split(":")[0];
                String ip = siteCodeAndIP.split(":")[1];
                SiteCmd actionCmd = new SiteCmd.CaptureClientCmd(ip);
                centerServer.getCenter().getVSite(siteCode).redirect(actionCmd, ctx.channel());
            } else {
                cmdResp = "未知命令，请检查： " + cmd;
                ConsoleCmdMsg respMsg = new ConsoleCmdMsg(cmdResp);
                ctx.channel().writeAndFlush(respMsg);
            }

        }
//        if (type == SiteMsgType.STATUS.shortValue()) {
//            ByteBuf buf = Unpooled.buffer();
//            buf.writeBytes(centerServer.getCenter().getReportString().getBytes(Charsets.UTF_8));
//            SocketMsg nm = new SocketMsg(msg);
//            nm.data(buf);
//            ctx.channel().writeAndFlush(nm);
//            return;
//        } else {
//            ByteBuf buf = msg.data().content();
//            String siteCode = buf.toString(Charsets.UTF_8);
//            logger.info("receive SiteCode is {}", siteCode);
//            SocketMsg newMsg = new SocketMsg(msg);
//            newMsg.setData(null);
//            VSite vSite = centerServer.getCenter().getVSite(siteCode);
//            if (vSite != null && vSite.isConnected()) {
//                vSite.redirect(newMsg, ctx.channel());
//                return;
//            }
//
//        }
//        sendErrorResp(ctx, msg);
    }

    private void sendErrorResp(ChannelHandlerContext ctx, SocketMsg msg) {
        GenResp resp = GenRespUtil.createS2ClientFailResp(msg.getMessageId(), msg.getMessageType());
        ctx.channel().writeAndFlush(resp);
    }
}
