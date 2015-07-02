/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author C.lins@aliyun.com
 * @date 2014年11月04
 * @time 下午4:56
 * @description :
 */
public class RmiServiceTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:rmi/applicationContext-service.xml");
        context.getBean("rmiService");
    }

}
