package org.eastbar.center;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by andyliang on 6/26/15.
 */
public class RedisClientMain {
    public final static Logger logger= LoggerFactory.getLogger(RedisClientMain.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
                "classpath:/applicationContext.xml"
        });

        RedisClient redisClient = context.getBean(RedisClient.class);
        redisClient.testGetPolicyVersion();

    }
}
