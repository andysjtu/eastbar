package org.eastbar.log2db;

import org.eastbar.log2db.dao.SiteUrlLogDao;
import org.eastbar.log2db.entity.SiteUrlLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by AndySJTU on 2015/6/24.
 */
public class MQClient {
    public final static Logger logger= LoggerFactory.getLogger(MQClient.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
				"applicationContext-log2db.xml"
        });
        
        List<SiteUrlLog> siteList = new ArrayList<>();
//        List<SiteIllegalLog> legelList = new ArrayList<>();
    	SiteUrlLogDao surl = (SiteUrlLogDao) context.getBean("siteUrlLogDao");
//    	SiteIllegalLogDao ilegal = (SiteIllegalLogDao)context.getBean("siteIllegalLogDao");
//
//    	SiteIllegalLog legel = new SiteIllegalLog();
//    	legel.setCustomerId("111");
//    	legel.setCustomerName("张三");
//    	legel.setHostIp("192.168.3.12");
//    	legel.setRecordTime(new Date());
//    	legel.setBlocked("1");
//    	legel.setCustomerType("2");
//    	legelList.add(legel);
//    	SiteIllegalLog legels = new SiteIllegalLog();
//    	legels.setCustomerId("112");
//    	legels.setCustomerName("张三");
//    	legels.setHostIp("192.168.3.132");
//    	legels.setRecordTime(new Date());
//    	legels.setBlocked("1");
//    	legels.setCustomerType("2");
//    	legelList.add(legels);
//    	ilegal.saveListLegalHistory(legelList);
////
    	SiteUrlLog site = new SiteUrlLog();
		site.setCustomerId("0000001");
		site.setCustomerName("张三");
		site.setHostIp("192.168.3.5");
		site.setRecordTime(new Date());
		site.setBlocked(true);
		site.setCustomerType("1");
		site.setUrl("www.baidus.com");
		siteList.add(site);

		SiteUrlLog sites = new SiteUrlLog();
		sites.setCustomerId("00000002");
		sites.setCustomerName("李四");
		sites.setHostIp("192.168.3.5");
		sites.setRecordTime(new Date());
		sites.setBlocked(true);
		sites.setCustomerType("2");
		sites.setUrl("www.baidus.com");
		siteList.add(sites);

		surl.saveListUrlHistory(siteList);
//
		logger.info("启动日志处理入库成功");
    }
}
