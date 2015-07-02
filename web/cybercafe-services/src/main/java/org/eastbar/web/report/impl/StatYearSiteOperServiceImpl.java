/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.report.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.eastbar.web.Bean2Map;
import org.eastbar.web.PageInfo;
import org.eastbar.web.Percent;
import org.eastbar.web.ipc.dao.SiteDao;
import org.eastbar.web.ipc.entity.Site;
import org.eastbar.web.pagehelper.PageHelper;
import org.eastbar.web.report.StatYearSiteOperService;
import org.eastbar.web.report.biz.StatYearSiteOperBO;
import org.eastbar.web.report.dao.StatYearSiteOperDao;
import org.eastbar.web.report.entity.StatYearSiteOper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年12月30
 * @time 下午1:31
 * @description :
 */
@Service
public class StatYearSiteOperServiceImpl implements StatYearSiteOperService {

    @Autowired
    private StatYearSiteOperDao statYearSiteOperDao;
    @Autowired
    private SiteDao siteDao;

    @Override
    public PageInfo getAll(StatYearSiteOperBO statYearSiteOperBO) {
        try{
            PageHelper.startPage(statYearSiteOperBO.getPage(), statYearSiteOperBO.getRows());
            Map<String,Object> re= Bean2Map.trans(statYearSiteOperBO);
            List<StatYearSiteOper> list=statYearSiteOperDao.getAll(re);
            return PageInfo.clone(list);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return null;
    }
}
