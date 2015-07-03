/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.unit.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.eastbar.web.Bean2Map;
import org.eastbar.web.PageInfo;
import org.eastbar.web.pagehelper.PageHelper;
import org.eastbar.web.shiro.ShiroCustomUtils;
import org.eastbar.web.unit.CustomerHistoryService;
import org.eastbar.web.unit.biz.CustomerHistoryBO;
import org.eastbar.web.unit.dao.CustomerHistoryDao;
import org.eastbar.web.unit.entity.CustomerHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年10月17
 * @time 上午10:20
 * @description :
 */
@Service
@Transactional
public class CustomerHistoryServiceImpl implements CustomerHistoryService {

    @Autowired
    private CustomerHistoryDao customerHistoryDao;

    @Override
    public CustomerHistoryBO getCustomerHistory(Integer id){
        try {
            CustomerHistoryBO customerHistoryBO=new CustomerHistoryBO();
            BeanUtils.copyProperties(customerHistoryBO, customerHistoryDao.getCustomerHistory(id));
            return customerHistoryBO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PageInfo getAllCustomerHistory(CustomerHistoryBO customerHistoryBO){
        try {
            PageHelper.startPage(customerHistoryBO.getPage(), customerHistoryBO.getRows());

            Long t1 = System.currentTimeMillis();
            Map<String,Object> re = Bean2Map.trans(customerHistoryBO);
            //获取当前用户的域
            List<String> monitorCodes= ShiroCustomUtils.getMonitors();
            if(monitorCodes.size()>0){//判断是否为超级管理员，如果是超级管理员，则size为0
                re.put("monitor",monitorCodes.get(0));
            }
            System.out.println(System.currentTimeMillis() - t1 + " : " + re);
            List<CustomerHistory> list = customerHistoryDao.getAllCustomerHistory(re);
            PageInfo pr= PageInfo.clone(list);
            return pr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public Boolean save(CustomerHistoryBO customerHistoryBO){
        try {
            CustomerHistory customerHistory=new CustomerHistory();
            BeanUtils.copyProperties(customerHistory, customerHistoryBO);
            customerHistoryDao.save(customerHistory);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean update(CustomerHistoryBO customerHistoryBO){
        try {
            CustomerHistory customerHistory=new CustomerHistory();
            BeanUtils.copyProperties(customerHistory, customerHistoryBO);
            customerHistoryDao.update(customerHistory);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean delete(Integer id){
        try {

            customerHistoryDao.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
