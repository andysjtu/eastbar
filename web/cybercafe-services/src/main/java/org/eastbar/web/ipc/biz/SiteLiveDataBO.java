/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.ipc.biz;

import org.eastbar.web.PageInfo;

/**
 * @author cindy-jia
 * @date 2014年12月08
 * @time 上午10:44
 * @description :
 */
public class SiteLiveDataBO extends PageInfo {
    private Integer id; //ID
    private String siteCode; //SITE_CODE
    private Long connectTerm; //CONNECT_TERM
    private Long activeCustomerCount; //ACTIVE_CUSTOMER_COUNT
    private Integer runStatus; //RUN_STATUS
    private Long totalAlarm; //TOTAL_ALARM
    private Long totalPunish; //TOTAL_PUNISH
    private String lastUpdateTime; //LAST_UPDATE_TIME

    private String lname;
    private String mname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public Long getConnectTerm() {
        return connectTerm;
    }

    public void setConnectTerm(Long connectTerm) {
        this.connectTerm = connectTerm;
    }

    public Long getActiveCustomerCount() {
        return activeCustomerCount;
    }

    public void setActiveCustomerCount(Long activeCustomerCount) {
        this.activeCustomerCount = activeCustomerCount;
    }

    public Integer getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(Integer runStatus) {
        this.runStatus = runStatus;
    }

    public Long getTotalAlarm() {
        return totalAlarm;
    }

    public void setTotalAlarm(Long totalAlarm) {
        this.totalAlarm = totalAlarm;
    }

    public Long getTotalPunish() {
        return totalPunish;
    }

    public void setTotalPunish(Long totalPunish) {
        this.totalPunish = totalPunish;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }
}
