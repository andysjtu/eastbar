package org.eastbar.log2db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by AndySJTU on 2015/6/24.
 */
public class Log2DbMain {
    public final static Logger logger= LoggerFactory.getLogger(Log2DbMain.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
				"applicationContext-log2db.xml"
        });

		logger.info("启动日志处理入库成功");
    }
}
