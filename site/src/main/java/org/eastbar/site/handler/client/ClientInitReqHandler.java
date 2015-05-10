package org.eastbar.site.handler.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.eastbar.codec.*;
import org.eastbar.site.Site;

/**
 * Created by andysjtu on 2015/5/10.
 */
public class ClientInitReqHandler extends SimpleChannelInboundHandler<SocketMsg>{
    private final Site site;

    public ClientInitReqHandler(Site site) {
        this.site = site;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        short messageType = msg.getMessageType();
        if(messageType!=0x1001){
            sendErrorResponse(ctx,msg);
        }
        ClientInitReq req = (ClientInitReq)msg;
        req.parse();
        ClientAuthScheme authScheme =req.getAuthSchme();
        //TODO
        ClientAuthResp resp = new ClientAuthResp();
        resp.setIdType("1");
        resp.setSpecialModel(false);
        resp.setUserId("310107197902026432");
        resp.setUserAccount("OA333");
        resp.setUserName("梁琳");
        resp.setVersion("1.0");

        sendSucessResponse(ctx,req);
    }

    private void sendSucessResponse(ChannelHandlerContext ctx, ClientInitReq req) {
        short recMessageId= req.getMessageId();
        short recMsgType = req.getMessageType();
        GenResp resp = GenRespUtil.createS2ClientSucessResp(recMessageId,recMsgType);
        ctx.channel().writeAndFlush(resp);
    }

    private void sendErrorResponse(ChannelHandlerContext ctx, SocketMsg msg) {
        short recMessageId= msg.getMessageId();
        short recMsgType = msg.getMessageType();
        ClientInitResp resp = new ClientInitResp();
        resp.setRecMessageId(recMessageId);
        resp.setRecMessageType(recMsgType);
        //TODO
        ctx.channel().writeAndFlush(resp);
    }
}
