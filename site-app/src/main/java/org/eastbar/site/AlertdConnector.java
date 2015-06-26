package org.eastbar.site;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.eastbar.codec.EastbarFrameDecoder;
import org.eastbar.codec.SocketMsgDecoder;
import org.eastbar.codec.SocketMsgEncoder;
import org.eastbar.comm.AbstractConnector;
import org.eastbar.loadb.DomainAndPort;
import org.eastbar.loadb.LoadbClient;
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
 * Created by AndySJTU on 2015/6/11.
 */
@Component
public class AlertdConnector extends AbstractConnector {

    public static int DEFAULT_LOCAL_PORT = 19979;

    @Autowired
    private LoadbClient loadbClient;


    @PostConstruct
    public void init() throws Exception {
        try {
            DomainAndPort domainAndPort = loadbClient.getServerAddr("alert");
            logger.info("receive alert server address is {}/{}",domainAndPort.getDomain(),domainAndPort.getPort());
            remoteAddress = domainAndPort.getDomain();
            remotePort = domainAndPort.getPort();
        } catch (Throwable t) {
            remoteAddress="alert.nbscreen.com";
            remotePort=9001;
        }

        localPort=DEFAULT_LOCAL_PORT;

    }



    @Override
    protected void registerHandler(ChannelPipeline pipeline) {
        pipeline.addLast("logHandler", new LoggingHandler("CONN-TO-ALERT-SERVER", LogLevel.DEBUG));
        pipeline.addLast("soketMsgEncoder", new SocketMsgEncoder());
        pipeline.addLast("eastframeDecoder", new EastbarFrameDecoder());
        pipeline.addLast("socketMsgDecoder", new SocketMsgDecoder());
    }


}
