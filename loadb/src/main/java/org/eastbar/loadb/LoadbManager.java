package org.eastbar.loadb;

import com.google.common.collect.Sets;
import org.assertj.core.util.Maps;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by AndySJTU on 2015/6/12.
 */
@Component
public class LoadbManager {

    private ServerLoad alertServerLoad;
    private ServerLoad logServerLoad;
    private ServerLoad statusServerLoad;
    private ServerLoad captureServerLoad;


    @PostConstruct
    public void init() throws IOException {
        Properties properties = PropertiesLoaderUtils.loadAllProperties("application-loadserver.properties");
        alertServerLoad = new ServerLoad(Sets.newHashSet(PropertiesUtil.getServerList(properties, "alert")));
        logServerLoad = new ServerLoad(Sets.newHashSet(PropertiesUtil.getServerList(properties, "log")));
        statusServerLoad = new ServerLoad(Sets.newHashSet(PropertiesUtil.getServerList(properties, "status")));
        captureServerLoad = new ServerLoad(Sets.newHashSet(PropertiesUtil.getServerList(properties, "capture")));
    }


    public DomainAndPort getLogServerAddr(String siteCode) {
        return logServerLoad.allocateServerAddress(siteCode);
    }

    public DomainAndPort getAlertServerAddr(String siteCode) {
        return alertServerLoad.allocateServerAddress(siteCode);
    }

    public DomainAndPort getCaptureServerAddr(String siteCode) {
        return captureServerLoad.allocateServerAddress(siteCode);
    }

    public DomainAndPort getStatusServerAddr(String siteCode) {
        return statusServerLoad.allocateServerAddress(siteCode);
    }
}
