/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.report.biz;

import org.eastbar.web.PageInfo;

/**
 * @author cindy-jia
 * @date 2014年12月08
 * @time 下午3:04
 * @description :
 */
public class StatMonthAlarmBO extends PageInfo {

    private Integer id;
    private Integer staYear;
    private Integer staMonth;
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


}
