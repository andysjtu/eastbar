package org.eastbar.site.biz.model;

import java.io.Serializable;

/**
 * BREQ_REGISTER
 * 注册新顾客/用户
 * @author Gxx
 *
 */
public class C1111 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int type;
	
	private String version;
	
	private String sessionId;
	
	private String userAccount;
	
	private String name;
	
	private String customerIdType;
	
	private String customerId;
	
	private String issuingAuthority;
	
	private String oldIssuingAuthorith;
	
	private String nationality;
	
	private String description;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public String getOldIssuingAuthorith() {
		return oldIssuingAuthorith;
	}

	public void setOldIssuingAuthorith(String oldIssuingAuthorith) {
		this.oldIssuingAuthorith = oldIssuingAuthorith;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "C1111{" +
				"customerId='" + customerId + '\'' +
				", type=" + type +
				", version='" + version + '\'' +
				", sessionId='" + sessionId + '\'' +
				", userAccount='" + userAccount + '\'' +
				", name='" + name + '\'' +
				", customerIdType='" + customerIdType + '\'' +
				", issuingAuthority='" + issuingAuthority + '\'' +
				", oldIssuingAuthorith='" + oldIssuingAuthorith + '\'' +
				", nationality='" + nationality + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
