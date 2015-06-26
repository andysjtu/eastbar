package org.eastbar.site.biz.model;

import java.io.Serializable;
import java.util.Date;

/**
 * BREQ_SEND_TERMINAL
 * 每隔30分钟向场所服务器发送所有终端信息
 * @author Gxx
 *
 */
public class C1122 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int type;
	
	private String version;
	
	private String sessionId;
	
	private String num;
	
	private String userAccount;
	
	private String name;
	
	private String customerIdType;
	
	private String customerId;
	
	private String issuingAuthority;
	
	private String ip;
	
	private String loginTime;

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCustomerIdType() {
		return customerIdType;
	}

	public void setCustomerIdType(String customerIdType) {
		this.customerIdType = customerIdType;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getIssuingAuthority() {
		return issuingAuthority;
	}

	public void setIssuingAuthority(String issuingAuthority) {
		this.issuingAuthority = issuingAuthority;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	
}
