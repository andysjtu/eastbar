package org.eastbar.site;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.eastbar.codec.*;
import org.eastbar.comm.AbstractConnector;
import org.eastbar.loadb.DomainAndPort;
import org.eastbar.loadb.LoadbClient;
import org.eastbar.site.handler.center.Center2ClientCmdHandler;
import org.eastbar.site.handler.center.PolicyUpdateHandler;
import org.eastbar.site.handler.center.StatusHandler;
import org.eastbar.site.policy.PolicySaver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by AndySJTU on 2015/6/10.
 */
@Component
public class CaptureConnector extends AbstractConnector {
    public static final Logger logger = LoggerFactory.getLogger(CenterConnector.class);
    public static final int DEFAULT_LOCAL_PORT = 19997;


    @Autowired
    private Site site;
    @Autowired
    private LoadbClient loadbClient;


    @PostConstruct
    public void init() throws Exception {
        try {
            DomainAndPort domainAndPort = loadbClient.getServerAddr("capture");
            logger.info("receive status server address is {}/{}",domainAndPort.getDomain(),domainAndPort.getPort());
            remoteAddress = domainAndPort.getDomain();
            remotePort = domainAndPort.getPort();
        } catch (Throwable t) {
            throw new Exception("Center Server Address cannot found");
        }

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
        pipeline.addLast("centerCmdHandler", new Center2ClientCmdHandler(site));
    }


    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

}
