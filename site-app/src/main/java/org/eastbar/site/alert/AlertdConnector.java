package org.eastbar.site.alert;

import io.netty.channel.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.eastbar.codec.EastbarFrameDecoder;
import org.eastbar.codec.SocketMsgDecoder;
import org.eastbar.codec.SocketMsgEncoder;
import org.eastbar.net.AbstractConnector;
import org.eastbar.net.DomainAndPort;
import org.eastbar.site.loadb.LoadbClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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
