/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.log;

import org.eastbar.web.PageInfo;
import org.eastbar.web.log.biz.OperLogBO;

/**
 * @author cindy-jia
 * @date 2014年12月04
 * @time 下午4:13
 * @description :
 */
public interface OperLogService {

    PageInfo getAll(OperLogBO operLogBO);
    PageInfo getAllAdmin(OperLogBO operLogBO);
    void add(OperLogBO operLogBO);
}
