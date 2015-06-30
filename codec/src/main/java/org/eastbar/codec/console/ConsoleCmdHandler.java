package org.eastbar.codec.console;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import org.eastbar.codec.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Executor;

/**
 * Created by andyliang on 6/29/15.
 */
public class ConsoleCmdHandler extends SimpleChannelInboundHandler<SocketMsg> {
    private final Executor executor;

    public ConsoleCmdHandler(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                processCmd(ctx);
            }
        });

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, SocketMsg msg) throws Exception {
        if (msg.getMessageType() == SiteMsgType.SITE_INIT_CONN.shortValue()) {
            SiteInitReq initReq = new SiteInitReq(msg);
            System.out.println("收到Site端初始化数据 ");
            System.out.println("Site Report is :" + initReq.getSiteReport());
            System.out.println("Term Report is : " + initReq.getTermReport());

        } else if (msg.getMessageType() == SiteMsgType.TERM_STATUS.shortValue()) {
            TermReportMsg termReportMsg = new TermReportMsg(msg);
            System.out.println(termReportMsg.getReport());
            return;
        } else if (msg.getMessageType() == SiteMsgType.POLICY_STATUS.shortValue()) {
            SiteReportMsg siteReportMsg = new SiteReportMsg(msg);
            System.out.println(siteReportMsg.getReport());
            return;
        } else if (msg.getMessageType() == SiteMsgType.LIST.shortValue()) {
            System.out.println(msg.data().content().toString(Charsets.UTF_8));
        } else if (msg.getMessageType() == SiteMsgType.STATUS.shortValue()) {
            System.out.println(msg.data().content().toString(Charsets.UTF_8));
        } else if (msg.getMessageType() == SiteMsgType.GEN_RESP.shortValue()) {
            ByteBuf buf = msg.data().content();
            buf.readShort();
            buf.readShort();
            System.out.println("操作结果是 : " + buf.readByte());
        } else if (msg.getMessageType() == ClientMsgType.QUERY_CLIENT_PROCESS.shortValue()) {
            ByteBuf buf = msg.data().content();
            buf.readShort();
            buf.readShort();
            System.out.println("操作结果是 : " + buf.toString(Charsets.UTF_8));
        } else if (msg.getMessageType() == ClientMsgType.QUERY_CLIENT_MODULE.shortValue()) {
            ByteBuf buf = msg.data().content();
            buf.readShort();
            buf.readShort();
            System.out.println("操作结果是 : " + buf.toString(Charsets.UTF_8));
        } else if (msg.getMessageType() == ClientMsgType.CAPTURE_CLIENT.shortValue()) {
            ByteBuf buf = msg.data().content();
            buf.readShort();
            buf.readShort();
            byte[] content = new byte[buf.readableBytes()];
            buf.readBytes(content);

            BufferedImage imageBuffer = ImageIO.read(new ByteArrayInputStream(content));
            Path path = Files.createTempFile("TEST", ".jpeg");
            System.out.println("path is : " + path);
            ImageIO.write(imageBuffer, "JPEG", path.toFile());
        } else {
            System.out.println("收到未知类型包数据" + Integer.toHexString(msg.getMessageType()));
        }
    }

    private void processCmd(ChannelHandlerContext ctx) {
        ConsoleUtil consoleUtil = new ConsoleUtil(System.in);
        do {
            try {
                String line = consoleUtil.readLine().trim();
                if ("quit".equals(line)) {
                    ctx.close();
                    break;
                } else {
                    ConsoleCmdMsg msg = new ConsoleCmdMsg(line);
                    ctx.channel().writeAndFlush(msg).addListener(new ChannelFutureListener() {
                        @Override
                        public void operationComplete(ChannelFuture future) throws Exception {
                            if (!future.isSuccess()) {
                                System.out.println("命令发送失败，原因是 :" + future.cause().getMessage());
                            }
                        }
                    });
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (true);
    }
}
