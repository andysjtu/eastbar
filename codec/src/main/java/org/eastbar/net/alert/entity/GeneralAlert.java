package org.eastbar.net.alert.entity;

import org.eastbar.net.EntityObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by AndySJTU on 2015/6/8.
 */
@Entity
@Table(name="general_alarm")
public class GeneralAlert extends EntityObject {

    private String customerAccount;
    private String customerIdType;
    private String customerId;
    private String customerName;
    private String hostIp;
    private Date alertTime;
    private boolean isBlock;
    private String alarmType;
    private String alarmRank;
    @Column(length=1000)
    private String alarmContent;

    public String getAlarmContent() {
        return alarmContent;
    }

    public void setAlarmContent(String alarmContent) {
        this.alarmContent = alarmContent;
    }

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

    public Date getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(Date alertTime) {
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

    public boolean isBlock() {
        return isBlock;
    }

    public void setIsBlock(boolean isBlock) {
        this.isBlock = isBlock;
    }



    @Override
    public String toString() {
        return "GeneralAlert{" +
                "alarmContent='" + alarmContent + '\'' +
                ", customerAccount='" + customerAccount + '\'' +
                ", customerIdType='" + customerIdType + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", hostIp='" + hostIp + '\'' +
                ", alertTime=" + alertTime +
                ", isBlock=" + isBlock +
                ", alarmType='" + alarmType + '\'' +
                ", alarmRank='" + alarmRank + '\'' +
                "} " + super.toString();
    }
}
