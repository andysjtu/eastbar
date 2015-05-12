package org.eastbar.site.handler.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.eastbar.codec.ClientMsgType;
import org.eastbar.codec.GenResp;
import org.eastbar.codec.GenRespUtil;
import org.eastbar.codec.SocketMsg;
import org.eastbar.site.Site;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Socket;

/**
 * Created by AndySJTU on 2015/5/11.
 */
public class ClientBeatenHandler extends SimpleChannelInboundHandler<SocketMsg> {
    public final static Logger logger= LoggerFactory.getLogger(ClientBeatenHandler.class);


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, SocketMsg socketMsg) throws Exception {
        short value = socketMsg.getMessageType();
        ClientMsgType type = ClientMsgType.valueOf(value);
        if (type == ClientMsgType.BEATEN) {
            logger.info("收到心跳信息");
            GenResp resp = GenRespUtil.createS2ClientSucessResp(socketMsg.getMessageId(), socketMsg.getMessageType());
            channelHandlerContext.channel().writeAndFlush(resp);
        } else
            channelHandlerContext.fireChannelRead(ReferenceCountUtil.retain(socketMsg));
    }
}
