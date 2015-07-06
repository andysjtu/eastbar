/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.manager;

import org.eastbar.common.rmi.RmiService;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

/**
 * @author C.lins@aliyun.com
 * @date 2015年05月24
 * @time 下午5:43
 * @description :
 */
public class RmiClientTest{

    public static void main(String[] args) {
        RmiProxyFactoryBean factory = new RmiProxyFactoryBean();
        factory.setServiceInterface(RmiService.class);
        factory.setServiceUrl("rmi://10.10.10.58:1199/RMIService");
        factory.afterPropertiesSet();

        RmiService userService = (RmiService)factory.getObject();
        System.out.println(userService.Screenshot("3101010007","192.168.9.100").length);
       // userService.sendBannedProgVersion(3,143);
    }
}
