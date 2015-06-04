package org.eastbar.alertd;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.eastbar.codec.SiteMsgType;
import org.eastbar.codec.SocketMsg;
import org.eastbar.logd.LogSaver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by AndySJTU on 2015/6/4.
 */
public class AlertHandler extends SimpleChannelInboundHandler<SocketMsg> {
    public final static Logger logger= LoggerFactory.getLogger(AlertHandler.class);

    
    private final LogSaver logSaver;

    public AlertHandler(LogSaver logSaver) {
        this.logSaver = logSaver;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        short typeValue = msg.getMessageType();
        SiteMsgType siteMsgType = SiteMsgType.valueOf(typeValue);
        switch (siteMsgType) {
            case URL_ALERT:
                break;
            case ILLEGAL_ALERT:
                break;
            case PROG_ALERT:
                break;
            default:
                logger.warn("收到未知告警类型的数据，请检查SocketMsg: {}", msg);
        }
    }
}
