package org.eastbar.site;

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
public class AlertServer {
    private ExecutorService service = Executors.newSingleThreadExecutor();



    private FileAppender fileAppender;

    @Value("${alert.dir}")
    private String alertDir;

    public void appendAlert(final byte[] content) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                fileAppender.append(content);
            }
        });
    }

    @PostConstruct
    public void initFileAppender() {
        if (alertDir == null) throw new RuntimeException("alert.dir must be set");
        fileAppender = new RollingFileAppender();
        fileAppender.setFile(Paths.get(alertDir, "sitealert").toString());
        fileAppender.start();
    }

    @PreDestroy
    public void close(){
        fileAppender.stop();
    }
}
