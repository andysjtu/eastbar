/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.syslog;


import org.eastbar.web.PageInfo;
import org.eastbar.web.syslog.biz.SysLogBO;
import org.eastbar.web.syslog.entity.SysLog;

public interface SysLogService {

    void add(SysLog sysLog);
    PageInfo getAll(SysLogBO sysLogBO);
}
