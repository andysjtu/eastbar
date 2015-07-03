/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.report;

import org.eastbar.web.PageInfo;
import org.eastbar.web.report.biz.StatMonthUrlBO;

/**
 * @author cindy-jia
 * @date 2014年12月08
 * @time 下午3:10
 * @description :
 */
public interface StatMonthUrlService {
    PageInfo getAll(StatMonthUrlBO statMonthUrlBO);
}
