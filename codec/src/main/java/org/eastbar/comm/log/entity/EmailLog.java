package org.eastbar.comm.log.entity;

import org.eastbar.comm.EntityObject;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by AndySJTU on 2015/5/29.
 */
@Entity
@Table(name = "email_log")
public class EmailLog extends EntityObject {


    private String customerId;
    private String customerName;
    private String hostIp;
    private Date recordTime;
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

    @Override
    public String toString() {
        return "EmailLog{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", hostIp='" + hostIp + '\'' +
                ", recordTime='" + recordTime + '\'' +
                ", isBlock=" + isBlock +
                ", emailType='" + emailType + '\'' +
                ", emailAccount='" + emailAccount + '\'' +
                ", emailReceptor='" + emailReceptor + '\'' +
                ", emailSubject='" + emailSubject + '\'' +
                "} " + super.toString();
    }

    public boolean isBlock() {
        return isBlock;
    }

    public void setIsBlock(boolean isBlock) {
        this.isBlock = isBlock;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }
}
