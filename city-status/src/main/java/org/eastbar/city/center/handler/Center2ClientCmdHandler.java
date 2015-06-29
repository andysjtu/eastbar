package org.eastbar.city.center.handler;

import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;
import org.apache.commons.codec.Charsets;
import org.eastbar.center.net.CenterHub;
import org.eastbar.city.CityCenter;
import org.eastbar.city.VSite;
import org.eastbar.codec.ClientMsgType;
import org.eastbar.codec.GenResp;
import org.eastbar.codec.ProxyChannelHandler;
import org.eastbar.codec.SocketMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by andyliang on 6/29/15.
 */
public class Center2ClientCmdHandler extends SimpleChannelInboundHandler<SocketMsg> {
    public final static Logger logger = LoggerFactory.getLogger(Center2ClientCmdHandler.class);


    private final CityCenter cityCenter;

    public Center2ClientCmdHandler(CityCenter centerHub) {
        this.cityCenter = centerHub;
    }

    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, final SocketMsg msg) throws Exception {
        short value = msg.getMessageType();


        if (value == ClientMsgType.CAPTURE_CLIENT.shortValue()
                || value == ClientMsgType.QUERY_CLIENT_PROCESS.shortValue()
                || value == ClientMsgType.QUERY_CLIENT_MODULE.shortValue()
                || value == ClientMsgType.CLOSE_CLIENT_MODULE.shortValue()
                || value == ClientMsgType.KILL_CLIENT_PORCESS.shortValue()
                || value == ClientMsgType.LOCK_CLIENT.shortValue()
                || value == ClientMsgType.UNLOCK_CLIENT.shortValue()
                || value == ClientMsgType.SHUTDOWN_CLIENT.shortValue()
                || value == ClientMsgType.RESTART_CLIENT.shortValue()
                ) {
            logger.debug("收到需要转发的命令 : " + value);
            String siteCode = msg.data().content().duplicate().toString(Charsets.UTF_8);
            VSite vSite = cityCenter.getVSite(siteCode);
            if (vSite != null) {
                vSite.redirect(ReferenceCountUtil.retain(msg), ctx.channel());
            } else {
                logger.debug("没有找到对应的Site ：{}", siteCode);
                sendErrorResponse(msg.getMessageId(), msg.getMessageType(), ctx.channel());
            }
//            Channel channel = cityCenter.getVSite(siteCode).
//            if (channel != null && channel.isActive()) {
//                final ProxyChannelHandler proxyChannelHandler = (ProxyChannelHandler) channel.pipeline().get(ProxyChannelHandler.HANDLER_NAME);
//                msg.changeSiteToClientAttr();
//
//                channel.writeAndFlush(ReferenceCountUtil.retain(msg)).addListener(new ChannelFutureListener() {
//                    @Override
//                    public void operationComplete(ChannelFuture future) throws Exception {
//                        if (future.isSuccess()) {
//                            logger.debug("命令转发成功");
//                            proxyChannelHandler.registerTarget(msg.getMessageId(), msg.getMessageType(), ctx.channel());
//                        } else {
//                            logger.debug("命令转发失败");
//                            sendErrorResponse(msg.getMessageId(), msg.getMessageType(), ctx.channel());
//                        }
//                    }
//                });
//
//            } else {
//                logger.debug("Site通道没有建立：{}",siteCode);
//                sendErrorResponse(msg.getMessageId(), msg.getMessageType(), ctx.channel());
//            }
//            return;
        } else {
            ctx.fireChannelRead(ReferenceCountUtil.retain(msg));
        }
    }

    private void sendErrorResponse(short messageId, short messageType, Channel channel) {
        GenResp resp = new GenResp.S2CenterGenResp(messageId, messageType, GenResp.Status.Failure);
        channel.writeAndFlush(resp);
    }
}
