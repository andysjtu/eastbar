package org.eastbar.site.handler.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.eastbar.codec.ClientMsgType;
import org.eastbar.codec.SocketMsg;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by AndySJTU on 2015/5/11.
 */
public class ClientCmdRespHandler extends SimpleChannelInboundHandler<SocketMsg> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        short value = msg.getMessageType();
        ClientMsgType type = ClientMsgType.valueOf(value);
        switch (type) {
            case CAPTURE_CLIENT:
                System.out.println("receive general resp");
                ByteBuf byteBuf = msg.data().content().duplicate();
                short recMssageId = byteBuf.readShort();
                short recMsgType = byteBuf.readShort();
                System.out.println("8888888888888888888888888888888888888888");
                System.out.println("recMsgtype is " + recMsgType);


                try {
                    byte[] content = new byte[byteBuf.readableBytes()];
                    byteBuf.readBytes(content);
                    BufferedImage image = ImageIO.read(new ByteArrayInputStream(content));
                    File file = new File("test.jpg");
                    System.out.println("file.absole : "+file.getAbsolutePath());
                    ImageIO.write(image, "jpg", file);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

        }
    }
}
