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
 * @time 下午2:38
 * @description : 监管中心信息表
 */
public class Monitor implements Serializable {  //T_MONITOR_INFO
    private String monitorCode; //MONITOR_CODE
    private Integer type; //TYPE
    private String parentCode; //PARENT_CODE
    private String name; //NAME
    private String address;
    private String zip; //ZIP
    private String principal; //PRINCIPAL
    private String principalTel; //PRINCIPAL_TEL
    private String contactPerson; //CONTACT_PERSON
    private String contactTel; //CONTACT_TEL
    private String email; //EMAIL
    private String createTime; //CREATE_TIME
    private String creator; //CREATOR
    private String updateTime; //UPDATE_TIME
    private String updator; //UPDATOR
    private Long permitSite; //PERMIT_SITE

    private String hourVer;
    private String urlVer;
    private String progVer;
    private String specialVer;
    private String keyWordVer;


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

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public Long getPermitSite() {
        return permitSite;
    }

    public void setPermitSite(Long permitSite) {
        this.permitSite = permitSite;
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

    public String getKeyWordVer() {
        return keyWordVer;
    }

    public void setKeyWordVer(String keyWordVer) {
        this.keyWordVer = keyWordVer;
    }

    @Override
    public boolean equals(Object o) {

        return null != o &&
                o instanceof Monitor &&
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
