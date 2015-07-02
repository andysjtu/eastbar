/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.unit.dao;

import org.eastbar.web.annotation.MyBatisRepository;
import org.eastbar.web.unit.entity.InstantMessageHistory;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年12月05
 * @time 上午10:25
 * @description :
 */
@MyBatisRepository
public interface InstantMessageHistoryDao {

    List<InstantMessageHistory> getAll(Map<String, Object> re);
}
