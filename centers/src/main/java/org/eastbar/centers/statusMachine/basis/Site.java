/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.centers.statusMachine.basis;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.eastbar.centers.Po2Json;
import org.eastbar.centers.statusMachine.HostEvent;
import org.eastbar.centers.statusMachine.ResetEvent;
import org.eastbar.centers.statusMachine.core.Count;
import org.eastbar.centers.statusMachine.core.Event;
import org.eastbar.centers.statusMachine.core.Status2RedisExecutors;
import org.eastbar.centers.utils.SpringContextHolder;
import org.eastbar.common.redis.CenterRedisService;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author C.lins@aliyun.com
 * @date 2015年01月20
 * @time 下午1:19
 * @description :
 */
public class Site implements Serializable {

    private String code;
    private int[] runStatus = {0,0,0,0};
    private boolean isActive = false;
    private Map<String,Terminal> terminalMap = new ConcurrentHashMap<>();

    private void addTerminal(Terminal terminal){
        terminalMap.put(terminal.getIp(),terminal);
    }
    public Terminal findTerminal(String ip){
        return terminalMap.get(ip);
    }
    public int[] analysis(Event event){
        int[] offset = {0,0,0,0};
        if(event instanceof HostEvent){
            this.isActive = true;
            String terminalIp = ((HostEvent)event).getIp();
            Terminal terminal = findTerminal(terminalIp);
            if(terminal==null){
                terminal = new Terminal();
                terminal.setIp(terminalIp);
                addTerminal(terminal);
            }
            offset = terminal.analysis((HostEvent)event);
        }else if(event instanceof ResetEvent){
            for(Terminal t:terminalMap.values()){
                Count.overlay(offset, t.analysis(event) );
            }
            this.isActive = false;
        }
        synchronized (this.runStatus){
            Count.runStatus(this.runStatus,offset);
            Status2RedisExecutors.getInstance().push(new Site2Redis(this));
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

    public boolean isActive() {
        return isActive;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
class Site2Redis implements Runnable{

    private Site site;
    public Site2Redis(Site site){
        this.site = site;
    }

    @Override
    public void run() {
        CenterRedisService cs = SpringContextHolder.getBean("centerRedisServiceImpl");

        //siteCode、activeCustomerCount、runStatus、totalAlarm、totalPunish
        Map<String,String> sitemap = Maps.newHashMap();
        sitemap.put("siteCode",site.getCode());
        sitemap.put("activeCustomerCount",site.getRunStatus()[0]+"");
        sitemap.put("runStatus", Po2Json.toJson(site.getRunStatus()));
        sitemap.put("totalAlarm","-999");
        sitemap.put("totalPunish","-999");

        cs.siteHashPut(sitemap);
    }
}