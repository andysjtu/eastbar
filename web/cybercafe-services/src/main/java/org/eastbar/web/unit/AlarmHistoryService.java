/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.unit;

import org.eastbar.web.PageInfo;
import org.eastbar.web.unit.biz.AlarmHistoryBO;

/**
 * @author cindy-jia
 * @date 2014年10月17
 * @time 上午10:08
 * @description :
 */
public interface AlarmHistoryService {

    AlarmHistoryBO getAlarmHistory(Integer id);
    PageInfo getAllAlarmHistory(AlarmHistoryBO alarmHistoryBO);
    Boolean save(AlarmHistoryBO alarmHistoryBO);
    Boolean update(AlarmHistoryBO alarmHistoryBO);
    Boolean delete(Integer id);
}
