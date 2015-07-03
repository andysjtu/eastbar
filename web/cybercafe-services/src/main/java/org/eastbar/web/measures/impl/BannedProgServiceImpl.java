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
import org.eastbar.web.measures.BannedProgService;
import org.eastbar.web.measures.ManageRuleService;
import org.eastbar.web.measures.biz.BannedProgBO;
import org.eastbar.web.measures.dao.BannedProgDao;
import org.eastbar.web.measures.dao.ManageRuleDao;
import org.eastbar.web.measures.entity.BannedProg;
import org.eastbar.web.measures.entity.ManageRule;
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
public class BannedProgServiceImpl implements BannedProgService {

    @Autowired
    private BannedProgDao bannedProgDao;

    @Autowired
    private ManageRuleDao manageRuleDao;

    @Autowired
    private ManageRuleService manageRuleService;

    @Autowired
    private RmiService rmiService;

    @Autowired
    private MonitorService monitorService;

    @Override
    public BannedProgBO getBannedProg(Integer id){
        BannedProgBO bannedProgBO = new BannedProgBO();
       try {
            BannedProg bannedProg=bannedProgDao.getBannedProg(id);
            BeanUtils.copyProperties(bannedProgBO,bannedProg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bannedProgBO;

    }

    @Override
    public PageInfo getAllBannedProg(BannedProgBO bannedProgBO){
        try {
            PageHelper.startPage(bannedProgBO.getPage(), bannedProgBO.getRows());

            Long t1 = System.currentTimeMillis();
            Map<String,Object> re = Bean2Map.trans(bannedProgBO);
            //获取当前用户的域
            List<String> monitorCodes= ShiroCustomUtils.getMonitors();
            if(monitorCodes.size()>0){//判断是否为超级管理员，如果是超级管理员，则size为0
                re.put("monitorCode",monitorCodes.get(0));
            }
            System.out.println(System.currentTimeMillis() - t1 + " : " + re);
            List<BannedProg>  list = bannedProgDao.getAllBannedProg(re);
            return PageInfo.clone(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public Boolean save(BannedProgBO bannedProgBO){
        BannedProg bannedProg=new BannedProg();
        BannedProgBO bannedProgBO1=new BannedProgBO();
        String[] monitorCodes;
        String[] siteCodes=bannedProgBO.getSiteCodes();
        if(siteCodes[0].equals("-1")){

            monitorCodes=bannedProgBO.getMonitorCodes();
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
            for(int i=0;i<bannedProgBO.getProgNames().length;i++){
                for(int j=0;j<monitorCodes.length;j++){
                bannedProgBO1.setMonitorCode(monitorCodes[j]);
                    if(bannedProgBO.getProgNames().length>i)
                        bannedProgBO1.setProgName((bannedProgBO.getProgNames())[i]);
                    if(bannedProgBO.getProgFileNames().length>i)
                        bannedProgBO1.setProgFileName((bannedProgBO.getProgFileNames())[i]);
                    if(bannedProgBO.getProgTypes().length>i)
                        bannedProgBO1.setProgType((bannedProgBO.getProgTypes())[i]);
                    if(bannedProgBO.getProgressNames().length>i)
                        bannedProgBO1.setProgressName((bannedProgBO.getProgressNames())[i]);
                    if(bannedProgBO.getProgMFs().length>i)
                        bannedProgBO1.setProgMF((bannedProgBO.getProgMFs())[i]);
                    if(bannedProgBO.getFeatureCodes().length>i)
                        bannedProgBO1.setFeatureCode((bannedProgBO.getFeatureCodes())[i]);
                    if(bannedProgBO.getAlarmTypes().length>i)
                        bannedProgBO1.setAlarmType((bannedProgBO.getAlarmTypes())[i]);
                    if(bannedProgBO.getAlarmRanks().length>i)
                        bannedProgBO1.setAlarmRank((bannedProgBO.getAlarmRanks())[i]);
                BeanUtils.copyProperties(bannedProg, bannedProgBO1);
                bannedProg.setOperation("add");
                bannedProg.setDeleteFlag(0);
                bannedProg.setIsPub(0);
                bannedProgDao.save(bannedProg);
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
            BannedProg bannedProg=bannedProgDao.getBannedProg(id);
            ManageRule manageRule=manageRuleDao.get();
            bannedProg.setOperation("remove");
            bannedProg.setDeleteFlag(1);
            bannedProg.setIsPub(0);
            bannedProgDao.update(bannedProg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean update(BannedProgBO bannedProgBO){
        BannedProg bannedProg=new BannedProg();
        try {
            BeanUtils.copyProperties(bannedProg, bannedProgBO);
            bannedProg.setOperation("edit");
            bannedProgDao.update(bannedProg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean deleteMany(Integer[] ids) {
        boolean flag=false;
        int ver=manageRuleService.get().getProgVerNum();
        int i=rmiService.sendBannedProgVersion(ver+1,ids,"remove");
        if(i!=0){
            throw new RuntimeException();
        }else{
            flag=true;
        }
        return flag;
    }

    @Override
    public int releaseMany(Integer[] ids) {

        int i=1;
        int ver=manageRuleService.get().getProgVerNum();
        i=rmiService.sendBannedProgVersion(ver+1,ids,"release");
        if(i!=0){
            throw new RuntimeException();
         }
        return i;
    }
}

