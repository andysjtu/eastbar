/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.unit.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author cindy-jia
 * @date 2014年12月05
 * @time 上午10:06
 * @description :
 */
public class MailHistory implements Serializable {

    private Integer cid;
    private String customerId;
    private String siteCode;
    private String monitorCode;
    private String provinceCode;
    private String cityCode;
    private String countyCode;
    private String hostIp;
    private String emailType;
    private String startTime;
    private String endTime;
    private String customerName;
    private Integer customerIdType;
    private String emailAccount;
    private String receive;
    private String theme;
    private String isBlock;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getCustomerIdType() {
        return customerIdType;
    }

    public void setCustomerIdType(Integer customerIdType) {
        this.customerIdType = customerIdType;
    }

    public String getEmailAccount() {
        return emailAccount;
    }

    public void setEmailAccount(String emailAccount) {
        this.emailAccount = emailAccount;
    }

    public String getReceive() {
        return receive;
    }

    public void setReceive(String receive) {
        this.receive = receive;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getIsBlock() {
        return isBlock;
    }

    public void setIsBlock(String isBlock) {
        this.isBlock = isBlock;
    }

    @Override
    public boolean equals(Object o) {

        return null != o &&
                o instanceof MailHistory &&
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