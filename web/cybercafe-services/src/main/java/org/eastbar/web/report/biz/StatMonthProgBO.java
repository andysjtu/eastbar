/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.report.biz;

import org.eastbar.web.PageInfo;

/**
 * @author cindy-jia
 * @date 2014年12月08
 * @time 下午3:00
 * @description :
 */
public class StatMonthProgBO extends PageInfo {

    private Integer id;
    private Integer sta_year;
    private Integer sta_month;
    private String sta_time;
    private String siteCode;
    private String monitorCode;
    private String progName;
    private Integer count;

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

    public String getProgName() {
        return progName;
    }

    public void setProgName(String progName) {
        this.progName = progName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
