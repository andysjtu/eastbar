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
 * @time 下午5:05
 * @description :
 */
public class BannedUrlBO extends PageInfo {

    private Integer id;
    private Integer urlType;//url_type
    private String urlValue;//url_value
    private Integer alarmType;//alarm_type
    private Integer isBlock;//is_block
    private String alarmRank;//alarm_rank
    private String monitorCode;//monitor_code
    private Integer isPub;//is_pub
    private String creator;//creator
    private String createTime;//create_time
    private String updator;//updator
    private String updateTime;//update_time
    private String version;
    private String operation;
    private Integer deleteFlag;

    private Integer[] urlTypes;
    private String[] urlValues;
    private Integer[] alarmTypes;
    private Integer[] isBlocks;
    private String[] alarmRanks;
    private String[] monitorCodes;
    private String[] siteCodes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUrlType() {
        return urlType;
    }

    public void setUrlType(Integer urlType) {
        this.urlType = urlType;
    }

    public String getUrlValue() {
        return urlValue;
    }

    public void setUrlValue(String urlValue) {
        this.urlValue = urlValue;
    }

    public Integer getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Integer alarmType) {
        this.alarmType = alarmType;
    }

    public Integer getIsBlock() {
        return isBlock;
    }

    public void setIsBlock(Integer isBlock) {
        this.isBlock = isBlock;
    }

    public String getAlarmRank() {
        return alarmRank;
    }

    public void setAlarmRank(String alarmRank) {
        this.alarmRank = alarmRank;
    }

    public String getMonitorCode() {
        return monitorCode;
    }

    public void setMonitorCode(String monitorCode) {
        this.monitorCode = monitorCode;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
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

    public Integer[] getUrlTypes() {
        return urlTypes;
    }

    public void setUrlTypes(Integer[] urlTypes) {
        this.urlTypes = urlTypes;
    }

    public String[] getUrlValues() {
        return urlValues;
    }

    public void setUrlValues(String[] urlValues) {
        this.urlValues = urlValues;
    }

    public Integer[] getAlarmTypes() {
        return alarmTypes;
    }

    public void setAlarmTypes(Integer[] alarmTypes) {
        this.alarmTypes = alarmTypes;
    }

    public Integer[] getIsBlocks() {
        return isBlocks;
    }

    public void setIsBlocks(Integer[] isBlocks) {
        this.isBlocks = isBlocks;
    }

    public String[] getAlarmRanks() {
        return alarmRanks;
    }

    public void setAlarmRanks(String[] alarmRanks) {
        this.alarmRanks = alarmRanks;
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
