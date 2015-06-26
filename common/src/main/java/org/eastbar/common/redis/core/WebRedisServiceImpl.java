/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.common.redis.core;

import org.eastbar.common.redis.WebRedisService;
import org.eastbar.common.redis.util.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author cindy-jia
 * @date 2015年05月11
 * @time 上午10:30
 * @description :主要包含web端对redis的操作，从redis获取活动数据
 */

@Service
public class WebRedisServiceImpl implements WebRedisService {


    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据提供的监管中心编码获取一级监管中心的活动数据
     * @param monitorCode
     * @return
     */
    public Map getCeneterHash(String monitorCode){
        HashOperations<String,String,String> hashOperations=redisTemplate.opsForHash();
        if(monitorCode!=null){
            String key= RedisKey.createCenterKey(monitorCode);
            Map<String,String> map=hashOperations.entries(key);
            return map;
        }
        return null;
    }

    /**
     * 根据提供的监管中心编码获取二级监管中心的活动数据
     * @param monitorCode
     * @return
     */
    public  Map  getMonitorHash(String monitorCode){
        HashOperations<String,String,String> hashOperations=redisTemplate.opsForHash();
        if(monitorCode!=null && !"".equals(monitorCode)){
            String key= RedisKey.createMonitorKey(monitorCode);
            Map<String,String> map=hashOperations.entries(key);
            return map;
        }
        return null;
    }

    /**
     * 根据场所的场所编码获取场所的活动数据
     * @param siteCode
     * @return
     */
    public  Map getSiteHash(String siteCode){
        HashOperations<String,String,String> hashOperations=redisTemplate.opsForHash();
        if(siteCode!=null && !"".equals(siteCode)){
            String key= RedisKey.createSiteKey(siteCode);
            Map<String,String> map=hashOperations.entries(key);
            return map;
        }
        return null;
    }

    /**
     * 根据场所编码获取ip集
     * @param siteCode
     * @return
     */
    public Set getIpSet(String siteCode){
        SetOperations<String,String> setOperations=redisTemplate.opsForSet();
        if(siteCode!=null && !"".equals(siteCode)){
            String key= RedisKey.createSetTerminalKey(siteCode);
            Set<String> ips=setOperations.members(key);
            return ips;
        }
        return null;
    }

    /**
     *根据场所ip集，获取场所内所有终端活动信息
     * @param ips
     * @param siteCode
     * @return
     */
    public List getTerminalHash(Set<String> ips,String siteCode){
        HashOperations<String,String,String> hashOperations=redisTemplate.opsForHash();
        Map<String,String> map=new HashMap<String,String>();
        List<String> terminalLogs=new ArrayList<String>();
        if(ips.size()>0 && siteCode!=null && !"".equals(siteCode)){
            for(String ip:ips){
                map=hashOperations.entries(RedisKey.createTerminalKey(siteCode, ip));
                for(String key:map.keySet()){
                    terminalLogs.add(map.get(key));
                }
            }
            return terminalLogs;
        }
        return null;
    }

}
