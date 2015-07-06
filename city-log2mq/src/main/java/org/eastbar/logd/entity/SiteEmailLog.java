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
@Table(name = "t_mail_history")
public class SiteEmailLog {
    private String siteCode;
    @Column(name = "customer_id")
    private String customerId;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "host_ip")
    private String hostIp;
    @Column(name = "access_time")
    private Timestamp recordTime;
    @Column(name = "is_block")
    private boolean blocked = false;
    @Column(name = "customer_type")
    private String customerType;

    @Column(name = "email_type")
    private String emailType;
    @Column(name = "email_account")
    private String emailAccount;
    @Column(name = "receive")
    private String emailReceptor;
    @Column(name = "theme")
    private String emailSubject;

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
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

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getEmailAccount() {
        return emailAccount;
    }

    public void setEmailAccount(String emailAccount) {
        this.emailAccount = emailAccount;
    }

    public String getEmailReceptor() {
        return emailReceptor;
    }

    public void setEmailReceptor(String emailReceptor) {
        this.emailReceptor = emailReceptor;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
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

    @Override
    public String toString() {
        return "SiteEmailLog{" +
                "siteCode='" + siteCode + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", hostIp='" + hostIp + '\'' +
                ", recordTime=" + recordTime +
                ", blocked=" + blocked +
                ", customerType='" + customerType + '\'' +
                ", emailType='" + emailType + '\'' +
                ", emailAccount='" + emailAccount + '\'' +
                ", emailReceptor='" + emailReceptor + '\'' +
                ", emailSubject='" + emailSubject + '\'' +
                '}';
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

}
