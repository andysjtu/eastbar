package org.eastbar.logd;

import org.eastbar.comm.Listener;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by AndySJTU on 2015/6/4.
 */
public class LogMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
                "applicationContext-logd.xml"
        });

        LogListener listener = context.getBean(LogListener.class);
        listener.listen();
    }


}
