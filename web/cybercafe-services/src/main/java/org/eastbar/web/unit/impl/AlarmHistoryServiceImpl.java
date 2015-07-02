/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.unit.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.eastbar.web.Bean2Map;
import org.eastbar.web.PageInfo;
import org.eastbar.web.pagehelper.PageHelper;
import org.eastbar.web.shiro.ShiroCustomUtils;
import org.eastbar.web.unit.AlarmHistoryService;
import org.eastbar.web.unit.biz.AlarmHistoryBO;
import org.eastbar.web.unit.dao.AlarmHistoryDao;
import org.eastbar.web.unit.entity.AlarmHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年10月17
 * @time 上午10:14
 * @description :
 */
@Service
@Transactional
public class AlarmHistoryServiceImpl implements AlarmHistoryService {

    @Autowired
    private AlarmHistoryDao alarmHistoryDao;

    @Override
    public AlarmHistoryBO getAlarmHistory(Integer id){

        try {
            AlarmHistoryBO alarmHistoryBO=new AlarmHistoryBO();
            BeanUtils.copyProperties(alarmHistoryBO, alarmHistoryDao.getAlarmHistory(id));
            return alarmHistoryBO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PageInfo getAllAlarmHistory(AlarmHistoryBO alarmHistoryBO){
        try {
            PageHelper.startPage(alarmHistoryBO.getPage(), alarmHistoryBO.getRows());

            Long t1 = System.currentTimeMillis();
            Map<String,Object> re = Bean2Map.trans(alarmHistoryBO);
            //获取当前用户的域
            List<String> monitorCodes= ShiroCustomUtils.getMonitors();
            if(monitorCodes.size()>0){//判断是否为超级管理员，如果是超级管理员，则size为0
                re.put("monitor",monitorCodes.get(0));//域
            }
            System.out.println(System.currentTimeMillis() - t1 + " : " + re);
            List<AlarmHistory> list = alarmHistoryDao.getAllAlarmHistory(re);
            PageInfo pr= PageInfo.clone(list);
            return pr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public Boolean save(AlarmHistoryBO alarmHistoryBO){
        AlarmHistory alarmHistory=new AlarmHistory();
        try {
            BeanUtils.copyProperties(alarmHistory, alarmHistoryBO);
            alarmHistoryDao.save(alarmHistory);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean update(AlarmHistoryBO alarmHistoryBO){
        AlarmHistory alarmHistory=new AlarmHistory();
        try {
            BeanUtils.copyProperties(alarmHistory, alarmHistoryBO);
            alarmHistoryDao.update(alarmHistory);
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
            alarmHistoryDao.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

