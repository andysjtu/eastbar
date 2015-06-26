/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.centers.rmi.core;


import org.eastbar.centers.strategy.service.BannedProgService;
import org.eastbar.centers.strategy.service.BannedUrlService;
import org.eastbar.centers.strategy.service.KeyWordService;
import org.eastbar.centers.strategy.service.SpecialCustomerService;
import org.eastbar.centers.strategy.util.Strategy;
import org.eastbar.common.rmi.RmiService;
import org.eastbar.common.redis.CenterRedisService;
import org.eastbar.common.redis.SiteRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author cindy-jia
 * @date 2015年05月04
 * @time 上午10:38
 * @description :
 */
@Component
public class RmiServiceImpl implements RmiService {

    @Autowired
    private BannedProgService bannedProgService;

    @Autowired
    private BannedUrlService bannedUrlService;

    @Autowired
    private KeyWordService keyWordService;

    @Autowired
    private SpecialCustomerService specialCustomerService;

    @Autowired
    private CenterRedisService centerRedisService;

    @Autowired
    private SiteRedisService siteRedisService;

    @Override
    public int shutDown(String siteCode, String ip) {
        //TODO
        //对客户端执行关机操作
        return 0;
    }

    @Override
    public int restart(String siteCode, String ip) {
        //TODO
        //对客户端执行重启操作
        return 0;
    }

    @Override
    public int locking(String siteCode, String ip) {
        //TODO
        //对客户端执行锁定操作
        return 0;
    }

    @Override
    public int Unlock(String siteCode, String ip) {
        //TODO
        //对客户端执行解锁操作
        return 0;
    }

    @Override
    public byte[] Screenshot(String siteCode, String ip) {
        //TODO
        //对客户端执行截图操作
        return new byte[0];
    }

    @Override
    public int sendSpecialCustomerVersion(final int version)throws RuntimeException {
        boolean flag = false;
                try {
                    //获取redis中当前specialCustomer已更新的最新版本号
                    String num = siteRedisService.returnUpdatedVersion(Strategy.SPECIALCUSTOMER);
                    Integer redisVersion = 0;
                    if (num != null && !"".equals(num)) {//如果版本号不为空，转化成Integer
                        redisVersion = Integer.parseInt(num);
                    }
                    for (int i = redisVersion; i < version; i++) {
                        //根据条件读取prog列表
                        Map<String, Map<String, String>> specialCustomers = specialCustomerService.control(i + 1);
                        //调用redis，把prog版本列表信息存储到redis中
                        centerRedisService.saveSpecialCustomerVersionList(specialCustomers, i + 1 + "");
                        //存redis版本总库
                        centerRedisService.saveUpdatedVersion(Strategy.SPECIALCUSTOMER, i + 1);
                    }


            centerRedisService.saveLastedVersion(Strategy.SPECIALCUSTOMER, version);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        if (!flag) {
            return 1;
        }
        return 0;


    }

    /**
     * 发送keyword版本号的实现类
     *
     * @param version
     * @return
     */
    @Override
    public int sendKeyWordVersion(final int version)throws RuntimeException {
        boolean flag = false;

        try {

            //获取redis中当前keyword已更新的最新版本号
            String num = siteRedisService.returnUpdatedVersion(Strategy.KEYWORD);
            Integer redisVersion = 0;
            if (num != null && !"".equals(num)) {//如果版本号不为空，转成Integer
                redisVersion = Integer.parseInt(num);
            }
            for (int i = redisVersion; i < version; i++) {
                //根据条件读取prog列表
                Map<String, Map<String, String>> keywords = keyWordService.control(i + 1);
                //调用redis，把prog版本列表信息存储到redis中
                centerRedisService.saveKeyWordVersionList(keywords, i + 1 + "");
                //存redis版本总库
                centerRedisService.saveUpdatedVersion(Strategy.KEYWORD, i + 1);
            }
            centerRedisService.saveLastedVersion(Strategy.KEYWORD, version);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        if (!flag) {
            return 1;
        }
        return 0;
    }

    /**
     * 发送url版本号的实现类
     *
     * @param version
     * @return int 1 代表成功  0 代表失败
     */
    @Override
    public int sendBannedUrlVersion(final int version)throws RuntimeException {
        boolean flag = false;
        try {
            //获取redis中当前bannedurl已更新的最新版本号
            String num = siteRedisService.returnUpdatedVersion(Strategy.BANNEDURL);
            Integer redisVersion = 0;
            if (num != null && !"".equals(num)) {//如果版本号不为空，就转成Integer
                redisVersion = Integer.parseInt(num);
            }
            for (int i = redisVersion; i < version; i++) {
                //根据条件读取prog列表
                Map<String, Map<String, String>> bannedUrlList = bannedUrlService.control(i + 1);
                //调用redis，把prog版本列表信息存储到redis中
                centerRedisService.saveUrlVersionList(bannedUrlList, i + 1 + "");
                //存redis版本总库
                centerRedisService.saveUpdatedVersion(Strategy.BANNEDURL, i + 1);
            }

            centerRedisService.saveLastedVersion(Strategy.BANNEDURL, version);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        if (!flag) {
            return 1;
        }
        return 0;

    }

    /**
     * 发送prog版本号的实现类
     *
     * @param version
     * @return int 1  代表成功 0 代表失败
     */
    @Override
    public int sendBannedProgVersion(final int version) throws RuntimeException{
        boolean flag = false;
        try {
            //获取redis中当前bannedprog已更新的最新版本号
            String num = siteRedisService.returnUpdatedVersion(Strategy.BANNEDPROG);
            Integer redisVersion = 0;
            if (num != null && !"".equals(num)) {//如果版本号不为空，则转成Integer
                redisVersion = Integer.parseInt(num);
            }
            for (int i = redisVersion; i < version; i++) {
                //根据条件读取prog列表
                Map<String, Map<String, String>> bannedProgs = bannedProgService.control(i + 1);
                if (bannedProgs.size() > 0) {
                    //调用redis，把prog版本列表信息存储到redis中
                    centerRedisService.saveProgVersionList(bannedProgs, i + 1 + "");
                    //存redis版本总库
                    centerRedisService.saveUpdatedVersion(Strategy.BANNEDPROG, i + 1);
                }
            }

            centerRedisService.saveLastedVersion(Strategy.BANNEDPROG, version);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        if (!flag) {
            return 1;
        }
        return 0;

    }
}
