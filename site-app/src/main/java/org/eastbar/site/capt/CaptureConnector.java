package org.eastbar.site.capt;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.eastbar.codec.EastbarFrameDecoder;
import org.eastbar.codec.HeartBeatenHandler;
import org.eastbar.codec.SocketMsgDecoder;
import org.eastbar.codec.SocketMsgEncoder;
import org.eastbar.net.AbstractConnector;
import org.eastbar.net.DomainAndPort;
import org.eastbar.site.Site;
import org.eastbar.site.city.CityConnector;
import org.eastbar.site.city.handler.City2ClientCmdHandler;
import org.eastbar.site.loadb.LoadbClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * Created by AndySJTU on 2015/6/10.
 */
@Component
public class CaptureConnector extends AbstractConnector {
    public static final Logger logger = LoggerFactory.getLogger(CaptureConnector.class);
    public static final int DEFAULT_LOCAL_PORT = 19997;


    @Autowired
    private Site site;
    @Autowired
    private LoadbClient loadbClient;


//    @PostConstruct
    public void init() throws Exception {
        try {
            DomainAndPort domainAndPort = loadbClient.getServerAddr("capture");
            logger.debug("receive capture server address is {}/{}",domainAndPort.getDomain(),domainAndPort.getPort());
            remoteAddress = domainAndPort.getDomain();
            remotePort = domainAndPort.getPort();
        } catch (Throwable t) {
            remoteAddress="status.nbscreen.com";
            remotePort=9056;
        }

        localPort=DEFAULT_LOCAL_PORT;

    }

    @Override
    protected void beforeConnect() {
        try {
            this.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        configBootstrap();
    }


    @Override
    protected Bootstrap configOptions(Bootstrap bootstrap) {
        super.configOptions(bootstrap);
        bootstrap.option(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(1024 * 20));
        return bootstrap;
    }

    @Override
    protected void registerHandler(ChannelPipeline pipeline) {
        pipeline.addLast("logHandler", new LoggingHandler("CON-TO-CAPTURE-SERVER", LogLevel.DEBUG));
        pipeline.addLast("idleHandler", new IdleStateHandler(0, 0, 60, TimeUnit.SECONDS));
        pipeline.addLast("soketMsgEncoder", new SocketMsgEncoder());
        pipeline.addLast("eastframeDecoder", new EastbarFrameDecoder());
        pipeline.addLast("socketMsgDecoder", new SocketMsgDecoder());
        pipeline.addLast("bentenHandler", new HeartBeatenHandler());
        pipeline.addLast("centerCmdHandler", new City2ClientCmdHandler(site));
    }


    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

}
