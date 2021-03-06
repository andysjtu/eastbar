package org.eastbar.city;


import org.springframework.context.support.ClassPathXmlApplicationContext;

/**

 * Created by AndySJTU on 2015/4/5.
 */
public class CityCenterMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
                "applicationContext-city.xml"
        });

        CityCenterServer server = context.getBean(CityCenterServer.class);

        server.start();
    }
}
