/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.measures.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.eastbar.common.rmi.RmiService;
import org.eastbar.web.Bean2Map;
import org.eastbar.web.PageInfo;
import org.eastbar.web.Times;
import org.eastbar.web.ipc.MonitorService;
import org.eastbar.web.ipc.entity.Monitor;
import org.eastbar.web.measures.ManageRuleService;
import org.eastbar.web.measures.SpecialCustomerService;
import org.eastbar.web.measures.biz.SpecialCustomerBO;
import org.eastbar.web.measures.dao.ManageRuleDao;
import org.eastbar.web.measures.dao.SpecialCustomerDao;
import org.eastbar.web.measures.entity.ManageRule;
import org.eastbar.web.measures.entity.SpecialCustomer;
import org.eastbar.web.pagehelper.PageHelper;
import org.eastbar.web.shiro.ShiroCustomUtils;
import org.eastbar.web.version.Versions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年10月14
 * @time 上午9:40
 * @description :
 */
@Service
@Transactional
public class SpecialCustomerServiceImpl implements SpecialCustomerService {

    @Autowired
    private SpecialCustomerDao specialCustomerDao;

    @Autowired
    private ManageRuleDao manageRuleDao;

    @Autowired
    private ManageRuleService manageRuleService;

    @Autowired
    private RmiService rmiService;

    @Autowired
    private MonitorService monitorService;

    @Override
    public SpecialCustomerBO getSpecialCustomer(Integer id){
        SpecialCustomerBO specialCustomerBO=new SpecialCustomerBO();
        try {
            BeanUtils.copyProperties(specialCustomerBO, specialCustomerDao.getSpecialCustomer(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return specialCustomerBO;
    }

    @Override
    public PageInfo getAllSpecialCustomer(SpecialCustomerBO specialCustomerBO){
        try {
            PageHelper.startPage(specialCustomerBO.getPage(), specialCustomerBO.getRows());

            Long t1 = System.currentTimeMillis();
            Map<String,Object> re = Bean2Map.trans(specialCustomerBO);
            //获取当前用户的域
            List<String> monitorCodes= ShiroCustomUtils.getMonitors();
            if(monitorCodes.size()>0){//判断是否为超级管理员，如果是超级管理员，则size为0
                re.put("monitorCode",monitorCodes.get(0));
            }
            System.out.println(System.currentTimeMillis() - t1 + " : " + re);
            List<SpecialCustomer>  list = specialCustomerDao.getAllSpecialCustomer(re);
            return PageInfo.clone(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public Boolean save(SpecialCustomerBO specialCustomerBO){
        SpecialCustomer specialCustomer=new SpecialCustomer();
        SpecialCustomerBO specialCustomerBO1=new SpecialCustomerBO();
        ManageRule manageRule=manageRuleDao.get();
        String[] monitorCodes;
        String[] siteCodes=specialCustomerBO.getSiteCodes();
        if(siteCodes[0].equals("-1")){

            monitorCodes=specialCustomerBO.getMonitorCodes();
        }else{
            monitorCodes=siteCodes;
        }
        List<String> codes=new ArrayList<>();
        for(String c:monitorCodes){
            codes.add(c);
        }
        if(codes.contains("310")){
            monitorCodes=new String[]{"310"};
        }else{
            List<Monitor> monitorList=monitorService.getMonitors();
            List<String> ms=new ArrayList<>();
            for(Monitor m:monitorList){
                if(codes.contains(m.getMonitorCode())){
                    ms.add(m.getMonitorCode());
                }
            }
            if(ms.size()>0){
                monitorCodes= (String[]) ms.toArray(new String[ms.size()]);
            }
        }
        try {
            for(int i=0;i<specialCustomerBO.getNames().length;i++){
                for(int j=0;j<monitorCodes.length;j++){
                    specialCustomerBO1.setMonitorCode(monitorCodes[j]);
                    if(specialCustomerBO.getNames().length>i);
                        specialCustomerBO1.setName((specialCustomerBO.getNames())[i]);
                    if(specialCustomerBO.getCertIds().length>i);
                        specialCustomerBO1.setCertId((specialCustomerBO.getCertIds())[i]);
                    if(specialCustomerBO.getCertTypes().length>i);
                        specialCustomerBO1.setCertType((specialCustomerBO.getCertTypes())[i]);
                    if(specialCustomerBO.getAlarmTypes().length>i);
                        specialCustomerBO1.setAlarmType((specialCustomerBO.getAlarmTypes())[i]);
                    if(specialCustomerBO.getAlarmRanks().length>i)
                        specialCustomerBO1.setAlarmRank((specialCustomerBO.getAlarmRanks())[i]);
                    if(specialCustomerBO.getNationalitys().length>i);
                        specialCustomerBO1.setNationality((specialCustomerBO.getNationalitys())[i]);
                BeanUtils.copyProperties(specialCustomer, specialCustomerBO1);
                specialCustomer.setCreator(ShiroCustomUtils.getCurrentUserName());
                specialCustomer.setCreateTime(Times.now());
                specialCustomer.setDeleteFlag(0);
                specialCustomer.setOperation("add");
                specialCustomer.setIsPub(0);
                specialCustomerDao.save(specialCustomer);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean delete(Integer id){
        try {
            SpecialCustomer specialCustomer=specialCustomerDao.getSpecialCustomer(id);
            specialCustomer.setDeleteFlag(1);
            specialCustomer.setOperation("remove");
            specialCustomer.setIsPub(0);
            specialCustomer.setUpdateTime(Times.now());
            specialCustomerDao.update(specialCustomer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean update(SpecialCustomerBO specialCustomerBO){
        SpecialCustomer specialCustomer=new SpecialCustomer();
        try {
            BeanUtils.copyProperties(specialCustomer, specialCustomerBO);
            specialCustomer.setUpdator(ShiroCustomUtils.getCurrentUserName());
            specialCustomer.setUpdateTime(Times.now());
            specialCustomer.setOperation("edit");
            specialCustomerDao.update(specialCustomer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean deleteMany(Integer[] ids) {
        boolean flag=false;
        SpecialCustomerBO specialCustomerBO=getSpecialCustomer(ids[0]);
        if(specialCustomerBO!=null && specialCustomerBO.getIsPub()!=null && specialCustomerBO.getIsPub()==1){
            int ver=manageRuleService.get().getSpecialVerNum();
            int i=rmiService.sendSpecialCustomerVersion(ver+1,ids,"remove");
            if(i!=0){
                throw new RuntimeException();
            }else{
                flag=true;
            }
            return flag;
        }else{
            deleteByLocal(ids);
            return true;
        }

    }

    @Override
    @Transactional
    public int releaseMany(Integer[] ids) {
        int i=1;
        int ver=manageRuleService.get().getSpecialVerNum();
        i=rmiService.sendSpecialCustomerVersion(ver+1,ids,"release");
        if(i!=0){
            throw new RuntimeException();
         }
        return i;
    }

    @Transactional
    private boolean deleteByLocal(Integer[] ids){
        for(Integer id:ids){
            delete(id);
        }
        return true;
    }
}

