/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.unit.entity;

import java.io.Serializable;

/**
 * @author cindy-jia
 * @date 2014年10月16
 * @time 下午7:46
 * @description :
 */
public class AlarmHistory implements Serializable {//t_alarm_history

    private String siteCode; //site_code
    private String monitorCode;//monitor_code
    private String provinceCode;//province_code
    private String cityCode;//city_code
    private String countyCode;//county_code
    private String hostIp;//host_ip
    private String alarmTime;//alarm_time
    private Integer alarmType;//alarm_type
    private String alarmLevel;//alarm_level
    private String alarmContent;
    private String customerName;//customer_name
    private String customerCertType;//customer_cert_type
    private String customerCertId;//customer_cert_id
    private Integer isBlock;

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

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public Integer getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Integer alarmType) {
        this.alarmType = alarmType;
    }

    public String getAlarmContent() {
        return alarmContent;
    }

    public void setAlarmContent(String alarmContent) {
        this.alarmContent = alarmContent;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerCertType() {
        return customerCertType;
    }

    public void setCustomerCertType(String customerCertType) {
        this.customerCertType = customerCertType;
    }

    public String getCustomerCertId() {
        return customerCertId;
    }

    public void setCustomerCertId(String customerCertId) {
        this.customerCertId = customerCertId;
    }

    public Integer getIsBlock() {
        return isBlock;
    }

    public void setIsBlock(Integer isBlock) {
        this.isBlock = isBlock;
    }
}
