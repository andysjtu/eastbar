/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.centers.strategy.service.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.eastbar.centers.Po2Json;
import org.eastbar.centers.strategy.dao.KeyWordDao;
import org.eastbar.centers.strategy.entity.KeyWord;
import org.eastbar.centers.strategy.service.KeyWordService;
import org.eastbar.centers.strategy.service.biz.KeyWordBO;
import org.eastbar.centers.strategy.util.KeyWordJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2015年05月13
 * @time 下午3:40
 * @description :
 */
@Service
public class KeyWordServiceImpl implements KeyWordService{

    @Autowired
    private KeyWordDao keyWordDao;

    @Override
    public Map<String,Map<String, String>> control(Integer version) throws Exception{
        //根本版本号获取所有的add操作的keyword列表
        List<KeyWord> addKeywordsProgs=keyWordDao.getAllAddKeywords(version);

        List<KeyWord> editKeywords=keyWordDao.getAllEditKeywords(version);

        List<KeyWord> removeKeywords=keyWordDao.getAllRemoveKeywords(version);
        //根据版本号获得monitorCode列表
        List<String> monitorCodes=keyWordDao.getMonitorCodesByVersion(version);
        Map<String,String> addMap=new HashMap<>();
        Map<String,String> editMap=new HashMap<>();
        Map<String,String> removeMap=new HashMap<>();
        Map<String,Map<String,String>> operationMap=new HashMap<>();
        //将add操作的prog列表根据监管中心编码存进map中
        if(addKeywordsProgs.size()>0){
            for(int i=0;i<monitorCodes.size();i++){
                //根据所给监管中心编码，将同一监管中心的数据保存到list中
                String lists=getKeyWordsByCondition(addKeywordsProgs, monitorCodes.get(i));
                //再将monitorCode作为key，将list作为value存储
                //再将monitorCode作为key，将list作为value存储
                 if(!lists.equals("")){
                    addMap.put(monitorCodes.get(i),lists);
                 }
            }
            operationMap.put("add",addMap);
        }
        //将edit操作的prog列表根据监管中心编码存进map中
        if(editKeywords.size()>0){
            for(int i=0;i<monitorCodes.size();i++){
                //根据所给监管中心编码，将同一监管中心的数据保存到list中
                String lists=getKeyWordsByCondition(addKeywordsProgs, monitorCodes.get(i));
                //再将monitorCode作为key，将list作为value存储
                //再将monitorCode作为key，将list作为value存储
                if(!lists.equals("")){
                    editMap.put(monitorCodes.get(i),lists);
                }
            }
            operationMap.put("edit",editMap);
        }
        //将remove操作的prog列表根据监管中心编码存进map中
        if(removeKeywords.size()>0){
            for(int i=0;i<monitorCodes.size();i++){
                //根据所给监管中心编码，将同一监管中心的数据保存到list中
                String lists=getKeyWordsByCondition(addKeywordsProgs, monitorCodes.get(i));
                //再将monitorCode作为key，将list作为value存储
                //再将monitorCode作为key，将list作为value存储
                if(!lists.equals("")){
                    removeMap.put(monitorCodes.get(i),lists);
                }
            }
            operationMap.put("remove",removeMap);
        }
        return operationMap;
    }

    @Override
    public String siteControl(String siteCode, Integer version) throws Exception{
        //根本版本号获取所有的操作位add的keyword列表
        List<KeyWord> addKeywords=keyWordDao.getAllAddKeywords(version);

        List<KeyWord> editKeywords=keyWordDao.getAllEditKeywords(version);

        List<KeyWord> removeKeywords=keyWordDao.getAllRemoveKeywords(version);
        //根据版本号获得monitorCode列表
        List<String> monitorCodes=keyWordDao.getMonitorCodesByVersion(version);

        List<KeyWordBO> addList=new ArrayList<>();
        List<KeyWordBO> editList=new ArrayList<>();
        List<KeyWordBO> removeList=new ArrayList<>();
        if(addKeywords.size()>0){
            addList=restructString(addKeywords, monitorCodes, siteCode);
        }
        if(editKeywords.size()>0){
            editList=restructString(editKeywords, monitorCodes, siteCode);
        }
        if(removeKeywords.size()>0){
            removeList=restructString(removeKeywords, monitorCodes, siteCode);
        }
        KeyWordJson keyWordJson=new KeyWordJson();
        keyWordJson.setVerNum(version);
        keyWordJson.setAdd(addList);
        keyWordJson.setEdit(editList);
        keyWordJson.setRemove(removeList);
        String json=Po2Json.toJson(keyWordJson);
        return json;
    }

    private List<KeyWordBO> restructString(List<KeyWord> keyWords,List<String> monitorCodes,String siteCode) throws Exception{
        List<KeyWordBO> operationList=new ArrayList<>();
        //将场所提供的siteCode，分别截取出 3,4,6三个部分
        String s3=siteCode.substring(0,3);
        String s4=siteCode.substring(0,4);
        String s6=siteCode.substring(0,6);
        for(int i=0;i<monitorCodes.size();i++){
            String monitorCode=monitorCodes.get(i);
            if(s3.equals(monitorCode)){//siteCode 是monitorCode的一部分时，根据monitorCode获取list并添加到list中
                List<KeyWordBO> list=getDbKeyWordsByCondition(keyWords, monitorCode);
                operationList.addAll(list);
            }
            if(s4.equals(monitorCode)){//同上
                List<KeyWordBO> list=getDbKeyWordsByCondition(keyWords, monitorCode);
                operationList.addAll(list);
            }
            if(s6.equals(monitorCode)){//同上
                List<KeyWordBO> list=getDbKeyWordsByCondition(keyWords, monitorCode);
                operationList.addAll(list);
            }
            if(siteCode.equals(monitorCode)){//同上
                List<KeyWordBO> list=getDbKeyWordsByCondition(keyWords, monitorCode);
                operationList.addAll(list);
            }
        }
        return operationList;
    }

    private String getKeyWordsByCondition(List<KeyWord> keyWords, String monitorCode) throws Exception{
        List<KeyWordBO> keyWordList=new ArrayList<>();

        for(int i=0;i<keyWords.size();i++){
            KeyWord keyWord=keyWords.get(i);
            String monitorCode_1=keyWord.getMonitorCode();
            if(monitorCode.equals(monitorCode_1)){
                KeyWordBO keyWordBO=new KeyWordBO();
                BeanUtils.copyProperties(keyWordBO,keyWord);
                keyWordList.add(keyWordBO);
            }
        }
       if(keyWordList.size()>0){
            return Po2Json.toJson(keyWordList);
       }else{
            return "";
       }
    }
    private List<KeyWordBO> getDbKeyWordsByCondition(List<KeyWord> keyWords, String monitorCode)throws Exception{
        List<KeyWordBO> keyWorBOdList=new ArrayList<>();

        for(int i=0;i<keyWords.size();i++){
            KeyWord keyWord=keyWords.get(i);
            String monitorCode_1=keyWord.getMonitorCode();
            if(monitorCode.equals(monitorCode_1)){
                KeyWordBO keyWordBO=new KeyWordBO();
                BeanUtils.copyProperties(keyWordBO,keyWord);
                keyWorBOdList.add(keyWordBO);
            }
        }
       return keyWorBOdList;
    }
}
