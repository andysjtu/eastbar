/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.ipc.entity;

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
public class Site implements Serializable {  //T_SITE_INFO
    private String siteCode; //SITE_CODE
    private String monitorCode; //MONITOR_CODE
    private String name; //NAME
    private String regStatus; //REG_STATUS
    private String address; //ADDRESS
    private String zip; //ZIP
    private String legalRepresent; //LEGAL_REPRESENT
    private String principal; //PRINCIPAL
    private String principalTel; //PRINCIPAL_TEL
    private String administrator; //ADMINISTRATOR
    private String adminTel; //ADMIN_TEL
    private String terminalNum; //TEMINAL_NUM
    private String createTime; //CREATE_TIME
    private String creator; //CREATOR
    private String updateTime; //UPDATE_TIME
    private String updator; //UPDATOR
    private String hourVer;
    private String urlVer;
    private String progVer;
    private String specialVer;
    private String keywordVer;

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

    public String getAdministrator() {
        return administrator;
    }

    public void setAdministrator(String administrator) {
        this.administrator = administrator;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
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

    @Override
    public boolean equals(Object o) {

        return null != o &&
                o instanceof Site &&
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
