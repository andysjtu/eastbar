/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.strategy.core;

import org.eastbar.center.strategy.dao.SiteDao;
import org.eastbar.center.strategy.CenterStrategyEvent;
import org.eastbar.center.strategy.service.ManageRuleService;
import org.eastbar.common.redis.SiteRedisService;
import org.eastbar.common.redis.util.Strategy;
import org.eastbar.common.rmi.RmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author cindy-jia
 * @date 2015年05月29
 * @time 上午10:11
 * @description :
 */
@Component
public class CenterStrategyEventImpl implements CenterStrategyEvent {

    @Autowired
    private SiteRedisService siteRedisService;

    @Autowired
    private ManageRuleService manageRuleService;

    @Autowired
    private SiteDao siteDao;

    @Autowired
    private RmiService rmiService;

    @Override
    public int sendSpecialCustomerVersion(int version) {
        return rmiService.sendSpecialCustomerVersion(version);
    }

    @Override
    public int sendKeyWordVersion(int version) {
        return rmiService.sendKeyWordVersion(version);
    }

    @Override
    public int sendBannedUrlVersion(int version) {
        return rmiService.sendBannedUrlVersion(version);
    }

    @Override
    public int sendBannedProgVersion(int version) {
        return rmiService.sendBannedProgVersion(version);
    }

    //定期检查redis的版本是否是最新，需要更新就拉取列表
    public int regularFreshProgVersion() throws Exception{
        int result=0;
        //version是redis已更新的prog的最新版本号
        String version=siteRedisService.returnUpdatedVersion(Strategy.BANNEDPROG);
        Integer redisVersion=0;
        if(version!=null && !"".equals(version)){
            redisVersion=Integer.parseInt(version);
        }
        //TODO 从数据库获取对应的最大版本号
        Map<String,Object> map=manageRuleService.getAll();
        //从数据库获取prog当前最新的版本号
        Integer progVersion=Integer.parseInt(map.get(Strategy.BANNEDPROG)+"");
        if(progVersion>redisVersion){//如果数据库的最新版本号大于redis最新版本号，则进行更新
            result=rmiService.sendBannedProgVersion(progVersion);
        }else{//否则不更新
            result=-1;
        }
        return result;
    }

    @Override
    public int regularFreshUrlVersion() throws Exception {
        int result=0;
        //version是redis已更新的url的最新版本号
        String version=siteRedisService.returnUpdatedVersion(Strategy.BANNEDURL);
        Integer redisVersion=0;
        if(version!=null && !"".equals(version)){
            redisVersion=Integer.parseInt(version);
        }
        //TODO 从数据库获取对应的最大版本号
        Map<String,Object> map=manageRuleService.getAll();
        //从数据库获取url当前最新的版本号
        Integer urlVersion=Integer.parseInt(map.get(Strategy.BANNEDURL)+"");
        if(urlVersion>redisVersion){//如果数据库的最新版本号大于redis的最新版本号，则进行更新
            result=rmiService.sendBannedUrlVersion(urlVersion);
        }else{//否则不更新
            result=-1;
        }
        return result;
    }

    @Override
    public int regularFreshSpecialCustomerVersion() throws Exception {
        int result=0;
        //version是redis已更新的specialcustomer的最新版本号
        String version=siteRedisService.returnUpdatedVersion(Strategy.SPECIALCUSTOMER);
        Integer redisVersion=0;
        if(version!=null && !"".equals(version)){
            redisVersion=Integer.parseInt(version);
        }
        //TODO 从数据库获取对应的最大版本号
        Map<String,Object> map=manageRuleService.getAll();
        //从数据库获取specialcustomer当前最新的版本号
        Integer specialVersion=Integer.parseInt(map.get(Strategy.SPECIALCUSTOMER)+"");
        if(specialVersion>redisVersion){//如果数据库的最新版本号大于redis的最新版本号，则进行更新
            result=rmiService.sendSpecialCustomerVersion(specialVersion);
        }else{//否则不更新
            result=-1;
        }
        return result;
    }

    @Override
    public int regularFreshKeyWordVersion() throws Exception {
        int result=0;
        //version是redis已更新的keyword的最新版本号
        String version=siteRedisService.returnUpdatedVersion(Strategy.KEYWORD);
        Integer redisVersion=0;
        if(version!=null && !"".equals(version)){
            redisVersion=Integer.parseInt(version);
        }
        //TODO 从数据库获取对应的最大版本号
        Map<String,Object> map=manageRuleService.getAll();
        //从数据库获取keyword当前最新的版本号
        Integer keywordVersion=Integer.parseInt(map.get(Strategy.KEYWORD)+"");
        if(keywordVersion>redisVersion){//如果数据库的最新版本号大于redis的最新版本号，则进行更新
            result=rmiService.sendSpecialCustomerVersion(keywordVersion);
        }else{//否则不更新
            result=-1;
        }
        return result;
    }
}
