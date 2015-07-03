/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.log.dao;

import org.eastbar.web.annotation.MyBatisRepository;
import org.eastbar.web.log.entity.OperLog;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年12月03
 * @time 下午3:41
 * @description :
 */
@MyBatisRepository
public interface OperLogDao {
     List<OperLog> getAll(Map<String, Object> re);
    List<OperLog> getAllAdmin(Map<String, Object> re);
     void save(OperLog operLog);
}
