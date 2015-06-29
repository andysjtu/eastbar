package org.eastbar.center;


import org.eastbar.center.net.CityCenterListener;
import org.eastbar.center.customerLog.service.CustomerService;
import org.eastbar.center.net.CityCenterListener;
import org.eastbar.center.statusMachine.HostEvent;
import org.eastbar.center.statusMachine.IEventPipe;
import org.eastbar.center.statusMachine.core.EventPipe;
import org.eastbar.center.statusMachine.core.StatusSnapshotFactory;
import org.eastbar.center.statusMachine.StatusMachine;
import org.eastbar.center.strategy.util.Times;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

public class ManagerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

        //初始化Status快照
        try{
            String OS = System.getProperty("os.name").toLowerCase();
            System.out.println("os:"+OS);
            if(OS.indexOf("windows")!=-1){
                StatusSnapshotFactory.init(new File("d:/status.res"));
            }else if(OS.indexOf("linux")!=-1){
                StatusSnapshotFactory.init(new File("/usr/local/eastbar/status.res"));
            }

        }catch(Throwable t){
            System.exit(1);
        }

        System.setProperty("java.rmi.server.hostname","192.168.9.119");//建议从配置文件加载。

        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                new String[] { "classpath:applicationContext.xml","classpath:applicationContext-eastbar.xml"
                });
        IEventPipe pipe = (EventPipe) ctx.getBean("eventPipe");
		//启动内部核心事件处理模块
        StatusMachine sm = (StatusMachine)ctx.getBean("statusMachine");
	    sm.onStart();
        System.out.println("--------------start manager-----------------");


        System.out.println("------启动网络侦听-------");
        CityCenterListener listener = ctx.getBean(CityCenterListener.class);
        listener.listen();

//        //启动事件模拟 器
//        Thread.sleep(10000);
//        HostEvent event2 = new HostEvent();
//        event2.setAccount("12132456789");
//        event2.setAuthOrg("中国公安");
//        event2.setCertId("2132789");
//        event2.setIdType("2");
//        event2.setIp("196.186.3.125");
//        event2.setLoginTime(Times.now());
//        event2.setMacAddress("21-02-2-34");
//        event2.setName("");
//        event2.setNation("中国");
//        event2.setOs("win 7");
//        event2.setSiteCode("3101010001");
//        event2.setStatus(3);
//        event2.setVersion("2");
//        pipe.addEvents(event2);
//        System.out.println( Times.now()+"-------------增加Event2-上机----------------");
/**
 *
 *
 *
 hostEvent = org.eastbar.center.statusMachine.HostEvent@7deb98f6[
 account=1234567
 name=林超
 certId=410107197902026432
 idType=1
 authOrg=徐汇公安局
 loginTime=<null>
 logoutTime=<null>
 nation=<null>
 version=1.0.0.1
 os=Microsoft Windows 7
 macAddress=64.5A.04.CF.BB.90
 ip=192.168.9.100
 status=3
 siteCode=3101010001

 hostEvent = org.eastbar.center.statusMachine.HostEvent@76877f0f[
 account=1234567
 name=梁琳
 certId=310107197902026432
 idType=1
 authOrg=徐汇公安局
 loginTime=<null>
 logoutTime=<null>
 nation=<null>
 version=<null>
 os=<null>
 macAddress=<null>
 ip=192.168.9.156
 status=1
 siteCode=3101010001

 hostEvent = org.eastbar.center.statusMachine.HostEvent@39a6ee94[
 account=<null>
 name=<null>
 certId=<null>
 idType=<null>
 authOrg=<null>
 loginTime=<null>
 logoutTime=<null>
 nation=<null>
 version=1.0.0.1
 os=Microsoft Windows 7
 macAddress=00.05.9A.3C.78.00
 ip=172.17.0.5
 status=2
 siteCode=3101010001
 */
//
//        Thread.sleep(10000);
//        HostEvent event3 = new HostEvent();
//        event3.setAccount("qazxsw");
//        event3.setAuthOrg("中国公安");
//        event3.setCertId("12345678978");
//        event3.setIdType("2");
//        event3.setIp("196.186.7.25");
//        event3.setLoginTime(Times.now());
//        event3.setMacAddress("61-07-5-74");
//        event3.setName("王兰");
//        event3.setNation("中国");
//        event3.setOs("win 7");
//        event3.setSiteCode("3101020002");
//        event3.setStatus(3);
//        event3.setVersion("2");
//        pipe.addEvents(event3);
//        System.out.println( Times.now()+"-------------增加Event3-上机----------------");


//        Thread.sleep(10000);
//        event2.setStatus(0);
//        event2.setLogoutTime(Times.now());
//        pipe.addEvents(event2);
//        System.out.println( Times.now()+"-------------增加Event2-下机----------------");


//        Thread.sleep(10000);
//        event3.setStatus(2);
//        event3.setLogoutTime(Times.now());
//        pipe.addEvents(event3);
//        System.out.println( Times.now()+"-------------增加Event3-空闲----------------");


    //    Center.getInstance().toString();
	}
}
