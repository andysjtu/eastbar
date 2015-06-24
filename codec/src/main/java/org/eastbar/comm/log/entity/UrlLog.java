package org.eastbar.comm.log.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by AndySJTU on 2015/5/29.
 */
@Entity
@Table(name = "url_log")
/**
 * testAccount ： 1 ： 310101197902026432 ： 张三 ： 192.168.16.128 ： 2015-06-03 13时40分00秒 ： http://www.sina.com.cn/ ： 0 ： 1 ： 2;
 */
public class UrlLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String siteCode;
    private String customerId;
    private String customerName;
    private String hostIp;
    private Timestamp recordTime;
    private boolean blocked =false;
    @Column(length = 500)
    private String url;



    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "UrlLog{" +
                "customerId='" + customerId + '\'' +
                ", id=" + id +
                ", customerName='" + customerName + '\'' +
                ", hostIp='" + hostIp + '\'' +
                ", recordTime='" + recordTime + '\'' +
                ", blocked=" + blocked +
                ", url='" + url + '\'' +
                '}';
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

    public boolean isBlocked() {
        return blocked;
    }

    public void setIsBlock(boolean isBlock) {
        this.blocked = isBlock;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = new Timestamp(recordTime.getTime());
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }
}
