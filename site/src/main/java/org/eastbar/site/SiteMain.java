package org.eastbar.site;

import com.google.common.collect.Lists;
import org.eastbar.loadb.DomainAndPort;
import org.eastbar.loadb.LoadbClient;
import org.eastbar.site.alert.dao.AlertService;
import org.eastbar.comm.alert.entity.UrlBlockAlert;
import org.eastbar.site.policy.PolicyManager;
import org.eastbar.site.policy.PolicyService;
import org.eastbar.site.policy.entity.BanUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

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
