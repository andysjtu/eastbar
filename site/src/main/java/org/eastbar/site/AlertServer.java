package org.eastbar.site;

import com.google.common.collect.Lists;
import org.eastbar.comm.alert.entity.GeneralAlert;
import org.eastbar.comm.log.entity.EmailLog;
import org.eastbar.site.alert.dao.AlertService;
import org.eastbar.site.file.FileAppender;
import org.eastbar.site.file.RollingFileAppender;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.xml.ws.Service;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by AndySJTU on 2015/5/21.
 */
@Component
public class AlertServer {
    public final static Logger logger = LoggerFactory.getLogger(AlertServer.class);

    private ExecutorService service = Executors.newFixedThreadPool(3);
    public final static String DEFAULT_LINE_SPLITTER = "\r\n";
    public final static String DEFAULT_IN_LINE_SPLITTER = " @ ";

    public static DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH时mm分ss秒");

    @Value("${siteCode}")
    private String siteCode;

    @Autowired
    private AlertService alertService;

    public void appendAlert(final byte type, final String content) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    String[] logContents = content.split(DEFAULT_LINE_SPLITTER);
                    for (String logContent : logContents) {
                        String[] logAttrs = logContent.split(DEFAULT_IN_LINE_SPLITTER);
                        List<GeneralAlert> alerts = Lists.newArrayList();
                        switch (type) {
                            case 0x01:
                                if (logAttrs.length == 10) {
                                    GeneralAlert generalAlert = new GeneralAlert();
                                    generalAlert.setCustomerAccount(logAttrs[0]);
                                    generalAlert.setCustomerIdType(logAttrs[1]);
                                    generalAlert.setCustomerId(logAttrs[2]);
                                    generalAlert.setCustomerName(logAttrs[3]);
                                    generalAlert.setHostIp(logAttrs[4]);
                                    try {
                                        generalAlert.setAlertTime(format.parseDateTime(logAttrs[5].trim()).toDate());
                                    } catch (Throwable t) {
                                        t.printStackTrace();
                                    }
                                    generalAlert.setAlarmContent(logAttrs[6]);

                                    generalAlert.setIsBlock(isBlock(logAttrs[7]));
                                    generalAlert.setAlarmType(logAttrs[8].trim());
                                    generalAlert.setAlarmRank(logAttrs[9].trim());
                                    generalAlert.setSiteCode(siteCode);
                                    alerts.add(generalAlert);
                                } else {
                                    logger.warn("告警格式错误，类型是:1,内容是{}", content);
                                }
                                break;
                            case 0x02:
//                                logger.info("处理类型0x02的告警");
                                if (logAttrs.length == 13) {
                                    GeneralAlert generalAlert = new GeneralAlert();
                                    generalAlert.setCustomerAccount(logAttrs[0]);
                                    generalAlert.setCustomerIdType(logAttrs[1]);
                                    generalAlert.setCustomerId(logAttrs[2]);
                                    generalAlert.setCustomerName(logAttrs[3]);
                                    generalAlert.setHostIp(logAttrs[4]);
                                    try {
                                        generalAlert.setAlertTime(format.parseDateTime(logAttrs[5].trim()).toDate());
                                    } catch (Throwable t) {
                                        t.printStackTrace();
                                    }
                                    String content = logAttrs[6] + ":" + logAttrs[7] + ":" + logAttrs[8] + ":" + logAttrs[9];
                                    generalAlert.setAlarmContent(content);

                                    generalAlert.setIsBlock(isBlock(logAttrs[10]));
                                    generalAlert.setAlarmType(logAttrs[11].trim());
                                    generalAlert.setAlarmRank(logAttrs[12].trim());
                                    generalAlert.setSiteCode(siteCode);
                                    alerts.add(generalAlert);
                                } else {
                                    logger.warn("告警格式错误，类型是:2,内容是{}", content);
                                }
                                break;
                            case 0x03:
//                                logger.info("处理类型0x03的告警");
                                if (logAttrs.length == 11) {
                                    GeneralAlert generalAlert = new GeneralAlert();
                                    generalAlert.setCustomerAccount(logAttrs[0]);
                                    generalAlert.setCustomerIdType(logAttrs[1]);
                                    generalAlert.setCustomerId(logAttrs[2]);
                                    generalAlert.setCustomerName(logAttrs[3]);
                                    generalAlert.setHostIp(logAttrs[4]);
                                    try {
                                        generalAlert.setAlertTime(format.parseDateTime(logAttrs[5].trim()).toDate());
                                    } catch (Throwable t) {
                                        t.printStackTrace();
                                    }
                                    generalAlert.setAlarmContent(logAttrs[6] + ":" + logAttrs[7]);

                                    generalAlert.setIsBlock(isBlock(logAttrs[8]));
                                    generalAlert.setAlarmType(logAttrs[9].trim());
                                    generalAlert.setAlarmRank(logAttrs[10].trim());
                                    generalAlert.setSiteCode(siteCode);
                                    alerts.add(generalAlert);
                                } else {
                                    logger.warn("告警格式错误，类型是:3,内容是{}", content);
                                }
                                break;
                            default:
                                logger.warn("告警格式错误，类型是:{},内容是{}", type, content);

                        }
//                        logger.info("alerts.size is : {}",alerts.size());
                        if (alerts.size() > 0) {
                            alertService.saveGeneralAlert(alerts);
                        }
                    }

                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        });
    }

    private boolean isBlock(String logAttr) {
        return "0".equals(logAttr) ? false : true;
    }


}
