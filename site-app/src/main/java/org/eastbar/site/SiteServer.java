package org.eastbar.site;

import org.eastbar.site.alert.AlertdConnector;
import org.eastbar.site.biz.BizProxyConnector;
import org.eastbar.site.city.CityConnector;
import org.eastbar.site.client.ClientListener;
import org.eastbar.site.client.PolicyDldListener;
import org.eastbar.site.console.ConsoleListener;
import org.eastbar.site.log.LogUploader;
import org.eastbar.site.log.LogdConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private CityConnector centerConnector;
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
        System.out.println("cenllllllllll");
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


