/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.centers.customerLog.dao;


import org.eastbar.centers.customerLog.entity.Customer;
import org.springframework.stereotype.Repository;

/**
 * @author cindy-jia
 * @date 2014年10月17
 * @time 上午9:59
 * @description :
 */
@Repository
public interface CustomerDao {

    Customer getCustomer(Customer customer);

    int save(Customer customer);

    int update(Customer customer);
}
