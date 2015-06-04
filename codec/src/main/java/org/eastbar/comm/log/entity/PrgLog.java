package org.eastbar.comm.log.entity;

import javax.persistence.*;

/**
 * Created by AndySJTU on 2015/5/29.
 */
@Entity
@Table(name = "prg_log")
public class PrgLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String customerId;
    private String customerName;
    private String hostIp;
    private String recordTime;
    private boolean isBlock=false;

    private String progName;
    private String processName;
    private String startTime;
    private String endTime;

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

    @Override
    public String toString() {
        return "PrgLog{" +
                "customerId='" + customerId + '\'' +
                ", id=" + id +
                ", customerName='" + customerName + '\'' +
                ", hostIp='" + hostIp + '\'' +
                ", recordTime='" + recordTime + '\'' +
                ", isBlocked=" + isBlock +
                ", progName='" + progName + '\'' +
                ", processName='" + processName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
