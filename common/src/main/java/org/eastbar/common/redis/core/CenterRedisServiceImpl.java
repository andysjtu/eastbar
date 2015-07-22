/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.common.redis.core;

import org.eastbar.common.redis.CenterRedisService;
import org.eastbar.common.redis.util.Dates;
import org.eastbar.common.redis.util.RedisKey;
import org.eastbar.common.redis.util.Strategy;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author cindy-jia
 * @date 2015年05月11
 * @time 上午10:28
 * @description :主要包含中心端的对reids的操作，包括在redis中添加活动数据和添加策略列表信息
 */
@Service
public class CenterRedisServiceImpl implements CenterRedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 一级监管中心活动数据的添加操作
     * @param monitorLiveData 包含monitorCode、totalSite、openSite、totalTerminal、totalAlarm、totalPunish
     */
    public  void centerHashPut(Map<String,String> monitorLiveData){
        //验证map内的键值对,并返回符合条件的map
        monitorLiveData= ValidatePut.validateCenterPut(monitorLiveData);
        if(monitorLiveData!=null){
            HashOperations<String,String,String> hashOperations= redisTemplate.opsForHash();
            String monitorCode=monitorLiveData.get("monitorCode");
            String key= RedisKey.createCenterKey(monitorCode);
            hashOperations.putAll(key,monitorLiveData);
        }

    }

    /**
     * 二级监管中心活动数据的添加操作
     * @param monitorLiveData 包含monitorCode、totalSite、openSite、totalTerminal、totalAlarm、totalPunish
     */
    public  void monitorHashPut(Map<String,String> monitorLiveData){
        //验证map内的键值对,并返回符合条件的map
        monitorLiveData= ValidatePut.validateMonitorPut(monitorLiveData);
        if(monitorLiveData!=null){
            HashOperations<String,String,String> hashOperations= redisTemplate.opsForHash();
            String monitorCode=monitorLiveData.get("monitorCode");
            String key= RedisKey.createMonitorKey(monitorCode);
            hashOperations.putAll(key,monitorLiveData);
        }


    }

    /**
     * 场所活动数据的添加操作
     * @param siteLiveData 包含siteCode、activeCustomerCount、runStatus、totalAlarm、totalPunish
     */
    public  void siteHashPut(Map<String,String> siteLiveData){
        //验证map内的键值对,并返回符合条件的map
        siteLiveData= ValidatePut.validateSitePut(siteLiveData);
        if(siteLiveData!=null){
            HashOperations<String,String,String> hashOperations= redisTemplate.opsForHash();
            String siteCode=siteLiveData.get("siteCode");
            String key= RedisKey.createSiteKey(siteCode);
            hashOperations.putAll(key,siteLiveData);
        }
    }

    /**
     * 终端计算机活动数据的添加操作
     * @param terminalLog 包含siteCode、hostIp、customerName、certId、siteState、onlineTime、authOrg、customerIdType、nationality
     */
    public  void terminalDataPut(Map<String,String> terminalLog){

        //验证map内的键值对,并返回符合条件的map
        terminalLog= ValidatePut.validateTermianlHashPut(terminalLog);
        if(terminalLog!=null){
            HashOperations<String,String,String> hashOperations=redisTemplate.opsForHash();
            SetOperations<String,String> setOperations=redisTemplate.opsForSet();
            String siteCode=terminalLog.get("siteCode");
            String ip=terminalLog.get("hostIp");
            String key= RedisKey.createTerminalKey(siteCode, ip);
            String setKey= RedisKey.createSetTerminalKey(siteCode);
            String onlineTime=terminalLog.get("onlineTime");
            Dates.dateTransfer(onlineTime);
            terminalLog.put("onlineTime",onlineTime);
            hashOperations.putAll(key,terminalLog);
            setOperations.add(setKey,ip);
        }


    }

    /**
     * 将程序版本信息缓存到redis里
     * @param versionList
     * @param version
     * @return
     * @throws Exception
     */
    @Override
    public Boolean saveProgVersionList(Map<String,Map<String,String>> versionList, String version) throws Exception{

        Boolean flag=false;
        try{
            //hash 索引结构，key是版本号，value是持有该版本的所有监管中心或者场所
            HashOperations<String,String,String> hashOperations=redisTemplate.opsForHash();
            //set 存放 某监管中心的某个版本的所有策略信息
           // ZSetOperations<String,String> versionSet=redisTemplate.opsForZSet();
            ValueOperations valueOperations=redisTemplate.opsForValue();
            //hashValue  hash的值
            List<String> hashValue=new ArrayList<>();
            Map<String,String> operationVersionList=new HashMap<>();
            for (String operation : versionList.keySet()) {
                operationVersionList= versionList.get(operation);
                for (String key : operationVersionList.keySet()) {//key是中心编码
                   // versionSet.add(Strategy.BANNEDPROG+"."+key+"."+version+"."+operation, operationVersionList.get(key),Double.parseDouble(version));
                    valueOperations.set(Strategy.BANNEDPROG+"."+key+"."+version+"."+operation,operationVersionList.get(key));
                    if(!hashValue.contains(key)){
                        hashValue.add(key);
                    }
                }
            }
            hashOperations.put(Strategy.PROG_CODE_VERSION_INDEX,version,hashValue.toString());
        }catch (Exception e){
            throw e;
        }

        return flag;
    }

    /**
     * 将url版本信息缓存到redis里
     * @param versionList
     * @param version
     * @return
     * @throws Exception
     */
    @Override
    public Boolean saveUrlVersionList(Map<String,Map<String,String>> versionList, String version) throws Exception {
        Boolean flag=false;
        try{
            //hash 索引结构，key是版本号，value是持有该版本的所有监管中心或者场所
            HashOperations<String,String,String> hashOperations=redisTemplate.opsForHash();
            //set 存放 某监管中心的某个版本的所有策略信息
            //ZSetOperations<String,String> versionSet=redisTemplate.opsForZSet();
            ValueOperations valueOperations=redisTemplate.opsForValue();
            //hashValue  hash的值
            List<String> hashValue=new ArrayList<>();
            Map<String,String> operationVersionList=new HashMap<>();
            for (String operation : versionList.keySet()) {
                operationVersionList= versionList.get(operation);
                for (String key : operationVersionList.keySet()) {//key是中心编码
                    //versionSet.add(Strategy.BANNEDURL+"."+key+"."+version+"."+operation, operationVersionList.get(key),Double.parseDouble(version));
                    valueOperations.set(Strategy.BANNEDURL+"."+key+"."+version+"."+operation, operationVersionList.get(key));
                    if(!hashValue.contains(key)){
                        hashValue.add(key);
                    }
                }
            }
            hashOperations.put(Strategy.URL_CODE_VERSION_INDEX,version,hashValue.toString());
        }catch (Exception e){
            throw e;
        }
        return flag;
    }

    /**
     * 将关键字版本信息缓存到redis里
     * @param versionList
     * @param version
     * @return
     * @throws Exception
     */
    @Override
    public Boolean saveKeyWordVersionList(Map<String,Map<String,String>> versionList, String version) throws Exception {
        Boolean flag=false;
        try{
            //hash 索引结构，key是版本号，value是持有该版本的所有监管中心或者场所
            HashOperations<String,String,String> hashOperations=redisTemplate.opsForHash();
            //set 存放 某监管中心的某个版本的所有策略信息
            //ZSetOperations<String,String> versionSet=redisTemplate.opsForZSet();
            ValueOperations valueOperations=redisTemplate.opsForValue();
            //hashValue  hash的值
            List<String> hashValue=new ArrayList<>();
            Map<String,String> operationVersionList=new HashMap<>();
            for (String operation : versionList.keySet()) {
                operationVersionList= versionList.get(operation);
                for (String key : operationVersionList.keySet()) {//key是中心编码
                   // versionSet.add(Strategy.KEYWORD+"."+key+"."+version+"."+operation, operationVersionList.get(key),Double.parseDouble(version));
                    valueOperations.set(Strategy.KEYWORD+"."+key+"."+version+"."+operation, operationVersionList.get(key));
                    if(!hashValue.contains(key)){
                        hashValue.add(key);
                    }
                }
            }
            hashOperations.put(Strategy.KEYWORD_CODE_VERSION_INDEX,version,hashValue.toString());
        }catch (Exception e){
            throw e;
        }
        return flag;
    }

    /**
     * 将特殊人员版本信息缓存到redis里
     * @param versionList
     * @param version
     * @return
     * @throws Exception
     */
    @Override
    public Boolean saveSpecialCustomerVersionList(Map<String,Map<String,String>> versionList, String version) throws Exception {
        Boolean flag=false;
        try{
            //hash 索引结构，key是版本号，value是持有该版本的所有监管中心或者场所
            HashOperations<String,String,String> hashOperations=redisTemplate.opsForHash();
            //set 存放 某监管中心的某个版本的所有策略信息
          //  ZSetOperations<String,String> versionSet=redisTemplate.opsForZSet();
            ValueOperations valueOperations=redisTemplate.opsForValue();
            //hashValue  hash的值
            List<String> hashValue=new ArrayList<>();
            Map<String,String> operationVersionList=new HashMap<>();
            for (String operation : versionList.keySet()) {
                operationVersionList= versionList.get(operation);
                for (String key : operationVersionList.keySet()) {//key是中心编码
                   // versionSet.add(Strategy.SPECIALCUSTOMER+"."+key+"."+version+"."+operation, operationVersionList.get(key),Double.parseDouble(version));
                    valueOperations.set(Strategy.SPECIALCUSTOMER+"."+key+"."+version+"."+operation, operationVersionList.get(key));
                    if(!hashValue.contains(key)){
                        hashValue.add(key);
                    }
                }
            }
            hashOperations.put(Strategy.SPECIALcUSTOMER_CODE_VERSION_INDEX,version,hashValue.toString());
        }catch (Exception e){
            throw e;
        }
        return flag;
    }


    /**
     * 将最新版本编号更新到redis库中
     * @param measure
     * @param version
     * @return
     * @throws Exception
     */
    @Override
    public Boolean saveLastedVersion(String measure,Integer version) throws Exception {
        Boolean flag=false;
        HashOperations<String,String, String> hashOperations=redisTemplate.opsForHash();
        hashOperations.put(Strategy.LASTED_VERSIONS,measure,version+"");
        flag=true;
        return flag;
    }

    @Override
    public Boolean saveUpdatedVersion(String measure, Integer version) throws Exception {
        Boolean flag=false;
        HashOperations<String,String, String> hashOperations=redisTemplate.opsForHash();
        hashOperations.put(Strategy.UPDATED_VERSIONS,measure,version+"");
        flag=true;
        return flag;
    }

    @Override
    public Boolean deleteMonitorLibrary() throws Exception {
        boolean flag=false;
        Set center=redisTemplate.keys("center*");
        Set monitor=redisTemplate.keys("monitor*");
        Set site=redisTemplate.keys("site*");
        Set terminal=redisTemplate.keys("terminal*");
        center.addAll(monitor);
        center.addAll(site);
        center.addAll(terminal);
        redisTemplate.delete(center);
        flag=true;
        return false;
    }

    @Override
    public Boolean deleteStrategyLibrary() throws Exception {
        return null;
    }
}
