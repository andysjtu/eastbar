/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.customerLog.service.impl;

import org.eastbar.center.statusMachine.HostEvent;
import org.eastbar.center.customerLog.dao.CustomerDao;
import org.eastbar.center.customerLog.dao.CustomerHostDao;
import org.eastbar.center.customerLog.entity.Customer;
import org.eastbar.center.customerLog.entity.CustomerHost;
import org.eastbar.center.customerLog.service.CustomerService;
import org.eastbar.center.strategy.util.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2015年06月01
 * @time 下午3:47
 * @description :
 */
@Component
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private CustomerHostDao customerHostDao;

    @Override
    public Boolean saveOrUpdate(HostEvent hostEvent) {
        Boolean flag = false;
        try {
            if (hostEvent != null && hostEvent.getLoginTime() != null && !"".equals(hostEvent.getLoginTime())) {
                Customer customer = new Customer();
                customer.setAccountId(hostEvent.getAccount());
                String siteCode = hostEvent.getSiteCode();
                customer.setSiteCode(siteCode);
                if (siteCode != null && !"".equals(siteCode)) {
                    String cityCode = siteCode.substring(0, 4);
                    customer.setCityCode(cityCode);
                    String provinceCode = siteCode.substring(0, 3);
                    customer.setProvinceCode(provinceCode);
                    String countyCode = siteCode.substring(0, 6);
                    customer.setCountyCode(countyCode);

                    if (hostEvent.getIdType() != null && !"".equals(hostEvent.getIdType())) {
                        Integer idType = Integer.parseInt(hostEvent.getIdType());
                        customer.setCertType(idType);
                    } else {
                        customer.setCertType(-1);
                    }
                    customer.setName(hostEvent.getName());
                    customer.setNationality(hostEvent.getNation() != null ? hostEvent.getNation() : "中国");
                    customer.setCertId(hostEvent.getCertId());
                    customer.setAuthOrg(hostEvent.getAuthOrg());
                    customer.setOpenTime(hostEvent.getLoginTime());
                    customer.setCloseTime(hostEvent.getLogoutTime());
                    customer.setStatus(hostEvent.getStatus() + "");
                    CustomerHost customerHost = new CustomerHost();
                    customerHost.setSiteCode(siteCode);
                    customerHost.setVersion(hostEvent.getVersion());
                    customerHost.setOsSystem(hostEvent.getOs());
                    customerHost.setIpAdd(hostEvent.getIp());
                    customerHost.setMacAddress(hostEvent.getMacAddress());
                    customerHost.setOnlineTime(hostEvent.getLoginTime());
                    if(hostEvent.getStatus()==3||hostEvent.getStatus()==0){
                        int result = customerDao.update(customer);
                        if (result == 0) {
                            customerDao.save(customer);
                        }
                        customerHost.setOfflineTime(hostEvent.getLogoutTime());
                        if (customerHostDao.get(customerHost) == null) {
                            Customer c = customerDao.getCustomer(customer);
                            customerHost.setCid(c.getId());
                            customerHostDao.save(customerHost);
                        } else {
                            customerHostDao.update(customerHost);
                        }
                        flag = true;
                    }else{
                        flag = false;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }

        return flag;
    }

    @Override
    public void resetOfflineTime(String siteCode) {

        Map<String,String> map = new HashMap<>();
        map.put("siteCode",siteCode);
        map.put("closeTime", Times.now());

        customerDao.reset(map);
        customerHostDao.reset(map);
    }

}
