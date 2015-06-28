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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        Boolean flag=false;
        try{
            if(hostEvent!=null){
                Customer customer=new Customer();
                customer.setAccountId(hostEvent.getAccount());
                String siteCode=hostEvent.getSiteCode();
                customer.setSiteCode(siteCode);
                String cityCode=siteCode.substring(0,4);
                customer.setCityCode(cityCode);
                String provinceCode=siteCode.substring(0,3);
                customer.setProvinceCode(provinceCode);
                String countyCode=siteCode.substring(0,6);
                customer.setCountyCode(countyCode);
                customer.setName(hostEvent.getName());
                customer.setNationality(hostEvent.getName());
                customer.setCertId(hostEvent.getCertId());
                customer.setCertType(Integer.parseInt(hostEvent.getIdType()));
                customer.setAuthOrg(hostEvent.getAuthOrg());
                customer.setOpenTime(hostEvent.getLoginTime());
                customer.setCloseTime(hostEvent.getLogoutTime());
                customer.setNationality(hostEvent.getNation());
                customer.setStatus(hostEvent.getStatus()+"");
                CustomerHost customerHost=new CustomerHost();
                customerHost.setVersion(hostEvent.getVersion());
                customerHost.setOsSystem(hostEvent.getOs());
                customerHost.setIpAdd(hostEvent.getIp());
                customerHost.setMacAddress(hostEvent.getMacAddress());
                customerHost.setOnlineTime(hostEvent.getLoginTime());
                int result=customerDao.save(customer);
                if(result==0){
                    customerHost.setOfflineTime(hostEvent.getLogoutTime());
                    customerDao.update(customer);
                    if(customerHostDao.get(customerHost)==null){
                        Customer c=customerDao.getCustomer(customer);
                        customerHost.setCid(c.getId());
                        customerHostDao.save(customerHost);
                    }else{
                        customerHostDao.update(customerHost);
                    }

                }else{
                    customerHost.setCid(customer.getId());
                    customerHostDao.save(customerHost);
                }
                flag=true;
            }
        }catch (Exception e){
            e.printStackTrace();
            flag=false;
        }

        return flag;
    }

}
