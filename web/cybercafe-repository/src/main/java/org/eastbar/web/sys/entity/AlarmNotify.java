/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.sys.entity;

import java.io.Serializable;

/**
 * @author cindy-jia
 * @date 2014年10月17
 * @time 下午1:15
 * @description :
 */
public class AlarmNotify implements Serializable {//t_alarm_notifier

    private Integer id; //id
    private Integer notifierType; //notifier_type
    private String monitorCode;//monitor_code
    private String receiver;//receiver
    private String sender;//sender
    private String senderPass;//sender_pass
    private String smtpAddress;//smtp_address
    private String smtpPort;//smtp_port

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNotifierType() {
        return notifierType;
    }

    public void setNotifierType(Integer notifierType) {
        this.notifierType = notifierType;
    }

    public String getMonitorCode() {
        return monitorCode;
    }

    public void setMonitorCode(String monitorCode) {
        this.monitorCode = monitorCode;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderPass() {
        return senderPass;
    }

    public void setSenderPass(String senderPass) {
        this.senderPass = senderPass;
    }

    public String getSmtpAddress() {
        return smtpAddress;
    }

    public void setSmtpAddress(String smtpAddress) {
        this.smtpAddress = smtpAddress;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }
}

