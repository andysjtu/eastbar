/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.log.biz;

import org.eastbar.web.PageInfo;
import org.eastbar.web.log.entity.MonitorCmd;

/**
 * @author cindy-jia
 * @date 2014年12月04
 * @time 下午4:14
 * @description :
 */
public class OperLogBO extends PageInfo {

    private Integer id;
    private Integer userId;
    private String operTime;
    private String operLog;
    private Integer operType;
    private Integer cmdId;

    private String userName;
    private MonitorCmd monitorCmd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOperTime() {
        return operTime;
    }

    public void setOperTime(String operTime) {
        this.operTime = operTime;
    }

    public String getOperLog() {
        return operLog;
    }

    public void setOperLog(String operLog) {
        this.operLog = operLog;
    }

    public Integer getOperType() {
        return operType;
    }

    public void setOperType(Integer operType) {
        this.operType = operType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public MonitorCmd getMonitorCmd() {
        return monitorCmd;
    }

    public void setMonitorCmd(MonitorCmd monitorCmd) {
        this.monitorCmd = monitorCmd;
    }

    public Integer getCmdId() {
        return cmdId;
    }

    public void setCmdId(Integer cmdId) {
        this.cmdId = cmdId;
    }
}
