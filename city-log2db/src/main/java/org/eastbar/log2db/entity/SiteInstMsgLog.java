package org.eastbar.log2db.entity;


import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by AndySJTU on 2015/6/4.
 */
@Entity
@Table(name="t_inst_msg_history")
public class SiteInstMsgLog  {
    private String siteCode;
    @Column(name="customer_id")
    private String customerId;
    
    @Column(name="customer_name")
    private String customerName;
    
    @Column(name = "host_ip")
    private String hostIp;
    
    @Column(name = "access_time")
    private Timestamp recordTime;

    
    @Column(name = "customer_id_type")
    private String customerType;

    @Column(name = "prog_type")
    private String progType;

    private String programName;
    
    @Column(name = "prog_account")
    private String progAccount;
    
    @Column(name = "start_time")
    private Date startTime;
    
    @Column(name = "end_time")
    private Date endTime;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public Timestamp getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Timestamp recordTime) {
        this.recordTime = recordTime;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getProgType() {
        return progType;
    }

    public void setProgType(String progType) {
        this.progType = progType;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgAccount() {
        return progAccount;
    }

    public void setProgAccount(String progAccount) {
        this.progAccount = progAccount;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    @Override
    public String toString() {
        return "SiteInstMsgLog{" +
                "siteCode='" + siteCode + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", hostIp='" + hostIp + '\'' +
                ", recordTime=" + recordTime +
                ", customerType='" + customerType + '\'' +
                ", progType='" + progType + '\'' +
                ", programName='" + programName + '\'' +
                ", progAccount='" + progAccount + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
