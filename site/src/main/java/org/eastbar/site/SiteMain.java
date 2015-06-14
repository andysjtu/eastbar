package org.eastbar.site;

import com.google.common.collect.Lists;
import org.eastbar.site.alert.dao.AlertService;
import org.eastbar.comm.alert.entity.UrlBlockAlert;
import org.eastbar.site.policy.PolicyManager;
import org.eastbar.site.policy.PolicyService;
import org.eastbar.site.policy.entity.BanUrl;
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

        SiteServer server = context.getBean(SiteServer.class);
        System.out.println("场所服务器正在开启中....");
        server.start();

//        AlertService service = context.getBean(AlertService.class);
//        UrlBlockAlert alert = new UrlBlockAlert();
//        alert.setAlarmRank("1");
//        alert.setAlertTime(new Timestamp(System.currentTimeMillis()));
//        service.saveUrlBlockAlert(alert);
//        List<UrlBlockAlert> list =service.getOldestUrlBlockRecord();
//        System.out.println("list is : "+list);



//        PolicyService service = context.getBean(PolicyService.class);
//        BanUrl banUrl = new BanUrl();
//        banUrl.setId(1);
//        banUrl.setAlarmRank(2);
//        banUrl.setAlarmType(1);
//        banUrl.setUrlType(1);
//        banUrl.setUrlValue("www.sina.com.cn");
//        banUrl.setIsBlock(1);
//        banUrl.setVerNum(1);
//        List<BanUrl> addList = Lists.newArrayList(banUrl);
//        service.updateUrlPolicy(1,null, addList, null);
//
//        PolicyManager policyManager = context.getBean(PolicyManager.class);
//        System.out.println(policyManager.getBanUrlString());

    }

}
