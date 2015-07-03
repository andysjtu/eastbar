/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.ipc;

import org.eastbar.web.PageInfo;
import org.eastbar.web.ipc.biz.MonitorLiveDataBO;
import org.eastbar.web.ipc.entity.MonitorLiveData;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年10月21
 * @time 下午3:38
 * @description :
 */
public interface MonitorLiveDataService {
    MonitorLiveData get(Integer id);
    Map<String,MonitorLiveData> getAllLatest();
    List<MonitorLiveData> byMonitorCode(String monitorCode);
    PageInfo getAll(MonitorLiveDataBO monitorLiveDataBO);
}
