package org.eastbar.site;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.eastbar.codec.SocketMsg;
import org.eastbar.codec.log.EmailLogMsg;
import org.eastbar.codec.log.ProgLogMsg;
import org.eastbar.comm.log.entity.EmailLog;
import org.eastbar.comm.log.entity.PrgLog;
import org.eastbar.site.log.dao.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by AndySJTU on 2015/5/21.
 */
@Component
public class LogUploader {

    @Autowired
    private LogdConnector logdConnector;

    @Autowired
    private LogService logService;

    private ScheduledExecutorService service = Executors.newScheduledThreadPool(1);

    public void start() {
        scheduleNextCheck();
    }

    private void scheduleNextCheck() {
        service.schedule(new Runnable() {
            @Override
            public void run() {
                if (logdConnector.isConnected()) {
                    doWork();
                } else {
                    scheduleNextCheck();
                }
            }
        }, 30, TimeUnit.SECONDS);
    }

    public void doWork() {
        List<EmailLog> emailLogList = logService.getOldestEmailRecords();
        EmailLogMsg emailLogMsg = new EmailLogMsg(emailLogList);
        try {
            logdConnector.channel().writeAndFlush(emailLogMsg).sync();
            logService.deleteEmailLog(emailLogList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<PrgLog> prgLogList = logService.getOldestProgRecords();
        ProgLogMsg progLogMsg = new ProgLogMsg(prgLogList);
        try {
            logdConnector.channel().writeAndFlush(progLogMsg).sync();
            logService.deleteProgLog(prgLogList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduleNextCheck();

    }

    public static class RemoveLogHandler extends SimpleChannelInboundHandler<SocketMsg> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {

        }
    }
}
