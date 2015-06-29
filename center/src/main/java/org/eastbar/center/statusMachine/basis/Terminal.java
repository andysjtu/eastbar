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
import org.eastbar.center.utils.SpringContextHolder;
import org.eastbar.center.statusMachine.core.Event;
import org.eastbar.center.statusMachine.core.Status;
import org.eastbar.center.statusMachine.core.Status2RedisExecutors;
import org.eastbar.common.redis.CenterRedisService;

import java.io.Serializable;
import java.util.Map;

/**
 * @author C.lins@aliyun.com
 * @date 2015年01月20
 * @time 下午1:20
 * @description :
 */
public class Terminal implements Serializable {

    private Status status = null;
    private String account;
    private String name;
    private String certId;
    private String idType;
    private String authOrg;
    private String nation;

    private String siteCode;
    private String ip;
    private String version;
    private String os;
    private String macAddress;
    private String loginTime; //开户时间、上机时间

    public int[] analysis(Event event){
        int[] offset = {0,0,0,0};
        if(event instanceof HostEvent){
            Status status = int2Eunm(((HostEvent)event).getStatus());
            if(this.status==null){
                offset = status.getValue();
            }else{
                offset = status.countOffset(this.status);
            }
            this.status = status;
            this.account = ((HostEvent) event).getAccount();
            this.name = ((HostEvent) event).getName();
            this.certId = ((HostEvent) event).getCertId();
            this.idType = ((HostEvent) event).getIdType();
            this.authOrg = ((HostEvent) event).getAuthOrg();
            this.nation = ((HostEvent) event).getNation();

            this.loginTime = ((HostEvent) event).getLoginTime();
            this.siteCode = ((HostEvent) event).getSiteCode();
            this.ip = ((HostEvent) event).getIp();
            this.macAddress = ((HostEvent) event).getMacAddress();
            this.os = ((HostEvent) event).getOs();
            this.version = ((HostEvent) event).getVersion();

        }else if(event instanceof ResetEvent){
            offset = Status.OFFLINE.countOffset(this.status);
            this.status = Status.OFFLINE;
        }
        Status2RedisExecutors.getInstance().push(new Terminal2Redis(this));
        return offset;
    }

    public Status getStatus() {
        return status;
    }

    public String getAccount() {
        return account;
    }

    public String getName() {
        return name;
    }

    public String getCertId() {
        return certId;
    }

    public String getIdType() {
        return idType;
    }

    public String getAuthOrg() {
        return authOrg;
    }

    public String getNation() {
        return nation;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getVersion() {
        return version;
    }

    public String getOs() {
        return os;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public String getLoginTime() {
        return loginTime;
    }

    private Status int2Eunm(int status){
        switch (status){
            case 0:return Status.OFFLINE;
            case 1:return Status.UNKNOWN;
            case 2:return Status.FREE;
            case 3:return Status.ONLINE;
        }
        return null;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
class Terminal2Redis implements Runnable{

    private Terminal terminal;
    public Terminal2Redis(Terminal terminal){
        this.terminal = terminal;
    }

    @Override
    public void run() {
        CenterRedisService cs =  SpringContextHolder.getBean("centerRedisServiceImpl");

        //siteCode、hostIp、customerName、certId、siteState、onlineTime
        Map<String,String> termap = Maps.newHashMap();
        termap.put("siteCode",terminal.getSiteCode()!=null?terminal.getSiteCode():"");
        termap.put("hostIp",terminal.getIp()!=null?terminal.getIp():"");
        termap.put("customerName",terminal.getName()!=null?terminal.getName():"");
        termap.put("certId",terminal.getCertId()!=null?terminal.getCertId():"");
        termap.put("siteState",terminal.getStatus().toString());
        termap.put("onlineTime",terminal.getLoginTime()!=null?terminal.getLoginTime():"");

        cs.terminalDataPut(termap);
        //cs.terminalSetPut(terminal.getSiteCode(),terminal.getIp());
    }
}
