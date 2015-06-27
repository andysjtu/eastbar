package org.eastbar.city;

import org.eastbar.net.Listener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by andysjtu on 2015/5/10.
 */
@Component
public class CenterServer {
    @Autowired
    private List<Listener> listeners;
    @Autowired
    private Center center;

    @Autowired
    private HubConnector connector;

    public void start() {
        for (Listener listener : listeners) {
            listener.listen();
        }
        connector.connect();
    }

    public void stop() {
        for (Listener listener : listeners) {
            listener.stopListen();
        }
        connector.disconnect();

        center.disconnectAllSites();
    }

    public List<Listener> getListeners() {
        return listeners;
    }

    public void setListeners(List<Listener> listeners) {
        this.listeners = listeners;
    }

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }
}
