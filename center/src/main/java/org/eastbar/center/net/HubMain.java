package org.eastbar.center.net;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by AndySJTU on 2015/6/1.
 */
public class HubMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
                "applicationContext-hub.xml"
        });

        CityCenterListener listener = context.getBean(CityCenterListener.class);
        listener.listen();
    }
}
