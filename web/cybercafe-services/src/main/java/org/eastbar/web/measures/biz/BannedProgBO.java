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
 * @time 下午5:07
 * @description :
 */
public class BannedProgBO extends PageInfo {


    private Integer id;//id
    private Integer progType;//prog_type
    private String progName;//prog_name
    private String progFileName;//prog_filename
    private String progressName;//progress_name
    private String progMF;//prog_mf
    private String featureCode;//feature_code
    private Integer alarmType;//alarm_type
    private Integer alarmRank;//alarm_rank
    private Integer isPub;//is_pub
    private String version;
    private String operation;
    private Integer deleteFlag;
    private String monitorCode;

    private String[] progNames;//prog_name
    private Integer[] progTypes;
    private String[] progFileNames;//prog_filename
    private String[] progressNames;//progress_name
    private String[] progMFs;//prog_mf
    private String[] featureCodes;//reature_code
    private Integer[] alarmTypes;//alarm_type
    private Integer[] alarmRanks;//alarm_rank
    private String[] monitorCodes;
    private String[] siteCodes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProgType() {
        return progType;
    }

    public void setProgType(Integer progType) {
        this.progType = progType;
    }

    public String getProgName() {
        return progName;
    }

    public void setProgName(String progName) {
        this.progName = progName;
    }

    public String getProgFileName() {
        return progFileName;
    }

    public void setProgFileName(String progFileName) {
        this.progFileName = progFileName;
    }

    public String getProgressName() {
        return progressName;
    }

    public void setProgressName(String progressName) {
        this.progressName = progressName;
    }

    public String getProgMF() {
        return progMF;
    }

    public void setProgMF(String progMF) {
        this.progMF = progMF;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
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

    public String[] getProgNames() {
        return progNames;
    }

    public void setProgNames(String[] progNames) {
        this.progNames = progNames;
    }

    public String[] getProgFileNames() {
        return progFileNames;
    }

    public void setProgFileNames(String[] progFileNames) {
        this.progFileNames = progFileNames;
    }

    public String[] getProgressNames() {
        return progressNames;
    }

    public void setProgressNames(String[] progressNames) {
        this.progressNames = progressNames;
    }

    public String[] getProgMFs() {
        return progMFs;
    }

    public void setProgMFs(String[] progMFs) {
        this.progMFs = progMFs;
    }

    public String[] getFeatureCodes() {
        return featureCodes;
    }

    public void setFeatureCodes(String[] featureCodes) {
        this.featureCodes = featureCodes;
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

    public Integer[] getProgTypes() {
        return progTypes;
    }

    public void setProgTypes(Integer[] progTypes) {
        this.progTypes = progTypes;
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
