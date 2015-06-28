/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.statusMachine;

import org.eastbar.center.statusMachine.core.Handles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @author C.lins@aliyun.com
 * @date 2015年03月31
 * @time 下午4:44
 * @description :
 */
@Component
public class StatusMachine {

    private ExecutorService statusPools = null;
    private ExecutorService dbPools = null;
    @Autowired(required = true)
    Handles handles;
    public void onStart(){
        init();
    }

    private void init(){
        if(statusPools==null){
            statusPools = Executors.newFixedThreadPool(3);
        }
        if(dbPools==null){
            dbPools = Executors.newFixedThreadPool(5);
        }
        handles.setPools(statusPools);
        handles.setDbPools(dbPools);
        handles.start();
    }
}
