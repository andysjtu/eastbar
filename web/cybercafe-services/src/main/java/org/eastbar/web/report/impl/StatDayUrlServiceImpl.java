/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.report.impl;

import org.eastbar.web.Bean2Map;
import org.eastbar.web.PageInfo;
import org.eastbar.web.pagehelper.PageHelper;
import org.eastbar.web.report.StatDayUrlService;
import org.eastbar.web.report.biz.StatDayUrlBO;
import org.eastbar.web.report.dao.StatDayUrlDao;
import org.eastbar.web.report.entity.StatDayUrl;
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
public class StatDayUrlServiceImpl implements StatDayUrlService {

    @Autowired
    private StatDayUrlDao statDayUrlDao;

    @Override
    public PageInfo getAll(StatDayUrlBO statDayUrlBO) {
        try{
            PageHelper.startPage(statDayUrlBO.getPage(), statDayUrlBO.getRows());
            Map<String,Object> re= Bean2Map.trans(statDayUrlBO);
            List<StatDayUrl> list=statDayUrlDao.getAll(re);
            return PageInfo.clone(list);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
