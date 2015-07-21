/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.statusMachine.basis;


import com.google.common.collect.Maps;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.eastbar.center.statusMachine.HostEvent;
import org.eastbar.center.statusMachine.ResetEvent;
import org.eastbar.center.statusMachine.core.Count;
import org.eastbar.center.statusMachine.core.Offset;
import org.eastbar.center.statusMachine.core.Event;
import org.eastbar.center.statusMachine.core.Status2RedisExecutors;
import org.eastbar.center.utils.SpringContextHolder;
import org.eastbar.common.redis.CenterRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author C.lins@aliyun.com
 * @date 2015年01月19
 * @time 下午2:52
 * @description :
 */
public class City implements Serializable {
    public final static Logger log = LoggerFactory.getLogger(City.class);

    private String code;
    private int[] runStatus = {0,0,0,0};
    private AtomicInteger openSite = new AtomicInteger(0);
    private AtomicInteger totalSite = new AtomicInteger(0);
    private Map<String,County> countyMap = new ConcurrentHashMap<>();

    private synchronized void addCounty(County county){
        countyMap.put(county.getCode(),county);
    }

    public County findCounty(String code){
        if(countyMap.containsKey(code)){
            return countyMap.get(code);
        }
        return null;
    }

    public Offset analysis(Event event){
        String countyCode = event.getSiteCode().substring(0,6);
        County county = findCounty(countyCode);
        if(event instanceof ResetEvent){
            if(county==null){
                log.info("编号:County-{} 未上报过状态，无法Reset.",countyCode);
                return null;
            }
        }

        if(county==null){
            county = new County();
            county.setCode(countyCode);
            addCounty(county);
        }
        Offset offset = county.analysis(event);
        if(offset!=null){
            this.openSite.getAndAdd(offset.getOpen());
            this.totalSite.getAndAdd(offset.getTotal());
            synchronized (this.runStatus){
                Count.runStatus(this.runStatus, offset.getRun());
                Status2RedisExecutors.getInstance().push(new City2Redis(this));
            }
        }
        return offset;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int[] getRunStatus() {
        return runStatus;
    }

    public AtomicInteger getTotalSite() {
        return totalSite;
    }

    public AtomicInteger getOpenSite() {
        return openSite;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}

class City2Redis implements Runnable{

    private City city;
    public City2Redis(City city){
        this.city = city;
    }

    @Override
    public void run() {
        CenterRedisService cs = SpringContextHolder.getBean("centerRedisServiceImpl");

        //monitorCode、totalSite、openSite、totalTerminal、totalAlarm、totalPunish
        Map<String,String> citymap = Maps.newHashMap();
        citymap.put("monitorCode",city.getCode());
        citymap.put("totalSite",city.getTotalSite().toString());
        citymap.put("openSite",city.getOpenSite().toString());
        int totalTerminal=0;
        for(int i:city.getRunStatus()){
            totalTerminal += i;
        }
        citymap.put("totalTerminal",totalTerminal+"");
        citymap.put("totalAlarm","-999");
        citymap.put("totalPunish","-999");

        cs.centerHashPut(citymap);

    }
}