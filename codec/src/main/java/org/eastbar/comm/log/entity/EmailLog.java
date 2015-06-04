package org.eastbar.comm.log.entity;

import javax.persistence.*;

/**
 * Created by AndySJTU on 2015/5/29.
 */
@Entity
@Table(name = "email_log")
public class EmailLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String customerId;
    private String customerName;
    private String hostIp;
    private String recordTime;
    private boolean isBlock = false;

    private String emailType;
    private String emailAccount;
    private String emailReceptor;
    private String emailSubject;

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

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    @Override
    public String toString() {
        return "EmailLog{" +
                "customerId='" + customerId + '\'' +
                ", id=" + id +
                ", customerName='" + customerName + '\'' +
                ", hostIp='" + hostIp + '\'' +
                ", recordTime='" + recordTime + '\'' +
                ", isBlocked=" + isBlock +
                ", emailType='" + emailType + '\'' +
                ", emailAccount='" + emailAccount + '\'' +
                ", emailReceptor='" + emailReceptor + '\'' +
                ", emailSubject='" + emailSubject + '\'' +
                '}';
    }
}
