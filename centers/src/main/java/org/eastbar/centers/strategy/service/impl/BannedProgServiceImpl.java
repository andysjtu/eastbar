/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.centers.strategy.service.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.eastbar.centers.Po2Json;
import org.eastbar.centers.strategy.dao.BannedProgDao;
import org.eastbar.centers.strategy.entity.BannedProg;
import org.eastbar.centers.strategy.service.BannedProgService;
import org.eastbar.centers.strategy.service.biz.BannedProgBO;
import org.eastbar.centers.strategy.util.BannedProgJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BulkMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2015年05月13
 * @time 下午3:35
 * @description :
 */
@Service
public class BannedProgServiceImpl implements BannedProgService {

    @Autowired
    private BannedProgDao bannedProgDao;

    /**
     * 中心端往redis存储版本信息时，使用这个方法，返回版本列表信息
     * @param version
     * @return
     */
    @Override
    public Map<String,Map<String, String>> control(Integer version) throws Exception{
        //根本版本号获取所有的add操作的prog列表
        List<BannedProg> addBannedProgs=bannedProgDao.getAllAddProgs(version);
        //根本版本号获取所有的edit操作的prog列表
        List<BannedProg> editBannedProgs=bannedProgDao.getAllEditProgs(version);
        //根本版本号获取所有的remove操作的prog列表
        List<BannedProg> removeBannedProgs=bannedProgDao.getAllRemoveProgs(version);
        //根据版本号获得monitorCode列表
        List<String> monitorCodes=bannedProgDao.getMonitorCodesByVersion(version);
        Map<String,String> addMap=new HashMap<>();
        Map<String,String> editMap=new HashMap<>();
        Map<String,String> removeMap=new HashMap<>();
        Map<String,Map<String,String>> operationMap=new HashMap<>();
        //将add操作的prog列表根据监管中心编码存进map中
        if(addBannedProgs.size()>0){
            for(int i=0;i<monitorCodes.size();i++){
                //根据所给监管中心编码，将同一监管中心的数据保存到list中
                String lists=getProgsByCondition(addBannedProgs, monitorCodes.get(i));
                //再将monitorCode作为key，将list作为value存储
                if(!lists.equals("")){
                    addMap.put(monitorCodes.get(i),lists);
                }
            }
            operationMap.put("add",addMap);
        }
        //将edit操作的prog列表根据监管中心编码存进map中
        if(editBannedProgs.size()>0){
            for(int i=0;i<monitorCodes.size();i++){
                //根据所给监管中心编码，将同一监管中心的数据保存到list中
                String lists=getProgsByCondition(editBannedProgs, monitorCodes.get(i));
                //再将monitorCode作为key，将list作为value存储
                if(!lists.equals("")){
                    editMap.put(monitorCodes.get(i),lists);
                }

            }
            operationMap.put("edit",editMap);
        }
        //将remove操作的prog列表根据监管中心编码存进map中
        if(removeBannedProgs.size()>0){
            for(int i=0;i<monitorCodes.size();i++){
                //根据所给监管中心编码，将同一监管中心的数据保存到list中
                String lists=getProgsByCondition(removeBannedProgs, monitorCodes.get(i));
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
        //根本版本号获取所有的操作位add的prog列表
        List<BannedProg> addBannedProgs=bannedProgDao.getAllAddProgs(version);
        //根本版本号获取所有的操作位edit的prog列表
        List<BannedProg> editBannedProgs=bannedProgDao.getAllEditProgs(version);
        //根本版本号获取所有的操作位edit的prog列表
        List<BannedProg> removeBannedProgs=bannedProgDao.getAllRemoveProgs(version);
        //根据版本号获得monitorCode列表
        List<String> monitorCodes=bannedProgDao.getMonitorCodesByVersion(version);
        List<BannedProgBO> addList=new ArrayList<>();
        List<BannedProgBO> editList=new ArrayList<>();
        List<BannedProgBO> removeList=new ArrayList<>();
        if(addBannedProgs.size()>0){
            addList=restructString(addBannedProgs, monitorCodes, siteCode);
        }
        if(editBannedProgs.size()>0){
            editList=restructString(editBannedProgs,monitorCodes,siteCode);
        }
        if(removeBannedProgs.size()>0){
            removeList=restructString(removeBannedProgs,monitorCodes,siteCode);
        }

        BannedProgJson bannedProgJson=new BannedProgJson();
        bannedProgJson.setVerNum(version);
        bannedProgJson.setAdd(addList);
        bannedProgJson.setEdit(editList);
        bannedProgJson.setRemove(removeList);
        String json=Po2Json.toJson(bannedProgJson);
        return json;
    }

    private List<BannedProgBO> restructString(List<BannedProg> bannedProgs,List<String> monitorCodes,String siteCode) throws Exception{
        List<BannedProgBO> operationList=new ArrayList<>();
        //将场所提供的siteCode，分别截取出 3,4,6三个部分
        String s3=siteCode.substring(0,3);
        String s4=siteCode.substring(0,4);
        String s6=siteCode.substring(0,6);
        for(int i=0;i<monitorCodes.size();i++){
            String monitorCode=monitorCodes.get(i);
            if(s3.equals(monitorCode)){//siteCode 是monitorCode的一部分时，根据monitorCode获取list并添加到list中
                List<BannedProgBO> list=getDbProgsByCondition(bannedProgs, monitorCode);
                operationList.addAll(list);
            }
            if(s4.equals(monitorCode)){//同上
                List<BannedProgBO> list=getDbProgsByCondition(bannedProgs, monitorCode);
                operationList.addAll(list);
            }
            if(s6.equals(monitorCode)){//同上
                List<BannedProgBO> list=getDbProgsByCondition(bannedProgs, monitorCode);
                operationList.addAll(list);
            }
            if(siteCode.equals(monitorCode)){//同上
                List<BannedProgBO> list=getDbProgsByCondition(bannedProgs, monitorCode);
                operationList.addAll(list);
            }
        }
        return operationList;
    }

    private String getProgsByCondition(List<BannedProg> bannedProgs,String monitorCode) throws Exception{

        List<BannedProgBO> bannedProgBOList=new ArrayList<>();

        for(int i=0;i<bannedProgs.size();i++){
            BannedProg bannedProg=bannedProgs.get(i);
            String monitorCode_1=bannedProg.getMonitorCode();
            if(monitorCode.equals(monitorCode_1)){
                BannedProgBO bannedProgBO=new BannedProgBO();
                BeanUtils.copyProperties(bannedProgBO,bannedProg);
                bannedProgBOList.add(bannedProgBO);
            }
        }
        if(bannedProgs.size()>0){

            return Po2Json.toJson(bannedProgBOList);
        }else{
            return "";
        }
    }

    private List<BannedProgBO> getDbProgsByCondition(List<BannedProg> bannedProgs,String monitorCode) throws Exception{

        List<BannedProgBO> bannedProgBOList=new ArrayList<>();

        for(int i=0;i<bannedProgs.size();i++){
            BannedProg bannedProg=bannedProgs.get(i);
            String monitorCode_1=bannedProg.getMonitorCode();
            if(monitorCode.equals(monitorCode_1)){
                BannedProgBO bannedProgBO=new BannedProgBO();
                BeanUtils.copyProperties(bannedProgBO,bannedProg);
                bannedProgBOList.add(bannedProgBO);
            }
        }
       return bannedProgBOList;
    }
}
