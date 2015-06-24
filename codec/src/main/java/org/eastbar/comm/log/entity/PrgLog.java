package org.eastbar.comm.log.entity;

import org.eastbar.comm.EntityObject;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by AndySJTU on 2015/5/29.
 */
@Entity
@Table(name = "prg_log")
public class PrgLog extends EntityObject{


    private String customerId;
    private String customerName;
    private String hostIp;
    private Date recordTime;
    private boolean isBlock=false;

    private String progName;
    @Column(length = 500)
    private String processName;
    private Date startTime;
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

    public Date getEndTime() {
        return endTime;
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

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getProgName() {
        return progName;
    }

    public void setProgName(String progName) {
        this.progName = progName;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "PrgLog{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", hostIp='" + hostIp + '\'' +
                ", recordTime='" + recordTime + '\'' +
                ", isBlock=" + isBlock +
                ", progName='" + progName + '\'' +
                ", processName='" + processName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                "} " + super.toString();
    }
}
