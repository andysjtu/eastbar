package org.eastbar.center;

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

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

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


    public void initSite(SiteInitReq initReq, Channel channel) {
        SiteReport report = initReq.getSiteReport();
        String siteCode = report.getSiteCode();
        logger.info("siteCode is : "+siteCode);
        VSite vSite = sites.get(siteCode);
        if (vSite == null) {
            vSite = new VSite(siteCode);
            vSite.setCenter(this);
            sites.put(siteCode, vSite);
        }
        vSite.online(initReq, channel);
    }

    public CenterNotice genNotice() {

        CenterNotice notice = new CenterNotice();
        notice.setKwVersion(kwVersion);
        notice.setPgVersion(pgVersion);
        notice.setSpVersion(spVersion);
        notice.setUrlPolicyVersion(urlVersion);
        return notice;
    }

    public VSite getVSite(String siteCode){
        return sites.get(siteCode);
    }
}
