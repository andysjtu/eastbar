/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.centers.statusMachine.core;

import org.eastbar.centers.statusMachine.IEventPipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

/**
 * @author C.lins@aliyun.com
 * @date 2015年01月20
 * @time 下午2:13
 * @description :
 */
@Component
public class EventPipe implements IEventPipe {
    private static int MAX_SIZE = 100000;
    private LinkedList<Event> EVENTS = null;

    private static Logger log = LoggerFactory.getLogger(EventPipe.class);

    public EventPipe(){
        this(100000);
    }
    public EventPipe(int max){
        MAX_SIZE = max;
        if(EVENTS == null){
            EVENTS = new LinkedList<>();
        }
    }

    @Override
    public boolean addEvents(Event event) {
        synchronized(EVENTS){
            try{
                if(EVENTS.size() > MAX_SIZE){
                    EVENTS.wait();
                }

                if(EVENTS.offerLast(event)){
                    log.info("加入队列" + event.toString());
                    EVENTS.notifyAll();
                    return true;
                }
            }catch (Exception e){
                e.printStackTrace();
                EVENTS.notify();
                log.error("加入队列出错！" + event.toString());
            }
            return false;
        }
    }

    @Override
    public Event getEvents() {
        synchronized(EVENTS){
            if(EVENTS.size()==0){
                try {
                    EVENTS.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    EVENTS.notifyAll();
                }
            }
            Event event = EVENTS.pollFirst();
            EVENTS.notifyAll();
            return event;
        }
    }

}
