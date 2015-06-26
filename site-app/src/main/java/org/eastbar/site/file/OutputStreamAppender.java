package org.eastbar.site.file;

import ch.qos.logback.core.Layout;
import ch.qos.logback.core.encoder.LayoutWrappingEncoder;
import ch.qos.logback.core.spi.DeferredProcessingAware;
import ch.qos.logback.core.status.ErrorStatus;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.locks.ReentrantLock;

import static ch.qos.logback.core.CoreConstants.CODES_URL;

/**
 * Created by AndySJTU on 2015/5/21.
 */
public class OutputStreamAppender {

    protected volatile boolean started = false;
    /**
     * All synchronization in this class is done via the lock object.
     */
    protected final ReentrantLock lock = new ReentrantLock(true);

    /**
     * This is the {@link OutputStream outputStream} where output will be written.
     */
    private OutputStream outputStream;

    /**
     * The underlying output stream used by this appender.
     *
     * @return
     */
    public OutputStream getOutputStream() {
        return outputStream;
    }

    /**
     * Checks that requires parameters are set and if everything is in order,
     * activates this appender.
     */
    public void start() {
        if (this.outputStream == null) {
            throw new RuntimeException("outputStream must not be null");
        }
        this.started = true;

    }


    /**
     * Stop this appender instance. The underlying stream or writer is also
     * closed.
     * <p/>
     * <p/>
     * Stopped appenders cannot be reused.
     */
    public void stop() {
        lock.lock();
        try {
            closeOutputStream();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Close the underlying {@link OutputStream}.
     */
    protected void closeOutputStream() {
        if (this.outputStream != null) {
            try {
                this.outputStream.close();
                this.outputStream = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void setOutputStream(OutputStream outputStream) {
        lock.lock();
        try {
            // close any previously opened output stream
            closeOutputStream();
            this.outputStream = outputStream;
        } finally {
            lock.unlock();
        }
    }


    public void append(byte[] content) {
        if (!isStarted()) {
            return;
        }
        try {
            lock.lock();
            try {
                outputStream.write(content);
                outputStream.flush();
            } finally {
                lock.unlock();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            this.started = false;
        }
    }

    public boolean isStarted() {
        return started;
    }
}
