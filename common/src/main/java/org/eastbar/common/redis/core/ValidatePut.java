/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.common.redis.core;

import java.util.Map;
import java.util.Set;

/**
 * @author cindy-jia
 * @date 2015年04月22
 * @time 下午3:24
 * @description :
 */
public class ValidatePut {

    /**
     * 验证一级监管中心的map参数
     * @param monitorLiveData
     * @return
     */
    public static Map validateCenterPut(Map<String,String> monitorLiveData){

        String params="monitorCode,totalSite,openSite,totalTerminal,totalAlarm,totalPunish";
        if(monitorLiveData!=null){
            String monitorCode=monitorLiveData.get("monitorCode");
            if(monitorCode!=null && !"".equals(monitorCode)){
                Set<String> keys=monitorLiveData.keySet();
                for(String key:keys){
                   if(!params.contains(key))
                       monitorLiveData.remove(key);
                }
                return monitorLiveData;
            }
        }
        return null;
    }

    /**
     * 验证二级监管中心的map参数
     * @param monitorLiveData
     * @return
     */
    public static Map validateMonitorPut(Map<String,String> monitorLiveData){

        String params="monitorCode,totalSite,openSite,totalTerminal,totalAlarm,totalPunish";
        if(monitorLiveData!=null){
            String monitorCode=monitorLiveData.get("monitorCode");
            if(monitorCode!=null && !"".equals(monitorCode)){
                Set<String> keys=monitorLiveData.keySet();
                for(String key:keys){
                    if(!params.contains(key))
                        monitorLiveData.remove(key);
                }
                return monitorLiveData;
            }
        }
        return null;
    }

    /**
     * 验证场所的map参数
     * @param siteLiveData
     * @return
     */
    public static Map validateSitePut(Map<String,String> siteLiveData){

        String params="siteCode,activeCustomerCount,runStatus,totalAlarm,totalPunish,isActive";
        if(siteLiveData!=null){
            String siteCodeCode=siteLiveData.get("siteCode");
            if(siteCodeCode!=null && !"".equals(siteCodeCode)){
                Set<String> keys=siteLiveData.keySet();
                for(String key:keys){
                    if(!params.contains(key))
                        siteLiveData.remove(key);
                }
                return siteLiveData;
            }
        }
        return null;
    }

    /**
     * 验证终端map的参数
     * @param terminalLog
     * @return
     */
    public static Map validateTermianlHashPut(Map<String,String> terminalLog){

        String params="siteCode,hostIp,customerName,certId,siteState,onlineTime";
        if(terminalLog!=null){
            String siteCode=terminalLog.get("siteCode");
            String hostIp=terminalLog.get("hostIp");
            if(siteCode!=null && !"".equals(siteCode)){
                if(hostIp!=null && !"".equals(hostIp)){
                    Set<String> keys=terminalLog.keySet();
                    for(String key:keys){
                        if(!params.contains(key))
                            terminalLog.remove(key);
                    }
                    return terminalLog;
                }
            }
        }
        return null;
    }
}
