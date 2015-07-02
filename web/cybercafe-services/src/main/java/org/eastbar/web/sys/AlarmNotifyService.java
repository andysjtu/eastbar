/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.sys;

import org.eastbar.web.sys.entity.AlarmNotify;

import java.util.List;

/**
 * @author cindy-jia
 * @date 2014年10月17
 * @time 下午2:21
 * @description :
 */
public interface AlarmNotifyService {

    AlarmNotify getAlarmNotify(Integer id);
    List<AlarmNotify> getAllAlarmNotify();
    Boolean save(AlarmNotify alarmNotify);
    Boolean update(AlarmNotify alarmNotify);
    Boolean delete(Integer id);
    List<AlarmNotify> getAlarmNotifysByCondition(AlarmNotify alarmNotify);
}
