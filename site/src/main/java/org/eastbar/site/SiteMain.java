package org.eastbar.site;

import com.google.common.collect.Lists;
import org.eastbar.site.alert.dao.AlertService;
import org.eastbar.comm.alert.entity.UrlBlockAlert;
import org.eastbar.site.policy.PolicyManager;
import org.eastbar.site.policy.PolicyService;
import org.eastbar.site.policy.entity.BanUrl;
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
    public static void main(String[] args) throws IOException {
//        String siteCode = readSiteCodeFromProperties();
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
                "applicationContext-site.xml"
        });

        SiteServer server = context.getBean(SiteServer.class);
        System.out.println("Site Server is starting....");
        server.start();
        System.out.println();


    }

    public static String readSiteCodeFromProperties() throws IOException {
        Path propertiesPath = Paths.get("/root", "netbar", "sitecode");
        FileSystemResource resource = new FileSystemResource(propertiesPath.toFile());
        Properties properties = PropertiesLoaderUtils.loadProperties(resource);
        return properties.getProperty("sitecode");
    }

}
