/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.centers.statusMachine.basis;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.eastbar.centers.statusMachine.HostEvent;
import org.eastbar.centers.statusMachine.ResetEvent;
import org.eastbar.centers.statusMachine.core.Count;
import org.eastbar.centers.statusMachine.core.Event;
import org.eastbar.centers.statusMachine.core.Offset;
import org.eastbar.centers.statusMachine.core.Status2RedisExecutors;
import org.eastbar.centers.utils.SpringContextHolder;
import org.eastbar.common.redis.CenterRedisService;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author C.lins@aliyun.com
 * @date 2015年01月19
 * @time 下午3:29
 * @description :
 */
public class County implements Serializable {

    private String code;
    private int[] runStatus = {0,0,0,0};
    private AtomicInteger openSite = new AtomicInteger(0);
    private Map<String,Site> siteMap = new ConcurrentHashMap<>();

    private void addSite(Site site){
        siteMap.put(site.getCode(),site);
    }
    public Site findSite(String code){
        if(siteMap.containsKey(code)){
            return siteMap.get(code);
        }
        return null;
    }
    public Offset analysis(Event event){
        Offset offset = new Offset();
        String siteCode = event.getSiteCode();
        Site site = findSite(siteCode);
        if(event instanceof HostEvent){
            if(site==null){
                site = new Site();
                site.setCode(siteCode);
                offset.setTotal(1);
                addSite(site);
            }
            if(!site.isActive()){
                this.openSite.getAndIncrement();
                offset.setOpen(1);
            }
        }else if (event instanceof ResetEvent){
            if(site.isActive()){
                this.openSite.getAndDecrement();
                offset.setOpen(-1);
            }
        }
        offset.setRun(site.analysis(event));
        synchronized (this.runStatus){
            Count.runStatus(this.runStatus, offset.getRun());
            Status2RedisExecutors.getInstance().push(new County2Redis(this));
        }
        return offset;
    }
    public int getTotalSite(){
        return this.siteMap.size();
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

    public AtomicInteger getOpenSite() {
        return openSite;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
class County2Redis implements Runnable{

    private County county;
    public County2Redis(County county){
        this.county = county;
    }

    @Override
    public void run() {
        CenterRedisService cs = SpringContextHolder.getBean("centerRedisServiceImpl");

        //monitorCode、totalSite、openSite、totalTerminal、totalAlarm、totalPunish
        Map<String,String> countymap = Maps.newHashMap();
        countymap.put("monitorCode",county.getCode());
        countymap.put("totalSite",county.getTotalSite()+"");
        countymap.put("openSite",county.getOpenSite().toString());
        int totalTerminal=0;
        for(int i:county.getRunStatus()){
            totalTerminal += i;
        }
        countymap.put("totalTerminal",totalTerminal+"");
        countymap.put("totalAlarm","-999");
        countymap.put("totalPunish","-999");

        cs.monitorHashPut(countymap);
    }
}