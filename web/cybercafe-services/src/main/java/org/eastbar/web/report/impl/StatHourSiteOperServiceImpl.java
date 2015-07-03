/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.report.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.eastbar.web.Bean2Map;
import org.eastbar.web.PageInfo;
import org.eastbar.web.pagehelper.PageHelper;
import org.eastbar.web.report.StatHourSiteOperService;
import org.eastbar.web.report.biz.StatHourSiteOperBO;
import org.eastbar.web.report.dao.StatHourSiteOperDao;
import org.eastbar.web.report.entity.StatHourSiteOper;
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
public class StatHourSiteOperServiceImpl implements StatHourSiteOperService {

    @Autowired
    private StatHourSiteOperDao statHourSiteOperDao;

    @Override
    public PageInfo getAll(StatHourSiteOperBO statHourSiteOperBO) {
        try{
            PageHelper.startPage(statHourSiteOperBO.getPage(), statHourSiteOperBO.getRows());
            Map<String,Object> re= Bean2Map.trans(statHourSiteOperBO);
            List<StatHourSiteOper> list=statHourSiteOperDao.getAll(re);
            return PageInfo.clone(list);
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List getListByDay(StatHourSiteOperBO statHourSiteOperBO) {
        try{
            Map<String,Object> re= Bean2Map.trans(statHourSiteOperBO);
            List lists=statHourSiteOperDao.getListByDay(re);
            return lists;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

	@Override
	public List getListByWeek(StatHourSiteOperBO statHourSiteOperBO) {
		try{
			Map<String,Object> re= Bean2Map.trans(statHourSiteOperBO);
			List lists=statHourSiteOperDao.getListByWeek(re);
			return lists;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
