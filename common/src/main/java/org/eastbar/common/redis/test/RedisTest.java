/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.common.redis.test;

import org.eastbar.common.redis.CenterRedisService;
import org.eastbar.common.redis.SiteRedisService;
import org.eastbar.common.redis.WebRedisService;
import org.eastbar.common.rmi.RmiService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2015年05月05
 * @time 上午11:14
 * @description :
 */
public class RedisTest {

    public static void main(String[] args) throws Exception{
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                new String[] {"classpath:applicationContext.xml"});
        CenterRedisService centerRedisService= (CenterRedisService) ctx.getBean("centerRedisServiceImpl");
        WebRedisService webRedisService=(WebRedisService)ctx.getBean("webRedisServiceImpl");
        SiteRedisService siteRedisService=(SiteRedisService)ctx.getBean("siteRedisServiceImpl");
        RmiService rmiService=(RmiService)ctx.getBean("rmiServiceImpl");
        //redis测试begin
        Map<String,String> center=new HashMap<>();

        //rmiService.sendBannedProgVersion(3,143);
//        center.put("monitorCode","1234");
//        center.put("totalSite","231");
//        centerRedisService.centerHashPut(center);
//        centerRedisService.terminalSetPut("3101010001", "192.168.3.21");
//        centerRedisService.terminalSetPut("3101010001","192.168.3.22");
//        centerRedisService.terminalSetPut("3101010001","192.168.3.23");
//        Map<String,String> terminal1=new HashMap<>();
//        terminal1.put("siteCode","3101010001");
//        terminal1.put("hostIp","192.168.3.21");
//        centerRedisService.terminalHashPut(terminal1);
//        Map<String,String> terminal2=new HashMap<>();
//        terminal2.put("siteCode","3101010001");
//        terminal2.put("hostIp","192.168.3.22");
//        centerRedisService.terminalHashPut(terminal2);
//        Map<String,String> terminal3=new HashMap<>();
//        terminal3.put("siteCode","3101010001");
//        terminal3.put("hostIp","192.168.3.23");
//       centerRedisService.terminalHashPut(terminal3);
        //System.out.println("开始输出");
       // System.out.println(webRedisService.getTerminalHash(webRedisService.getIpSet("3101010001"),"3101010001"));
        //redis测试end

//        List<BannedProg> scs=new ArrayList<BannedProg>();
//        BannedProg bannedProg=new BannedProg();
//        bannedProg.setId(1);
//        bannedProg.setMonitorCode("310101");
//        bannedProg.setVerNum(1L);
//        bannedProg.setProgName("test");
//        scs.add(bannedProg);
//        BannedProg bannedProg0=new BannedProg();
//        bannedProg0.setId(4);
//        bannedProg0.setMonitorCode("310101");
//        bannedProg0.setProgName("test0");
//        bannedProg0.setVerNum(1L);
//        scs.add(bannedProg0);
//        BannedProg bannedProg1=new BannedProg();
//        bannedProg1.setId(2);
//        bannedProg1.setMonitorCode("310");
//        bannedProg1.setVerNum(1L);
//        scs.add(bannedProg1);
//        BannedProg bannedProg2=new BannedProg();
//        bannedProg2.setId(3);
//        bannedProg2.setMonitorCode("310201");
//        bannedProg2.setVerNum(1L);
//        scs.add(bannedProg2);
//
//        List<SpecialCustomer> list=new ArrayList<>();
//
//        SpecialCustomer specialCustomer=new SpecialCustomer();
//        specialCustomer.setId(1);
//        specialCustomer.setMonitorCode("310101");
//        specialCustomer.setVerNum(2L);
//        specialCustomer.setName("test");
//        list.add(specialCustomer);
//        SpecialCustomer specialCustomer1=new SpecialCustomer();
//        specialCustomer1.setId(4);
//        specialCustomer1.setMonitorCode("310101");
//        specialCustomer1.setName("test0");
//        specialCustomer1.setVerNum(2L);
//        list.add(specialCustomer1);
//        SpecialCustomer specialCustomer2=new SpecialCustomer();
//        specialCustomer2.setId(2);
//        specialCustomer2.setMonitorCode("310");
//        specialCustomer2.setVerNum(2L);
//        list.add(specialCustomer2);
//        SpecialCustomer specialCustomer3=new SpecialCustomer();
//        specialCustomer3.setId(3);
//        specialCustomer3.setMonitorCode("310201");
//        specialCustomer3.setVerNum(2L);
//        list.add(specialCustomer3);
//
//        //System.out.println(Po2Json.toJson(specialCustomer));
//        centerRedisService.saveProgVersionList(scs,"1");
//        centerRedisService.saveSpecialCustomerVersionList(list, "2");
//        System.out.println(siteRedisService.returnProgVersionList("3101010001", 1));
//        System.out.println(siteRedisService.returnSpecialCustomerVersionList("3101010001", 2));
//        centerRedisService.saveUpdatedVersion("bannedProg", 0);
//        centerRedisService.saveUpdatedVersion("specialCustomer",1);

    }
}
