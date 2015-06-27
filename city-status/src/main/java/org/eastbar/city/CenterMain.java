package org.eastbar.city;


import org.springframework.context.support.ClassPathXmlApplicationContext;

/**

 * Created by AndySJTU on 2015/4/5.
 */
public class CenterMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
                "applicationContext.xml"
        });

        CenterServer server = context.getBean(CenterServer.class);

        server.start();
    }
}
