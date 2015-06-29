package org.eastbar.center.manager;

import org.eastbar.common.rmi.RmiService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest
{


   public static void main(String[] args){
       ApplicationContext ctx = new ClassPathXmlApplicationContext(
               new String[] {"classpath:applicationContext.xml","classpath:applicationContext-eastbar.xml"});

       RmiService rmiService=(RmiService)ctx.getBean("rmiServiceImpl");
     //  rmiService.sendBannedProgVersion(6,145);
      // rmiService.sendSpecialCustomerVersion(2,143);
      // rmiService.sendKeyWordVersion(2,142);

       Integer[] ids={181,182};
       rmiService.sendBannedUrlVersion(20,ids);

   }
}
