package org.eastbar.log2db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by AndySJTU on 2015/6/24.
 */
public class MQClient {
    public final static Logger logger= LoggerFactory.getLogger(MQClient.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
                "applicationContext-logd-client.xml"
        });

        System.out.println("start.....");
    }
}
