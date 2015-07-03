/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.unit;

import org.eastbar.web.PageInfo;
import org.eastbar.web.unit.biz.CustomerHostBO;

/**
 * @author cindy-jia
 * @date 2014年10月21
 * @time 上午9:44
 * @description :
 */
public interface CustomerHostService {

    CustomerHostBO getCustomerHost(Integer id);
    PageInfo getAllCustomerHost(CustomerHostBO customerHostBO);
    Boolean save(CustomerHostBO customerHostBO);
    Boolean update(CustomerHostBO customerHostBO);
    Boolean delete(Integer id);
}
