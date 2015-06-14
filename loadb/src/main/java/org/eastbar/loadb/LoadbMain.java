package org.eastbar.loadb;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.applet.Main;

/**
 * Created by AndySJTU on 2015/6/11.
 */
public class LoadbMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
                "applicationContext-loadb.xml"
        });

        LoadbListener loadbListener = context.getBean(LoadbListener.class);
        loadbListener.listen();
    }
}
