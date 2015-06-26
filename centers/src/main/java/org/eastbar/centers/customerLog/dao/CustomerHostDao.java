/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.centers.customerLog.dao;

import org.eastbar.centers.customerLog.entity.CustomerHost;
import org.springframework.stereotype.Repository;

/**
 * @author cindy-jia
 * @date 2014年10月21
 * @time 上午9:28
 * @description :
 */
@Repository
public interface CustomerHostDao {

    void save(CustomerHost customerHost);

    void update(CustomerHost customerHost);

    CustomerHost get(CustomerHost customerHost);
}
