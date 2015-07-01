package org.eastbar.site.city.handler;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;
import org.eastbar.codec.*;
import org.eastbar.site.Site;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

/**
 * Created by andysjtu on 2015/5/12.
 */
public class City2ClientCmdHandler extends SimpleChannelInboundHandler<SocketMsg> {
    private final Site site;
    public final static Logger logger= LoggerFactory.getLogger(City2ClientCmdHandler.class);


    public City2ClientCmdHandler(Site site) {
        this.site = site;
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
                logger.info("找到对应的终端");
                final ProxyChannelHandler proxyChannelHandler = (ProxyChannelHandler) channel.pipeline().get(ProxyChannelHandler.HANDLER_NAME);
                msg.changeSiteToClientAttr();
                channel.writeAndFlush(ReferenceCountUtil.retain(msg)).addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
                        if (future.isSuccess()) {
                            logger.info("输出到终端");
                            proxyChannelHandler.registerTarget(msg.getMessageId(), msg.getMessageType(), ctx.channel());
                        } else {
                            logger.info("没有输出到终端");
                            sendErrorResponse(msg.getMessageId(), msg.getMessageType(), ctx.channel());
                        }
                    }
                });

            } else {
                logger.warn("没有找到对应的终端");
                sendErrorResponse(msg.getMessageId(), msg.getMessageType(), ctx.channel());
            }
            return;
        } else if (value == SiteMsgType.LIST.shortValue()) {//
            Set<String> hosts = site.getHosts();
            SocketMsg newMsg = new SocketMsg(msg);
            newMsg.setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
            ByteBuf buf = Unpooled.buffer();
            SiteReport report = site.getSiteReport();
            buf.writeBytes(("UrlVersion=" + report.getUrlVersion() + "\n").getBytes(Charsets.UTF_8));
            buf.writeBytes(("PgVersion=" + report.getPrgVersion() + "\n").getBytes(Charsets.UTF_8));
            buf.writeBytes(("KwVersion=" + report.getKwVersion() + "\n").getBytes(Charsets.UTF_8));
            buf.writeBytes(("SpVersion=" + report.getSmVersion() + "\n").getBytes(Charsets.UTF_8));
            List<TermReport> termReportList = site.getTermReportList();
            if (termReportList.size() == 0) {
                buf.writeBytes("没有终端连上\n".getBytes(Charsets.UTF_8));
            }
            for (TermReport terminal : termReportList) {
                StringBuilder builder = new StringBuilder();
                builder.append("hostIp=").append(terminal.getIp()).append("\n");
                buf.writeBytes(builder.toString().getBytes(Charsets.UTF_8));
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

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warn("出现异常，连接City通道将关闭，异常原因是",cause);
        ctx.close();
    }
}
