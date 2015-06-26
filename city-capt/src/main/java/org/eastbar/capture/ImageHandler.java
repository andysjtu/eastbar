package org.eastbar.capture;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import org.eastbar.codec.ClientMsgType;
import org.eastbar.codec.SiteCmd;
import org.eastbar.codec.SiteMsgType;
import org.eastbar.codec.SocketMsg;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * Created by AndySJTU on 2015/6/10.
 */
public class ImageHandler extends SimpleChannelInboundHandler<SocketMsg> {

    private final String dirName;
    private Path dirPath;

    public ImageHandler(String dirName) {
        this.dirName = dirName;
        dirPath = Paths.get(dirName);
        if (Files.notExists(dirPath)) {
            try {
                Files.createDirectories(dirPath);
                System.out.println("dirPath is "+dirPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {

        ctx.executor().schedule(new Runnable() {
            @Override
            public void run() {
                list(ctx);
            }
        }, 10, TimeUnit.SECONDS);
    }

    public void list(final ChannelHandlerContext ctx) {
        final SiteCmd.ListTerminalCmd list = new SiteCmd.ListTerminalCmd();
        ctx.channel().writeAndFlush(list).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
               ctx.executor().schedule(new Runnable() {
                   @Override
                   public void run() {
                       list(ctx);
                   }
               },10,TimeUnit.SECONDS);
            }
        });
        SiteCmd.CaptureClientCmd cmd = new SiteCmd.CaptureClientCmd("192.168.9.100");
        ctx.channel().writeAndFlush(cmd);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {

        if (msg.getMessageType() == SiteMsgType.LIST.shortValue()) {
            System.out.println(msg.data().content().toString(Charsets.UTF_8));
        }
        if (msg.getMessageType() == SiteMsgType.STATUS.shortValue()) {
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
            System.out.println("dirPath is : "+dirPath);
            Path path = Files.createTempFile(dirPath, "192.168.9.11", ".jpeg");
//            Path path = Files.createTempFile("TEST", ".jpeg");
            System.out.println("path is : " + path);
            ImageIO.write(imageBuffer, "JPEG", path.toFile());
        }

    }

    public static void main(String[] args) {
        Path path = Paths.get("D:/img");
        try {
            Path newPaht = Files.createTempFile(path, "192.168.9.100", ".jpeg");
            System.out.println(newPaht);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
