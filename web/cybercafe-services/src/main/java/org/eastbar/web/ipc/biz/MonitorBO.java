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
 * @date 2014年10月21
 * @time 下午5:22
 * @description : 监管中心业务类
 */
public class MonitorBO extends PageInfo {

    /** Info Data */
    private String monitorCode;
    private Integer type;
    private String parentCode;
    private String name;
    private String address;
    private String zip;
    private String principal;
    private String principalTel;
    private String contactPerson;
    private String contactTel;
    private String email;
    private String createTime;
    private String creator;
    private String updateTime;
    private String updator;
    private Long permitSite; //PERMIT_SITE
    private String hourVer;
    private String urlVer;
    private String progVer;
    private String specialVer;
    private String keywordVer;
    {
        this.setCreateTime(Times.now());
    }
    /** Live Data */
    private Integer status; //STATUS
    private Long totalSite; //TOTAL_SITE
    private Long setupSite; //SETUP_SITE
    private Long openSite; //OPEN_SITE
    private Long totalTerminal; //TOTAL_TERMINAL
    private Long totalAlarm; //TOTAL_ALARM
    private Long totalPunish; //TOTAL_PUNISH
    private Long lastUpdateTime; //LAST_UPDATE_TIME

    public String getMonitorCode() {
        return monitorCode;
    }

    public void setMonitorCode(String monitorCode) {
        this.monitorCode = monitorCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getTotalSite() {
        return totalSite;
    }

    public void setTotalSite(Long totalSite) {
        this.totalSite = totalSite;
    }

    public Long getPermitSite() {
        return permitSite;
    }

    public void setPermitSite(Long permitSite) {
        this.permitSite = permitSite;
    }

    public Long getSetupSite() {
        return setupSite;
    }

    public void setSetupSite(Long setupSite) {
        this.setupSite = setupSite;
    }

    public Long getOpenSite() {
        return openSite;
    }

    public void setOpenSite(Long openSite) {
        this.openSite = openSite;
    }

    public Long getTotalTerminal() {
        return totalTerminal;
    }

    public void setTotalTerminal(Long totalTerminal) {
        this.totalTerminal = totalTerminal;
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

    public Long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
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
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
