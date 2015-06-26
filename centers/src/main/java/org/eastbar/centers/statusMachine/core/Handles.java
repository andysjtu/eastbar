/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.centers.statusMachine.core;

import org.eastbar.centers.customerLog.service.impl.CustomerServiceImpl;
import org.eastbar.centers.statusMachine.HostEvent;
import org.eastbar.centers.statusMachine.IEventPipe;
import org.eastbar.centers.statusMachine.ResetEvent;
import org.eastbar.centers.statusMachine.basis.Center;
import org.eastbar.centers.utils.SpringContextHolder;
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
        while(true){
            Event event = pipe.getEvents();
            if(event!=null){
                center = Center.getInstance();
                pools.execute(new EventAnalysis(event,center));

                if(event instanceof HostEvent){
                    dbPools.execute(new DbAnalysis((HostEvent)event));
                }
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

        // 对事件进行处理，改变状态树
        if(event instanceof HostEvent){
            center.analysis((HostEvent)event);
        }
        // 重置某Stie节点下面所有Terminal
        else if(event instanceof ResetEvent){
            center.analysis((ResetEvent)event);
        }
    }
}

class DbAnalysis implements Runnable{

    private HostEvent event;

    DbAnalysis(HostEvent event){
        this.event = event;
    }
    @Override
    public void run() {

        //入库t_customer顾客上网信息  t_customer_host顾客上网记录
        CustomerServiceImpl csi = SpringContextHolder.getBean("customerServiceImpl");
        csi.saveOrUpdate(event);
    }
}