/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.sys.impl;

import org.eastbar.web.sys.AlarmNotifyService;
import org.eastbar.web.sys.dao.AlarmNotifyDao;
import org.eastbar.web.sys.entity.AlarmNotify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author cindy-jia
 * @date 2014年10月17
 * @time 下午3:01
 * @description :
 */

@Service
public class AlarmNotifyServiceServiceImpl implements AlarmNotifyService {

    @Autowired
    private AlarmNotifyDao alarmNotifyDao;

    @Override
    public AlarmNotify getAlarmNotify(Integer id){
       return  alarmNotifyDao.getAlarmNotify(id);
    }

    @Override
    public List<AlarmNotify> getAllAlarmNotify(){
        return alarmNotifyDao.getAllAlarmNotify();
    }

    @Override
    public Boolean save(AlarmNotify alarmNotify){
        try{
            alarmNotifyDao.save(alarmNotify);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public Boolean update(AlarmNotify alarmNotify){
        try{
            alarmNotifyDao.update(alarmNotify);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }


    }

    @Override
    @Transactional
    public Boolean delete(Integer id){
        try{
            alarmNotifyDao.delete(id);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public List<AlarmNotify> getAlarmNotifysByCondition(AlarmNotify alarmNotify){
        return alarmNotifyDao.getAlarmNotifysByCondition(alarmNotify);
    }
}
