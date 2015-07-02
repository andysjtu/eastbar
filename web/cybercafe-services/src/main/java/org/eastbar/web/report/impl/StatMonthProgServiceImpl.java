/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.report.impl;

import org.eastbar.web.Bean2Map;
import org.eastbar.web.PageInfo;
import org.eastbar.web.pagehelper.PageHelper;
import org.eastbar.web.report.StatMonthProgService;
import org.eastbar.web.report.biz.StatMonthProgBO;
import org.eastbar.web.report.dao.StatMonthProgDao;
import org.eastbar.web.report.entity.StatMonthProg;
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
public class StatMonthProgServiceImpl implements StatMonthProgService {

    @Autowired
    private StatMonthProgDao statMonthProgDao;

    @Override
    public PageInfo getAll(StatMonthProgBO statMonthProgBO) {
        try{
            PageHelper.startPage(statMonthProgBO.getPage(), statMonthProgBO.getRows());
            Map<String,Object> re= Bean2Map.trans(statMonthProgBO);
            List<StatMonthProg> list=statMonthProgDao.getAll(re);
            return PageInfo.clone(list);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
