package org.eastbar.site;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import org.eastbar.site.file.FileAppender;
import org.eastbar.site.file.RollingFileAppender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by AndySJTU on 2015/5/21.
 */
@Component
public class LogServer {
    private ExecutorService service = Executors.newSingleThreadExecutor();



    private FileAppender fileAppender;

    @Value("${log.dir}")
    private String logDir;

    public void appendLog(final byte[] content) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                fileAppender.append(content);
            }
        });
    }

    @PostConstruct
    public void initFileAppender() {
        if (logDir == null) throw new RuntimeException("logDir must be set");
        fileAppender = new RollingFileAppender();
        fileAppender.setFile(Paths.get(logDir,"sitelog").toString());
        fileAppender.start();
    }

    @PreDestroy
    public void close(){
        fileAppender.stop();
    }


}
