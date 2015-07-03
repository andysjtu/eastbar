/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.report.biz;

import org.eastbar.web.PageInfo;

/**
 * @author cindy-jia
 * @date 2014年12月08
 * @time 下午3:03
 * @description :
 */
public class StatDayUrlBO extends PageInfo {

    private Integer id;
    private Integer staYear;
    private Integer staMonth;
    private Integer staDay;
    private String staTime;
    private String siteCode;
    private String monitorCode;
    private String url;
    private Integer accessCount;

    private String type;
    private Integer startYear;
    private Integer endYear;
    private Integer startMonth;
    private Integer endMonth;


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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getAccessCount() {
        return accessCount;
    }

    public void setAccessCount(Integer accessCount) {
        this.accessCount = accessCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public Integer getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(Integer startMonth) {
        this.startMonth = startMonth;
    }

    public Integer getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(Integer endMonth) {
        this.endMonth = endMonth;
    }
}
