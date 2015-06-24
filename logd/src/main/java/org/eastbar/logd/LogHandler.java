package org.eastbar.logd;

import com.google.common.base.Charsets;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.eastbar.codec.SiteMsgType;
import org.eastbar.codec.SocketMsg;
import org.eastbar.codec.log.*;
import org.eastbar.comm.log.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by AndySJTU on 2015/6/4.
 */
public class LogHandler extends SimpleChannelInboundHandler<SocketMsg> {
    public final static Logger logger = LoggerFactory.getLogger(LogHandler.class);

    private final LogSaver service;

    private final PrgLogSender logSender;

    public LogHandler(LogSaver service, PrgLogSender logSender) {
        this.service = service;
        this.logSender = logSender;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        short typeValue = msg.getMessageType();
        SiteMsgType msgType = SiteMsgType.valueOf(typeValue);
        switch (msgType) {
            case INST_MSG_LOG:
                InstMsgLogMsg instMsgLogMsg = new InstMsgLogMsg(msg);
                List<InstMsgLog> instMsgLogs = instMsgLogMsg.getLogs();
                service.saveInstMsgLog(instMsgLogs);
                break;
            case EMAIL_LOG:
                EmailLogMsg emailLogMsg = new EmailLogMsg(msg);
                List<EmailLog> emailLogs = emailLogMsg.getLogs();
                service.saveEmailLogs(emailLogs);
                break;
            case PROG_MSG_LOG:
//                ProgLogMsg progLogMsg = new ProgLogMsg(msg);
//                List<PrgLog> prgLogs = progLogMsg.getLogs();
                logSender.sendPrgLog(msg.data().content().toString(Charsets.UTF_8));
                break;
            case URL_LOG:
//                UrlLogMsg logMsg = new UrlLogMsg(msg);
//                List<UrlLog> urlLogList = logMsg.getUrlLogs();
//                service.saveSiteUrlLog(urlLogList);
                logSender.sendUrlLog(msg.data().content().toString(Charsets.UTF_8));
                break;
            case ILLEGAL_LOG:
                IllegalLogMsg illegalLogMsg = new IllegalLogMsg(msg);
                List<IllegalLog> illegalLogs = illegalLogMsg.getLogs();
                service.saveIllegalLogs(illegalLogs);
                break;
            default:
                logger.warn("收到未知日志类型的数据，请检查SocketMsg: {}", msg);
        }
    }
}
