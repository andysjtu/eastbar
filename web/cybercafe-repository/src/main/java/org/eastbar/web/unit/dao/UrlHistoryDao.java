/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.unit.dao;

import org.eastbar.web.annotation.MyBatisRepository;
import org.eastbar.web.unit.entity.UrlHistory;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年10月17
 * @time 上午9:59
 * @description :
 */
@MyBatisRepository
public interface UrlHistoryDao {

    UrlHistory getUrlHistory(Integer id);
    List<UrlHistory> getAllUrlHistory(Map<String, Object> attr);
    void save(UrlHistory urlHistory);
    void update(UrlHistory urlHistory);
    void delete(Integer id);
}
