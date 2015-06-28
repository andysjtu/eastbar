/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.customerLog.dao.impl;

import org.eastbar.center.customerLog.dao.CustomerDao;
import org.eastbar.center.customerLog.entity.Customer;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author cindy-jia
 * @date 2015年05月30
 * @time 下午4:29
 * @description :
 */
@Component
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public Customer getCustomer(Customer customer) {
        Customer customer1=sqlSession.selectOne("getCustomer",customer);
        return customer1;
    }

    @Override
    public int save(Customer customer) {
        int result=sqlSession.insert("saveCustomer",customer);
        return result;
    }

    @Override
    public int update(Customer customer) {
        int result=sqlSession.update("updateCustomer",customer);
        return result;
    }

}
