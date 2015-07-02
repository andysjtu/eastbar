/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.report.dao;

import org.eastbar.web.annotation.MyBatisRepository;
import org.eastbar.web.report.entity.StatHourSiteOper;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年12月30
 * @time 下午1:02
 * @description :
 */
@MyBatisRepository
public interface StatHourSiteOperDao {
    List<StatHourSiteOper> getAll(Map<String, Object> re);

	//分时段日使用时间统计
	List getListByDay(Map<String, Object> re);
	//分时段周使用时间统计
	List getListByWeek(Map<String, Object> re);
}
