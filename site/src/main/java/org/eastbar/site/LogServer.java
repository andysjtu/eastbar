package org.eastbar.site;

import org.eastbar.comm.log.entity.*;
import org.eastbar.site.log.dao.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by AndySJTU on 2015/5/21.
 */
@Component
public class LogServer {
    private ExecutorService service = Executors.newFixedThreadPool(3);
    public final static Logger logger = LoggerFactory.getLogger(LogServer.class);

    public final static String DEFAULT_LINE_SPLITTER = "\r\n";
    public final static String DEFAULT_IN_LINE_SPLITTER = " ：";

    public static SimpleDateFormat format = new SimpleDateFormat("YYYY-DD-MM");

    @Autowired
    private LogService logService;

    public void appendLog(byte type, final String content) {
        switch (type) {
            case 1:
                service.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            appendUrlLog(content);
                        } catch (Throwable t) {
                            logger.warn("parse or save url log,some error happened", t);
                        }
                    }
                });
                break;
            case 2:
                service.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            appendPrgLog(content);
                        } catch (Throwable t) {
                            logger.warn("parse or save program log,some error happened", t);
                        }
                    }
                });
                break;
            case 3:
                service.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            appendIllegalLog(content);
                        } catch (Throwable t) {
                            logger.warn("parse or save illegal log,some error happened", t);
                        }
                    }
                });
                break;
            case 4:
                service.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            appendInstMsg(content);
                        } catch (Throwable t) {
                            logger.warn("parse or save instant msg log,some error happened", t);
                        }
                    }
                });
                break;
            case 5:
                service.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            appendEmailLog(content);
                        } catch (Throwable t) {
                            logger.warn("parse or save email log,some error happened", t);
                        }
                    }
                });
                break;
            default:
                logger.warn("unknown type {},check content : {}", type, content);
        }
    }

    public void appendEmailLog(String content) {
        String[] logContents = content.split(DEFAULT_LINE_SPLITTER);
        for (String logContent : logContents) {
            String[] logAttrs = logContent.split(DEFAULT_IN_LINE_SPLITTER);

            if (logAttrs.length == 9) {
                EmailLog emailLog = new EmailLog();
                emailLog.setCustomerId(logAttrs[0]);
                emailLog.setCustomerName(logAttrs[1]);
                emailLog.setHostIp(logAttrs[2]);
                emailLog.setRecordTime(logAttrs[3]);
                emailLog.setEmailType(logAttrs[4]);
                emailLog.setEmailAccount(logAttrs[5]);
                emailLog.setEmailReceptor(logAttrs[6]);
                emailLog.setEmailSubject(logAttrs[7]);
                emailLog.setIsBlock("0".equals(logAttrs[8]) ? false : true);
                logService.saveEmailLog(emailLog);
            } else {
                logger.warn("日志格式错误，类型是:5,内容是{}", content);
            }
        }
    }

    public void appendInstMsg(String content) {
        String[] logContents = content.split(DEFAULT_LINE_SPLITTER);
        for (String logContent : logContents) {
            String[] logAttrs = logContent.split(DEFAULT_IN_LINE_SPLITTER);

            if (logAttrs.length == 9) {
                InstMsgLog instMsgLog = new InstMsgLog();
                instMsgLog.setCustomerId(logAttrs[0]);
                instMsgLog.setCustomerName(logAttrs[1]);
                instMsgLog.setHostIp(logAttrs[2]);
                instMsgLog.setRecordTime(logAttrs[3]);
                instMsgLog.setProgType(logAttrs[4]);
                instMsgLog.setProgAccount(logAttrs[5]);
                instMsgLog.setStartTime(logAttrs[6]);
                instMsgLog.setEndTime(logAttrs[7]);
                instMsgLog.setIsBlock("0".equals(logAttrs[8]) ? false : true);
                logService.saveInstMsgLog(instMsgLog);
            } else {
                logger.warn("日志格式错误，类型是:4,内容是{}", content);
            }
        }
    }

    public void appendIllegalLog(String content) {
        String[] logContents = content.split(DEFAULT_LINE_SPLITTER);
        for (String logContent : logContents) {
            String[] logAttrs = logContent.split(DEFAULT_IN_LINE_SPLITTER);

            if (logAttrs.length == 7) {
                IllegalLog illegalLog = new IllegalLog();
                illegalLog.setCustomerId(logAttrs[0]);
                illegalLog.setCustomerName(logAttrs[1]);
                illegalLog.setHostIp(logAttrs[2]);
                illegalLog.setRecordTime(logAttrs[3]);
                illegalLog.setKeyword(logAttrs[4]);
                illegalLog.setUrl(logAttrs[5]);
                illegalLog.setIsBlock("0".equals(logAttrs[6]) ? false : true);
                logService.saveIllegalLog(illegalLog);
            } else {
                logger.warn("日志格式错误，类型是:3,内容是{}", content);
            }
        }
    }

    public void appendPrgLog(String content) {
        String[] logContents = content.split(DEFAULT_LINE_SPLITTER);
        for (String logContent : logContents) {
            String[] logAttrs = logContent.split(DEFAULT_IN_LINE_SPLITTER);

            if (logAttrs.length == 9) {
                PrgLog prgLog = new PrgLog();
                prgLog.setCustomerId(logAttrs[0]);
                prgLog.setCustomerName(logAttrs[1]);
                prgLog.setHostIp(logAttrs[2]);
                prgLog.setRecordTime(logAttrs[3]);
                prgLog.setProgName(logAttrs[4]);
                prgLog.setProgName(logAttrs[5]);
                prgLog.setStartTime(logAttrs[6]);
                prgLog.setEndTime(logAttrs[7]);
                prgLog.setIsBlock("0".equals(logAttrs[8]) ? false : true);
                logService.savePrgLog(prgLog);
            } else {
                logger.warn("日志格式错误，类型是:2,内容是{}", content);
            }
        }
    }


    public void appendUrlLog(String content) {
        String[] logContents = content.split(DEFAULT_LINE_SPLITTER);
        for (String logContent : logContents) {
            String[] logAttrs = logContent.split(DEFAULT_IN_LINE_SPLITTER);

            if (logAttrs.length == 6) {
                UrlLog urlLog = new UrlLog();
                urlLog.setCustomerId(logAttrs[0]);
                urlLog.setCustomerName(logAttrs[1]);
                urlLog.setHostIp(logAttrs[2]);
                try {
                    urlLog.setRecordTime(format.parse(logAttrs[3].trim()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                urlLog.setUrl(logAttrs[4]);
                urlLog.setIsBlock(logAttrs[5].startsWith("0") ? false : true);
                logService.saveUrlLog(urlLog);
            } else {
                logger.warn("日志格式错误，类型是:1,内容是{}", content);
            }
        }
    }


}
