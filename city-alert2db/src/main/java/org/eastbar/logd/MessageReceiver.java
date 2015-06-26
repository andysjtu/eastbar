package org.eastbar.logd;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by AndySJTU on 2015/6/24.
 */
public class MessageReceiver implements MessageListener {


    @Override
    public void onMessage(Message message) {
        System.out.println("receive message"+message);
        MapMessage mapMessage = (MapMessage)message;
        try {
            String content = mapMessage.getString("content");
            System.out.println("content is : "+content);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
