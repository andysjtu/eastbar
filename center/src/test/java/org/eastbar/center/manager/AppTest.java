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
       Integer[] ids={149,150};
       rmiService.sendBannedProgVersion(9,ids,"realease");  //已测
      // rmiService.sendSpecialCustomerVersion(3,ids);已测
      //rmiService.sendKeyWordVersion(3,ids,"release");


     ///  rmiService.sendBannedUrlVersion(23,ids);  已测

   }
}
