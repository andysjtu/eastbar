/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.unit.dao;

import org.eastbar.web.annotation.MyBatisRepository;
import org.eastbar.web.unit.entity.AlarmHistory;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年10月17
 * @time 上午9:59
 * @description :
 */
@MyBatisRepository
public interface AlarmHistoryDao {

    AlarmHistory getAlarmHistory(Integer id);
    List<AlarmHistory> getAllAlarmHistory(Map<String, Object> attr);
    void save(AlarmHistory alarmHistory);
    void update(AlarmHistory alarmHistory);
    void delete(Integer id);
    Long getCountByCode(String code);
}
