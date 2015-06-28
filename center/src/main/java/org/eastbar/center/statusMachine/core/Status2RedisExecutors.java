/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.statusMachine.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author C.lins@aliyun.com
 * @date 2015年06月02
 * @time 下午2:43
 * @description :
 */
public class Status2RedisExecutors {

    private static ExecutorService s2rPools = null;

    private static Status2RedisExecutors executors;
    public static Status2RedisExecutors getInstance(){
        if(executors==null){
            executors = new Status2RedisExecutors();
            s2rPools = Executors.newFixedThreadPool(5);
        }
        return executors;
    }

    public void push(Runnable runnable){
        this.s2rPools.execute(runnable);
    }
}
