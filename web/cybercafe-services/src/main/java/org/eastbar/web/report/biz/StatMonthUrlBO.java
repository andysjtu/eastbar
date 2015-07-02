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
public class StatMonthUrlBO extends PageInfo {

    private Integer id;
    private Integer sta_year;
    private Integer sta_month;
    private String sta_time;
    private String siteCode;
    private String monitorCode;
    private String url;
    private Integer accessCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSta_year() {
        return sta_year;
    }

    public void setSta_year(Integer sta_year) {
        this.sta_year = sta_year;
    }

    public Integer getSta_month() {
        return sta_month;
    }

    public void setSta_month(Integer sta_month) {
        this.sta_month = sta_month;
    }

    public String getSta_time() {
        return sta_time;
    }

    public void setSta_time(String sta_time) {
        this.sta_time = sta_time;
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
}
