package org.eastbar.logd;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import org.eastbar.codec.DozerUtil;
import org.eastbar.comm.log.entity.EmailLog;
import org.eastbar.comm.log.entity.IllegalLog;
import org.eastbar.comm.log.entity.InstMsgLog;
import org.eastbar.comm.log.entity.UrlLog;
import org.eastbar.logd.dao.LogService;
import org.eastbar.logd.entity.SiteEmailLog;
import org.eastbar.logd.entity.SiteIllegalLog;
import org.eastbar.logd.entity.SiteInstMsgLog;
import org.eastbar.logd.entity.SiteUrlLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by AndySJTU on 2015/6/4.
 */
@Component
public class LogSaver {
    public final static Logger logger = LoggerFactory.getLogger(LogSaver.class);

    @Autowired
    private LogService logService;
    private final ExecutorService service = Executors.newFixedThreadPool(50);

    public void saveSiteUrlLog(final List<UrlLog> siteUrlLogList) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    List<SiteUrlLog> list = FluentIterable.from(siteUrlLogList).transform(new Function<UrlLog, SiteUrlLog>() {
                        @Nullable
                        @Override
                        public SiteUrlLog apply(@Nullable UrlLog input) {
                            SiteUrlLog siteUrlLog = new SiteUrlLog();
                            DozerUtil.copyProperties(input, siteUrlLog);
                            siteUrlLog.setId(null);
                            return siteUrlLog;
                        }
                    }).toList();
//                    List<SiteUrlLog> list = Lists.newArrayList();
//                    for (UrlLog urlLog : siteUrlLogList) {
//                        SiteUrlLog siteUrlLog = new SiteUrlLog();
//                        DozerUtil.copyProperties(urlLog, siteUrlLog);
//                        siteUrlLog.setId(null);
//                        list.add(siteUrlLog);
//
//                    }
                    logService.saveSiteUrlLog(list);
                } catch (Throwable t) {
                    logger.warn("保存SiteUrlLog出错,输入是: {}请检查{}", siteUrlLogList, t);
                }
            }
        });
    }

    public void saveInstMsgLog(final List<InstMsgLog> instMsgLogs) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    List<SiteInstMsgLog> list = FluentIterable.from(instMsgLogs).transform(new Function<InstMsgLog, SiteInstMsgLog>() {
                        @Nullable
                        @Override
                        public SiteInstMsgLog apply(@Nullable InstMsgLog input) {
                            SiteInstMsgLog siteIllegalLog = new SiteInstMsgLog();
                            DozerUtil.copyProperties(input, siteIllegalLog);
                            siteIllegalLog.setId(null);
                            return siteIllegalLog;
                        }
                    }).toList();

                    logService.saveInstMsgLog(list);
                } catch (Throwable t) {
                    logger.warn("保存SiteUrlLog出错,输入是: {}请检查{}", instMsgLogs, t);
                }
            }
        });
    }

    public void saveEmailLogs(final List<EmailLog> emailLogs) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    List<SiteEmailLog> list = FluentIterable.from(emailLogs).transform(new Function<EmailLog, SiteEmailLog>() {
                        @Nullable
                        @Override
                        public SiteEmailLog apply(@Nullable EmailLog input) {
                            SiteEmailLog siteEmailLog = new SiteEmailLog();
                            DozerUtil.copyProperties(input, siteEmailLog);
                            siteEmailLog.setId(null);
                            return siteEmailLog;
                        }
                    }).toList();

                    logService.saveEmailLog(list);
                } catch (Throwable t) {
                    logger.warn("保存SiteUrlLog出错,输入是: {}请检查{}", emailLogs, t);
                }
            }
        });
    }

    public void saveIllegalLogs(final List<IllegalLog> illegalLogs) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    List<SiteIllegalLog> list = FluentIterable.from(illegalLogs).transform(new Function<IllegalLog, SiteIllegalLog>() {
                        @Nullable
                        @Override
                        public SiteIllegalLog apply(@Nullable IllegalLog input) {
                            SiteIllegalLog siteIllegalLog = new SiteIllegalLog();
                            DozerUtil.copyProperties(input, siteIllegalLog);
                            siteIllegalLog.setId(null);
                            return siteIllegalLog;
                        }
                    }).toList();

                    logService.saveIllegalLog(list);
                } catch (Throwable t) {
                    logger.warn("保存SiteUrlLog出错,输入是: {}请检查{}", illegalLogs, t);
                }
            }
        });
    }
}
