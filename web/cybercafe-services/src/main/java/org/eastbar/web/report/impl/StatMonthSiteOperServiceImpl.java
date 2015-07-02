/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.report.impl;

import org.eastbar.web.Bean2Map;
import org.eastbar.web.PageInfo;
import org.eastbar.web.pagehelper.PageHelper;
import org.eastbar.web.report.StatMonthSiteOperService;
import org.eastbar.web.report.biz.StatMonthSiteOperBO;
import org.eastbar.web.report.dao.StatMonthSiteOperDao;
import org.eastbar.web.report.entity.StatMonthSiteOper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年12月30
 * @time 下午1:31
 * @description :
 */
@Service
public class StatMonthSiteOperServiceImpl implements StatMonthSiteOperService {

    @Autowired
    private StatMonthSiteOperDao statMonthSiteOperDao;

    @Override
    public PageInfo getAll(StatMonthSiteOperBO statMonthSiteOperBO) {
        try{
            PageHelper.startPage(statMonthSiteOperBO.getPage(), statMonthSiteOperBO.getRows());
            Map<String,Object> re= Bean2Map.trans(statMonthSiteOperBO);
            List<StatMonthSiteOper> list=statMonthSiteOperDao.getAll(re);
            return PageInfo.clone(list);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return null;
    }
}
