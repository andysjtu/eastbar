package org.eastbar.comm.log.entity;

import javax.persistence.*;

/**
 * Created by AndySJTU on 2015/5/29.
 */
@Entity
@Table(name = "illegal_log")
public class IllegalLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String customerId;
    private String customerName;
    private String hostIp;
    private String recordTime;
    private boolean isBlock = false;
    private String keyword;
    private String url;

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

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "IllegalLog{" +
                "customerId='" + customerId + '\'' +
                ", id=" + id +
                ", customerName='" + customerName + '\'' +
                ", hostIp='" + hostIp + '\'' +
                ", recordTime='" + recordTime + '\'' +
                ", isBlocked=" + isBlock +
                ", keyword='" + keyword + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
