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



    public void appendAlert(byte type,String content) {

    }




}
