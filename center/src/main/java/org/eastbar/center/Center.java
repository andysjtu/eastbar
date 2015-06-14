package org.eastbar.center;

import com.google.common.collect.Lists;
import com.sun.xml.internal.xsom.impl.Ref;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.util.ReferenceCountUtil;
import org.assertj.core.util.Maps;
import org.eastbar.codec.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by andysjtu on 2015/5/10.
 */
@Component
public class Center {

    public final static Logger logger = LoggerFactory.getLogger(Center.class);

    private Map<String, VSite> sites = Maps.newConcurrentHashMap();

    @Autowired
    private HubConnector connector;

    public void disconnectAllSites() {
        Collection<VSite> channelList = sites.values();
        for (VSite vsite : channelList) {
            vsite.close();
        }
    }

    public String getReportString() {
        StringBuilder builder = new StringBuilder();
        Iterator<Map.Entry<String, VSite>> it = Collections.unmodifiableSet(sites.entrySet()).iterator();
        while (it.hasNext()) {
            Map.Entry<String, VSite> entry = it.next();
            builder.append("siteCode=").append(entry.getKey()).append(": \n");
        }
        return builder.toString();
    }


    public void initSite(SiteInitReq initReq, Channel channel) {
        SiteReport report = initReq.getSiteReport();
        String siteCode = report.getSiteCode();
        VSite vSite = sites.get(siteCode);
        if (vSite == null) {
            vSite = new VSite(siteCode);
            vSite.setCenter(this);
            sites.put(siteCode, vSite);
        }
        vSite.online(initReq, channel);
//        Channel hubChannel = connector.channel();

//        if (hubChannel != null && hubChannel.isActive()) {
//            Map<SiteReport, List<TermReport>> reportListMap = Maps.newHashMap();
//            reportListMap.put(report, initReq.getTermReport());
//            CenterInitReq centerInitReq = new CenterInitReq(reportListMap);
//            hubChannel.writeAndFlush(centerInitReq);
//        }
    }


    public VSite getVSite(String siteCode) {
        return sites.get(siteCode);
    }

    public List<SiteReport> getSiteReports() {
        List<SiteReport> siteReports = Lists.newArrayList();
        Collection<VSite> vSites = Collections.unmodifiableCollection(sites.values());
        for (VSite vSite : vSites) {
            SiteReport report = vSite.getSiteReport();
            siteReports.add(report);
        }
        return siteReports;
    }

    public void updateTermStatus(TermReport reportMsg) {
        String siteCode = reportMsg.getSiteCode();
        VSite vSite = getVSite(siteCode);
        vSite.updateTermStatus(reportMsg);
    }

    public void updateTermStatus(List<TermReport> reportMsg) {
        for (TermReport termReport : reportMsg) {
            updateTermStatus(termReport);
        }
    }

    public void updateSitePolicyStatus(SiteReport msg) {
        String siteCode = msg.getSiteCode();
        VSite vSite = getVSite(siteCode);
        if(vSite==null){
            vSite = new VSite(siteCode);
            vSite.setCenter(this);
            sites.put(siteCode,vSite);
        }
        vSite.updatePolicyStatus(msg);
    }

    public void notifyTermEvent(List<TermReport> termReport) {
        Channel channel = connector.channel();
        if (channel != null && channel.isActive()) {
            TermReportMsg termReportMsg = new TermReportMsg(Lists.newArrayList(termReport));
            Channel hubChannel = connector.channel();
            if (hubChannel != null && hubChannel.isActive()) {
                hubChannel.writeAndFlush(termReportMsg);
            }
        }
    }

    public void notifyTermEvent(TermReport termReport) {
        notifyTermEvent(Lists.newArrayList(termReport));
    }

    public List<TermReport> getTermReports() {
        List<TermReport> termReports = Lists.newArrayList();
        Collection<VSite> vSites = Collections.unmodifiableCollection(sites.values());
        for (VSite vSite : vSites) {
            List<TermReport> reports = vSite.getTermReport();
            termReports.addAll(reports);
        }
        return termReports;
    }

    public Map<SiteReport, List<TermReport>> getSiteTermReports() {
        Map<SiteReport, List<TermReport>> result = Maps.newHashMap();
        Collection<VSite> vSites = Collections.unmodifiableCollection(sites.values());
        for (VSite vSite : vSites) {
            SiteReport siteReport = vSite.getSiteReport();
            List<TermReport> termReports = vSite.getTermReport();
            result.put(siteReport, termReports);
        }
        logger.info("_--------------------------------------------------------");
        logger.info("result is : " + JsonUtil.toJson(result));
        logger.info("_--------------------------------------------------------");
        return result;

    }

    public void notifySiteOnline(SiteReport siteReport, List<TermReport> termReport) {
        Map<SiteReport,List<TermReport>> maps = Maps.newHashMap();
        maps.put(siteReport, termReport);
        Channel channel = connector.channel();
        if(channel!=null&&channel.isActive()){
            //FIXME
            channel.writeAndFlush(new CenterInitReq(maps));
        }
    }

    public void notifySiteOffline(SiteReport siteReport, List<TermReport> termReport) {
        Map<SiteReport,List<TermReport>> maps = Maps.newHashMap();
        maps.put(siteReport, termReport);
        Channel channel = connector.channel();
        if(channel!=null&&channel.isActive()){
            //FIXME
            channel.writeAndFlush(new CenterInitReq(maps));
        }
    }
}
