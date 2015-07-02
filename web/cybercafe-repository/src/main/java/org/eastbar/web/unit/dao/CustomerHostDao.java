/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.unit.dao;

import org.eastbar.web.annotation.MyBatisRepository;
import org.eastbar.web.unit.entity.CustomerHost;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年10月21
 * @time 上午9:28
 * @description :
 */
@MyBatisRepository
public interface CustomerHostDao {

    CustomerHost getCustomerHost(Integer id);
    List<CustomerHost> getAllCustomerHost(Map<String, Object> attr);
    void save(CustomerHost customerHost);
    void update(CustomerHost customerHost);
    void delete(Integer id);
}
