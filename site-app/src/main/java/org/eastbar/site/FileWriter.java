package org.eastbar.site;

import com.google.common.base.Charsets;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by AndySJTU on 2015/5/21.
 */
public class FileWriter {
    public final static Logger logger = LoggerFactory.getLogger(FileWriter.class);

    public final static DateTimeFormatter format = DateTimeFormat.forPattern("yyyyMMddHHMMSS");


    public static final long DEFAULT_FILE_SIZE = 1024L * 1024;

    private String baseDir;

    private String fileName;

    private long fileLimit = DEFAULT_FILE_SIZE;


    private FileChannel channel;

    private FileOutputStream outStream;

    private Path activePath;


    public FileWriter(String baseDir, String name) {
        this.baseDir = baseDir;
        this.fileName = name;
        activePath = Paths.get(baseDir, name);
    }

    protected String createNewFileName() {
        StringBuilder builder = new StringBuilder(fileName);
        builder.append(format.print(System.currentTimeMillis()));
        return builder.toString();
    }

    private void createFile(Path filePath) throws IOException {
        Path parentPath = filePath.getParent();
        if (!Files.exists(parentPath)) {
            Files.createDirectories(parentPath);
        }
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
    }

    protected void openFile(Path filePath, boolean append) throws IOException {
        createFile(filePath);
        outStream = new FileOutputStream(filePath.toFile(), append);
        channel = outStream.getChannel();
    }

    protected void preAppend() throws IOException {
        if (outStream == null || channel == null) {
            openFile(activePath, true);
        }
    }

    protected void subAppend() throws IOException {
        if (channel.size() > fileLimit) {
            logger.debug("channel.size {} > fileLimit {}",channel.size(),fileLimit);
            outStream.close();
            channel.close();
            renameFile();
        }
    }

    private void renameFile() throws IOException {
        Path targetPath = activePath.resolveSibling(createNewFileName());
        boolean result = activePath.toFile().renameTo(targetPath.toFile());
        if (result) {
            openFile(activePath, false);
        }else{
            logger.warn("rename failure");
            openFile(activePath, true);
        }
    }

    private void appendFile(final byte[] content) throws IOException {
        outStream.write(content);
        outStream.flush();
    }

    public void append(byte[] content) throws IOException {
        preAppend();
        appendFile(content);
        subAppend();
    }

    public void close() throws IOException {
        if (outStream != null) {
            outStream.close();
        }
        if (channel != null && channel.isOpen()) {
            channel.close();
        }
    }

    public static void main(String[] args) throws IOException {
        FileWriter writer = new FileWriter("log", "192.168.9.3.log");
        writer.setFileLimit(1024*20);
        for (int i = 0; i < 1000; i++) {
            writer.append("this is a test for append\r\nthis is a test for append\nthis is a test for append\n".getBytes(Charsets.UTF_8));
        }
        writer.close();
    }


    public void setFileLimit(int fileLimit) {
        this.fileLimit = fileLimit;
    }
}
