package org.eastbar.city;

import org.apache.commons.lang3.StringUtils;
import org.eastbar.centers.strategy.SiteStrategyEvent;
import org.eastbar.common.redis.SiteRedisService;
import org.eastbar.common.redis.util.Strategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by andyliang on 6/26/15.
 */
@Component
public class RedisClient {
    @Autowired
    private SiteStrategyEvent siteStrategyEvent;

    public void testGetPolicyVersion(){
        String sitecode = "3102011001";
        int version=0;
        for(int i=0;;i++)
        try {
            String urlPolicyStr = siteStrategyEvent.returnUrlList(sitecode,i);
            System.out.println("urlPolicyStr is "+urlPolicyStr);
            if(StringUtils.trimToNull(urlPolicyStr)==null)break;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






}
