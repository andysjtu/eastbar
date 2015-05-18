package org.eastbar.site.handler.center;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;
import org.eastbar.codec.*;
import org.eastbar.site.handler.client.ClientProxyChannelHandler;
import org.eastbar.site.Site;

import java.util.Set;

/**
 * Created by andysjtu on 2015/5/12.
 */
public class Center2ClientCmdHandler extends SimpleChannelInboundHandler<SocketMsg> {
    private final Site site;

    private final ClientProxyChannelHandler proxyChannelHandler;

    public Center2ClientCmdHandler(Site site, ClientProxyChannelHandler proxyChannelHandler) {
        this.site = site;
        this.proxyChannelHandler = proxyChannelHandler;
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
            String ip = msg.getHost().toRegularIpFormat();
            Channel channel = site.getTerminalChannel(ip);
            if (channel != null && channel.isActive()) {
                msg.changeSiteToClientAttr();
                channel.writeAndFlush(ReferenceCountUtil.retain(msg)).addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
                        if (future.isSuccess()) {
                            proxyChannelHandler.registerTarget(msg.getMessageId(), msg.getMessageType(), ctx.channel());
                        } else {
                            sendErrorResponse(msg.getMessageId(), msg.getMessageType(), ctx.channel());
                        }
                    }
                });

            } else {
                sendErrorResponse(msg.getMessageId(), msg.getMessageType(), ctx.channel());
            }
            return;
        } else if (value == SiteMsgType.LIST.shortValue()) {//
            Set<String> hosts = site.getHosts();
            SocketMsg newMsg = new SocketMsg(msg);
            newMsg.setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
            ByteBuf buf = Unpooled.buffer();
            if (hosts.size() == 0) {
                buf.writeBytes("没有终端连上\n".getBytes(Charsets.UTF_8));
            } else {
                for (String host : hosts) {
                    buf.writeBytes(host.getBytes(Charsets.UTF_8));
                    buf.writeBytes("\n".getBytes());
                }
            }
            newMsg.data(buf);
            ctx.writeAndFlush(newMsg);
            return;
        }
        ctx.fireChannelRead(ReferenceCountUtil.retain(msg));
    }

    private void sendErrorResponse(short messageId, short messageType, Channel channel) {
        GenResp resp = new GenResp.S2CenterGenResp(messageId, messageType, GenResp.Status.Failure);
        channel.writeAndFlush(resp);
    }
}
