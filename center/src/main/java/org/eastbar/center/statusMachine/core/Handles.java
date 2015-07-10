/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.statusMachine.core;

import org.eastbar.center.customerLog.service.impl.CustomerServiceImpl;
import org.eastbar.center.statusMachine.HostEvent;
import org.eastbar.center.statusMachine.InitEvent;
import org.eastbar.center.statusMachine.ResetEvent;
import org.eastbar.center.statusMachine.basis.Center;
import org.eastbar.center.statusMachine.IEventPipe;
import org.eastbar.center.utils.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;


/**
 * @author C.lins@aliyun.com
 * @date 2015年04月07
 * @time 上午10:11
 * @description :
 */
@Component
public class Handles extends Thread {

    ExecutorService pools = null;
    ExecutorService dbPools = null;
    @Autowired(required = true)
    IEventPipe pipe;
    Center center;

    public void run(){
        center = Center.getInstance();
        while(true){
            Event event = pipe.getEvents();
            if(event!=null){

                pools.execute(new EventAnalysis(event,center));
                dbPools.execute(new DbAnalysis(event));
            }
        }
    }
    public void setPools(ExecutorService pools) {
        this.pools = pools;
    }

    public void setDbPools(ExecutorService dbPools) {
        this.dbPools = dbPools;
    }
}
class EventAnalysis implements Runnable{

    private Event event;
    private Center center;
    EventAnalysis(Event event,Center center){
        this.event = event;
        this.center = center;
    }
    @Override
    public void run() {
        center.analysis(event);
    }
}

class DbAnalysis implements Runnable{

    private Event event;

    DbAnalysis(Event event){
        this.event = event;
    }
    @Override
    public void run() {
        CustomerServiceImpl csi = SpringContextHolder.getBean("customerServiceImpl");
        if(event instanceof HostEvent){
        //入库t_customer顾客上网信息  t_customer_host顾客上网记录
            csi.saveOrUpdate((HostEvent)event);
        }
        if(event instanceof ResetEvent){
            csi.resetOfflineTime(event.getSiteCode());
        }
    }
}