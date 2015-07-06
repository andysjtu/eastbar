package org.eastbar.log2db;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Charsets;
import org.eastbar.codec.JsonUtil;
import org.eastbar.log2db.dao.LogService;
import org.eastbar.log2db.entity.SiteProgLog;
import org.eastbar.log2db.entity.SiteUrlLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.List;

/**
 * Created by AndySJTU on 2015/6/24.
 */
public class SitePrgLogMsgRcvr implements MessageListener {

    public final static Logger logger = LoggerFactory.getLogger(SitePrgLogMsgRcvr.class);

    private LogService logService;


    @Override
    public void onMessage(Message message) {
        MapMessage mapMessage = (MapMessage) message;
        String content = null;
        try {
            content = mapMessage.getString("content");
            List<SiteProgLog> siteUrlLogList = JsonUtil.fromJson(new TypeReference<List<SiteProgLog>>() {
            }, content.getBytes(Charsets.UTF_8));
            if (logger.isDebugEnabled()) {
                logger.debug("SiteProgLogList :{}", siteUrlLogList);
            }
            if (siteUrlLogList.size() > 0)
                logService.saveProgLog(siteUrlLogList);
        } catch (JMSException e) {
            logger.warn("处理SiteProgLogJMS异常出错:", e);
        } catch (Throwable t) {
            logger.info("SiteProg cotent is {}", content);
            logger.warn("处理SiteProgLog入库程序出错：", t);
        }
    }

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }
}
