/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.unit;

import org.eastbar.web.PageInfo;
import org.eastbar.web.unit.biz.CustomerHistoryBO;

/**
 * @author cindy-jia
 * @date 2014年10月17
 * @time 上午10:12
 * @description :
 */
public interface CustomerHistoryService {
    CustomerHistoryBO getCustomerHistory(Integer id);
    PageInfo getAllCustomerHistory(CustomerHistoryBO customerHistoryBO);
    Boolean save(CustomerHistoryBO customerHistoryBO);
    Boolean update(CustomerHistoryBO customerHistoryBO);
    Boolean delete(Integer id);
}
