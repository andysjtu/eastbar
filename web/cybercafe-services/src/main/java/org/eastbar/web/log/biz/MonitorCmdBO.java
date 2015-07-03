/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.log.biz;

import org.eastbar.web.PageInfo;

/**
 * @author cindy-jia
 * @date 2014年12月08
 * @time 上午9:44
 * @description :
 */
public class MonitorCmdBO extends PageInfo {

    private Integer id;
    private Integer cmdType;
    private String customerId;
    private Integer customerIdType;
    private String hostIp;
    private String siteCode;
    private String monitorCode;
    private String commander;
    private String cmdTime;
    private Integer isSuccess;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCmdType() {
        return cmdType;
    }

    public void setCmdType(Integer cmdType) {
        this.cmdType = cmdType;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Integer getCustomerIdType() {
        return customerIdType;
    }

    public void setCustomerIdType(Integer customerIdType) {
        this.customerIdType = customerIdType;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
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

    public String getCommander() {
        return commander;
    }

    public void setCommander(String commander) {
        this.commander = commander;
    }

    public String getCmdTime() {
        return cmdTime;
    }

    public void setCmdTime(String cmdTime) {
        this.cmdTime = cmdTime;
    }

    public Integer getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Integer isSuccess) {
        this.isSuccess = isSuccess;
    }
}
