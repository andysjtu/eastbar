/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.centers.strategy.dao.impl;

import org.eastbar.centers.Po2Json;
import org.eastbar.centers.strategy.dao.SpecialCustomerDao;
import org.eastbar.centers.strategy.entity.SpecialCustomer;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cindy-jia
 * @date 2015年05月07
 * @time 上午10:28
 * @description :
 */
@Repository
public class SpecialCustomerDaoImpl implements SpecialCustomerDao {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<SpecialCustomer> getAll(Integer parmas) {
        List<SpecialCustomer> specialCustomers=sqlSession.selectList("getAllSpecialCustomer",parmas);
        return specialCustomers;
    }

    @Override
    public List<String> getMonitorCodesByVersion(Integer params) {
        List<String> monitorCodes=sqlSession.selectList("getSpecialCustomerMonitorCodesByVersion", params);
        return monitorCodes;
    }

    @Override
    public List<SpecialCustomer> getAllAddSpecials(Integer parmas) {
        List<SpecialCustomer> specialCustomers=sqlSession.selectList("getAllAddSpecials",parmas);
        return specialCustomers;
    }

    @Override
    public List<SpecialCustomer> getAllEditSpecails(Integer parmas) {
        List<SpecialCustomer> specialCustomers=sqlSession.selectList("getAllEditSpecails",parmas);
        return specialCustomers;
    }

    @Override
    public List<SpecialCustomer> getAllRemoveSpecials(Integer parmas) {
        List<SpecialCustomer> specialCustomers=sqlSession.selectList("getAllRemoveSpecials",parmas);
        return specialCustomers;
    }

    @Override
    public String getSpecialCustomersByCondition(List<SpecialCustomer> specialCustomers, String monitorCode) {
        List<SpecialCustomer> specialCustomerList=new ArrayList<>();

        for(int i=0;i<specialCustomers.size();i++){
            SpecialCustomer specialCustomer=specialCustomers.get(i);
            String monitorCode_1=specialCustomer.getMonitorCode();
            if(monitorCode.equals(monitorCode_1)){
                specialCustomerList.add(specialCustomer);
            }
        }
        if(specialCustomerList.size()>0){
            return Po2Json.toJson(specialCustomerList);
        }else{
            return "";
        }

    }
}
