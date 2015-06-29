/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.strategy.service.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.eastbar.center.Po2Json;
import org.eastbar.center.strategy.dao.ManageRuleDao;
import org.eastbar.center.strategy.dao.SpecialCustomerDao;
import org.eastbar.center.strategy.entity.ManageRule;
import org.eastbar.center.strategy.util.SpecialCustomerJson;
import org.eastbar.center.strategy.entity.SpecialCustomer;
import org.eastbar.center.strategy.service.SpecialCustomerService;
import org.eastbar.center.strategy.service.biz.SpecialCustomerBO;
import org.eastbar.center.strategy.util.Times;
import org.eastbar.center.utils.Versions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2015年05月13
 * @time 下午3:42
 * @description :
 */
@Service
public class SpecialCustomerServiceImpl implements SpecialCustomerService {

    @Autowired
    private SpecialCustomerDao specialCustomerDao;

    @Autowired
    private ManageRuleDao manageRuleDao;

    @Override
    public Map<String,Map<String, String>> control(Integer version) throws Exception{
        //根本版本号获取所有的add操作的prog列表
        List<SpecialCustomer> addSpecials=specialCustomerDao.getAllAddSpecials(version);
        //根本版本号获取所有的edit操作的prog列表
        List<SpecialCustomer> editSpecails=specialCustomerDao.getAllEditSpecails(version);
        //根本版本号获取所有的remove操作的prog列表
        List<SpecialCustomer> removeSpecials=specialCustomerDao.getAllRemoveSpecials(version);
        //根据版本号获得monitorCode列表
        List<String> monitorCodes=specialCustomerDao.getMonitorCodesByVersion(version);
        Map<String,String> addMap=new HashMap<>();
        Map<String,String> editMap=new HashMap<>();
        Map<String,String> removeMap=new HashMap<>();
        Map<String,Map<String,String>> operationMap=new HashMap<>();
        //将add操作的prog列表根据监管中心编码存进map中
        if(addSpecials.size()>0){
            for(int i=0;i<monitorCodes.size();i++){
                //根据所给监管中心编码，将同一监管中心的数据保存到list中
                String lists=getSpecialCustomersByCondition(addSpecials,monitorCodes.get(i));
                //再将monitorCode作为key，将list作为value存储
                if(!lists.equals("")){
                    addMap.put(monitorCodes.get(i),lists);
                }

            }
            operationMap.put("add",addMap);
        }
        //将edit操作的prog列表根据监管中心编码存进map中
        if(editSpecails.size()>0){
            for(int i=0;i<monitorCodes.size();i++){
                //根据所给监管中心编码，将同一监管中心的数据保存到list中
                String lists=getSpecialCustomersByCondition(editSpecails, monitorCodes.get(i));
                //再将monitorCode作为key，将list作为value存储
                if(!lists.equals("")){
                    editMap.put(monitorCodes.get(i),lists);
                }

            }
            operationMap.put("edit",editMap);
        }
        //将remove操作的prog列表根据监管中心编码存进map中
        if(removeSpecials.size()>0){
            for(int i=0;i<monitorCodes.size();i++){
                String code=monitorCodes.get(i);
                //根据所给监管中心编码，将同一监管中心的数据保存到list中
                String lists=getSpecialCustomersByCondition(removeSpecials, monitorCodes.get(i));
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
        List<SpecialCustomer> addSpecials=specialCustomerDao.getAllAddSpecials(version);

        List<SpecialCustomer> editSpecails=specialCustomerDao.getAllEditSpecails(version);

        List<SpecialCustomer> removeSpecials=specialCustomerDao.getAllRemoveSpecials(version);
        //根据版本号获得monitorCode列表
        List<String> monitorCodes=specialCustomerDao.getMonitorCodesByVersion(version);
        List<SpecialCustomerBO> addList=new ArrayList<>();
        List<SpecialCustomerBO> editList=new ArrayList<>();
        List<SpecialCustomerBO> removeList=new ArrayList<>();
        if(addSpecials.size()>0){
            addList=restructString(addSpecials,monitorCodes,siteCode);
        }
        if(editSpecails.size()>0){
            editList=restructString(editSpecails,monitorCodes,siteCode);
        }
        if(removeSpecials.size()>0){
            removeList=restructString(removeSpecials,monitorCodes,siteCode);
        }
        String json="";
        if(addList.size()>0 || editList.size()>0 || removeList.size()>0){
            SpecialCustomerJson specialCustomerJson=new SpecialCustomerJson();
            specialCustomerJson.setVerNum(version);
            specialCustomerJson.setAdd(addList);
            specialCustomerJson.setEdit(editList);
            specialCustomerJson.setRemove(removeList);

             json= Po2Json.toJson(specialCustomerJson);
        }
        return json;

    }

    private List<SpecialCustomerBO> restructString(List<SpecialCustomer> specialCustomers,List<String> monitorCodes,String siteCode) throws Exception{
        List<SpecialCustomerBO> operationList=new ArrayList<>();
        //将场所提供的siteCode，分别截取出 3,4,6三个部分
        String s3=siteCode.substring(0,3);
        String s4=siteCode.substring(0,4);
        String s6=siteCode.substring(0,6);
        for(int i=0;i<monitorCodes.size();i++){
            String monitorCode=monitorCodes.get(i);
            if(s3.equals(monitorCode)){//siteCode 是monitorCode的一部分时，根据monitorCode获取list并添加到list中
                List<SpecialCustomerBO> list=getDbSpecialCustomersByCondition(specialCustomers, monitorCode);
                operationList.addAll(list);
            }
            if(s4.equals(monitorCode)){//同上
                List<SpecialCustomerBO> list=getDbSpecialCustomersByCondition(specialCustomers, monitorCode);
                operationList.addAll(list);
            }
            if(s6.equals(monitorCode)){//同上
                List<SpecialCustomerBO> list=getDbSpecialCustomersByCondition(specialCustomers, monitorCode);
                operationList.addAll(list);
            }
            if(siteCode.equals(monitorCode)){//同上
                List<SpecialCustomerBO> list=getDbSpecialCustomersByCondition(specialCustomers, monitorCode);
                operationList.addAll(list);
            }
        }
        return operationList;
    }

    private String getSpecialCustomersByCondition(List<SpecialCustomer> specialCustomers, String monitorCode) throws Exception{
        List<SpecialCustomerBO> specialCustomerList=new ArrayList<>();

        for(int i=0;i<specialCustomers.size();i++){
            SpecialCustomer specialCustomer=specialCustomers.get(i);
            String monitorCode_1=specialCustomer.getMonitorCode();
            if(monitorCode.equals(monitorCode_1)){
                SpecialCustomerBO specialCustomerBO=new SpecialCustomerBO();
                BeanUtils.copyProperties(specialCustomerBO,specialCustomer);
                specialCustomerList.add(specialCustomerBO);
            }
        }
        if(specialCustomerList.size()>0){
            return Po2Json.toJson(specialCustomerList);
        }else{
            return "";
        }

    }

    private List<SpecialCustomerBO> getDbSpecialCustomersByCondition(List<SpecialCustomer> specialCustomers, String monitorCode) throws Exception{
        List<SpecialCustomerBO> specialCustomerList=new ArrayList<>();

        for(int i=0;i<specialCustomers.size();i++){
            SpecialCustomer specialCustomer=specialCustomers.get(i);
            String monitorCode_1=specialCustomer.getMonitorCode();
            if(monitorCode.equals(monitorCode_1)){
                SpecialCustomerBO specialCustomerBO=new SpecialCustomerBO();
                BeanUtils.copyProperties(specialCustomerBO,specialCustomer);
                specialCustomerList.add(specialCustomerBO);
            }
        }
       return specialCustomerList;

    }

    public Boolean update(Integer[] ids){
        ManageRule manageRule=manageRuleDao.get();
        String version= Versions.computeVersion(manageRule.getSpecialPersonVer());
        Integer num=manageRule.getSpecialVerNum()+1;
        for(int i=0;i<ids.length;i++){
            SpecialCustomer specialCustomer=specialCustomerDao.get(ids[i]);
            specialCustomer.setUpdator("center");
            specialCustomer.setUpdateTime(Times.now());
            specialCustomer.setOperation("edit");
            specialCustomer.setIsPub(1);
            specialCustomer.setVersion(version);
            specialCustomer.setVerNum(num);
            specialCustomerDao.update(specialCustomer);
        }
            manageRule.setSpecialVerNum(num);
            manageRule.setSpecialPersonVer(version);
            manageRule.setUpdateTime(Times.now());
            manageRuleDao.update(manageRule);

        return true;
    }

}
