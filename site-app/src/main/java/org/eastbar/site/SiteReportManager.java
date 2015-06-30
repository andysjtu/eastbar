package org.eastbar.site;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.util.internal.ConcurrentSet;
import org.eastbar.codec.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by AndySJTU on 2015/6/10.
 */
@Component
public class SiteReportManager {
    private ConcurrentSet<Channel> channels = new ConcurrentSet<>();

    @Autowired
    private Site site;

    public void registerChannel(Channel channel) {
        channels.add(channel);
        channel.closeFuture().addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                channels.remove(future.channel());
            }
        });
        reportWholeSiteStatus(channel);
    }

    private void reportWholeSiteStatus(Channel channel) {
//        SiteReport siteReport = site.getSiteReport();
//        List<TermReport> termReports = site.getTermReportList();
//        SiteReportMsg siteReportMsg = new SiteReportMsg(siteReport);
//        channel.writeAndFlush(siteReportMsg);
//        TermReportMsg termReportMsg = new TermReportMsg(termReports);
//        channel.writeAndFlush(termReportMsg);
        SiteReport siteReport = site.getSiteReport();
        List<TermReport> termReports = site.getTermReportList();
        SiteInitReq msg = new SiteInitReq(siteReport,termReports);
        channel.writeAndFlush(msg);
    }

    public void report(TermReport termReport) {
        Iterator<Channel> iterator = channels.iterator();
        while (iterator.hasNext()) {
            TermReportMsg msg = new TermReportMsg(Lists.newArrayList(termReport));
            Channel channel = iterator.next();
            channel.writeAndFlush(msg);
        }
    }
}
