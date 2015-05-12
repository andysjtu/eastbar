package org.eastbar.site.handler.console;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;
import org.eastbar.codec.ClientCmd;
import org.eastbar.codec.ClientMsgType;
import org.eastbar.codec.SocketMsg;
import org.eastbar.site.Site;
import org.eastbar.site.SiteServer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Set;

/**
 * Created by andysjtu on 2015/5/10.
 */
public class ConsoleHandler extends SimpleChannelInboundHandler<String> {

    private final SiteServer siteServer;

    public ConsoleHandler(SiteServer siteServer) {
        this.siteServer = siteServer;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("receive String is : " + msg);
        if ("quit".equalsIgnoreCase(msg)) {
            //TODO
            siteServer.stop();
        } else if ("list".equalsIgnoreCase(msg)) {
            Site site = siteServer.getSite();
            Set<String> hosts = site.getHosts();
            ctx.channel().writeAndFlush("hosts " + hosts + "\r\n");
        } else if (msg.startsWith("lock")) {
            lockClient(msg);

        } else if (msg.startsWith("unlock")) {
            unlockClient(msg);
        } else if (msg.startsWith("restart")) {
            restartClient(msg);
        } else if (msg.startsWith("shutdown")) {
            shutdownClient(msg);
        }else if(msg.startsWith("capture")){
            captureClient(msg);
        }
    }

    private void shutdownClient(String msg) {
        Site site = siteServer.getSite();
        String hostIp = msg.replace("shutdown", "").trim();

        Channel channel = site.getTerminalChannel(hostIp);
        if (channel != null) {
            channel.writeAndFlush(new ClientCmd.ShutdownClientCmd());
        }
    }

    private void captureClient(String msg) {
        Site site = siteServer.getSite();
        String hostIp = msg.replace("capture", "").trim();

        Channel channel = site.getTerminalChannel(hostIp);
        if (channel != null) {
            SocketMsg cmdMsg = new ClientCmd.CaptureClientCmd();
            channel.writeAndFlush(cmdMsg);
            channel.pipeline().addLast(new TempCmdRespHandler(cmdMsg.getMessageId()));
        }
    }

    private void restartClient(String msg) {
        Site site = siteServer.getSite();
        String hostIp = msg.replace("restart", "").trim();

        Channel channel = site.getTerminalChannel(hostIp);
        if (channel != null) {
            channel.writeAndFlush(new ClientCmd.RestartClientCmd());
        }
    }

    private void unlockClient(String msg) {
        Site site = siteServer.getSite();
        String hostIp = msg.replace("unlock", "").trim();

        System.out.println("hostIp is " + hostIp);


        Channel channel = site.getTerminalChannel(hostIp);
        if (channel != null) {
            channel.writeAndFlush(new ClientCmd.UnlockClientCmd());
        }
    }

    private void lockClient(String msg) {
        String hostIp = msg.replace("lock", "").trim();

        System.out.println("hostIp is " + hostIp);

        Site site = siteServer.getSite();

        Channel channel = site.getTerminalChannel(hostIp);
        if (channel != null) {
            channel.writeAndFlush(new ClientCmd.LockClientCmd());
        }
    }

    private static class TempCmdRespHandler extends SimpleChannelInboundHandler<SocketMsg> {
        private final short messageId;

        public TempCmdRespHandler(short messageId) {
            this.messageId = messageId;
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
            short value = msg.getMessageType();
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            ClientMsgType type = ClientMsgType.valueOf(value);
            if (type == ClientMsgType.GEN_RESP) {
                ByteBuf byteBuf = msg.data().content().duplicate();
                short recMssageId = byteBuf.readShort();
                short recMsgType = byteBuf.readShort();

                if(recMsgType==ClientMsgType.CAPTURE_CLIENT.shortValue()&&recMssageId==messageId){

                    byte[] content = new byte[byteBuf.readableBytes()];
                    byteBuf.readBytes(content);
                    BufferedImage image = ImageIO.read(new ByteArrayInputStream(content));
                    File file = new File("test.img");
                    ImageIO.write(image,"IMG",file);
                    return;
                }

            }
            ctx.fireChannelRead(ReferenceCountUtil.retain(msg));
        }
    }
}
