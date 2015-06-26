package org.eastbar.site;

import org.assertj.core.util.Maps;
import org.eastbar.loadb.DomainAndPort;
import org.eastbar.loadb.LoadbClient;
import org.eastbar.loadb.LoadbConnector;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by AndySJTU on 2015/6/19.
 */
@Component
public class Config implements InitializingBean {
    private Map<String, DomainAndPort> configs = Maps.newConcurrentHashMap();

    @Autowired
    private LoadbClient loadbClient;

    @Override
    public void afterPropertiesSet() throws Exception {


    }


    public DomainAndPort getDomainAndPort(String type){
        return null;
    }
}
