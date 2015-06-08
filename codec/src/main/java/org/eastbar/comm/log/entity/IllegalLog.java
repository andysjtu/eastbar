package org.eastbar.comm.log.entity;

import org.eastbar.comm.EntityObject;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by AndySJTU on 2015/5/29.
 */
@Entity
@Table(name = "illegal_log")
public class IllegalLog extends EntityObject{


    private String customerId;
    private String customerName;
    private String hostIp;
    private Date recordTime;
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

    @Override
    public String toString() {
        return "IllegalLog{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", hostIp='" + hostIp + '\'' +
                ", recordTime='" + recordTime + '\'' +
                ", isBlock=" + isBlock +
                ", keyword='" + keyword + '\'' +
                ", url='" + url + '\'' +
                "} " + super.toString();
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

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
