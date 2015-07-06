/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.ipc.biz;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.eastbar.web.PageInfo;
/**
 * @author C.lins@aliyun.com
 * @date 2014年10月29
 * @time 下午2:10
 * @description :
 */
public class TerminalBO extends PageInfo {

    /** Terminal Info */
    private Integer id;
    private String siteCode;
    private String monitorCode;
    private String hostIp;
    private String accountId;
    private String customerName;
    private String certId;
    private Integer customerIdType;
    private String onlineTime;
    private String offlineTime;
    private Integer timeSpan;

    /** Site Info */
    private String siteName;
    private String lastUpdateTime;
    private String siteRunStatus;
    private Integer siteTerminalTotalNum;
    private Integer[] siteStatus;  //机器状态
    private String siteState;//

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getSiteRunStatus() {
        return siteRunStatus;
    }

    public void setSiteRunStatus(String siteRunStatus) {
        this.siteRunStatus = siteRunStatus;
    }

    public Integer getSiteTerminalTotalNum() {
        return siteTerminalTotalNum;
    }

    public void setSiteTerminalTotalNum(Integer siteTerminalTotalNum) {
        this.siteTerminalTotalNum = siteTerminalTotalNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public Integer getCustomerIdType() {
        return customerIdType;
    }

    public void setCustomerIdType(Integer customerIdType) {
        this.customerIdType = customerIdType;
    }

    public String getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(String onlineTime) {
        this.onlineTime = onlineTime;
    }

    public String getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(String offlineTime) {
        this.offlineTime = offlineTime;
    }

    public Integer getTimeSpan() {
        return timeSpan;
    }

    public void setTimeSpan(Integer timeSpan) {
        this.timeSpan = timeSpan;
    }

    public String getHostIp() {
        return hostIp;
    }

    public String getSiteState() {
        return siteState;
    }

    public void setSiteState(String siteState) {
        this.siteState = siteState;
    }

    public Integer[] getSiteStatus() {
        return siteStatus;
    }

    public void setSiteStatus(Integer[] siteStatus) {
        this.siteStatus = siteStatus;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
