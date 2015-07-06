package org.eastbar.alert2mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by AndySJTU on 2015/6/4.
 */
public class Alert2MqMain {
    public final static Logger logger= LoggerFactory.getLogger(Alert2MqMain.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
                "applicationContext-alert2mq.xml"
        });

        AlertListener listener = context.getBean(AlertListener.class);
        logger.info("开始启动告警接受服务器......");
        listener.listen();
    }
}
