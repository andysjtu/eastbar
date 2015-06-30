package org.eastbar.site;

import com.google.common.collect.Sets;
import io.netty.channel.Channel;
import org.apache.commons.lang3.StringUtils;
import org.eastbar.codec.*;
import org.eastbar.site.client.PolicyManager;
import org.eastbar.site.policy.entity.SitePolicyVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by andysjtu on 2015/5/9.
 */
@Component
public class Site {
    public final static Logger logger = LoggerFactory.getLogger(Site.class);

    private ScheduledExecutorService service = Executors.newScheduledThreadPool(5);

    @Value("${sitecode}")
    private String siteCode;


    @Autowired
    private TerminalManager terminalManager;

    private volatile Channel centerChannel;

    private SitePolicyVersion version;

    @Autowired
    private PolicyManager policyManager;

    @Autowired
    private SiteReportManager reportManager;


    public void setCenterChannel(Channel centerChannel) {
        this.centerChannel = centerChannel;
    }


    public TerminalManager getTerminalManager() {
        return terminalManager;
    }

    public void setTerminalManager(TerminalManager terminalManager) {
        this.terminalManager = terminalManager;
    }

    @PostConstruct
    public void initPolicyVersion() {
        this.version = policyManager.getPolicyVersion();
    }

    public SitePolicyVersion getVersion() {
        return version;
    }

    public void setVersion(SitePolicyVersion version) {
        this.version = version;
    }

    public void disconnectAll() {
        terminalManager.closeAll();
    }

    public Terminal findTerminal(String ip) {
        return terminalManager.getTerminalOrCreated(ip);
    }

    public void registerCustomerLogin(UserInfo loginEvent) {
        terminalManager.customerLogin(loginEvent);
    }

    public void registerCustomerLogout(UserInfo logoutEvent) {
        terminalManager.customerLogout(logoutEvent);
    }

    public void registerChannel(final Channel channel, ClientInitReq initReq) {
        ClientAuthScheme authScheme = initReq.getAuthSchme();
        logger.debug("authScheme is : " + authScheme);

        String host = getAddress(channel);
        logger.debug("上传通道地址是 : " + getAddress(channel));
        String reportHost = authScheme.getIp();

        if (!host.equalsIgnoreCase(reportHost)) {
            logger.warn("上传IP信息{}和Socket通道信息{}不一致，以上传IP信息为准 ", reportHost, host);
        }
        terminalManager.monitorActive(initReq, channel);

    }




    private String getAddress(Channel channel) {
        InetSocketAddress add = (InetSocketAddress) channel.remoteAddress();
        String host = add.getHostString();
        return host;
    }

    public Set<String> getHosts() {
        return Sets.newHashSet(terminalManager.getHostStatus());
    }

    public Channel getTerminalChannel(String hostIp) {
        Terminal terminal = terminalManager.getTerminalOrCreated(hostIp);
        return terminal.channel();
    }


    public SiteReport getSiteReport() {
        SiteReport report = new SiteReport();
        report.setUrlVersion(version.getUrlVersion());
        report.setKwVersion(version.getKwVersion());
        report.setPrgVersion(version.getPrgVersion());
        report.setSiteCode(siteCode);
        report.setSmVersion(version.getSmVersion());
        report.setConnected(true);
        return report;
    }

    public SiteReportManager getReportManager() {
        return reportManager;
    }

    public void setReportManager(SiteReportManager reportManager) {
        this.reportManager = reportManager;
    }




    public String getSiteCode() {
        return siteCode;
    }

    public void notifyEvent(TermReport report) {
        reportManager.report(report);
    }

    public List<TermReport> getTermReportList() {
        return terminalManager.getTerminalReport();
    }

    public void initTermInfos(List<UserInfo> userInfoList) {
        for (UserInfo userInfo : userInfoList) {
            String logoutTime = StringUtils.trimToNull(userInfo.getLogoutTime());
            if (logoutTime == null) {
                registerCustomerLogin(userInfo);
            } else {
                registerCustomerLogout(userInfo);
            }
        }
    }
}
