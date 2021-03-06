package org.eastbar.site.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.eastbar.codec.ClientMsgType;
import org.eastbar.codec.GenResp;
import org.eastbar.codec.GenRespUtil;
import org.eastbar.codec.SocketMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by AndySJTU on 2015/5/11.
 */
public class ClientBeatenHandler extends SimpleChannelInboundHandler<SocketMsg> {
    public final static Logger logger = LoggerFactory.getLogger(ClientBeatenHandler.class);


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, SocketMsg socketMsg) throws Exception {
        short value = socketMsg.getMessageType();
//        logger.info("receive message type value is : " + value);
        ClientMsgType type = ClientMsgType.valueOf(value);
        if (type == ClientMsgType.BEATEN) {
            logger.debug("收到来自:{}心跳信息", channelHandlerContext.channel().remoteAddress());
            GenResp resp = GenRespUtil.createS2ClientSucessResp(socketMsg.getMessageId(), socketMsg.getMessageType());
            channelHandlerContext.channel().writeAndFlush(resp);
        } else
            channelHandlerContext.fireChannelRead(ReferenceCountUtil.retain(socketMsg));
    }


}
