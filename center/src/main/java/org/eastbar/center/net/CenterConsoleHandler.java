package org.eastbar.center.net;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.eastbar.codec.*;
import org.eastbar.codec.console.ConsoleCmdMsg;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by andysjtu on 2015/6/30.
 */
public class CenterConsoleHandler extends SimpleChannelInboundHandler<SocketMsg> {
    private final CenterHub centerHub;

    public CenterConsoleHandler(CenterHub centerHub) {
        this.centerHub = centerHub;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        short type = msg.getMessageType();
        if (type == SiteMsgType.CONSOLE_CMD_MSG.shortValue()) {
            ConsoleCmdMsg cmdMsg = new ConsoleCmdMsg(msg);
            String cmd = cmdMsg.getCmdStr();
            String cmdResp = null;
            if ("list".equals(cmd)) {
                Map<Channel, List<SiteReport>> channelListMap = centerHub.getCenterChannels();
                StringBuilder builder = new StringBuilder();
                builder.append("Center/Channel个数是 " + channelListMap.size());
                Set<Channel> channelSet = channelListMap.keySet();
                for (Channel channel : channelSet) {
                    builder.append("Channel is ").append(channel.remoteAddress()).append("\n");
                    builder.append("Channel对应SiteReport is :" + channelListMap.get(channel)).append("\n");
                }
                cmdResp = builder.toString();
                ConsoleCmdMsg respMsg = new ConsoleCmdMsg(cmdResp);
                ctx.channel().writeAndFlush(respMsg);
            } else if (cmd.startsWith("lock")) {
                String siteCodeAndIP = cmd.replace("lock", "").trim();
                String siteCode = siteCodeAndIP.split(":")[0];
                String ip = siteCodeAndIP.split(":")[1];
                SiteCmd actionCmd = new SiteCmd.LockClientCmd(ip);
                Channel siteChannel = centerHub.getCenterChannelBySiteCode(siteCode);
                if (siteChannel == null || !siteChannel.isActive()) {
                    cmdResp = "SiteCode:" + siteCode + " 对应Channel没有建立，请检查";
                    ConsoleCmdMsg respMsg = new ConsoleCmdMsg(cmdResp);
                    ctx.channel().writeAndFlush(respMsg);
                } else {
                    siteChannel.writeAndFlush(actionCmd);
                }
            } else {
                cmdResp = "未知命令，请检查： " + cmd;
                ConsoleCmdMsg respMsg = new ConsoleCmdMsg(cmdResp);
                ctx.channel().writeAndFlush(respMsg);
            }

//            else if (cmd.startsWith("unlock")) {
//                String siteCodeAndIP = cmd.replace("unlock", "").trim();
//                String siteCode = siteCodeAndIP.split(":")[0];
//                String ip = siteCodeAndIP.split(":")[1];
//                SiteCmd actionCmd = new SiteCmd.UnlockClientCmd(ip);
//                centerServer.getCenter().getVSite(siteCode).redirect(actionCmd, ctx.channel());
//            } else if (cmd.startsWith("shutdown")) {
//                String siteCodeAndIP = cmd.replace("shutdown", "").trim();
//                String siteCode = siteCodeAndIP.split(":")[0];
//                String ip = siteCodeAndIP.split(":")[1];
//                SiteCmd actionCmd = new SiteCmd.ShutdownClientCmd(ip);
//                centerServer.getCenter().getVSite(siteCode).redirect(actionCmd, ctx.channel());
//            } else if (cmd.startsWith("restart")) {
//                String siteCodeAndIP = cmd.replace("restart", "").trim();
//                String siteCode = siteCodeAndIP.split(":")[0];
//                String ip = siteCodeAndIP.split(":")[1];
//                SiteCmd actionCmd = new SiteCmd.RestartClientCmd(ip);
//                centerServer.getCenter().getVSite(siteCode).redirect(actionCmd, ctx.channel());
//            } else if (cmd.startsWith("ps")) {
//                String siteCodeAndIP = cmd.replace("ps", "").trim();
//                String siteCode = siteCodeAndIP.split(":")[0];
//                String ip = siteCodeAndIP.split(":")[1];
//                SiteCmd actionCmd = new SiteCmd.QueryClientProcessCmd(ip);
//                centerServer.getCenter().getVSite(siteCode).redirect(actionCmd, ctx.channel());
//            } else if (cmd.startsWith("pm")) {
//                String siteCodeAndIP = cmd.replace("pm", "").trim();
//                String siteCode = siteCodeAndIP.split(":")[0];
//                String ip = siteCodeAndIP.split(":")[1];
//                SiteCmd actionCmd = new SiteCmd.QueryClientModuleCmd(ip);
//                centerServer.getCenter().getVSite(siteCode).redirect(actionCmd, ctx.channel());
//            } else if (cmd.startsWith("capt")) {
//                String siteCodeAndIP = cmd.replace("capt", "").trim();
//                String siteCode = siteCodeAndIP.split(":")[0];
//                String ip = siteCodeAndIP.split(":")[1];
//                SiteCmd actionCmd = new SiteCmd.CaptureClientCmd(ip);
//                centerServer.getCenter().getVSite(siteCode).redirect(actionCmd, ctx.channel());
//            }
        }
    }
}