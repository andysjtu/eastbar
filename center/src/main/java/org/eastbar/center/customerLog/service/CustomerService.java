/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.customerLog.service;

import org.eastbar.center.statusMachine.HostEvent;
import org.springframework.stereotype.Service;

/**
 * @author cindy-jia
 * @date 2015年06月01
 * @time 下午3:23
 * @description :
 */
@Service
public interface CustomerService {

    public Boolean saveOrUpdate(HostEvent hostEvent);

    public void resetOfflineTime(String siteCode);
}
