package org.eastbar.alert2db;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eastbar.alert2db.dao.SiteAlertDao;
import org.eastbar.alert2db.entity.SiteAlert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by AndySJTU on 2015/6/4.
 */

public class AlertSaver {
    public final static Logger logger = LoggerFactory.getLogger(AlertSaver.class);

    @Autowired
//    private AlertService alertService;
//    private final ExecutorService service = Executors.newFixedThreadPool(50);
    
    public static void main(String[] args) {
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
                "applicationContext-alertd.xml"
        });
    	List<SiteAlert> alertList = new ArrayList<>();
    	SiteAlertDao alertDao = (SiteAlertDao)context.getBean("siteAlertDao");
    	
    	SiteAlert alert = new SiteAlert();
    	alert.setCustomerId("1");
    	alert.setCustomerName("gg");
    	alert.setHostIp("192.168.1.1");
    	alert.setRecordTime(new Date());
    	alert.setCustomerType("1");
    	alert.setAlarmType("2");
    	alert.setAlarmLevel("3");
    	alert.setAlarmContent("暴力");
    	
    	alertList.add(alert);
    	
    	alertDao.saveListAlarmHistory(alertList);
    	System.out.println("alert start");
    	
	}

}
