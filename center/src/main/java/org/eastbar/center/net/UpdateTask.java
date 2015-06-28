package org.eastbar.center.net;

import io.netty.channel.Channel;
import org.eastbar.codec.SiteReport;

import java.util.List;

/**
 * Created by AndySJTU on 2015/5/27.
 */
public class UpdateTask implements Runnable {
    private final Channel channel;


    public UpdateTask(Channel channel,List<SiteReport> siteReportList) {
        this.channel = channel;
    }

    @Override
    public void run() {
        //TODO
    }
}
