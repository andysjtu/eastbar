package org.eastbar.logd;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * Created by AndySJTU on 2015/6/24.
 */
@Component
public class JmsLogSender {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("prgQueueDestination")
    private ActiveMQQueue prgQueue;

    @Autowired
    @Qualifier("urlQueueDestination")
    private ActiveMQQueue urlQueue;


    @Autowired
    @Qualifier("emailQueueDestination")
    private ActiveMQQueue emilQueue;

    @Autowired
    @Qualifier("instmsgQueueDestination")
    private ActiveMQQueue instMsgQueue;

    @Autowired
    @Qualifier("illegalQueueDestination")
    private ActiveMQQueue illegalLogQueue;

    public void sendPrgLog(final String prgListStr) {
        jmsTemplate.send(prgQueue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage message = session.createMapMessage();
                message.setString("content", prgListStr);
                return message;
            }
        });
    }

    public void sendUrlLog(final String urlListStr) {
        jmsTemplate.send(urlQueue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage message = session.createMapMessage();
                message.setString("content", urlListStr);
                return message;
            }
        });
    }

    public void sendEmailLog(final String emailUrlList) {
        jmsTemplate.send(emilQueue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage message = session.createMapMessage();
                message.setString("content", emailUrlList);
                return message;
            }
        });
    }

    public void sendInstMsgLog(final String content) {
        jmsTemplate.send(instMsgQueue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage message = session.createMapMessage();
                message.setString("content", content);
                return message;
            }
        });
    }


    public void sendIllegalMsgLog(final String content) {
        jmsTemplate.send(illegalLogQueue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage message = session.createMapMessage();
                message.setString("content", content);
                return message;
            }
        });
    }


}
