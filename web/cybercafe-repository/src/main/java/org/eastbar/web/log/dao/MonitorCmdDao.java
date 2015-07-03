/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.log.dao;

import org.eastbar.web.annotation.MyBatisRepository;
import org.eastbar.web.log.entity.MonitorCmd;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年12月08
 * @time 上午9:27
 * @description :
 */
@MyBatisRepository
public interface MonitorCmdDao {
    List<MonitorCmd> getAll(Map<String, Object> re);
    MonitorCmd get(Integer id);
    void save(MonitorCmd monitorCmd);
}
