package org.eastbar.site.file;

import com.google.common.base.Charsets;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by AndySJTU on 2015/5/21.
 */
public class RollingFileAppender extends FileAppender {
    public final static Logger logger = LoggerFactory.getLogger(RollingFileAppender.class);

    public final static DateTimeFormatter format = DateTimeFormat.forPattern("yyyyMMddHHMMSS");
    private final static int MODE = 20;
    File currentlyActiveFile;

    private long limit = 1;

    private volatile int startIndex = 0;


    public void start() {

        // we don't want to void existing log files
        if (!append) {
            append = true;
        }

        currentlyActiveFile = new File(getFile());
        super.start();
    }

    @Override
    public String getFile() {
        return super.getFile() + startIndex;
    }

    /**
     * Implemented by delegating most of the rollover work to a rolling policy.
     */
    public void rollover() {
        lock.lock();
        try {
            this.closeOutputStream();
            attemptRollover();
            attemptOpenFile();
        } finally {
            lock.unlock();
        }
    }

    private void attemptOpenFile() {
        try {
            currentlyActiveFile = new File(getFile());
            this.openFile(getFile(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void attemptRollover() {
        startIndex = (startIndex + 1) % MODE;
    }


    private String createNewName() {
        StringBuilder builder = new StringBuilder(fileName);
        builder.append(format.print(System.currentTimeMillis()));
        return builder.toString();
    }

    @Override
    public void append(byte[] content) {


        long length = currentlyActiveFile.length();
        if (length > 1024L * 1024L * limit) {
            rollover();
        }


        super.append(content);
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public static void main(String[] args) throws IOException {
        RollingFileAppender appender = new RollingFileAppender();
        appender.setFile("sitelogs/current_log");
        appender.start();

        for (int i = 0; i < 1000; i++) {
            appender.append(new Date().toString().getBytes(Charsets.UTF_8));
        }


    }


}
