package org.eastbar.alertd;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by andysjtu on 2015/6/24.
 */
@Component
public class JmsAlertSender {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("alertQueueDestination")
    private ActiveMQQueue alertQueue;

    public void sendAlert(final String content) {
        jmsTemplate.send(alertQueue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage message = session.createMapMessage();
                message.setString("content", content);
                return message;
            }
        });
    }
}
