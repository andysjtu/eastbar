/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.strategy.dao;


import org.eastbar.center.strategy.entity.SpecialCustomer;

import java.util.List;

/**
 * @author cindy-jia
 * @date 2014年10月14
 * @time 上午9:06
 * @description :
 */
public interface SpecialCustomerDao {

    List<SpecialCustomer> getAll(Integer attr);

    List<SpecialCustomer> getAllAddSpecials(Integer version);

    List<SpecialCustomer> getAllEditSpecails(Integer version);

    List<SpecialCustomer> getAllRemoveSpecials(Integer version);


    List<String> getMonitorCodesByVersion(Integer params);

    String getSpecialCustomersByCondition(List<SpecialCustomer> specialCustomers,String monitorCode);

    void update(SpecialCustomer specialCustomer);

    SpecialCustomer get(Integer id);

}
