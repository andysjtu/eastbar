/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.report.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author cindy-jia
 * @date 2014年12月08
 * @time 下午2:56
 * @description :
 */
public class  StatDayAlarm implements Serializable{

    private Integer id;
    private Integer staYear;
    private Integer staMonth;
    private Integer staDay;
    private String staTime;
    private String siteCode;
    private String monitorCode;
    private Integer alarmCount;
    private Integer punishCount;
    private Integer customerCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStaYear() {
        return staYear;
    }

    public void setStaYear(Integer staYear) {
        this.staYear = staYear;
    }

    public Integer getStaMonth() {
        return staMonth;
    }

    public void setStaMonth(Integer staMonth) {
        this.staMonth = staMonth;
    }

    public Integer getStaDay() {
        return staDay;
    }

    public void setStaDay(Integer staDay) {
        this.staDay = staDay;
    }

    public String getStaTime() {
        return staTime;
    }

    public void setStaTime(String staTime) {
        this.staTime = staTime;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getMonitorCode() {
        return monitorCode;
    }

    public void setMonitorCode(String monitorCode) {
        this.monitorCode = monitorCode;
    }

    public Integer getAlarmCount() {
        return alarmCount;
    }

    public void setAlarmCount(Integer alarmCount) {
        this.alarmCount = alarmCount;
    }

    public Integer getPunishCount() {
        return punishCount;
    }

    public void setPunishCount(Integer punishCount) {
        this.punishCount = punishCount;
    }

    public Integer getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(Integer customerCount) {
        this.customerCount = customerCount;
    }

    @Override
    public boolean equals(Object o) {

        return null != o &&
                o instanceof StatDayAlarm &&
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
