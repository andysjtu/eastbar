package org.eastbar.site;

import org.springframework.context.support.ClassPathXmlApplicationContext;

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

    }
}
