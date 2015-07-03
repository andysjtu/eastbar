package org.eastbar.alert2db;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Charsets;
import org.eastbar.alert2db.dao.AlertService;
import org.eastbar.alert2db.entity.SiteAlert;
import org.eastbar.codec.JsonUtil;
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
public class AlertMessageReceiver implements MessageListener {
    public final static Logger logger= LoggerFactory.getLogger(AlertMessageReceiver.class);


    private AlertService alertService;

    @Override
    public void onMessage(Message message) {
        System.out.println("receive Alert message" + message);
        MapMessage mapMessage = (MapMessage) message;
        try {
            String content = mapMessage.getString("content");
            List<SiteAlert> alertList = JsonUtil.fromJson(new TypeReference<List<SiteAlert>>() {
            }, content.getBytes(Charsets.UTF_8));
            System.out.println("alertList is : " + alertList);
            alertService.saveSiteAlet(alertList);
        } catch (JMSException e) {
            logger.warn("处理JMS信息出现异常",e);
        }catch(Throwable t){
            logger.warn("Alert信息入库，出现异常",t);
        }
    }

    public AlertService getAlertService() {
        return alertService;
    }

    public void setAlertService(AlertService alertService) {
        this.alertService = alertService;
    }
}
