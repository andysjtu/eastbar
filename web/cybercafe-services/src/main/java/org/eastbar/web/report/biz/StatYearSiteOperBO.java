/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.report.biz;

import org.eastbar.web.PageInfo;

/**
 * @author cindy-jia
 * @date 2014年12月30
 * @time 下午1:28
 * @description :
 */
public class StatYearSiteOperBO extends PageInfo {

    private Integer id;
    private Integer staYear;
    private Integer staTime;
    private String siteCode;
    private String monitorCode;
    private Float onlineTime;
    private Integer customerCount;
    private String terminaAveage;
    private String terminaTime;

    private String type;
    private Integer startYear;
    private Integer endYear;

    private String order;

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

    public Integer getStaTime() {
        return staTime;
    }

    public void setStaTime(Integer staTime) {
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

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getTerminaAveage() {
        return terminaAveage;
    }

    public void setTerminaAveage(String terminaAveage) {
        this.terminaAveage = terminaAveage;
    }

    public String getTerminaTime() {
        return terminaTime;
    }

    public void setTerminaTime(String terminaTime) {
        this.terminaTime = terminaTime;
    }
}
