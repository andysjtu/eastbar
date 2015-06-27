package org.eastbar.site.alert;

import com.google.common.collect.Sets;
import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;
import org.eastbar.codec.SiteMsgType;
import org.eastbar.codec.SocketMsg;
import org.eastbar.codec.alert.GeneralAlertMsg;
import org.eastbar.codec.log.*;
import org.eastbar.net.alert.entity.GeneralAlert;
import org.eastbar.net.log.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by andyliang on 6/27/15.
 */
public class AlertUploadHandler extends SimpleChannelInboundHandler<SocketMsg> {
    public static final int DEFAULT_UPLOAD_INTERVAL=3;
    private int uploadInterval=DEFAULT_UPLOAD_INTERVAL;
    public final static Logger logger= LoggerFactory.getLogger(AlertUploadHandler.class);

    private ScheduledExecutorService service = Executors.newScheduledThreadPool(3);

    private Set<ScheduledFuture> futureSet = Sets.newConcurrentHashSet();


    private final AlertService alertService;

    public AlertUploadHandler(AlertService alertService) {
        this.alertService = alertService;

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        schduleUploadTask(ctx.channel());
    }

    private void schduleUploadTask(Channel channel) {
        ScheduledFuture future = service.schedule(new UploadAlertJob(channel), uploadInterval, TimeUnit.SECONDS);
        futureSet.add(future);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        for (ScheduledFuture future : futureSet) {
            if (!future.isDone()) future.cancel(true);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warn("连接告警服务器出现异常，关闭通道，进行重连",cause);
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, SocketMsg socketMsg) throws Exception {
          short type = socketMsg.getMessageType();
        if(type== SiteMsgType.GEN_RESP.shortValue()){
            logger.debug("receive alert recept");
        }
        else{
            channelHandlerContext.fireChannelRead(ReferenceCountUtil.retain(socketMsg));
        }
    }

    private void removeDoneFuture() {
        Set<ScheduledFuture> unmod = Collections.unmodifiableSet(futureSet);
        for (ScheduledFuture future : unmod) {
            if (future.isDone()||future.isCancelled()) {
                futureSet.remove(future);
            }
        }
    }

    public class UploadAlertJob implements Runnable {

        private Channel channel;

        public UploadAlertJob(Channel channel) {
            this.channel = channel;
        }

        @Override
        public void run() {
            uploadAlert();
            removeDoneFuture();
        }

        private void uploadAlert() {
            try {
                final List<GeneralAlert> logList = alertService.getOldestGeneralAlarmRecord();
                final GeneralAlertMsg msg = new GeneralAlertMsg(logList);
                channel.writeAndFlush(msg).addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        if (channelFuture.isSuccess()) {
                            deleteGeneralAlertList(logList);
                        }
                        schduleUploadTask(channel);
                    }
                });

            } catch (Throwable t) {
                logger.warn("上传Alert出错，请检查", t);
                schduleUploadTask(channel);
            }
        }
    }

    private void deleteGeneralAlertList(final List<GeneralAlert> alerts) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    alertService.deleteGeneralAlert(alerts);
                } catch (Throwable t) {
                    logger.warn("删除GeneralAlert出错，请检查", t);
                }
            }
        });
    }


}
