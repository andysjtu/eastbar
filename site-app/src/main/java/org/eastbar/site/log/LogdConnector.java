package org.eastbar.site.log;

import io.netty.channel.*;
import io.netty.handler.codec.compression.ZlibCodecFactory;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.eastbar.codec.EastbarFrameDecoder;
import org.eastbar.codec.SocketMsgDecoder;
import org.eastbar.codec.SocketMsgEncoder;
import org.eastbar.net.AbstractConnector;
import org.eastbar.net.DomainAndPort;
import org.eastbar.site.loadb.LoadbClient;
import org.eastbar.site.log.handler.LogUploadHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by AndySJTU on 2015/6/11.
 */
@Component
public class LogdConnector extends AbstractConnector {
    public static final Logger logger = LoggerFactory.getLogger(LogdConnector.class);
    public final static int DEFAULT_LOCAL_PORT = 19989;

    @Autowired
    private LoadbClient loadbClient;


    @Autowired
    private LogService logService;


//    @PostConstruct
    public void init() throws Exception {
        try {
            DomainAndPort domainAndPort = loadbClient.getServerAddr("log");
            logger.debug("receive logd server address is {}/{}",domainAndPort.getDomain(),domainAndPort.getPort());
            remoteAddress = domainAndPort.getDomain();
            remotePort = domainAndPort.getPort();
        } catch (Throwable t) {
            remoteAddress="log.nbscreen.com";
            remotePort=9021;
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
    protected void registerHandler(ChannelPipeline pipeline) {
        pipeline.addLast("logHandler", new LoggingHandler("CONN-TO-LOG-SERVER", LogLevel.INFO));
        pipeline.addLast("gzipDecoder", ZlibCodecFactory.newZlibDecoder());
        pipeline.addLast("gzipEncoder", ZlibCodecFactory.newZlibEncoder(3));
        pipeline.addLast("soketMsgEncoder", new SocketMsgEncoder());
        pipeline.addLast("eastframeDecoder", new EastbarFrameDecoder());
        pipeline.addLast("socketMsgDecoder", new SocketMsgDecoder());
        pipeline.addLast("loguploadHandler",new LogUploadHandler(logService));
    }

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }
}
