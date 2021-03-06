package org.eastbar.alert2mq;

import com.google.common.base.Charsets;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.eastbar.codec.GenResp;
import org.eastbar.codec.GenRespUtil;
import org.eastbar.codec.SiteMsgType;
import org.eastbar.codec.SocketMsg;
import org.eastbar.codec.server.PingMsg;
import org.eastbar.codec.server.PongMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by AndySJTU on 2015/6/4.
 */
public class AlertHandler extends SimpleChannelInboundHandler<SocketMsg> {
    public final static Logger logger = LoggerFactory.getLogger(AlertHandler.class);


    private final JmsAlertSender alertSaver;

    public AlertHandler(JmsAlertSender logSaver) {
        this.alertSaver = logSaver;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        short typeValue = msg.getMessageType();
        SiteMsgType siteMsgType = SiteMsgType.valueOf(typeValue);
        switch (siteMsgType) {
//            case URL_ALERT:
//                UrlBlockAlertMsg urlBlockAlertMsg = new UrlBlockAlertMsg(msg);
//                List<UrlBlockAlert> urlBlockAlertList = urlBlockAlertMsg.getAlerts();
//                alertSaver.saveUrlAlert(urlBlockAlertList);
//                break;
//            case ILLEGAL_ALERT:
//                IllegalBlockAlertMsg illegalLogMsg = new IllegalBlockAlertMsg(msg);
//                List<IllegalBlockAlert> illegalBlockAlerts = illegalLogMsg.getAlerts();
//                alertSaver.saveIllegalAlert(illegalBlockAlerts);
//                break;
//            case PROG_ALERT:
//                ProgBlockAlertMsg progBlockAlertMsg = new ProgBlockAlertMsg(msg);
//                List<ProgBlockAlert> progBlockAlertList = progBlockAlertMsg.getAlerts();
//                alertSaver.saveProgAlert(progBlockAlertList);
//                break;
            case GENERAL_ALERT:
                alertSaver.sendAlert(msg.data().content().toString(Charsets.UTF_8));
                GenResp resp = GenRespUtil.createCenter2SiteSuccessResp(msg.getMessageId(), msg.getMessageType());
                ctx.channel().writeAndFlush(resp);
                break;
            case PING_REQ:
                ctx.writeAndFlush(new PongMsg());
                break;
            default:
                logger.warn("收到未知告警类型:{}的数据，请检查SocketMsg: {}", msg.getMessageType(), msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warn("接受告警数据出现异常", cause);
        ctx.close();
    }
}
