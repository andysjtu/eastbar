package org.eastbar.city;

import org.eastbar.city.center.HubConnector;
import org.eastbar.city.console.CityConsoleListener;
import org.eastbar.city.site.SiteListener;
import org.eastbar.net.Listener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by andysjtu on 2015/5/10.
 */
@Component
public class CityCenterServer {
    @Autowired
    private SiteListener siteListener;
    @Autowired
    private CityConsoleListener cityConsoleListener;
    @Autowired
    private CityCenter center;

    @Autowired
    private HubConnector connector;

    public void start() {
//        for (Listener listener : listeners) {
//            listener.listen();
//        }
        siteListener.listen();
        cityConsoleListener.listen();
        connector.connect();
    }

    public void stop() {

        siteListener.stopListen();
        cityConsoleListener.stopListen();
        connector.disconnect();

        center.disconnectAllSites();
    }

//    public List<Listener> getListeners() {
//        return listeners;
//    }
//
//    public void setListeners(List<Listener> listeners) {
//        this.listeners = listeners;
//    }

    public CityCenter getCenter() {
        return center;
    }

    public void setCenter(CityCenter center) {
        this.center = center;
    }

    public SiteListener getSiteListener() {
        return siteListener;
    }

    public void setSiteListener(SiteListener siteListener) {
        this.siteListener = siteListener;
    }

    public HubConnector getConnector() {
        return connector;
    }

    public void setConnector(HubConnector connector) {
        this.connector = connector;
    }
}
