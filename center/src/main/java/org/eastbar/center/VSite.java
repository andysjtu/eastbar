package org.eastbar.center;

import com.google.common.collect.Maps;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import org.eastbar.codec.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by andysjtu on 2015/5/12.
 */
public class VSite {
    public final static Logger logger = LoggerFactory.getLogger(VSite.class);

    private final String siteCode;

    private String siteIP;

    private PolicyVersion version = new PolicyVersion();

    private volatile Channel siteChannel;

    private Map<String, VTerminal> terminalMap = Maps.newConcurrentMap();

    private volatile Status status = Status.OFFLINE;
    private Center center;

    public VSite(String siteCode) {
        this.siteCode = siteCode;
    }

    private void sendErrorResponse(Channel respTargetChannel, short messageId, short messageType) {
        GenResp resp = new GenResp.S2CenterGenResp(messageId, messageType, GenResp.Status.Failure);
        respTargetChannel.writeAndFlush(resp);
    }

    public SiteReport getSiteReport() {
        SiteReport report = new SiteReport();
        report.setSiteCode(siteCode);
        DozerUtil.copyProperties(version, report);
        return report;
    }

    public void changeStatus(Status newStatus) {
        this.status = newStatus;
    }

    public void redirect(final SocketMsg msg, final Channel respChannel) {
        if (siteChannel != null && siteChannel.isActive()) {
            siteChannel.writeAndFlush(msg).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (!future.isSuccess()) {
                        logger.warn("发送命令失败", future.cause());
                        sendErrorResponse(respChannel, msg.getMessageId(), msg.getMessageType());
                    }
                }
            });
            ProxyChannelHandler handler = (ProxyChannelHandler) siteChannel.pipeline().get(ProxyChannelHandler.HANDLER_NAME);
            if (handler != null) {
                handler.registerTarget(msg.getMessageId(), msg.getMessageType(), respChannel);
            } else {
                logger.warn("没有找到ProxyHandler，请检查");
            }
        } else {
            try {
                logger.warn("SiteChannel没有建立，返回失败操作");
                sendErrorResponse(respChannel, msg.getMessageId(), msg.getMessageType());
            } finally {
                msg.release();
            }
        }
    }

    public void online(SiteInitReq req, Channel channel) {
        if (this.siteChannel == null) {
            this.siteChannel = channel;
            siteChannel.closeFuture().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    siteChannel = null;
                }
            });
            changeStatus(Status.ONLINE);
            init(req.getSiteReport());
            //TODO send success resp

//            CenterNotice notice = center.genNotice();
//            SiteInitResp resp = new SiteInitResp(notice);
//            channel.writeAndFlush(resp).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);

        } else {
            logger.warn("不允许重复siteCode :{}上报,关闭通道", siteCode);
            channel.close();
        }

    }

    public void offline() {
        changeStatus(Status.OFFLINE);
    }


    public void siteOnline() {

    }

    public void siteOffline() {
        status = Status.OFFLINE;
        siteChannel = null;
    }

    public void init(SiteReport report) {
        initPolicyVersion(report);
        initTerminalStatus(report);

    }

    private void initTerminalStatus(SiteReport reportt) {
        List<TermReport> termReportLis = reportt.getTermReportList();
        Map<String, VTerminal> newTerminalMap = Maps.newConcurrentMap();
        for (TermReport tr : termReportLis) {
            String ip = tr.getHostIp();
            VTerminal vt = new VTerminal(ip, reportt.getSiteCode());
            initVTerminal(vt, tr);
        }
        terminalMap.clear();
        terminalMap.putAll(newTerminalMap);
    }

    private void initVTerminal(VTerminal vt, TermReport tr) {
        BeanUtil.copyBeanProperties(tr, vt);
        vt.setStatus(VTerminal.Status.online);
    }

    private void initPolicyVersion(SiteReport report) {
        DozerUtil.copyProperties(report, version);
    }

    public void close() {
        if (siteChannel != null) {
            siteChannel.close();
        }
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    public boolean isConnected() {

        if (siteChannel != null && siteChannel.isActive()) {
            return true;
        }
        return false;
    }


}
