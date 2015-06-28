/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.strategy.service.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.eastbar.center.strategy.service.biz.BannedUrlBO;
import org.eastbar.center.Po2Json;
import org.eastbar.center.strategy.dao.BannedUrlDao;
import org.eastbar.center.strategy.entity.BannedUrl;
import org.eastbar.center.strategy.service.BannedUrlService;
import org.eastbar.center.strategy.util.BannedUrlJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2015年05月13
 * @time 下午3:37
 * @description :
 */
@Service
public class BannedUrlServiceImpl implements BannedUrlService {
    @Autowired
    private BannedUrlDao bannedUrlDao;
    /**
     * 中心端往redis存储版本信息时，使用这个方法，返回版本列表信息
     * @param  version
     * @return
     */
    @Override
    public Map<String,Map<String, String>> control(Integer version) throws Exception{
        //根本版本号获取所有的add操作的prog列表
        List<BannedUrl> addBannedUrls=bannedUrlDao.getAllAddUrls(version);
        //根本版本号获取所有的edit操作的prog列表
        List<BannedUrl> editBannedUrls=bannedUrlDao.getAllEditUrls(version);
        //根本版本号获取所有的remove操作的prog列表
        List<BannedUrl> removeBannedUrls=bannedUrlDao.getAllRemoveUrls(version);
        //根据版本号获得monitorCode列表
        List<String> monitorCodes=bannedUrlDao.getMonitorCodesByVersion(version);
        Map<String,String> addMap=new HashMap<>();
        Map<String,String> editMap=new HashMap<>();
        Map<String,String> removeMap=new HashMap<>();
        Map<String,Map<String,String>> operationMap=new HashMap<>();
        //将add操作的URL列表根据监管中心编码存进map中
        if(addBannedUrls.size()>0){
            for(int i=0;i<monitorCodes.size();i++){
                //根据所给监管中心编码，将同一监管中心的数据保存到list中
                String lists=getUrlssByCondition(addBannedUrls, monitorCodes.get(i));
                //再将monitorCode作为key，将list作为value存储
                if(!lists.equals("")){
                    addMap.put(monitorCodes.get(i),lists);
                }
            }
            operationMap.put("add",addMap);
        }
        //将edit操作的URL列表根据监管中心编码存进map中
        if(editBannedUrls.size()>0){
            for(int i=0;i<monitorCodes.size();i++){
                //根据所给监管中心编码，将同一监管中心的数据保存到list中
                String lists=getUrlssByCondition(editBannedUrls, monitorCodes.get(i));
                //再将monitorCode作为key，将list作为value存储
                if(!lists.equals("")){
                    editMap.put(monitorCodes.get(i),lists);
                }
            }
            operationMap.put("edit",editMap);
        }
        //将remove操作的URL列表根据监管中心编码存进map中
        if(removeBannedUrls.size()>0){
            for(int i=0;i<monitorCodes.size();i++){
                //根据所给监管中心编码，将同一监管中心的数据保存到list中
                String lists=getUrlssByCondition(removeBannedUrls, monitorCodes.get(i));
                //再将monitorCode作为key，将list作为value存储
                if(!lists.equals("")){
                    removeMap.put(monitorCodes.get(i),lists);
                }
            }
            operationMap.put("remove",removeMap);
        }
        return operationMap;
    }

    /**
     * 场所端从数据库拉版本信息时，使用这个方法，返回版本列表信息
     * @param siteCode
     * @param version
     * @return
     */
    @Override
    public String siteControl(String siteCode, Integer version) throws Exception{
        //根本版本号获取所有的操作位add的url列表
        List<BannedUrl> addBannedUrls=bannedUrlDao.getAllAddUrls(version);
        //根本版本号获取所有的操作位edit的url列表
        List<BannedUrl> editBannedUrls=bannedUrlDao.getAllEditUrls(version);
        //根本版本号获取所有的操作位edit的url列表
        List<BannedUrl> removeBannedUrls=bannedUrlDao.getAllRemoveUrls(version);
        //根据版本号获得monitorCode列表
        List<String> monitorCodes=bannedUrlDao.getMonitorCodesByVersion(version);
        List<BannedUrlBO> addList=new ArrayList<>();
        List<BannedUrlBO> editList=new ArrayList<>();
        List<BannedUrlBO> removeList=new ArrayList<>();
        if(addBannedUrls.size()>0){
            addList=restructString(addBannedUrls,monitorCodes,siteCode);
        }
        if(editBannedUrls.size()>0){
            editList=restructString(editBannedUrls,monitorCodes,siteCode);
        }
        if(removeBannedUrls.size()>0){
            removeList=restructString(removeBannedUrls,monitorCodes,siteCode);
        }

        String json="";
        if(addList.size()>0 || editList.size()>0 || removeList.size()>0){
            BannedUrlJson bannedUrlJson=new BannedUrlJson();
            bannedUrlJson.setVerNum(version);
            bannedUrlJson.setAdd(addList);
            bannedUrlJson.setEdit(editList);
            bannedUrlJson.setRemove(removeList);
            json=Po2Json.toJson(bannedUrlJson);
        }
        return json;
    }

    private List<BannedUrlBO> restructString(List<BannedUrl> bannedUrls,List<String> monitorCodes,String siteCode) throws Exception{
        List<BannedUrlBO> operationList=new ArrayList<>();
        //将场所提供的siteCode，分别截取出 3,4,6三个部分
        String s3=siteCode.substring(0,3);
        String s4=siteCode.substring(0,4);
        String s6=siteCode.substring(0,6);
        for(int i=0;i<monitorCodes.size();i++){
            String monitorCode=monitorCodes.get(i);
            if(s3.equals(monitorCode)){//siteCode 是monitorCode的一部分时，根据monitorCode获取list并添加到list中
                List<BannedUrlBO> list=getDbUrlssByCondition(bannedUrls, monitorCode);
                operationList.addAll(list);
            }
            if(s4.equals(monitorCode)){//同上
                List<BannedUrlBO> list=getDbUrlssByCondition(bannedUrls, monitorCode);
                operationList.addAll(list);
            }
            if(s6.equals(monitorCode)){//同上
                List<BannedUrlBO>list=getDbUrlssByCondition(bannedUrls, monitorCode);
                operationList.addAll(list);
            }
            if(siteCode.equals(monitorCode)){//同上
                List<BannedUrlBO> list=getDbUrlssByCondition(bannedUrls, monitorCode);
                operationList.addAll(list);
            }
        }
        return operationList;
    }

    private String getUrlssByCondition(List<BannedUrl> bannedUrls,String monitorCode) throws Exception{
        List<BannedUrlBO> bannedUrlList=new ArrayList<>();

        for(int i=0;i<bannedUrls.size();i++){
            BannedUrl bannedUrl=bannedUrls.get(i);
            String monitorCode_1=bannedUrl.getMonitorCode();
            if(monitorCode.equals(monitorCode_1)){
                BannedUrlBO bannedUrlBO=new BannedUrlBO();
                BeanUtils.copyProperties(bannedUrlBO,bannedUrl);
                bannedUrlList.add(bannedUrlBO);
            }
        }
        if(bannedUrlList.size()>0){
            return Po2Json.toJson(bannedUrlList);
        }
        else{
            return "";
        }
    }

    private List<BannedUrlBO> getDbUrlssByCondition(List<BannedUrl> bannedUrls,String monitorCode) throws Exception{
        List<BannedUrlBO> bannedUrlList=new ArrayList<>();

        for(int i=0;i<bannedUrls.size();i++){
            BannedUrl bannedUrl=bannedUrls.get(i);
            String monitorCode_1=bannedUrl.getMonitorCode();
            if(monitorCode.equals(monitorCode_1)){
                BannedUrlBO bannedUrlBO=new BannedUrlBO();
                BeanUtils.copyProperties(bannedUrlBO,bannedUrl);
                bannedUrlList.add(bannedUrlBO);
            }
        }
        return bannedUrlList;
    }
}
