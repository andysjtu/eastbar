package org.eastbar.comm.log.entity;

import org.eastbar.comm.EntityObject;

import javax.persistence.*;

/**
 * Created by AndySJTU on 2015/5/29.
 */
@Entity
@Table(name = "intmsg_log")
public class InstMsgLog extends EntityObject{


    private String customerId;
    private String customerName;
    private String hostIp;
    private String recordTime;
    private boolean isBlock=false;

    private String progType;
    private String progAccount;
    private String startTime;
    private String endTime;

    public String getProgAccount() {
        return progAccount;
    }

    public void setProgAccount(String progAccount) {
        this.progAccount = progAccount;
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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    @Override
    public String toString() {
        return "InstMsgLog{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", hostIp='" + hostIp + '\'' +
                ", recordTime='" + recordTime + '\'' +
                ", isBlock=" + isBlock +
                ", progType='" + progType + '\'' +
                ", progAccount='" + progAccount + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                "} " + super.toString();
    }

    public boolean isBlock() {
        return isBlock;
    }

    public void setIsBlock(boolean isBlock) {
        this.isBlock = isBlock;
    }

    public String getProgType() {
        return progType;
    }

    public void setProgType(String progType) {
        this.progType = progType;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

}
