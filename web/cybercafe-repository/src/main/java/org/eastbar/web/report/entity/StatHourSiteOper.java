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
 * @date 2014年12月22
 * @time 下午4:26
 * @description :
 */
public class StatHourSiteOper implements Serializable {

    private Integer id;
    private Integer staYear;
    private Integer staMonth;
    private Integer staHour;
    private String staTime;
    private String siteCode;
    private String monitorCode;
    private Float onlineTime;
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

    public Integer getStaHour() {
        return staHour;
    }

    public void setStaHour(Integer staHour) {
        this.staHour = staHour;
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

    public Float getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Float onlineTime) {
        this.onlineTime = onlineTime;
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
                o instanceof StatHourSiteOper &&
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
