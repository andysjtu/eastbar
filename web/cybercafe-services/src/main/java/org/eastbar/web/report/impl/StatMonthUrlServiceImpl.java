/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.report.impl;

import org.eastbar.web.Bean2Map;
import org.eastbar.web.PageInfo;
import org.eastbar.web.pagehelper.PageHelper;
import org.eastbar.web.report.StatMonthUrlService;
import org.eastbar.web.report.biz.StatMonthUrlBO;
import org.eastbar.web.report.dao.StatMonthUrlDao;
import org.eastbar.web.report.entity.StatMonthUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年12月09
 * @time 上午9:39
 * @description :
 */
@Service
public class StatMonthUrlServiceImpl implements StatMonthUrlService {

    @Autowired
    private StatMonthUrlDao statMonthUrlDao;

    @Override
    public PageInfo getAll(StatMonthUrlBO statMonthUrlBO) {
        try{
            PageHelper.startPage(statMonthUrlBO.getPage(), statMonthUrlBO.getRows());
            Map<String,Object> re= Bean2Map.trans(statMonthUrlBO);
            List<StatMonthUrl> list=statMonthUrlDao.getAll(re);
            return PageInfo.clone(list);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
