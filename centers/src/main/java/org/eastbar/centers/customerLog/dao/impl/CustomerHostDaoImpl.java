/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.centers.customerLog.dao.impl;

import org.eastbar.centers.customerLog.dao.CustomerHostDao;
import org.eastbar.centers.customerLog.entity.CustomerHost;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author cindy-jia
 * @date 2015年05月30
 * @time 下午4:35
 * @description :
 */
@Component
public class CustomerHostDaoImpl implements CustomerHostDao {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public void save(CustomerHost customerHost) {
        sqlSession.insert("saveHost",customerHost);
    }

    @Override
    public void update(CustomerHost customerHost) {
        sqlSession.update("updateHost",customerHost);
    }


    @Override
    public CustomerHost get(CustomerHost customerHost) {
        CustomerHost customerHost1=sqlSession.selectOne("getCustomerHost", customerHost);
        return customerHost1;
    }
}
