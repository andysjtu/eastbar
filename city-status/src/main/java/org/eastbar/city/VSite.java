package org.eastbar.city;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import org.eastbar.codec.*;
import org.eastbar.codec.policy.PolicyUpdateMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
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

    private volatile boolean connected = false;

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
        report.setConnected(connected);
        DozerUtil.copyProperties(version, report);
        return report;
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
            connected = true;
            siteChannel.closeFuture().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    siteOffline();
                }
            });
            init(req.getSiteReport(), req.getTermReport());
            center.notifySiteOffline(getSiteReport(), getTermReport());
        } else {
            logger.warn("不允许重复siteCode :{}上报,关闭通道", siteCode);
            channel.close();
        }

    }




    public void siteOffline() {
        connected = false;
        siteChannel = null;
        disAllTerm();
        center.notifySiteOffline(getSiteReport(),getTermReport());
    }

    private void disAllTerm() {
        Collection<VTerminal> vTerminals =terminalMap.values();
        for(VTerminal vt : vTerminals){
            vt.setConnected(false);
        }
    }

    public void init(SiteReport report, List<TermReport> termReportLis) {
        initPolicyVersion(report);
        initTerminalStatus(termReportLis);

    }

    private void initTerminalStatus(List<TermReport> termReportLis) {
        Map<String, VTerminal> newTerminalMap = Maps.newConcurrentMap();
        for (TermReport tr : termReportLis) {
            String ip = tr.getIp();
            VTerminal vt = new VTerminal(ip, tr.getSiteCode());
            DozerUtil.copyProperties(tr, vt);
            newTerminalMap.put(ip,vt);
        }
        terminalMap.putAll(newTerminalMap);
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


    public void updateTermStatus(TermReport reportMsg) {
        String hostIp = reportMsg.getIp();
        VTerminal vTerminal = terminalMap.get(hostIp);
        if (vTerminal == null) {
            vTerminal = new VTerminal(hostIp, siteCode);
            terminalMap.put(hostIp, vTerminal);
        }
        DozerUtil.copyProperties(reportMsg, vTerminal);
        center.notifyTermEvent(reportMsg);
    }

    public void updatePolicyStatus(SiteReport msg) {
        this.version.setKwVersion(msg.getKwVersion());
        version.setPrgVersion(msg.getPrgVersion());
        version.setSmVersion(msg.getSmVersion());
        version.setUrlVersion(msg.getUrlVersion());

        center.registerPolicyUpdate(this);
    }

    public void updateSitePolicy(PolicyUpdateMsg.POLICY_TYPE type,String policyStr){
        PolicyUpdateMsg msg = new PolicyUpdateMsg(type,policyStr.getBytes(Charsets.UTF_8));
        if(siteChannel!=null&&siteChannel.isActive()){
            siteChannel.writeAndFlush(msg);
        }
    }

    public List<TermReport> getTermReport() {
        List<TermReport> termReports = Lists.newArrayList();
        Collection<VTerminal> vTerminalCollection = Collections.unmodifiableCollection(terminalMap.values());
        for (VTerminal vt : vTerminalCollection) {
            TermReport report = new TermReport();
            report.setIp(vt.getTerminalIP());
            report.setSiteCode(vt.getSiteCode());
            DozerUtil.copyProperties(vt, report);
            termReports.add(report);
        }
        return termReports;

    }

    public PolicyVersion getVersion(){
        return version;
    }

    public String getSiteCode() {
        return siteCode;
    }
}
