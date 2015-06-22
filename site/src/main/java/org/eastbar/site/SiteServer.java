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
    private ClientListener clientListener;
    @Autowired
    private PolicyDldListener policyDldListener;
    @Autowired
    private ConsoleListener consoleListener;

    @Autowired
    private CenterConnector centerConnector;
    @Autowired
    private CaptureConnector captureConnector;
    @Autowired
    private LogdConnector logdConnector;
    @Autowired
    private AlertdConnector alertdConnector;

    @Autowired
    private Site site;
    @Autowired
    private LogUploader uploader;

    public void start() {
        bizProxyConnector.connect();
        centerConnector.connect();
        captureConnector.connect();

        policyDldListener.listen();
        clientListener.listen();
        consoleListener.listen();

        logdConnector.connect();
        alertdConnector.connect();

        uploader.start();
    }

    public void stop() {
        bizProxyConnector.disconnect();
        centerConnector.disconnect();
        captureConnector.disconnect();

        policyDldListener.stopListen();
        clientListener.stopListen();
        consoleListener.stopListen();

        logdConnector.disconnect();
        alertdConnector.disconnect();

        uploader.stop();
        site.disconnectAll();
    }

    public Site getSite() {
        return site;
    }
}


