package org.eastbar.capture;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.applet.Main;

/**
 * Created by AndySJTU on 2015/6/10.
 */
public class CaptureServer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
                "applicationContext-capture.xml"
        });

        CaptureListener server = context.getBean(CaptureListener.class);
        System.out.println("截图服务器正在开启中....");
        server.listen();
    }
}
