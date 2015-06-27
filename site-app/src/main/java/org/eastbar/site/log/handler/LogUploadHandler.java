package org.eastbar.site.log.handler;

import com.google.common.collect.Sets;
import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;
import org.eastbar.codec.SiteMsgType;
import org.eastbar.codec.SocketMsg;
import org.eastbar.codec.log.*;
import org.eastbar.net.log.entity.*;
import org.eastbar.site.log.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by andyliang on 6/27/15.
 */
public class LogUploadHandler extends SimpleChannelInboundHandler<SocketMsg> {
    public final static Logger logger = LoggerFactory.getLogger(LogUploadHandler.class);


    private ScheduledExecutorService service = Executors.newScheduledThreadPool(3);

    private Set<ScheduledFuture> futureSet = Sets.newConcurrentHashSet();

    private final LogService logService;

    public LogUploadHandler(LogService logService) {
        this.logService = logService;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        schduleUploadTask(ctx.channel());

    }

    private void schduleUploadTask(Channel channel) {
        ScheduledFuture future = service.schedule(new UploadEmailJob(channel), 3, TimeUnit.SECONDS);
        futureSet.add(future);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        //cancel uploadJob
        for (ScheduledFuture future : futureSet) {
            if (!future.isDone()) future.cancel(true);
        }
    }

    private void removeDoneFuture() {
        Set<ScheduledFuture> unmod = Collections.unmodifiableSet(futureSet);
        for (ScheduledFuture future : unmod) {
            if (future.isDone()) {
                futureSet.remove(future);
            }
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg socketMsg) throws Exception {
        //receive log accept message,then remove log list from db
        short type = socketMsg.getMessageType();
        if (type == SiteMsgType.GEN_RESP.shortValue()) {
            logger.debug("receive city log recept");
        } else {
            ctx.fireChannelRead(ReferenceCountUtil.retain(socketMsg));
        }
    }

    public class UploadEmailJob implements Runnable {

        private Channel channel;

        public UploadEmailJob(Channel channel) {
            this.channel = channel;
        }

        @Override
        public void run() {
            uploadLog();
            removeDoneFuture();
        }

        private void uploadLog() {
            try {
                final List<EmailLog> emailLogList = logService.getOldestEmailRecords();
                final EmailLogMsg msg = new EmailLogMsg(emailLogList);
                channel.writeAndFlush(msg).addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        if (channelFuture.isSuccess()) {
                            deleteEmailLogs(emailLogList);
                        }
                    }
                });

                final List<UrlLog> urlLogs = logService.getOldestURLRecords();
                final UrlLogMsg urlLogMsg = new UrlLogMsg(urlLogs);
                channel.writeAndFlush(urlLogMsg).addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        if(channelFuture.isSuccess()){
                            deleteURLLogs(urlLogs);
                        }
                    }
                });

                final List<PrgLog> prgLogs = logService.getOldestProgRecords();
                ProgLogMsg progLogMsg = new ProgLogMsg(prgLogs);
                channel.writeAndFlush(progLogMsg).addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        if(channelFuture.isSuccess()){
                            deletePrgLogs(prgLogs);
                        }
                    }
                });

                final List<InstMsgLog> instMsgLogs = logService.getOldestInstMsgRecords();
                InstMsgLogMsg instMsgLogMsg = new InstMsgLogMsg(instMsgLogs);
                channel.writeAndFlush(instMsgLogMsg).addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        if(channelFuture.isSuccess()){
                            deleteInstMsgLogs(instMsgLogs);
                        }
                    }
                });

                final List<IllegalLog> illegalLogs = logService.getOldestIllegalLogRecords();
                IllegalLogMsg illegalLogMsg = new IllegalLogMsg(illegalLogs);
                channel.writeAndFlush(illegalLogMsg).addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        if(channelFuture.isSuccess()){
                            deleteIllegalLogMsgLogs(illegalLogs);
                        }
                        schduleUploadTask(channel);
                    }
                });


            } catch (Throwable t) {
                logger.warn("上传EmailLog出错，请检查", t);
                schduleUploadTask(channel);
            }
        }
    }

    private void deleteEmailLogs(final List<EmailLog> emailLogList) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    logService.deleteEmailLog(emailLogList);
                } catch (Throwable t) {
                    logger.warn("删除EmailLogList出错，请检查", t);
                }
            }
        });
    }

    private void deleteURLLogs(final List<UrlLog> urlLogList) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    logService.deleteUrlLog(urlLogList);
                } catch (Throwable t) {
                    logger.warn("删除urlLogList出错，请检查", t);
                }
            }
        });
    }

    private void deletePrgLogs(final List<PrgLog> urlLogList) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    logService.deleteProgLog(urlLogList);
                } catch (Throwable t) {
                    logger.warn("删除PrgLogList出错，请检查", t);
                }
            }
        });
    }

    private void deleteInstMsgLogs(final List<InstMsgLog> urlLogList) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    logService.deleteInstMsgLog(urlLogList);
                } catch (Throwable t) {
                    logger.warn("删除InstMsgLogList出错，请检查", t);
                }
            }
        });
    }

    private void deleteIllegalLogMsgLogs(final List<IllegalLog> urlLogList) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    logService.deleteIllegalLog(urlLogList);
                } catch (Throwable t) {
                    logger.warn("删除IllegalLogList出错，请检查", t);
                }
            }
        });
    }
}
