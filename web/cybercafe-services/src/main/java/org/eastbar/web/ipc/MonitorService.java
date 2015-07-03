/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.ipc;

import org.eastbar.web.PageInfo;
import org.eastbar.web.ipc.biz.MonitorBO;
import org.eastbar.web.ipc.entity.Monitor;

import java.util.List;


/**
 * @author cindy-jia
 * @date 2014年10月21
 * @time 下午3:19
 * @description :
 */
public interface MonitorService {

    MonitorBO get(String monitorCode);
    PageInfo byParentCode(MonitorBO monitorBO);
    Boolean save(MonitorBO monitorBO);
    Boolean delete(String monitorCode);
    Boolean update(MonitorBO monitorBO);
    Boolean deleteMany(String[] monitorCodes);
    List<Monitor> getMonitors();
    List<Monitor> getPlaceMonitors();
    List<Monitor> getMonitorsByType(Integer type);
    public List<Monitor> getUserMonitors();
    List<Monitor> getMonitorListByParent(String parentCode);


	List<Monitor> getArea();


 }
