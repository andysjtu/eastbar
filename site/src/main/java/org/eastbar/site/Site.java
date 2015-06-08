package org.eastbar.site;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import org.eastbar.codec.*;
import org.eastbar.site.file.FileAppender;
import org.eastbar.site.policy.PolicyManager;
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

    @Value("${siteCode}")
    private String siteCode;


    @Autowired
    private TerminalManager terminalManager;

    private volatile Channel centerChannel;

    private SitePolicyVersion version;

    @Autowired
    private PolicyManager policyManager;


    private FileAppender appender;


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

    public Terminal findTerminal(String ip){
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
        logger.info("authScheme is : " + authScheme);

        String host = getAddress(channel);
        logger.info("上传通道地址是 : " + getAddress(channel));
        String reportHost = authScheme.getIp();

        if (!host.equalsIgnoreCase(reportHost)) {
            logger.warn("上传IP信息{}和Socket通道信息{}不一致，以上传IP信息为准 ", reportHost, host);
        }
//        reportToCenter(ReferenceCountUtil.retain(initReq));
        terminalManager.monitorActive(initReq, channel);

    }

//    private void reportToCenter(ClientInitReq initReq) {
//        if (centerChannel != null && centerChannel.isActive()) {
//            OnOffLineEvent event = new OnOffLineEvent(initReq.getAuthSchme(), true);
//            centerChannel.writeAndFlush(event);
//        }
//    }


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
//        return channelMap.get(hostIp);
    }

    public void writeDown(TermReport report){
        //
    }

    public void reportOnLine(final TermReport report) {
        if(centerChannel!=null&&centerChannel.isActive()){
            SocketMsg msg = new TermReportMsg(Lists.newArrayList(report));
            centerChannel.writeAndFlush(msg).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if(!future.isSuccess()){
                        writeDown(report);
                    }
                }
            });
        }
    }

    public void reportOffline() {

    }


    public SiteReport getSiteReport() {
        SiteReport report = new SiteReport();
        report.setUrlVersion(version.getUrlVersion());
        report.setKwVersion(version.getKwVersion());
        report.setPrgVersion(version.getPrgVersion());
        report.setSiteCode(siteCode);
        report.setUrlVersion(version.getUrlVersion());
        report.setConnected(true);
        return report;
    }



    @PostConstruct
    public void initFileAppender(){
        appender = new FileAppender();
        appender.setFile(Paths.get("status", "status.log").toString());
        appender.start();
    }



    public String getSiteCode() {
        return siteCode;
    }

    public void notifyEvent(TermReport report) {
        if(centerChannel!=null&&centerChannel.isActive()){
            SocketMsg msg = new TermReportMsg(Lists.newArrayList(report));
            centerChannel.writeAndFlush(msg).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
        }
    }

    public List<TermReport> getTermReportList() {
        return terminalManager.getTerminalReport();
    }
}
