package org.eastbar.site;

import org.eastbar.comm.Listener;
import org.eastbar.site.biz.BizProxyConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by andysjtu on 2015/5/9.
 */
@Component
public class SiteServer {

    @Autowired
    private BizProxyConnector bizProxyConnector;
    @Autowired
    private List<Listener> listeners;

    @Autowired
    private List<Connector> connectors;

    @Autowired
    private Site site;
    @Autowired
    private LogUploader uploader;

    public void start() {
        bizProxyConnector.connect();
        for (Connector connector : connectors) {
            connector.connect();
        }
        for (Listener listener : listeners) {
            listener.listen();
        }
        uploader.start();
    }

    public void stop() {
        for (Connector con : connectors) {
            con.disconnect();
        }

        for (Listener listener : listeners) {
            listener.stopListen();
        }
        site.disconnectAll();
    }

    public Site getSite() {
        return site;
    }
}


