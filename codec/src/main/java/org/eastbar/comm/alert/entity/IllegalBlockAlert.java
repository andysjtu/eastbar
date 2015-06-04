package org.eastbar.comm.alert.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by AndySJTU on 2015/6/4.
 */
@Entity
@Table(name = "illegal_alert")
public class IllegalBlockAlert {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String customerAccount;
    private String customerIdType;
    private String customerId;
    private String customerName;
    private String hostIp;
    private Timestamp alertTime;
    private boolean isBlock;
    private String alarmType;
    private String alarmRank;

    private String keyword;
    private String url;

    public String getAlarmRank() {
        return alarmRank;
    }

    public void setAlarmRank(String alarmRank) {
        this.alarmRank = alarmRank;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public Timestamp getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(Timestamp alertTime) {
        this.alertTime = alertTime;
    }

    public String getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(String customerAccount) {
        this.customerAccount = customerAccount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerIdType() {
        return customerIdType;
    }

    public void setCustomerIdType(String customerIdType) {
        this.customerIdType = customerIdType;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isBlock() {
        return isBlock;
    }

    public void setIsBlock(boolean isBlock) {
        this.isBlock = isBlock;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "IllegalBlockAlert{" +
                "alarmRank='" + alarmRank + '\'' +
                ", id=" + id +
                ", customerAccount='" + customerAccount + '\'' +
                ", customerIdType='" + customerIdType + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", hostIp='" + hostIp + '\'' +
                ", alertTime=" + alertTime +
                ", isBlocked=" + isBlock +
                ", alarmType='" + alarmType + '\'' +
                ", keyword='" + keyword + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
