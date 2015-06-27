package org.eastbar.net.log.entity;

import org.eastbar.net.EntityObject;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by AndySJTU on 2015/5/29.
 */
@Entity
@Table(name = "intmsg_log")
public class InstMsgLog extends EntityObject{


    private String customerId;
    private String customerName;
    private String hostIp;
    private Date recordTime;
    private boolean isBlock=false;

    private String progType;
    private String progAccount;
    private Date startTime;
    private Date endTime;

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

    public Date getEndTime() {
        return endTime;
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



    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
