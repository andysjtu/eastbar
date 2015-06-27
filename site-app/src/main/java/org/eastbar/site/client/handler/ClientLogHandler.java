package org.eastbar.site.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.eastbar.codec.ClientMsgType;
import org.eastbar.codec.GenResp;
import org.eastbar.codec.GenRespUtil;
import org.eastbar.codec.SocketMsg;
import org.eastbar.site.log.LogServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by AndySJTU on 2015/5/11.
 */
public class ClientLogHandler extends SimpleChannelInboundHandler<SocketMsg> {
    public final static Logger logger= LoggerFactory.getLogger(ClientLogHandler.class);

    private final LogServer logServer;

    public ClientLogHandler(LogServer logServer) {
        this.logServer = logServer;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        short value = msg.getMessageType();
        ClientMsgType type = ClientMsgType.valueOf(value);

        if(type==ClientMsgType.CLIENT_LOG){
            ByteBuf buf = msg.data().content();
            byte logType = buf.readByte();
            logger.info("收到来自{}的日志信息",ctx.channel().remoteAddress());
            logger.info("-----------------------------------------------------");
            logger.info("日志类型是 : " + logType);
            byte[] content = new byte[buf.readableBytes()];
            buf.readBytes(content);
            logger.info("日志内容是 : "+new String(content));
            logger.info("----------------------------------------------------");

            logServer.appendLog(logType,new String(content));

            sendResp(ctx,msg);
        }
        else{
            ctx.fireChannelRead(ReferenceCountUtil.retain(msg));
        }
    }

    private void sendResp(ChannelHandlerContext ctx, SocketMsg msg) {
        GenResp resp = GenRespUtil.createS2ClientSucessResp(msg.getMessageId(), msg.getMessageType());
        ctx.channel().writeAndFlush(resp);
    }
}
