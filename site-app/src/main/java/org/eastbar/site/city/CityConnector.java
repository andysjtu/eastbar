package org.eastbar.site.city;

import io.netty.channel.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.eastbar.codec.*;
import org.eastbar.codec.HeartBeatenHandler;
import org.eastbar.net.AbstractConnector;
import org.eastbar.net.DomainAndPort;
import org.eastbar.site.Site;
import org.eastbar.site.SiteReportManager;
import org.eastbar.site.city.handler.City2ClientCmdHandler;
import org.eastbar.site.city.handler.PolicyUpdateHandler;
import org.eastbar.site.city.handler.StatusHandler;
import org.eastbar.site.loadb.LoadbClient;
import org.eastbar.site.policy.PolicySaver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * Created by andysjtu on 2015/5/9.
 */
@Component
public class CityConnector extends AbstractConnector {
    public static final Logger logger = LoggerFactory.getLogger(CityConnector.class);
    public static final int DEFAULT_LOCAL_PORT = 19999;


    @Autowired
    private Site site;
    @Autowired
    private PolicySaver policySaver;
    @Autowired
    private SiteReportManager siteReportManager;
    @Autowired
    private LoadbClient loadbClient;

    @PostConstruct
    public void init() throws Exception {
        try {
            DomainAndPort domainAndPort = loadbClient.getServerAddr("status");
            logger.info("receive status server address is {}/{}", domainAndPort.getDomain(), domainAndPort.getPort());
            remoteAddress = domainAndPort.getDomain();
            remotePort = domainAndPort.getPort();
        } catch (Throwable t) {
//            remoteAddress = "status.nbscreen.com";
            remoteAddress="192.168.9.119";
            remotePort = 9041;
        }

        localPort=DEFAULT_LOCAL_PORT;

    }


    @Override
    protected void registerHandler(ChannelPipeline pipeline) {
        pipeline.addLast("logHandler", new LoggingHandler("CONN-TO-CITY", LogLevel.DEBUG));
        pipeline.addLast("idleHandler", new IdleStateHandler(0, 0, 60, TimeUnit.SECONDS));
        pipeline.addLast("soketMsgEncoder", new SocketMsgEncoder());
        pipeline.addLast("eastframeDecoder", new EastbarFrameDecoder());
        pipeline.addLast("socketMsgDecoder", new SocketMsgDecoder());
      pipeline.addLast("siteInitHandler", new StatusHandler(site));
        pipeline.addLast("bentenHandler", new HeartBeatenHandler());
        pipeline.addLast("policyUpdater", new PolicyUpdateHandler(policySaver));
        pipeline.addLast("centerCmdHandler", new City2ClientCmdHandler(site));
    }


    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }


    public int getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(int remotePort) {
        this.remotePort = remotePort;
    }


}
