package org.eastbar.site.file;

import ch.qos.logback.core.util.FileUtil;
import org.eastbar.site.file.OutputStreamAppender;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * Created by AndySJTU on 2015/5/21.
 */
public class FileAppender extends OutputStreamAppender {
    /**
     * Append to or truncate the file? The default value for this variable is
     * <code>true</code>, meaning that by default a <code>FileAppender</code> will
     * append to an existing file and not truncate it.
     */
    protected boolean append = true;

    /**
     * The name of the active log file.
     */
    protected String fileName = null;


    /**
     * The <b>File</b> property takes a string value which should be the name of
     * the file to append to.
     */
    public void setFile(String file) {
        if (file == null) {
            fileName = file;
        } else {
            // Trim spaces from both ends. The users probably does not want
            // trailing spaces in file names.
            fileName = file.trim();
        }
    }

    /**
     * Returns the value of the <b>Append</b> property.
     */
    public boolean isAppend() {
        return append;
    }

    /**
     * This method is used by derived classes to obtain the raw file property.
     * Regular users should not be calling this method.
     *
     * @return the value of the file property
     */
    final public String rawFileProperty() {
        return fileName;
    }

    /**
     * Returns the value of the <b>File</b> property.
     * <p/>
     * <p/>
     * This method may be overridden by derived classes.
     */
    public String getFile() {
        return fileName;
    }

    /**
     * If the value of <b>File</b> is not <code>null</code>, then
     * {@link #openFile} is called with the values of <b>File</b> and
     * <b>Append</b> properties.
     */
    public void start() {
        int errors = 0;
        if (getFile() != null) {
            try {
                openFile(getFile(),append);
            } catch (java.io.IOException e) {
                e.printStackTrace();
                errors++;

            }
        } else {
            errors++;
        }
        if (errors == 0) {
            super.start();
        }
    }

    /**
     * <p/>
     * Sets and <i>opens</i> the file where the log output will go. The specified
     * file must be writable.
     * <p/>
     * <p/>
     * If there was already an opened file, then the previous file is closed
     * first.
     * <p/>
     * <p/>
     * <b>Do not use this method directly. To configure a FileAppender or one of
     * its subclasses, set its properties one by one and then call start().</b>
     *
     * @param file_name The path to the log file.
     */
    public void openFile(String file_name,boolean append) throws IOException {
        lock.lock();
        try {
            File file = new File(file_name);
            if (FileUtil.isParentDirectoryCreationRequired(file)) {
                boolean result = FileUtil.createMissingParentDirectories(file);
                if (!result) {
                    throw new IOException("Failed to create parent directories for ["
                            + file.getAbsolutePath() + "]");
                }
            }

            FileOutputStream stream = new FileOutputStream(file,append);
            setOutputStream(stream);
        } finally {
            lock.unlock();
        }
    }


    private void safeWrite(ByteBuffer buffer) throws IOException {
        FileOutputStream resilientFOS = (FileOutputStream) getOutputStream();
        FileChannel fileChannel = resilientFOS.getChannel();
        if (fileChannel == null) {
            return;
        }
        FileLock fileLock = null;
        try {
            fileLock = fileChannel.lock();
            long position = fileChannel.position();
            long size = fileChannel.size();
            if (size != position) {
                fileChannel.position(size);
            }
            fileChannel.write(buffer);
        } finally {
            if (fileLock != null) {
                fileLock.release();
            }
        }
    }

//    @Override
//    public void append(byte[] content) {
//        try {
//            safeWrite(content);
//        } catch (IOException e) {
//            e.printStackTrace();
//            started = false;
//        }
//    }
}
