/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.ipc.biz;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.eastbar.web.PageInfo;
import org.eastbar.web.Times;

/**
 * @author cindy-jia
 * @date 2014年10月23
 * @time 下午4:46
 * @description : 场所端业务类
 */
public class SiteBO extends PageInfo {

    /** Info Data */
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
    private String hourVer;
    private String urlVer;
    private String progVer;
    private String specialVer;
    private String keywordVer;
    {
        this.setCreateTime(Times.now());
    }

    /** Live Data */
    private Long connectTerm; //CONNECT_TERM
    private Long activeCustomerCount; //ACTIVE_CUSTOMER_COUNT
    private Integer runStatus; //RUN_STATUS
    private Long totalAlarm; //TOTAL_ALARM
    private Long totalPunish; //TOTAL_PUNISH
    private Integer installationRate;
    private String lastUpdateTime; //LAST_UPDATE_TIME

    public Integer getInstallationRate() {
        return installationRate;
    }

    public void setInstallationRate(Integer installationRate) {
        this.installationRate = installationRate;
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

    public Long getConnectTerm() {
        return connectTerm;
    }

    public void setConnectTerm(Long connectTerm) {
        this.connectTerm = connectTerm;
    }

    public Long getActiveCustomerCount() {
        return activeCustomerCount;
    }

    public void setActiveCustomerCount(Long activeCustomerCount) {
        this.activeCustomerCount = activeCustomerCount;
    }

    public Integer getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(Integer runStatus) {
        this.runStatus = runStatus;
    }

    public Long getTotalAlarm() {
        return totalAlarm;
    }

    public void setTotalAlarm(Long totalAlarm) {
        this.totalAlarm = totalAlarm;
    }

    public Long getTotalPunish() {
        return totalPunish;
    }

    public void setTotalPunish(Long totalPunish) {
        this.totalPunish = totalPunish;
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

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
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
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
