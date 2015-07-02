/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.unit;

import org.eastbar.web.PageInfo;
import org.eastbar.web.unit.biz.MailHistoryBO;

/**
 * @author cindy-jia
 * @date 2014年12月05
 * @time 上午10:45
 * @description :
 */
public interface MailHistoryService {

    PageInfo getAll(MailHistoryBO mailHistoryBO);
}
