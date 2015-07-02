/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.report.impl;

import org.eastbar.web.Bean2Map;
import org.eastbar.web.PageInfo;
import org.eastbar.web.pagehelper.PageHelper;
import org.eastbar.web.report.StatYearUrlService;
import org.eastbar.web.report.biz.StatYearUrlBO;
import org.eastbar.web.report.dao.StatYearUrlDao;
import org.eastbar.web.report.entity.StatYearUrl;
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
public class StatYearUrlServiceImpl implements StatYearUrlService {

    @Autowired
    private StatYearUrlDao statYearUrlDao;

    @Override
    public PageInfo getAll(StatYearUrlBO statYearUrlBO) {
        try{
            PageHelper.startPage(statYearUrlBO.getPage(), statYearUrlBO.getRows());
            Map<String,Object> re= Bean2Map.trans(statYearUrlBO);
            List<StatYearUrl> list=statYearUrlDao.getAll(re);
            PageInfo pageInfo=PageInfo.clone(list);
            return pageInfo;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
