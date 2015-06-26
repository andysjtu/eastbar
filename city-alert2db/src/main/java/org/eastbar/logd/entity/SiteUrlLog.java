package org.eastbar.logd.entity;

import org.eastbar.comm.EntityObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by AndySJTU on 2015/6/4.
 */
@Entity
@Table(name = "t_url_history")
public class SiteUrlLog extends EntityObject {
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

    public boolean isBlocked() {
        return blocked;
    }

    public void setIsBlock(boolean isBlock) {
        this.blocked = isBlock;
    }

    public Timestamp getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Timestamp recordTime) {
        this.recordTime = recordTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
