package org.eastbar.alert2db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by AndySJTU on 2015/6/24.
 */
public class Mq2DbMain {
    public final static Logger logger= LoggerFactory.getLogger(Mq2DbMain.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
                "applicationContext-alert2db.xml"
        });

        System.out.println("启动告警处理入库程序成功");
    }
}
