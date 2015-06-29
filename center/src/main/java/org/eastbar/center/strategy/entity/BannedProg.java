/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.strategy.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author cindy-jia
 * @date 2014年10月13
 * @time 下午7:42
 * @description :
 */
public class BannedProg implements Serializable {//t_banned_prog

    private Integer id;
    private String progType;
    private String progName;
    private String progressName;
    private String featureCode;
    private String alarmType;
    private String alarmRank;
    private int isBlock;
    private int verNum;
    private int deleted;
    private int isPub;

    private String operation;

    private String monitorCode;
    private String version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProgType() {
        return progType;
    }

    public void setProgType(String progType) {
        this.progType = progType;
    }

    public String getProgName() {
        return progName;
    }

    public void setProgName(String progName) {
        this.progName = progName;
    }

    public String getProgressName() {
        return progressName;
    }

    public void setProgressName(String progressName) {
        this.progressName = progressName;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getAlarmRank() {
        return alarmRank;
    }

    public void setAlarmRank(String alarmRank) {
        this.alarmRank = alarmRank;
    }

    public int getIsBlock() {
        return isBlock;
    }

    public void setIsBlock(int isBlock) {
        this.isBlock = isBlock;
    }

    public int getVerNum() {
        return verNum;
    }

    public void setVerNum(int verNum) {
        this.verNum = verNum;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public String getMonitorCode() {
        return monitorCode;
    }

    public void setMonitorCode(String monitorCode) {
        this.monitorCode = monitorCode;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getIsPub() {
        return isPub;
    }

    public void setIsPub(int isPub) {
        this.isPub = isPub;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public boolean equals(Object o) {

        return null != o &&
                o instanceof BannedProg &&
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
