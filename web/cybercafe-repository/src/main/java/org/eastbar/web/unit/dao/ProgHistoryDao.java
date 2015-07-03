/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.unit.dao;

import org.eastbar.web.annotation.MyBatisRepository;
import org.eastbar.web.unit.entity.ProgHistory;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年10月17
 * @time 上午9:59
 * @description :
 */
@MyBatisRepository
public interface ProgHistoryDao {

    ProgHistory getProgHistory(Integer id);
    List<ProgHistory> getAllProgHistory(Map<String, Object> attr);
    void save(ProgHistory progHistory);
    void update(ProgHistory progHistory);
    void delete(Integer id);
}
