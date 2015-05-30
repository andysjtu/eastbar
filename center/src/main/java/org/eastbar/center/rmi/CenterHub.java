package org.eastbar.center.rmi;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelPipeline;
import org.eastbar.codec.*;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by AndySJTU on 2015/5/27.
 */
@Component
public class CenterHub {

    public ExecutorService checkUpdateService = Executors.newFixedThreadPool(1);

    private Map<Channel, List<SiteReport>> centerChannels = Maps.newConcurrentMap();

    public void check(){
        checkUpdateService.submit(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    public void updateUrlPolicy(Channel channel,String siteCode,int newVersion){
        List<SiteReport> reports = centerChannels.get(channel);
        for(SiteReport siteReport:reports){
            if(siteCode.equals(siteReport.getSiteCode())){
                siteReport.setUrlVersion(newVersion);
            }
        }
    }

    public void updateKwPolicy(Channel channel,String siteCode,int newVersion){
        List<SiteReport> reports = centerChannels.get(channel);
        for(SiteReport siteReport:reports){
            if(siteCode.equals(siteReport.getSiteCode())){
                siteReport.setKwVersion(newVersion);
            }
        }
    }

    public void updateSmPolicy(Channel channel,String siteCode,int newVersion){
        List<SiteReport> reports = centerChannels.get(channel);
        for(SiteReport siteReport:reports){
            if(siteCode.equals(siteReport.getSiteCode())){
                siteReport.setSmVersion(newVersion);
            }
        }
    }

    public void updatePrgPolicy(Channel channel,String siteCode,int newVersion){
        List<SiteReport> reports = centerChannels.get(channel);
        for(SiteReport siteReport:reports){
            if(siteCode.equals(siteReport.getSiteCode())){
                siteReport.setPrgVersion(newVersion);
            }
        }
    }

    public void undateSite(Channel channel,TermReport report){

    }

    public void registerSite(Channel channel,SiteReport site){
        //TODO
    }

    public void unregisterSite(Channel channel,String siteCode){
        //TODO
    }

    public void registerCenter(Channel channel, CenterInitReq initReq) {
        List<SiteReport> siteReportList = initReq.getSiteReportList();
        centerChannels.put(channel, siteReportList);
        channel.closeFuture().addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                unregisterCenter(future.channel());
            }
        });
    }

    public void unregisterCenter(Channel channel) {
        centerChannels.remove(channel);
    }

    public Channel getCenterChannelBySiteCode(String siteCode) {
        Preconditions.checkNotNull(siteCode, "SiteCode cannot be null");
        Map<Channel, List<SiteReport>> channelListMap = Collections.unmodifiableMap(centerChannels);
        Iterator<Channel> cit = channelListMap.keySet().iterator();
        while (cit.hasNext()) {
            Channel channel = cit.next();
            List<SiteReport> siteReportList = channelListMap.get(channel);
            for (SiteReport siteReport : siteReportList) {
                if (siteCode.equals(siteReport.getSiteCode())) {
                    return channel;
                }
            }
        }
        return null;
    }

    private SocketMsg createOperateSocketMsg(OPERATE_METHOD method, String siteCode, String hostIp) {
        SocketMsg msg = null;
        switch (method) {
            case LOCK:
                msg = new CenterCmd.LockClientCmd(siteCode, hostIp);
                break;
            case UNLOCK:
                msg = new CenterCmd.UnlockClientCmd(siteCode, hostIp);
                break;
            case SHUTDOWN:
                msg = new CenterCmd.ShutdownClientCmd(siteCode, hostIp);
                break;
            default:
                throw new RuntimeException("NOT SUPPORT");
        }
        return msg;
    }

    private int operate(OPERATE_METHOD method, String siteCode, String hostIp) {
        Channel channel = getCenterChannelBySiteCode(siteCode);
        if (channel != null && channel.isActive()) {
            final CountDownLatch latch = new CountDownLatch(1);
            final SocketMsg msg = createOperateSocketMsg(method, siteCode, hostIp);
            final CenterCmdRespHandler handler = getCenterCmdRespHandler(channel);
            sendCmdAndWait(channel, latch, msg, handler);
            CenterCmdRespHandler.ResultWrapper obj = handler.getResult(msg.getMessageId(), msg.getMessageType());
            if (obj != null) {
                if (obj.getResultType() == 1) {
                    return ((GenResp.Status) obj.getResult()).byteValue();
                }
            }
        }
        return 1;
    }

    public int lockScreen(String siteCode, String hostIp) {
        return operate(OPERATE_METHOD.LOCK, siteCode, hostIp);
    }

    private void sendCmdAndWait(Channel channel, final CountDownLatch latch, final SocketMsg msg, final CenterCmdRespHandler handler) {
        handler.register(msg.getMessageId(), msg.getMessageType(), latch);
        channel.writeAndFlush(msg).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (!future.isSuccess()) {
                    handler.unregister(msg.getMessageId(), msg.getMessageType());
                    latch.countDown();
                }
            }
        });
        try {
            latch.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            handler.unregister(msg.getMessageId(), msg.getMessageType());
            throw new RuntimeException(e.getMessage());
        }
    }

    private CenterCmdRespHandler getCenterCmdRespHandler(Channel channel) {
        ChannelPipeline pipeline = channel.pipeline();
        CenterCmdRespHandler centerCmdRespHandler = (CenterCmdRespHandler) pipeline.get(CenterCmdRespHandler.DEFAULT_HANDLER_NAME);
        if (centerCmdRespHandler == null) {
            centerCmdRespHandler = new CenterCmdRespHandler();
            pipeline.addLast(CenterCmdRespHandler.DEFAULT_HANDLER_NAME, centerCmdRespHandler);
        }
        return centerCmdRespHandler;
    }

    public byte[] capture(String siteCode, String hostIp) {
        Channel channel = getCenterChannelBySiteCode(siteCode);
        if (channel != null && channel.isActive()) {
            final CountDownLatch latch = new CountDownLatch(1);
            final SocketMsg msg = new CenterCmd.CaptureClientCmd(siteCode, hostIp);
            final CenterCmdRespHandler handler = getCenterCmdRespHandler(channel);

            sendCmdAndWait(channel, latch, msg, handler);
            CenterCmdRespHandler.ResultWrapper obj = handler.getResult(msg.getMessageId(), msg.getMessageType());
            if (obj != null) {
                if (obj.getResultType() == 2) {
                    return (byte[]) obj.getResult();
                }
            }
        }
        throw new RuntimeException("FAILURE");
    }

    public static enum OPERATE_METHOD {
        LOCK, UNLOCK, SHUTDOWN;
    }
}
