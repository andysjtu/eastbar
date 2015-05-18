package org.eastbar.site.handler.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.ScheduledFuture;
import org.assertj.core.util.Maps;
import org.eastbar.codec.ClientMsgType;
import org.eastbar.codec.SocketMsg;
import org.eastbar.site.Proxy;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by AndySJTU on 2015/5/11.
 */
public class ClientCmdRespHandler extends SimpleChannelInboundHandler<SocketMsg> {
    public final static org.slf4j.Logger logger = LoggerFactory.getLogger(ClientCmdRespHandler.class);


    private final Proxy proxy;

    public ClientCmdRespHandler(Proxy proxy) {
        this.proxy = proxy;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        short value = msg.getMessageType();
        ClientMsgType type = ClientMsgType.valueOf(value);
        logger.info("返回类型是 : {}", type);
        switch (type) {
            case CAPTURE_CLIENT://截图信息
            case QUERY_CLIENT_PROCESS:
            case QUERY_CLIENT_MODULE:
            case GEN_RESP://通用返回
                if (proxy.contains(msg.getMessageType(), msg.getMessageId())) {
                    if (proxy.isOpen()) {
                        SocketMsg proxyMsg = msg.retain();
                        proxyMsg.changeSiteToCenterAttr();
                        proxy.proxyChannel().writeAndFlush(proxyMsg);
                    } else {
                        logger.warn("由于命令响应返回时，Proxy通道没有打开，丢弃命令响应,响应类型是:{}", type);
                    }
                } else {
                    ctx.fireChannelRead(ReferenceCountUtil.retain(msg));
                }
                break;
            default:
                ctx.fireChannelRead(ReferenceCountUtil.retain(msg));

        }
    }


}
