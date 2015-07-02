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
 * @date 2014年11月07
 * @time 上午11:05
 * @description :
 */
public class KeyWordBO extends PageInfo {

    private Integer id;
    private String keyword;
    private Integer alarmType;
    private Integer alarmRank;
    private Integer isBlock;
    private Integer isPub;
    private String version;
    private String createTime;
    private String creator;
    private String updateTime;
    private String updator;
    private String operation;
    private Integer deleteFlag;
    private String monitorCode;

    private String[] keywords;
    private Integer[] alarmTypes;
    private Integer[] alarmRanks;
    private Integer[] isBlocks;
    private String[] siteCodes;
    private String[] monitorCodes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Integer alarmType) {
        this.alarmType = alarmType;
    }

    public Integer getAlarmRank() {
        return alarmRank;
    }

    public void setAlarmRank(Integer alarmRank) {
        this.alarmRank = alarmRank;
    }

    public Integer getIsBlock() {
        return isBlock;
    }

    public void setIsBlock(Integer isBlock) {
        this.isBlock = isBlock;
    }

    public Integer getIsPub() {
        return isPub;
    }

    public void setIsPub(Integer isPub) {
        this.isPub = isPub;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    public Integer[] getAlarmTypes() {
        return alarmTypes;
    }

    public void setAlarmTypes(Integer[] alarmTypes) {
        this.alarmTypes = alarmTypes;
    }

    public Integer[] getAlarmRanks() {
        return alarmRanks;
    }

    public void setAlarmRanks(Integer[] alarmRanks) {
        this.alarmRanks = alarmRanks;
    }

    public Integer[] getIsBlocks() {
        return isBlocks;
    }

    public void setIsBlocks(Integer[] isBlocks) {
        this.isBlocks = isBlocks;
    }

    public String getMonitorCode() {
        return monitorCode;
    }

    public void setMonitorCode(String monitorCode) {
        this.monitorCode = monitorCode;
    }

    public String[] getSiteCodes() {
        return siteCodes;
    }

    public void setSiteCodes(String[] siteCodes) {
        this.siteCodes = siteCodes;
    }

    public String[] getMonitorCodes() {
        return monitorCodes;
    }

    public void setMonitorCodes(String[] monitorCodes) {
        this.monitorCodes = monitorCodes;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
