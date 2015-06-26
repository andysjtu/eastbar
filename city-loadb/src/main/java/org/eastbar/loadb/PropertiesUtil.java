package org.eastbar.loadb;

import com.google.common.collect.Lists;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * Created by AndySJTU on 2015/6/12.
 */
public class PropertiesUtil {

    public static List<DomainAndPort> getServerList(Properties properties,String type) {
        List<DomainAndPort> list = Lists.newArrayList();
        String alertNumStr = StringUtils.trimToNull(properties.getProperty(type+".num"));
        if (alertNumStr != null) {
            int alertNum = 0;
            alertNum = Integer.parseInt(alertNumStr);

            for (int i = 1; i - 1 < alertNum; i++) {
                String domain = StringUtils.trimToNull(properties.getProperty(type+".ip" + i));
                String portStr = StringUtils.trimToNull(properties.getProperty(type+".port" + i));
                int port = Integer.parseInt(portStr);
                DomainAndPort domainAndPort = new DomainAndPort();
                domainAndPort.setDomain(domain);
                domainAndPort.setPort(port);
                list.add(domainAndPort);
            }

        }
        return list;
    }


    public static List<DomainAndPort> getAlertServerList(Properties properties) {
//        List<DomainAndPort> list = Lists.newArrayList();
//        String alertNumStr = StringUtils.trimToNull(properties.getProperty("alert.num"));
//        if (alertNumStr != null) {
//            int alertNum = 0;
//            alertNum = Integer.parseInt(alertNumStr);
//
//            for (int i = 1; i - 1 < alertNum; i++) {
//                String domain = StringUtils.trimToNull(properties.getProperty("alert.ip" + i));
//                String portStr = StringUtils.trimToNull(properties.getProperty("alert.port" + i));
//                int port = Integer.parseInt(portStr);
//                DomainAndPort domainAndPort = new DomainAndPort();
//                domainAndPort.setDomain(domain);
//                domainAndPort.setPort(port);
//                list.add(domainAndPort);
//            }
//
//        }
//        return list;
        return PropertiesUtil.getServerList(properties,"alert");
    }

    public static void main(String[] args) throws IOException {
//        ClassPathResource resource = new ClassPathResource("application-loadb.properties");
         Properties properties = PropertiesLoaderUtils.loadAllProperties("application-loadb.properties");
        System.out.println(PropertiesUtil.getAlertServerList(properties));
        System.out.println(PropertiesUtil.getServerList(properties,"log"));
    }


}
