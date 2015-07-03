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
import org.eastbar.web.measures.BannedUrlService;
import org.eastbar.web.measures.ManageRuleService;
import org.eastbar.web.measures.biz.BannedUrlBO;
import org.eastbar.web.measures.dao.BannedUrlDao;
import org.eastbar.web.measures.dao.ManageRuleDao;
import org.eastbar.web.measures.entity.BannedUrl;
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
public class BannedUrlServiceImpl implements BannedUrlService {

    @Autowired
    private BannedUrlDao bannedUrldao;

    @Autowired
    private ManageRuleDao manageRuleDao;

    @Autowired
    private RmiService rmiService;

    @Autowired
    private ManageRuleService manageRuleService;

    @Autowired
    private MonitorService monitorService;

    @Override
    public BannedUrlBO getBannedUrl(Integer id){
        BannedUrlBO bannedUrlBO = new BannedUrlBO();
        try {
            BeanUtils.copyProperties(bannedUrlBO, bannedUrldao.getBannedUrl(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bannedUrlBO;

    }

    @Override
    public PageInfo getAllBannedUrl(BannedUrlBO bannedUrlBO){
        try {
            PageHelper.startPage(bannedUrlBO.getPage(), bannedUrlBO.getRows());

            Long t1 = System.currentTimeMillis();
            Map<String,Object> re = Bean2Map.trans(bannedUrlBO);
            //获取当前用户的域
            List<String> monitorCodes= ShiroCustomUtils.getMonitors();
            if(monitorCodes.size()>0){//判断是否为超级管理员，如果是超级管理员，则size为0
                re.put("monitorCode",monitorCodes.get(0));
            }
            System.out.println(System.currentTimeMillis() - t1 + " : " + re);
              List<BannedUrl>  list = bannedUrldao.getAllBannedUrl(re);
            return PageInfo.clone(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public Boolean save(BannedUrlBO bannedUrlBO){
        BannedUrl bannedUrl=new BannedUrl();
        try {
            BannedUrlBO bannedUrlBO1=new BannedUrlBO();
            String[] monitorCodes;
            String[] siteCodes=bannedUrlBO.getSiteCodes();
            if(siteCodes[0].equals("-1")){

                monitorCodes=bannedUrlBO.getMonitorCodes();
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
            for(int i=0;i<bannedUrlBO.getUrlTypes().length;i++){
                for(int j=0;j<monitorCodes.length;j++){
                    bannedUrlBO1.setMonitorCode(monitorCodes[j]);
                    if(bannedUrlBO.getUrlTypes().length>i)
                        bannedUrlBO1.setUrlType((bannedUrlBO.getUrlTypes())[i]);
                    if(bannedUrlBO.getAlarmRanks().length>i);
                        bannedUrlBO1.setAlarmRank((bannedUrlBO.getAlarmRanks())[i]);
                    if(bannedUrlBO.getAlarmTypes().length>i)
                        bannedUrlBO1.setAlarmType((bannedUrlBO.getAlarmTypes())[i]);
                    if(bannedUrlBO.getUrlValues().length>i)
                        bannedUrlBO1.setUrlValue((bannedUrlBO.getUrlValues())[i]);
                    if(bannedUrlBO.getIsBlocks().length>i)
                        bannedUrlBO1.setIsBlock((bannedUrlBO.getIsBlocks())[i]);
                    BeanUtils.copyProperties(bannedUrl,bannedUrlBO1);
                    bannedUrl.setCreateTime(Times.now());
                    bannedUrl.setDeleteFlag(0);
                    bannedUrl.setOperation("add");
                    bannedUrl.setIsPub(0);
                    bannedUrl.setCreator(ShiroCustomUtils.getCurrentUserName());
                    bannedUrldao.save(bannedUrl);
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
            BannedUrl bannedUrl=bannedUrldao.getBannedUrl(id);
            bannedUrl.setOperation("remove");
            bannedUrl.setDeleteFlag(1);
            bannedUrl.setIsPub(0);
            bannedUrl.setUpdateTime(Times.now());
            bannedUrldao.update(bannedUrl);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
     public Boolean update(BannedUrlBO bannedUrlBO){
        BannedUrl bannedUrl=new BannedUrl();
        try {
            BeanUtils.copyProperties(bannedUrl, bannedUrlBO);
            bannedUrl.setUpdator(ShiroCustomUtils.getCurrentUserName());
            bannedUrl.setUpdateTime(Times.now());
            bannedUrl.setOperation("edit");
            bannedUrldao.update(bannedUrl);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PageInfo getBannedUrlsByUrlType(BannedUrlBO bannedUrlBO){

        try {
            PageHelper.startPage(bannedUrlBO.getPage(), bannedUrlBO.getRows());

            Long t1 = System.currentTimeMillis();
            Map<String,Object> re = Bean2Map.trans(bannedUrlBO);
            //获取当前用户的域
            List<String> monitorCodes=ShiroCustomUtils.getMonitors();
            if(monitorCodes.size()>0){//判断是否为超级管理员，如果是超级管理员，则size为0
                re.put("monitorCode",monitorCodes.get(0));
            }
            System.out.println(System.currentTimeMillis() - t1 + " : " + re);
            List<BannedUrl>  list = bannedUrldao.getBannedUrlsByUrlType(re);
            return PageInfo.clone(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean deleteMany(Integer[] ids) {
        boolean flag=false;
        int ver=manageRuleService.get().getUrlVerNum();
        int i=rmiService.sendBannedUrlVersion(ver+1,ids,"remove");
        if(i!=0){
            throw new RuntimeException();
        }else{
            flag=true;
        }
        return flag;
    }

    @Override
    @Transactional
    public int releaseMany(Integer[] ids) {
        int i=1;
        int ver=manageRuleService.get().getUrlVerNum();
        i=rmiService.sendBannedUrlVersion(ver+1,ids,"release");
        if(i!=0){
             throw new RuntimeException();
        }
        return i;
    }


    @Transactional
    public Boolean saves(BannedUrlBO bannedUrlBO){
        BannedUrl bannedUrl=new BannedUrl();
        try {
            ManageRule manageRule=manageRuleDao.get();
            BannedUrlBO bannedUrlBO1=new BannedUrlBO();
            String[] monitorCodes;
            String[] siteCodes=bannedUrlBO.getSiteCodes();
            if(siteCodes[0].equals("-1")){

                monitorCodes=bannedUrlBO.getMonitorCodes();
            }else{
                monitorCodes=siteCodes;
            }
            String codes=monitorCodes.toString();
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
                monitorCodes=(String[])ms.toArray();
            }
            for(int i=0;i<bannedUrlBO.getUrlTypes().length;i++){
                for(int j=0;j<monitorCodes.length;j++){
                    bannedUrlBO1.setMonitorCode(monitorCodes[j]);
                    if(bannedUrlBO.getUrlTypes().length>i)
                        bannedUrlBO1.setUrlType((bannedUrlBO.getUrlTypes())[i]);
                    if(bannedUrlBO.getAlarmRanks().length>i);
                    bannedUrlBO1.setAlarmRank((bannedUrlBO.getAlarmRanks())[i]);
                    if(bannedUrlBO.getAlarmTypes().length>i)
                        bannedUrlBO1.setAlarmType((bannedUrlBO.getAlarmTypes())[i]);
                    if(bannedUrlBO.getUrlValues().length>i)
                        bannedUrlBO1.setUrlValue((bannedUrlBO.getUrlValues())[i]);
                    if(bannedUrlBO.getIsBlocks().length>i)
                        bannedUrlBO1.setIsBlock((bannedUrlBO.getIsBlocks())[i]);
                    BeanUtils.copyProperties(bannedUrl,bannedUrlBO1);
                    bannedUrl.setCreateTime(Times.now());
                    bannedUrl.setDeleteFlag(0);
                    bannedUrl.setOperation("add");
                    bannedUrl.setIsPub(0);
                    bannedUrl.setCreator(ShiroCustomUtils.getCurrentUserName());
                    if(j==0 && i==0){
                        bannedUrl.setVersion(Versions.computeVersion(manageRule.getBanUrlVer()));
                        bannedUrl.setVerNum(manageRule.getUrlVerNum() + 1);
                        manageRule.setBanUrlVer(bannedUrl.getVersion());
                        manageRule.setUrlVerNum(bannedUrl.getVerNum());
                        manageRule.setUpdateTime(Times.now());
                    }else{
                        bannedUrl.setVersion(manageRule.getBanUrlVer());
                        bannedUrl.setVerNum(manageRule.getUrlVerNum());
                    }
                    bannedUrldao.save(bannedUrl);
                }

            }
            manageRuleDao.update(manageRule);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

