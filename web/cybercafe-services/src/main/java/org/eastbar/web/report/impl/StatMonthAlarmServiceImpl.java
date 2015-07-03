/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.report.impl;

import org.eastbar.web.Bean2Map;
import org.eastbar.web.PageInfo;
import org.eastbar.web.pagehelper.PageHelper;
import org.eastbar.web.report.StatMonthAlarmService;
import org.eastbar.web.report.biz.StatMonthAlarmBO;
import org.eastbar.web.report.dao.StatMonthAlarmDao;
import org.eastbar.web.report.entity.StatMonthAlarm;
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
public class StatMonthAlarmServiceImpl implements StatMonthAlarmService {

    @Autowired
    private StatMonthAlarmDao statMonthAlarmDao;


    @Override
    public PageInfo getAll(StatMonthAlarmBO statMonthAlarmBO) {
        try{
            PageHelper.startPage(statMonthAlarmBO.getPage(), statMonthAlarmBO.getRows());
            Map<String,Object> re= Bean2Map.trans(statMonthAlarmBO);
            List<StatMonthAlarm> list=statMonthAlarmDao.getAll(re);
            return PageInfo.clone(list);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
