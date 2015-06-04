package org.eastbar.site;

import org.eastbar.site.alert.dao.AlertService;
import org.eastbar.comm.alert.entity.UrlBlockAlert;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by andysjtu on 2015/5/9.
 */
public class SiteMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
                "applicationContext-site.xml"
        });

//        SiteServer server = context.getBean(SiteServer.class);
//        System.out.println("场所服务器正在开启中....");
//        server.start();

        AlertService service = context.getBean(AlertService.class);
        UrlBlockAlert alert = new UrlBlockAlert();
        alert.setAlarmRank("1");
        alert.setAlertTime(new Timestamp(System.currentTimeMillis()));
        service.saveUrlBlockAlert(alert);
        List<UrlBlockAlert> list =service.getOldestUrlBlockRecord();
        System.out.println("list is : "+list);

    }
}
