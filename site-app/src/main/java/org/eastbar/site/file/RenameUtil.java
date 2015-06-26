package org.eastbar.site.file;

import ch.qos.logback.core.rolling.*;
import ch.qos.logback.core.rolling.helper.FileStoreUtil;
import ch.qos.logback.core.util.EnvUtil;
import ch.qos.logback.core.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by AndySJTU on 2015/5/21.
 */
public class RenameUtil {
    public final static Logger logger= LoggerFactory.getLogger(RenameUtil.class);

    public void rename(String src, String target) throws RolloverFailure {
        if (src.equals(target)) {
            return;
        }
        File srcFile = new File(src);

        if (srcFile.exists()) {
            File targetFile = new File(target);
            createMissingTargetDirsIfNecessary(targetFile);


            boolean result = srcFile.renameTo(targetFile);

            if (!result) {
                logger.warn("Failed to rename file [" + srcFile + "] as [" + targetFile + "].");
                if (areOnDifferentVolumes(srcFile, targetFile)) {
                    logger.warn("Detected different file systems for source [" + src + "] and target [" + target + "]. Attempting rename by copying.");
//                    renameByCopying(src, target);
                    return;
                } else {
                    logger.warn("Please consider leaving the [file] option of " + ch.qos.logback.core.rolling.RollingFileAppender.class.getSimpleName() + " empty.");
                }
            }
        } else {
            throw new RolloverFailure("File [" + src + "] does not exist.");
        }
    }

    boolean areOnDifferentVolumes(File srcFile, File targetFile) throws RolloverFailure {
        if (!EnvUtil.isJDK7OrHigher())
            return false;

        File parentOfTarget = targetFile.getParentFile();

        try {
            boolean onSameFileStore = FileStoreUtil.areOnSameFileStore(srcFile, parentOfTarget);
            return !onSameFileStore;
        } catch (RolloverFailure rf) {
            logger.warn("Error while checking file store equality", rf);
            return false;
        }
    }




//    public void renameByCopying(String src, String target)
//            throws RolloverFailure {
//
//        FileUtil fileUtil = new FileUtil(getContext());
//        fileUtil.copy(src, target);
//
//        File srcFile = new File(src);
//        if (!srcFile.delete()) {
//            addWarn("Could not delete " + src);
//        }
//
//    }

    void createMissingTargetDirsIfNecessary(File toFile) throws RolloverFailure {
        if (FileUtil.isParentDirectoryCreationRequired(toFile)) {
            boolean result = FileUtil.createMissingParentDirectories(toFile);
            if (!result) {
                throw new RolloverFailure("Failed to create parent directories for ["
                        + toFile.getAbsolutePath() + "]");
            }
        }
    }

    @Override
    public String toString() {
        return "c.q.l.co.rolling.helper.RenameUtil";
    }
}
