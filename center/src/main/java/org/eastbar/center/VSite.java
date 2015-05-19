package org.eastbar.center;

import com.google.common.collect.Maps;
import io.netty.channel.Channel;
import org.eastbar.codec.Status;

import java.util.Map;

/**
 * Created by andysjtu on 2015/5/12.
 */
public class VSite {
    private final String siteCode;

    private String siteIP;

    private PolicyVersion version = new PolicyVersion();

    private volatile Channel siteChannel;

    private Map<String, VTerminal> terminalMap = Maps.newConcurrentMap();

    private volatile Status status = Status.OFFLINE;

    public VSite(String siteCode) {
        this.siteCode = siteCode;
    }


    public void changeStatus(Status newStatus) {
        this.status = newStatus;
    }

    public void online() {
        changeStatus(Status.ONLINE);
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


}
