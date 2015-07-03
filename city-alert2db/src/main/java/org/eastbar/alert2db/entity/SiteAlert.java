package org.eastbar.alert2db.entity;



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
    
    @Column(name="customer_name")
    private String customerName;
    
    @Column(name = "host_ip")
    private String hostIp;
    
    @Column(name = "alarm_time")
    private Date recordTime;
    
    @Column(name = "is_block")
    private String blocked;
    
    @Column(name = "customer_type")
    private String customerType;
    
    @Column(name = "alarm_type")
    private String alarmType;
    
    @Column(name = "alarm_level")
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

    public String isBlocked() {
        return blocked;
    }

    public void setBlocked(String blocked) {
        this.blocked = blocked;
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
}
