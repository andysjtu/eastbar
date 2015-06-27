package org.eastbar.site.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.eastbar.codec.*;
import org.eastbar.site.Site;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by andysjtu on 2015/5/10.
 */
public class ClientInitReqHandler extends SimpleChannelInboundHandler<SocketMsg>{
    public final static Logger logger= LoggerFactory.getLogger(ClientInitReqHandler.class);

    private final Site site;

    public ClientInitReqHandler(Site site) {
        this.site = site;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        short messageType = msg.getMessageType();
//        logger.info("收到的消息类型是 : {}",messageType);
        if(messageType!=ClientMsgType.CLIENT_INIT_REQ.shortValue()){
            sendErrorResponse(ctx, msg);
            logger.warn("格式错误，关闭通道");
            ctx.channel().close();
        }else {
            ClientInitReq req = new ClientInitReq(msg);
            ClientAuthScheme authScheme = req.getAuthSchme();

            site.registerChannel(ctx.channel(), req);

            ctx.pipeline().remove(this);

        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warn("exception is ",cause);
    }

    private void sendErrorResponse(ChannelHandlerContext ctx, SocketMsg msg) {
        GenResp resp= GenRespUtil.createS2ClientMsgErrorResp(msg.getMessageId(),msg.getMessageType());
        ctx.channel().writeAndFlush(resp);
    }
}
