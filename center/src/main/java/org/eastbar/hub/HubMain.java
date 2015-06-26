package org.eastbar.hub;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by AndySJTU on 2015/6/1.
 */
public class HubMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
                "applicationContext.xml"
        });

        CenterListener listener = context.getBean(CenterListener.class);
        listener.listen();
    }
}
