package org.eastbar.site.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.eastbar.codec.ClientMsgType;
import org.eastbar.codec.GenResp;
import org.eastbar.codec.GenRespUtil;
import org.eastbar.codec.SocketMsg;
import org.eastbar.site.alert.AlertServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by AndySJTU on 2015/5/11.
 */
public class ClientAlertHandler extends SimpleChannelInboundHandler<SocketMsg> {
    private final AlertServer alertServer;
    public final static Logger logger= LoggerFactory.getLogger(ClientAlertHandler.class);


    public ClientAlertHandler(AlertServer alertServer) {
        this.alertServer = alertServer;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        short value = msg.getMessageType();
        ClientMsgType type = ClientMsgType.valueOf(value);

        if (type == ClientMsgType.CLIENT_ALERT) {
            ByteBuf buf = msg.data().content();
            byte alertType = buf.readByte();
            logger.info("收到来自{}的告警信息",ctx.channel().remoteAddress());
            logger.info("-----------------------------------------------------");

            byte[] content = new byte[buf.readableBytes()];
            buf.readBytes(content);
            logger.info("告警类型是: " + alertType);
            logger.info("告警内容是:" + new String(content));
            logger.info("--------------------------------------------------");
            alertServer.appendAlert(alertType, new String(content));
            sendResp(ctx, msg);
        } else {
            ctx.fireChannelRead(ReferenceCountUtil.retain(msg));
        }
    }

    private void sendResp(ChannelHandlerContext ctx, SocketMsg msg) {
        GenResp resp = GenRespUtil.createS2ClientSucessResp(msg.getMessageId(), msg.getMessageType());
        ctx.channel().writeAndFlush(resp);
    }
}
