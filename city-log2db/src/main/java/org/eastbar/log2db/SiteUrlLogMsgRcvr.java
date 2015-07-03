package org.eastbar.log2db;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Charsets;
import org.eastbar.codec.JsonUtil;
import org.eastbar.log2db.dao.LogService;
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
public class SiteUrlLogMsgRcvr implements MessageListener {

    public final static Logger logger = LoggerFactory.getLogger(SiteUrlLogMsgRcvr.class);

    private LogService logService;


    @Override
    public void onMessage(Message message) {
        MapMessage mapMessage = (MapMessage) message;
        try {
            String content = mapMessage.getString("content");
            List<SiteUrlLog> siteUrlLogList = JsonUtil.fromJson(new TypeReference<List<SiteUrlLog>>() {
            }, content.getBytes(Charsets.UTF_8));
            logService.saveSiteUrlLog(siteUrlLogList);
        } catch (JMSException e) {
            logger.warn("处理UrlJMS异常出错:", e);
        } catch (Throwable t) {
            logger.warn("处理Url入库程序出错：", t);
        }
    }

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }
}
