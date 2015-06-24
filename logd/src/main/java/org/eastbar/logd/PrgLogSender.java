package org.eastbar.logd;

import org.apache.activemq.command.ActiveMQQueue;
import org.eastbar.comm.log.entity.PrgLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import java.util.List;

/**
 * Created by AndySJTU on 2015/6/24.
 */
@Component
public class PrgLogSender {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("prgQueueDestination")
    private ActiveMQQueue prgDestination;

    @Autowired
    @Qualifier("urlQueueDestination")
    private ActiveMQQueue urlDestion;

    public void sendPrgLog(final String prgListStr) {
        jmsTemplate.send(prgDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage message = session.createMapMessage();
                message.setString("content", prgListStr);
                return message;
            }
        });
    }

    public void sendUrlLog(final  String urlListStr){
        jmsTemplate.send(urlDestion, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage message = session.createMapMessage();
                message.setString("content", urlListStr);
                return message;
            }
        });
    }


}
