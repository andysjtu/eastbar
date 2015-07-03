/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.sys.dao;

import org.eastbar.web.annotation.MyBatisRepository;
import org.eastbar.web.sys.entity.AlarmNotify;

import java.util.List;

/**
 * @author cindy-jia
 * @date 2014年10月17
 * @time 下午2:04
 * @description :
 */
@MyBatisRepository
public interface AlarmNotifyDao {

    AlarmNotify getAlarmNotify(Integer id);
    List<AlarmNotify> getAllAlarmNotify();
    void save(AlarmNotify alarmNotify);
    void update(AlarmNotify alarmNotify);
    void delete(Integer id);
    List<AlarmNotify> getAlarmNotifysByCondition(AlarmNotify alarmNotify);
}
