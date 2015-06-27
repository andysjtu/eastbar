package org.eastbar.site;

import org.eastbar.site.loadb.LoadbClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by andysjtu on 2015/5/9.
 */
public class SiteMain {
    public final static Logger logger= LoggerFactory.getLogger(SiteMain.class);

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
                "applicationContext-site.xml"
        });

        LoadbClient client = context.getBean(LoadbClient.class);

        try {
            System.out.println("-------------------------");
            client.connect();
        } catch (Throwable e) {
            logger.warn("cannot connect to loadb server,enable default settings");
        }

        SiteServer server = context.getBean(SiteServer.class);

        try {
            System.out.println("Site Server is starting....");
            server.start();
            System.out.println("Site Server started...");
        } catch (Throwable t) {
            t.printStackTrace();
            server.stop();
        }


    }

//    public static String readSiteCodeFromProperties() throws IOException {
//        Path propertiesPath = Paths.get("/root", "netbar", "sitecode");
//        FileSystemResource resource = new FileSystemResource(propertiesPath.toFile());
//        Properties properties = PropertiesLoaderUtils.loadProperties(resource);
//        return properties.getProperty("sitecode");
//    }

}
