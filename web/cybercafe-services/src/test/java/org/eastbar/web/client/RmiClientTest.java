/**
* 上海交通大学-鹏越惊虹信息技术发展有限公司
*         Copyright © 2003-2014
*/
package org.eastbar.web.client;

import org.eastbar.common.rmi.RmiService;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

/**
* @author C.lins@aliyun.com
* @date 2014年11月04
* @time 下午5:43
* @description :
*/
public class RmiClientTest{
    public static void main(String[] args) {
        RmiProxyFactoryBean factory = new RmiProxyFactoryBean();
        factory.setServiceInterface(RmiService.class);
        factory.setServiceUrl("rmi://192.168.9.119:1199/RMIService");
        factory.afterPropertiesSet();

        RmiService userService = (RmiService)factory.getObject();
        System.out.println(userService.restart("new_user_01", "new_password_01"));


    }
}
