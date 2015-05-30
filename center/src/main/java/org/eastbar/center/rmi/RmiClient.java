package org.eastbar.center.rmi;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.applet.Main;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by AndySJTU on 2015/5/25.
 */
public class RmiClient {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext content = new ClassPathXmlApplicationContext(new String[]{
                "applicationContext-client.xml"
        });
        CenterOperator operator = content.getBean(CenterOperator.class);


//        operator.unlock("3101010001","192.168.9.146");
        byte[] c = operator.capture("3101010001", "192.168.9.146");
        BufferedImage imageBuffer = ImageIO.read(new ByteArrayInputStream(c));
        Path path = Files.createTempFile("TEST", ".jpeg");
        System.out.println("path is : " + path);
        ImageIO.write(imageBuffer, "JPEG", path.toFile());
    }
}
