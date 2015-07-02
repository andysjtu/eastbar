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
import org.eastbar.web.measures.KeyWordService;
import org.eastbar.web.measures.ManageRuleService;
import org.eastbar.web.measures.biz.KeyWordBO;
import org.eastbar.web.measures.dao.KeyWordDao;
import org.eastbar.web.measures.dao.ManageRuleDao;
import org.eastbar.web.measures.entity.KeyWord;
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
@Transactional
public class KeyWordServiceImpl implements KeyWordService {

    @Autowired
    private KeyWordDao keyWordDao;

    @Autowired
    private ManageRuleDao manageRuleDao;

    @Autowired
    private RmiService rmiService;

    @Autowired
    private ManageRuleService manageRuleService;

    @Autowired
    private MonitorService monitorService;

    @Override
    public KeyWordBO getKeyWord(Integer id) {
        KeyWordBO keyWordBO=new KeyWordBO();
        try {
            BeanUtils.copyProperties(keyWordBO, keyWordDao.getKeyWord(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyWordBO;
    }


    @Override
    public PageInfo getAllKeyWord(KeyWordBO keyWordBO) {
        try {
            PageHelper.startPage(keyWordBO.getPage(), keyWordBO.getRows());

            Long t1 = System.currentTimeMillis();
            Map<String,Object> re = Bean2Map.trans(keyWordBO);
            //获取当前用户的域
            List<String> monitorCodes= ShiroCustomUtils.getMonitors();
            if(monitorCodes.size()>0){//判断是否为超级管理员，如果是超级管理员，则size为0
                re.put("monitorCode",monitorCodes.get(0));
            }
            System.out.println(System.currentTimeMillis() - t1 + " : " + re);
            List<KeyWord>  list = keyWordDao.getAllKeyWord(re);
            return PageInfo.clone(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public Boolean save(KeyWordBO keyWordBO) {
        KeyWord keyWord=new KeyWord();
        KeyWordBO keyWordBO1=new KeyWordBO();
        ManageRule manageRule=manageRuleDao.get();
        String[] monitorCodes;
        String[] siteCodes=keyWordBO.getSiteCodes();
        if(siteCodes[0].equals("-1")){
            monitorCodes=keyWordBO.getMonitorCodes();
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
            for(int i=0;i<keyWordBO.getKeywords().length;i++){
                for(int j=0;j<monitorCodes.length;j++){
                keyWordBO1.setMonitorCode(monitorCodes[j]);
                keyWordBO1.setKeyword((keyWordBO.getKeywords())[i]);
                keyWordBO1.setAlarmType((keyWordBO.getAlarmTypes())[i]);
                keyWordBO1.setAlarmRank((keyWordBO.getAlarmRanks())[i]);
                keyWordBO1.setIsBlock((keyWordBO.getIsBlocks())[i]);
                BeanUtils.copyProperties(keyWord, keyWordBO1);
                keyWord.setCreator(ShiroCustomUtils.getCurrentUserName());
                keyWord.setCreateTime(Times.now());
                keyWord.setDeleteFlag(0);
                keyWord.setIsPub(0);
                keyWord.setOperation("add");
                keyWordDao.save(keyWord);
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
            KeyWord keyWord=keyWordDao.getKeyWord(id);
            keyWord.setDeleteFlag(1);
            keyWord.setOperation("remove");
            keyWord.setIsPub(0);
            keyWord.setUpdateTime(Times.now());
            keyWordDao.update(keyWord);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean update(KeyWordBO keyWordBO) {
        KeyWord keyWord=new KeyWord();
        try {
            BeanUtils.copyProperties(keyWord, keyWordBO);
            keyWord.setUpdator(ShiroCustomUtils.getCurrentUserName());
            keyWord.setUpdateTime(Times.now());
            keyWord.setOperation("edit");
            keyWordDao.update(keyWord);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean deleteMany(Integer[] ids) {
        boolean flag=false;
        int ver=manageRuleService.get().getKeywordVerNum();
        int i=rmiService.sendKeyWordVersion(ver+1,ids,"remove");
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
        int ver=manageRuleService.get().getKeywordVerNum();
        i=rmiService.sendKeyWordVersion(ver+1,ids,"release");
        if(i!=0){
            throw new RuntimeException();
         }
        return i;
    }
}

