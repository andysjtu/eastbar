package org.eastbar.alert2db.entity;



import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by AndySJTU on 2015/6/4.
 */
//@Entity
//@Table(name="t_alarm_history")
public class SiteAlert  {

    private String siteCode;

    @Column(name="customer_id")
    private String customerId;
    @JsonProperty("customerIdType")
    @Column(name="customer_cert_type")
    private String customerCertType;
    
    @Column(name="customer_name")
    private String customerName;

    private boolean isBlock;


    
    @Column(name = "host_ip")
    private String hostIp;

    @JsonProperty("alertTime")
    @Column(name = "alarm_time")
    private Date recordTime;

    @Column(name = "customer_type")
    private String customerType;
    
    @Column(name = "alarm_type")
    private String alarmType;
    
    @Column(name = "alarm_level")
    @JsonProperty("alarmRank")
    private String alarmLevel;
    
    @Column(name="alarm_content")
    private String alarmContent;

    public String getAlarmContent() {
        return alarmContent;
    }

    public void setAlarmContent(String alarmContent) {
        this.alarmContent = alarmContent;
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }



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

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public String getCustomerCertType() {
        return customerCertType;
    }

    public void setCustomerCertType(String customerCertType) {
        this.customerCertType = customerCertType;
    }

    public boolean isBlock() {
        return isBlock;
    }

    public void setIsBlock(boolean isBlock) {
        this.isBlock = isBlock;
    }

    @Override
    public String toString() {
        return "SiteAlert{" +
                "siteCode='" + siteCode + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerCertType='" + customerCertType + '\'' +
                ", customerName='" + customerName + '\'' +
                ", hostIp='" + hostIp + '\'' +
                ", recordTime=" + recordTime +
                ", customerType='" + customerType + '\'' +
                ", alarmType='" + alarmType + '\'' +
                ", alarmLevel='" + alarmLevel + '\'' +
                ", alarmContent='" + alarmContent + '\'' +
                '}';
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

}
