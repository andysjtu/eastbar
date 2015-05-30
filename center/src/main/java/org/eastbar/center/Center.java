package org.eastbar.center;

import com.google.common.collect.Lists;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import org.assertj.core.util.Maps;
import org.eastbar.codec.CenterNotice;
import org.eastbar.codec.SiteInitReq;
import org.eastbar.codec.SiteReport;
import org.eastbar.codec.SocketMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by andysjtu on 2015/5/10.
 */
@Component
public class Center {

    public final static Logger logger = LoggerFactory.getLogger(Center.class);

    private Map<String, VSite> sites = Maps.newConcurrentHashMap();

    private volatile int urlVersion = 1;
    private volatile int pgVersion = 1;
    private volatile int spVersion = 1;
    private volatile int kwVersion = 1;

    public void disconnectAllSites() {
        Collection<VSite> channelList = sites.values();
        for (VSite vsite : channelList) {
            vsite.close();
        }
    }

    public String getReportString() {
        StringBuilder builder = new StringBuilder();
        builder.append("urlVersion=").append(urlVersion).append("\n");
        builder.append("pgVersion=").append(pgVersion).append("\n");
        builder.append("spVersion=").append(spVersion).append("\n");
        builder.append("kwVersion=").append(kwVersion).append("\n");
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
        logger.info("siteCode is : " + siteCode);
        VSite vSite = sites.get(siteCode);
        if (vSite == null) {
            vSite = new VSite(siteCode);
            vSite.setCenter(this);
            sites.put(siteCode, vSite);
        }
        vSite.online(initReq, channel);
    }



    public VSite getVSite(String siteCode) {
        return sites.get(siteCode);
    }

    public List<SiteReport> getSiteReports() {
        List<SiteReport> siteReports = Lists.newArrayList();
        Collection<VSite> vSites = Collections.unmodifiableCollection(sites.values());
        for(VSite vSite:vSites){
            SiteReport report = vSite.getSiteReport();
            siteReports.add(report);
        }
        return siteReports;
    }
}
