/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.unit.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.eastbar.web.Bean2Map;
import org.eastbar.web.PageInfo;
import org.eastbar.web.pagehelper.PageHelper;
import org.eastbar.web.unit.CustomerHostService;
import org.eastbar.web.unit.biz.CustomerHostBO;
import org.eastbar.web.unit.dao.CustomerHostDao;
import org.eastbar.web.unit.entity.CustomerHost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年10月21
 * @time 上午9:44
 * @description :
 */
@Service
@Transactional
public class CustomerHostServiceImpl implements CustomerHostService {

    @Autowired
    private CustomerHostDao customerHostDao;

    @Override
    public CustomerHostBO getCustomerHost(Integer id) {
        try {
            CustomerHostBO customerHostBO=new CustomerHostBO();
            BeanUtils.copyProperties(customerHostBO, customerHostDao.getCustomerHost(id));
            return customerHostBO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PageInfo getAllCustomerHost(CustomerHostBO customerHostBO) {
        try {
            PageHelper.startPage(customerHostBO.getPage(), customerHostBO.getRows());

            Long t1 = System.currentTimeMillis();
            Map<String,Object> re = Bean2Map.trans(customerHostBO);
            System.out.println(System.currentTimeMillis() - t1 + " : " + re);
            List<CustomerHost> list = customerHostDao.getAllCustomerHost(re);
            PageInfo pr= PageInfo.clone(list);
            return pr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public Boolean save(CustomerHostBO customerHostBO) {
        try {
            CustomerHost customerHost=new CustomerHost();
            BeanUtils.copyProperties(customerHost, customerHostBO);
            customerHostDao.save(customerHost);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean update(CustomerHostBO customerHostBO) {
        try {
            CustomerHost customerHost=new CustomerHost();
            BeanUtils.copyProperties(customerHost, customerHostBO);
            customerHostDao.update(customerHost);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Integer id) {
        try {

            customerHostDao.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
