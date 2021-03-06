/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.report.impl;

import org.eastbar.web.Bean2Map;
import org.eastbar.web.PageInfo;
import org.eastbar.web.pagehelper.PageHelper;
import org.eastbar.web.report.StatDayProgService;
import org.eastbar.web.report.biz.StatDayProgBO;
import org.eastbar.web.report.dao.StatDayProgDao;
import org.eastbar.web.report.entity.StatDayProg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年12月09
 * @time 上午9:32
 * @description :
 */
@Service
public class StatDayProgServiceImpl implements StatDayProgService {

    @Autowired
    private StatDayProgDao statDayProgDao;

    @Override
    public PageInfo getAll(StatDayProgBO statDayProgBO) {
        try{
            PageHelper.startPage(statDayProgBO.getPage(), statDayProgBO.getRows());
            Map<String,Object> re= Bean2Map.trans(statDayProgBO);
            List<StatDayProg> list=statDayProgDao.getAll(re);
            return PageInfo.clone(list);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
