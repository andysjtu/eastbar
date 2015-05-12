package org.eastbar.site.handler.console;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;
import org.eastbar.codec.ClientCmd;
import org.eastbar.codec.ClientMsgType;
import org.eastbar.codec.SocketMsg;
import org.eastbar.site.Site;
import org.eastbar.site.SiteServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Set;

/**
 * Created by andysjtu on 2015/5/10.
 */
public class ConsoleHandler extends SimpleChannelInboundHandler<String> {

    public final static Logger logger = LoggerFactory.getLogger(ConsoleHandler.class);


    private final SiteServer siteServer;

    public ConsoleHandler(SiteServer siteServer) {
        this.siteServer = siteServer;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("receive String is : " + msg);
        if ("squit".equalsIgnoreCase(msg)) {
            logger.info("收到控制端指令退出");
            siteServer.stop();
        } else if ("list".equalsIgnoreCase(msg)) {
            Site site = siteServer.getSite();
            Set<String> hosts = site.getHosts();
            ctx.channel().writeAndFlush("hosts " + hosts + "\r\n");
        } else if (msg.startsWith("lock ")) {
            lockClient(msg, ctx.channel());

        } else if (msg.startsWith("unlock ")) {
            unlockClient(msg, ctx.channel());
        } else if (msg.startsWith("restart ")) {
            restartClient(msg);
        } else if (msg.startsWith("shutdown ")) {
            shutdownClient(msg);
        } else if (msg.startsWith("capture ")) {
            captureClient(msg);
        } else if (msg.startsWith("queryp ")) {
            queryClientProcess(msg, ctx.channel());
        }else if (msg.startsWith("querym ")) {
            queryClientModule(msg, ctx.channel());
        }else if(msg.startsWith("querypm ")){
            String hostiP = trimHostIp(msg,"querypm");

            Channel channel = findTerminalChannel(hostiP);
            System.out.println("channel is : "+channel);
            if(channel!=null){
                sendQueryClientProcessCmd(ctx.channel(), channel);
                sendQueryClientModuleCmd(ctx.channel(),channel);
            }
        }
    }

    private String trimHostIp(String msg,String key){
        String hostIp = msg.replace(key, "").trim();
        return hostIp;
    }

    public void sendCmd(String hostIp,Channel consoleChannel){
        Channel channel = findTerminalChannel(hostIp);
        if(channel!=null){

        }
    }

    private Channel findTerminalChannel(String host){
        Site site = siteServer.getSite();
        Channel channel = site.getTerminalChannel(host);
        return channel;
    }

    private void queryClientProcess(String msg, Channel consoleChannel) {
        Site site = siteServer.getSite();
        String hostIp = msg.replace("queryp", "").trim();

        Channel channel = site.getTerminalChannel(hostIp);
        if (channel != null) {
            sendQueryClientProcessCmd(consoleChannel, channel);
        }
    }

    private void sendQueryClientProcessCmd(Channel consoleChannel, Channel channel) {
        SocketMsg socketMsg = new ClientCmd.QueryClientProcessCmd();
        channel.writeAndFlush(socketMsg);
        channel.pipeline().addAfter("beatenHandler", "queryClientProcessHandler",
                new QueryClientProcessRespHandler(socketMsg.getMessageId(), socketMsg.getMessageType(), consoleChannel));
    }

    private void queryClientModule(String msg, Channel consoleChannel) {
        Site site = siteServer.getSite();
        String hostIp = msg.replace("querym", "").trim();

        Channel channel = site.getTerminalChannel(hostIp);
        if (channel != null) {
            sendQueryClientModuleCmd(consoleChannel, channel);
        }
    }

    private void sendQueryClientModuleCmd(Channel consoleChannel, Channel channel) {
        SocketMsg socketMsg = new ClientCmd.QueryClientModuleCmd();
        channel.writeAndFlush(socketMsg);
        channel.pipeline().addAfter("beatenHandler", "queryClientModuleHandler",
                new QueryClientProcessRespHandler(socketMsg.getMessageId(), socketMsg.getMessageType(), consoleChannel));
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

    private void unlockClient(String msg, Channel consoleChannel) {
        Site site = siteServer.getSite();
        String hostIp = msg.replace("unlock", "").trim();


        Channel channel = site.getTerminalChannel(hostIp);
        if (channel != null) {
            SocketMsg socketMsg = new ClientCmd.UnlockClientCmd();
            channel.writeAndFlush(socketMsg);
            channel.pipeline().addAfter("beatenHandler", "unlockCmdResp",
                    new CmdRespHandler(socketMsg.getMessageId(), socketMsg.getMessageType(), consoleChannel));
        }
    }

    private void lockClient(String msg, Channel consoleChannel) {
        String hostIp = msg.replace("lock", "").trim();

        Site site = siteServer.getSite();

        Channel channel = site.getTerminalChannel(hostIp);
        if (channel != null) {
            SocketMsg socketMsg = new ClientCmd.LockClientCmd();
            channel.writeAndFlush(socketMsg);
            channel.pipeline().addAfter("beatenHandler", "unlockCmdResp",
                    new CmdRespHandler(socketMsg.getMessageId(), socketMsg.getMessageType(), consoleChannel));
        }
    }

    private static class CmdRespHandler extends SimpleChannelInboundHandler<SocketMsg> {

        private final short messageId;
        private final short messageType;

        private final Channel consoleChannel;

        public CmdRespHandler(short messageId, short messageType, Channel consoleChannel) {
            this.messageId = messageId;
            this.messageType = messageType;
            this.consoleChannel = consoleChannel;
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
            short value = msg.getMessageType();
            ClientMsgType type = ClientMsgType.valueOf(value);
            if (type == ClientMsgType.GEN_RESP) {
                ByteBuf byteBuf = msg.data().content().duplicate();
                short recMssageId = byteBuf.readShort();
                short recMsgType = byteBuf.readShort();
                if (recMsgType == messageType && recMssageId == messageId) {
                    try {
                        byte cmdStatus = byteBuf.readByte();
                        consoleChannel.writeAndFlush("响应值是:" + Integer.toString(cmdStatus) + "\n");
                    } finally {
                        byteBuf.release();
                        ctx.pipeline().remove(this);
                    }
                    return;
                }

            }
            ctx.fireChannelRead(ReferenceCountUtil.retain(msg));
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
            ClientMsgType type = ClientMsgType.valueOf(value);
            if (type == ClientMsgType.CAPTURE_CLIENT) {
                ByteBuf byteBuf = msg.data().content().duplicate();
                short recMssageId = byteBuf.readShort();
                short recMsgType = byteBuf.readShort();

                if (recMsgType == ClientMsgType.CAPTURE_CLIENT.shortValue() && recMssageId == messageId) {

                    byte[] content = new byte[byteBuf.readableBytes()];
                    byteBuf.readBytes(content);
                    BufferedImage image = ImageIO.read(new ByteArrayInputStream(content));
                    File file = new File("test2.JPG");
                    ImageIO.write(image, "JPG", file);
                    ctx.pipeline().remove(this);
                    return;
                }

            }
            ctx.fireChannelRead(ReferenceCountUtil.retain(msg));
        }
    }

    private class QueryClientProcessRespHandler extends SimpleChannelInboundHandler<SocketMsg> {
        private final short messageId;
        private final short messageType;
        private final Channel consoleChannel;

        public QueryClientProcessRespHandler(short messageId, short messageType, Channel consoleChannel) {
            this.messageId = messageId;
            this.messageType = messageType;
            this.consoleChannel = consoleChannel;
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
            short value = msg.getMessageType();
            ClientMsgType type = ClientMsgType.valueOf(value);
            if (type == ClientMsgType.QUERY_CLIENT_PROCESS) {
                ByteBuf byteBuf = msg.data().content().duplicate();
                short recMssageId = byteBuf.readShort();
                short recMsgType = byteBuf.readShort();

                if (recMsgType == ClientMsgType.QUERY_CLIENT_PROCESS.shortValue() && recMssageId == messageId) {

                    try {

                        consoleChannel.writeAndFlush("响应值是:\n" + byteBuf.toString(Charsets.UTF_8) + "\n");
                    } finally {
                        byteBuf.release();
                        ctx.pipeline().remove(this);
                    }
                    return;
                }
            }
            else if(type==ClientMsgType.QUERY_CLIENT_MODULE){
                ByteBuf byteBuf = msg.data().content().duplicate();
                short recMssageId = byteBuf.readShort();
                short recMsgType = byteBuf.readShort();

                if (recMsgType == ClientMsgType.QUERY_CLIENT_MODULE.shortValue() && recMssageId == messageId) {

                    try {

                        consoleChannel.writeAndFlush("响应值是:\n" + byteBuf.toString(Charsets.UTF_8) + "\n");
                    } finally {
                        byteBuf.release();
                        ctx.pipeline().remove(this);
                    }
                    return;
                }
            }
            ctx.fireChannelRead(ReferenceCountUtil.retain(msg));
        }
    }
}
