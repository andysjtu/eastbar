/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.centers.strategy.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author C.lins@aliyun.com
 * @date 2014年10月14
 * @time 下午3:57
 * @description : 监管中心实时数据表, 每个管理中心每小时一条记录，每天清空 (N*24)*M
 */
public class MonitorLiveData implements Serializable{  //T_MONITOR_LIVE
    private static final long serialVersionUID = 522889572773714584L;

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

    @Override
    public boolean equals(Object o) {

        return null != o &&
                o instanceof MonitorLiveData &&
                EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
