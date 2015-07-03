/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.report.impl;

import org.eastbar.web.Bean2Map;
import org.eastbar.web.PageInfo;
import org.eastbar.web.pagehelper.PageHelper;
import org.eastbar.web.report.StatDayAlarmService;
import org.eastbar.web.report.biz.StatDayAlarmBO;
import org.eastbar.web.report.dao.StatDayAlarmDao;
import org.eastbar.web.report.entity.StatDayAlarm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年12月08
 * @time 下午4:50
 * @description :
 */
@Service
public class StatDayAlarmServiceImpl implements StatDayAlarmService {

    @Autowired
    private StatDayAlarmDao statDayAlarmDao;


    @Override
    public PageInfo getAll(StatDayAlarmBO statDayAlarmBO) {
        try{
            PageHelper.startPage(statDayAlarmBO.getPage(), statDayAlarmBO.getRows());
            Map<String,Object> re= Bean2Map.trans(statDayAlarmBO);
            List<StatDayAlarm> list=statDayAlarmDao.getAll(re);
            return PageInfo.clone(list);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
