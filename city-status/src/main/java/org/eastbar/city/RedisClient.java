package org.eastbar.city;

import org.apache.commons.lang3.StringUtils;
import org.eastbar.center.strategy.SiteStrategyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andyliang on 6/26/15.
 */
@Component
public class RedisClient {
    @Autowired
    private SiteStrategyEvent siteStrategyEvent;

    public void testGetPolicyVersion(){
        String sitecode = "3101011001";
        int version=0;
        for(int i=0;;i++)
        try {
            String urlPolicyStr = siteStrategyEvent.returnSpecialCustomerList(sitecode,i);
            System.out.println("urlPolicyStr is "+urlPolicyStr);
            if(StringUtils.trimToNull(urlPolicyStr)==null)break;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






}
