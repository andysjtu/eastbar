/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.report.dao;

import org.eastbar.web.annotation.MyBatisRepository;
import org.eastbar.web.report.entity.StatDayUrl;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年12月08
 * @time 下午3:12
 * @description :
 */
@MyBatisRepository
public interface StatDayUrlDao {

    List<StatDayUrl> getAll(Map<String, Object> re);
}
