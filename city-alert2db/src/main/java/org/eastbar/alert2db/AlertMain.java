package org.eastbar.alert2db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by AndySJTU on 2015/6/4.
 */
public class AlertMain {
    public final static Logger logger= LoggerFactory.getLogger(AlertMain.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
                "applicationContext-alert2db.xml"
        });

    }
}
