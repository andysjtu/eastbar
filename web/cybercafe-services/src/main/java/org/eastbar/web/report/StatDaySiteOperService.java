/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.report;

import org.eastbar.web.PageInfo;
import org.eastbar.web.report.biz.StatDaySiteOperBO;

/**
 * @author cindy-jia
 * @date 2014年12月30
 * @time 下午1:29
 * @description :
 */
public interface StatDaySiteOperService {
    PageInfo getAll(StatDaySiteOperBO statDaySiteOperBO);
    PageInfo getAllRank(StatDaySiteOperBO statDaySiteOperBO);
}
