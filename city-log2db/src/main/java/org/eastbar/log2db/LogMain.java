package org.eastbar.log2db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by AndySJTU on 2015/6/4.
 */
public class LogMain {
    public final static Logger logger= LoggerFactory.getLogger(LogMain.class);

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
                "applicationContext-log2db.xml"
        });

        LogListener listener = context.getBean(LogListener.class);
        logger.info("开始启动日志接受服务器......");
        listener.listen();

    }


}
