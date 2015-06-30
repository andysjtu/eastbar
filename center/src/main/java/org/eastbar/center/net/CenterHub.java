package org.eastbar.center.net;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelPipeline;
import org.eastbar.center.statusMachine.EventUtil;
import org.eastbar.center.statusMachine.HostEvent;
import org.eastbar.center.statusMachine.IEventPipe;
import org.eastbar.center.statusMachine.ResetEvent;
import org.eastbar.codec.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by AndySJTU on 2015/5/27.
 */
@Component
public class CenterHub {
    public final static org.slf4j.Logger logger = LoggerFactory.getLogger(CenterHub.class);


    public ExecutorService checkUpdateService = Executors.newFixedThreadPool(1);

    private Map<Channel, List<SiteReport>> centerChannels = Maps.newConcurrentMap();

    private Map<SiteReport, List<TermReport>> siteTermMaps = Maps.newConcurrentMap();

    @Autowired
    private IEventPipe eventPipe;

    public void check() {
        checkUpdateService.submit(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

//    public void updateUrlPolicy(Channel channel, String siteCode, int newVersion) {
//        List<SiteReport> reports = centerChannels.get(channel);
//        for (SiteReport siteReport : reports) {
//            if (siteCode.equals(siteReport.getSiteCode())) {
//                siteReport.setUrlVersion(newVersion);
//            }
//        }
//    }
//
//    public void updateKwPolicy(Channel channel, String siteCode, int newVersion) {
//        List<SiteReport> reports = centerChannels.get(channel);
//        for (SiteReport siteReport : reports) {
//            if (siteCode.equals(siteReport.getSiteCode())) {
//                siteReport.setKwVersion(newVersion);
//            }
//        }
//    }
//
//    public void updateSmPolicy(Channel channel, String siteCode, int newVersion) {
//        List<SiteReport> reports = centerChannels.get(channel);
//        for (SiteReport siteReport : reports) {
//            if (siteCode.equals(siteReport.getSiteCode())) {
//                siteReport.setSmVersion(newVersion);
//            }
//        }
//    }
//
//    public void updatePrgPolicy(Channel channel, String siteCode, int newVersion) {
//        List<SiteReport> reports = centerChannels.get(channel);
//        for (SiteReport siteReport : reports) {
//            if (siteCode.equals(siteReport.getSiteCode())) {
//                siteReport.setPrgVersion(newVersion);
//            }
//        }
//    }


    public void registerCenter(Channel channel, CenterInitReq initReq) {
        Map<SiteReport, List<TermReport>> reportListMap = initReq.getSiteTermReports();
        centerChannels.put(channel, Lists.newArrayList(reportListMap.keySet().iterator()));
        Set<SiteReport> reportSet = reportListMap.keySet();
        for (SiteReport siteReport : reportSet) {
            siteTermMaps.put(siteReport, reportListMap.get(siteReport));
            List<TermReport> termReports = reportListMap.get(siteReport);
            for (TermReport termReport : termReports) {
                HostEvent hostEvent = EventUtil.convertFromTermReport(termReport);
                eventPipe.addEvents(hostEvent);
            }
        }

        channel.closeFuture().addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                unregisterCenter(future.channel());
            }
        });

        printStatus();
    }

    public void unregisterCenter(Channel channel) {
        List<SiteReport> siteReports = centerChannels.remove(channel);
        if (siteReports != null) {
            for (SiteReport siteReport : siteReports) {
                ResetEvent resetEvent = new ResetEvent();
                resetEvent.setSiteCode(siteReport.getSiteCode());
                eventPipe.addEvents(resetEvent);
            }
        }

        printStatus();
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

    public int unlockScreen(String siteCode, String hostIp) {
        return operate(OPERATE_METHOD.UNLOCK, siteCode, hostIp);
    }

    public int shutdown(String siteCode, String hostIp) {
        return operate(OPERATE_METHOD.SHUTDOWN, siteCode, hostIp);
    }

    public int restart(String siteCode, String hostIp) {
        return operate(OPERATE_METHOD.RESTART, siteCode, hostIp);
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

    public void updateSiteStatus(SiteInitReq req, Channel channel) {
        SiteReport siteReport = req.getSiteReport();
        List<TermReport> termReports = req.getTermReport();
        siteTermMaps.put(siteReport, termReports);
        List<SiteReport> siteReports = centerChannels.get(channel);
        if (siteReports == null) {
            siteReports = Lists.newArrayList();
            centerChannels.put(channel, siteReports);
        }
        siteReports.add(siteReport);
        //submit event
        if (siteReport.isConnected()) {
            for (TermReport termReport : termReports) {
                HostEvent hostEvent = EventUtil.convertFromTermReport(termReport);
                eventPipe.addEvents(hostEvent);
            }
        } else {
            ResetEvent resetEvent = new ResetEvent();
            resetEvent.setSiteCode(siteReport.getSiteCode());
            eventPipe.addEvents(resetEvent);
        }
        printStatus();
    }

    public void updateTermStatus(List<TermReport> termReportList, Channel channel) {
        for (TermReport termReport : termReportList) {
            updateTermStatus(termReport, channel);
        }
        printStatus();
    }

    private void printStatus() {
        logger.info("status is : {} ", siteTermMaps);
        logger.info("channelMap is {}", centerChannels);

    }

    /**
     * site conn
     *
     * @param termReport
     * @param channel
     */
    public void updateTermStatus(TermReport termReport, Channel channel) {
        List<SiteReport> siteReports = centerChannels.get(channel);

        outer:
        for (SiteReport siteReport : siteReports) {
            if (siteReport.getSiteCode().equals(termReport.getSiteCode())) {
                List<TermReport> termReports = siteTermMaps.get(siteReport);
                for (TermReport t : termReports) {
                    if (t.getIp().equals(termReport.getIp())) {
                        t.changeStatus(termReport);
                        logger.info("break outer");
                        break outer;
                    }
                }
            }
        }
        HostEvent hostEvent = EventUtil.convertFromTermReport(termReport);
        eventPipe.addEvents(hostEvent);
        printStatus();
    }

    /**
     * site disconnect
     *
     * @param req
     * @param channel
     */
    public void unregisterSite(SiteDiscReq req, Channel channel) {
        SiteReport siteReport = req.getSiteReport();
        siteTermMaps.remove(siteReport);

        List<SiteReport> siteReports = centerChannels.get(channel);
        if (siteReports != null) {
            siteReports.remove(siteReport);
        }

        ResetEvent resetEvent = new ResetEvent();
        resetEvent.setSiteCode(siteReport.getSiteCode());
        eventPipe.addEvents(resetEvent);

    }

    public Map<Channel, List<SiteReport>> getCenterChannels() {
        return centerChannels;
    }

    public void setCenterChannels(Map<Channel, List<SiteReport>> centerChannels) {
        this.centerChannels = centerChannels;
    }

    public Map<SiteReport, List<TermReport>> getSiteTermMaps() {
        return siteTermMaps;
    }

    public void setSiteTermMaps(Map<SiteReport, List<TermReport>> siteTermMaps) {
        this.siteTermMaps = siteTermMaps;
    }

    public static enum OPERATE_METHOD {
        LOCK, UNLOCK, SHUTDOWN, RESTART;
    }
}
