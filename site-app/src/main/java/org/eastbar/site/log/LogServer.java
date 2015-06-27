package org.eastbar.site.log;

import org.eastbar.net.log.entity.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by AndySJTU on 2015/5/21.
 */
@Component
public class LogServer implements InitializingBean{
    public static final int DEFAULT_THREAD_NUM=5;
    private int processThreadNum =DEFAULT_THREAD_NUM;
    private ExecutorService service = null;
    public final static Logger logger = LoggerFactory.getLogger(LogServer.class);

    public final static String DEFAULT_LINE_SPLITTER = "\r\n";
    public final static String DEFAULT_IN_LINE_SPLITTER = " @ ";

    public static DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH时mm分ss秒");
    @Value("${sitecode}")
    private String siteCode;
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
                try {
                    emailLog.setRecordTime(format.parseDateTime(logAttrs[3].trim()).toDate());
                } catch (Throwable t) {
                    t.printStackTrace();
                }

                emailLog.setEmailType(logAttrs[4]);
                emailLog.setEmailAccount(logAttrs[5]);
                emailLog.setEmailReceptor(logAttrs[6]);
                emailLog.setEmailSubject(logAttrs[7]);
                emailLog.setIsBlock(isBlock(logAttrs[8]));
                emailLog.setSiteCode(siteCode);
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
                String yearPrefix = logAttrs[3].trim().split(" ")[0];
                try {
                    instMsgLog.setRecordTime(format.parseDateTime(logAttrs[3].trim()).toDate());
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                instMsgLog.setProgType(logAttrs[4]);
                instMsgLog.setProgAccount(logAttrs[5]);

                try {
                    instMsgLog.setStartTime(format.parseDateTime(yearPrefix + " " + logAttrs[6].trim()).toDate());
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                try {
                    instMsgLog.setEndTime(format.parseDateTime(yearPrefix + " " + logAttrs[7].trim()).toDate());
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                instMsgLog.setIsBlock(isBlock(logAttrs[8]));
                instMsgLog.setSiteCode(siteCode);
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

                try {
                    illegalLog.setRecordTime(format.parseDateTime(logAttrs[3].trim()).toDate());
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                illegalLog.setKeyword(logAttrs[4]);
                illegalLog.setUrl(logAttrs[5]);
                illegalLog.setIsBlock(isBlock(logAttrs[6]));
                illegalLog.setSiteCode(siteCode);
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
                String yearPrefix = logAttrs[3].trim().split(" ")[0];
                try {
                    prgLog.setRecordTime(format.parseDateTime(logAttrs[3].trim()).toDate());
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                prgLog.setProgName(logAttrs[4]);
                prgLog.setProcessName(logAttrs[5]);
                try {
                    prgLog.setStartTime(format.parseDateTime(yearPrefix + " " + logAttrs[6].trim()).toDate());
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                try {
                    Date endtime = format.parseDateTime(yearPrefix + " " + logAttrs[7].trim()).toDate();
                    prgLog.setEndTime(endtime);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                prgLog.setIsBlock(isBlock(logAttrs[8]));
                prgLog.setSiteCode(siteCode);
                logService.savePrgLog(prgLog);
            } else {
                logger.warn("日志格式错误，类型是:2,内容是{}", content);
            }
        }
    }

    private boolean isBlock(String logAttr) {
        return logAttr.startsWith("0") ? false : true;
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
                    urlLog.setRecordTime(format.parseDateTime(logAttrs[3].trim()).toDate());
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                urlLog.setUrl(logAttrs[4]);
                urlLog.setIsBlock(logAttrs[5].startsWith("0") ? false : true);
                urlLog.setSiteCode(siteCode);
                logService.saveUrlLog(urlLog);
            } else {
                logger.warn("日志格式错误，类型是:1,内容是{}", content);
            }
        }
    }

    public static void main(String[] args) throws ParseException {
//        String content = "2015-06-08 11时26分30秒";
//        System.out.println(format.parse(content));
//        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH时mm分ss秒");
//        DateTime dateTime = format.parseDateTime(content);
//        System.out.println(dateTime);

    }

    public int getProcessThreadNum() {
        return processThreadNum;
    }

    public void setProcessThreadNum(int processThreadNum) {
        this.processThreadNum = processThreadNum;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(processThreadNum <=0){
            throw new IllegalArgumentException("Property processThreadNum cannot be less than 0,please check");
        }
        service = Executors.newFixedThreadPool(processThreadNum);
    }
}
