package org.eastbar.logd.entity;

import org.eastbar.net.EntityObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by AndySJTU on 2015/6/4.
 */
@Entity
@Table(name="t_illegal_history")
public class SiteIllegalLog  {
    private String siteCode;
    @Column(name="customer_id")
    private String customerId;
    @Column(name="customer_name")
    private String customerName;
    @Column(name = "host_ip")
    private String hostIp;
    @Column(name = "access_time")
    private Timestamp recordTime;
    @Column(name = "is_block")
    private boolean blocked = false;
    @Column(name = "customer_type")
    private String customerType;

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
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

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public Timestamp getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Timestamp recordTime) {
        this.recordTime = recordTime;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }
}
