package org.eastbar.center;

import com.google.common.collect.Maps;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import org.dozer.DozerBeanMapper;
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


    public void changeStatus(Status newStatus) {
        this.status = newStatus;
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
            CenterNotice notice = center.genNotice();
            SiteInitResp resp = new SiteInitResp(notice);
            channel.writeAndFlush(resp).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);

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
        version.setKwVersion(report.getKwVersion());
        version.setPrgVersion(report.getProgVersion());
        version.setSpVersion(report.getSmVersion());
        version.setUrlVersion(report.getUrlVersion());
    }

    public void close() {
        if (siteChannel != null) {
            siteChannel.close();
        }
    }

    public void setCenter(Center center) {
        this.center = center;
    }
}
