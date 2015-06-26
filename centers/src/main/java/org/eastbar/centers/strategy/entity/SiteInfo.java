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
 * @time 下午4:11
 * @description : 场所端信息表
 */
public class SiteInfo implements Serializable {  //T_SITE_INFO

    private String siteCode;
    private String updateTime; //UPDATE_TIME
    private String updator; //UPDATOR
    private String hourVer;
    private String urlVer;
    private String progVer;
    private String specialVer;
    private String keywordVer;

    private String monitorCode;
    private String name;
    private String regStatus;
    private String address;
    private String zip;
    private String legalRepresent;
    private String principal;
    private String principalTel;
    private String adminstrator;
    private String adminTel;
    private String terminalNum;
    private String createTime;
    private String creator;




    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public String getHourVer() {
        return hourVer;
    }

    public void setHourVer(String hourVer) {
        this.hourVer = hourVer;
    }

    public String getUrlVer() {
        return urlVer;
    }

    public void setUrlVer(String urlVer) {
        this.urlVer = urlVer;
    }

    public String getProgVer() {
        return progVer;
    }

    public void setProgVer(String progVer) {
        this.progVer = progVer;
    }

    public String getSpecialVer() {
        return specialVer;
    }

    public void setSpecialVer(String specialVer) {
        this.specialVer = specialVer;
    }

    public String getKeywordVer() {
        return keywordVer;
    }

    public void setKeywordVer(String keywordVer) {
        this.keywordVer = keywordVer;
    }

    public String getMonitorCode() {
        return monitorCode;
    }

    public void setMonitorCode(String monitorCode) {
        this.monitorCode = monitorCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegStatus() {
        return regStatus;
    }

    public void setRegStatus(String regStatus) {
        this.regStatus = regStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getLegalRepresent() {
        return legalRepresent;
    }

    public void setLegalRepresent(String legalRepresent) {
        this.legalRepresent = legalRepresent;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getPrincipalTel() {
        return principalTel;
    }

    public void setPrincipalTel(String principalTel) {
        this.principalTel = principalTel;
    }

    public String getAdminstrator() {
        return adminstrator;
    }

    public void setAdminstrator(String adminstrator) {
        this.adminstrator = adminstrator;
    }

    public String getAdminTel() {
        return adminTel;
    }

    public void setAdminTel(String adminTel) {
        this.adminTel = adminTel;
    }

    public String getTerminalNum() {
        return terminalNum;
    }

    public void setTerminalNum(String terminalNum) {
        this.terminalNum = terminalNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public boolean equals(Object o) {

        return null != o &&
                o instanceof SiteInfo &&
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
