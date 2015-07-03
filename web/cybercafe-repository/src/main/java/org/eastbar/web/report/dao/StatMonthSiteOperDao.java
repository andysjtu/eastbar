/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.report.dao;

import org.eastbar.web.annotation.MyBatisRepository;
import org.eastbar.web.report.entity.StatMonthSiteOper;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年12月30
 * @time 下午1:06
 * @description :
 */
@MyBatisRepository
public interface StatMonthSiteOperDao {
    List<StatMonthSiteOper> getAll(Map<String, Object> re);
}
