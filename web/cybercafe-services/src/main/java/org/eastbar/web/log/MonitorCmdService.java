/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.log;

import org.eastbar.web.PageInfo;
import org.eastbar.web.log.biz.MonitorCmdBO;

/**
 * @author cindy-jia
 * @date 2014年12月08
 * @time 上午9:43
 * @description :
 */
public interface MonitorCmdService {
    PageInfo getAll(MonitorCmdBO monitorCmdBO);
    MonitorCmdBO get(Integer id);
    Boolean save(MonitorCmdBO monitorCmdBO);
}
