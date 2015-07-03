/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.ipc.dao;

import org.eastbar.web.annotation.MyBatisRepository;
import org.eastbar.web.ipc.entity.MonitorLiveData;

import java.util.List;
import java.util.Map;

/**
 * @author C.lins@aliyun.com
 * @date 2014年10月14
 * @time 下午4:47
 * @description :
 */
@MyBatisRepository
public interface MonitorLiveDataDao {
    MonitorLiveData get(Integer id);
    List<MonitorLiveData> byMonitorCode(String monitorCode);
    List<MonitorLiveData> getAllLatest();
    List<MonitorLiveData> getAll(Map<String, Object> re);
}
