/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.report;

import org.eastbar.web.PageInfo;
import org.eastbar.web.report.biz.StatHourSiteOperBO;

import java.util.List;

/**
 * @author cindy-jia
 * @date 2014年12月30
 * @time 下午1:22
 * @description :
 */
public interface StatHourSiteOperService {
    PageInfo getAll(StatHourSiteOperBO statHourSiteOperBO);
    List getListByDay(StatHourSiteOperBO statHourSiteOperBO);
	List getListByWeek(StatHourSiteOperBO statHourSiteOperBO);
}
