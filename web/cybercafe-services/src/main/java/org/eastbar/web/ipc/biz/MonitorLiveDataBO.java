/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.ipc.biz;

import org.eastbar.web.PageInfo;

/**
 * @author cindy-jia
 * @date 2014年12月08
 * @time 上午10:40
 * @description :
 */
public class MonitorLiveDataBO extends PageInfo {

    private Integer id; //ID
    private String monitorCode; //MONITOR_CODE
    private Integer status; //STATUS
    private Long totalSite; //TOTAL_SITE
    private Long setupSite; //SETUP_SITE
    private Long openSite; //OPEN_SITE
    private Long totalTerminal; //TOTAL_TERMINAL
    private Long totalAlarm; //TOTAL_ALARM
    private Long totalPunish; //TOTAL_PUNISH
    private String lastUpdateTime; //LAST_UPDATE_TIME

    private String lname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMonitorCode() {
        return monitorCode;
    }

    public void setMonitorCode(String monitorCode) {
        this.monitorCode = monitorCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getTotalSite() {
        return totalSite;
    }

    public void setTotalSite(Long totalSite) {
        this.totalSite = totalSite;
    }

    public Long getSetupSite() {
        return setupSite;
    }

    public void setSetupSite(Long setupSite) {
        this.setupSite = setupSite;
    }

    public Long getOpenSite() {
        return openSite;
    }

    public void setOpenSite(Long openSite) {
        this.openSite = openSite;
    }

    public Long getTotalTerminal() {
        return totalTerminal;
    }

    public void setTotalTerminal(Long totalTerminal) {
        this.totalTerminal = totalTerminal;
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

}
