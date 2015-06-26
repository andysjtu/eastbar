/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.common.redis;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2015年05月11
 * @time 上午10:21
 * @description :
 */
public interface CenterRedisService {

    /**
     * 一级监管中心活动数据的添加操作
     * @param monitorLiveData
     */
    public  void centerHashPut(Map<String, String> monitorLiveData);

    /**
     * 二级监管中心活动数据的添加操作
     * @param monitorLiveData
     */
    public  void monitorHashPut(Map<String, String> monitorLiveData);

    /**
     * 场所活动数据的添加操作
     * @param siteLiveData
     */
    public  void siteHashPut(Map<String, String> siteLiveData);

    /**
     * 终端计算机活动数据的添加操作
     * @param terminalLog
     */
    public  void terminalDataPut(Map<String, String> terminalLog);


    /**
     * 将禁止程序的信息缓存到redis中
     * @param versionList
     * @param version
     * @return
     * @throws Exception
     */
    public Boolean saveProgVersionList(Map<String,Map<String,String>> versionList, String version) throws Exception;

    /**
     * 将禁止url的信息缓存到redis中
     * @param versionList
     * @param version
     * @return
     * @throws Exception
     */
    public Boolean saveUrlVersionList(Map<String,Map<String,String>> versionList, String version) throws Exception;

    /**
     * 将关键字信息缓存到redis中
     * @param versionList
     * @param version
     * @return
     * @throws Exception
     */
    public Boolean saveKeyWordVersionList(Map<String,Map<String,String>> versionList, String version) throws Exception;

    /**
     * 将特殊人员的信息缓存到redis中
     * @param versionList
     * @param version
     * @return
     * @throws Exception
     */
    public Boolean saveSpecialCustomerVersionList(Map<String,Map<String,String>> versionList, String version) throws Exception;

    /**
     * 将收到的所有的版本的最新信息缓存到redis
     */
    public Boolean saveLastedVersion(String measure, Integer version) throws Exception;

    /**
     * 将redis每次升级一个版本成功后，版本信息存储起来
     */
    public Boolean saveUpdatedVersion(String measure,Integer version) throws Exception;

}
