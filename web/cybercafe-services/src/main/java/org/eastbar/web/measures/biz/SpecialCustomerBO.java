/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.measures.biz;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.eastbar.web.PageInfo;

/**
 * @author cindy-jia
 * @date 2014年10月23
 * @time 下午5:08
 * @description :
 */
public class SpecialCustomerBO extends PageInfo {

    private Integer id;
    private String name; //name
    private Integer certType; //cert_type
    private String certId; //cert_id
    private String nationality; //nationality
    private String alarmType;
    private Integer alarmRank; //alarm_rank
    private Integer isPub; //is_pub
    private String createTime; //create_time
    private String creator; //creator
    private String updateTime; //update_time
    private String updator; //updator
    private String version;
    private String operation;
    private Integer deleteFlag;
    private String monitorCode;

    private String[] names; //name
    private Integer[] certTypes; //cert_type
    private String[] certIds; //cert_id
    private String[] nationalitys; //nationality
    private String[] alarmTypes;
    private Integer[] alarmRanks; //alarm_rank
    private String[] monitorCodes;
    private String[] siteCodes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCertType() {
        return certType;
    }

    public void setCertType(Integer certType) {
        this.certType = certType;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public Integer getAlarmRank() {
        return alarmRank;
    }

    public void setAlarmRank(Integer alarmRank) {
        this.alarmRank = alarmRank;
    }

    public Integer getIsPub() {
        return isPub;
    }

    public void setIsPub(Integer isPub) {
        this.isPub = isPub;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public Integer[] getCertTypes() {
        return certTypes;
    }

    public void setCertTypes(Integer[] certTypes) {
        this.certTypes = certTypes;
    }

    public String[] getCertIds() {
        return certIds;
    }

    public void setCertIds(String[] certIds) {
        this.certIds = certIds;
    }

    public String[] getNationalitys() {
        return nationalitys;
    }

    public void setNationalitys(String[] nationalitys) {
        this.nationalitys = nationalitys;
    }

    public String[] getAlarmTypes() {
        return alarmTypes;
    }

    public void setAlarmTypes(String[] alarmTypes) {
        this.alarmTypes = alarmTypes;
    }

    public Integer[] getAlarmRanks() {
        return alarmRanks;
    }

    public void setAlarmRanks(Integer[] alarmRanks) {
        this.alarmRanks = alarmRanks;
    }

    public String getMonitorCode() {
        return monitorCode;
    }

    public void setMonitorCode(String monitorCode) {
        this.monitorCode = monitorCode;
    }

    public String[] getMonitorCodes() {
        return monitorCodes;
    }

    public void setMonitorCodes(String[] monitorCodes) {
        this.monitorCodes = monitorCodes;
    }

    public String[] getSiteCodes() {
        return siteCodes;
    }

    public void setSiteCodes(String[] siteCodes) {
        this.siteCodes = siteCodes;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
