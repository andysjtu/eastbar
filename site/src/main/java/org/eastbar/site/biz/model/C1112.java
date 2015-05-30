package org.eastbar.site.biz.model;

import java.io.Serializable;

/**
 * BREQ_UNREGISTER
 * 注销顾客/用户
 * @author Gxx
 *
 */
public class C1112 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int type;
	
	private String version;
	
	private String sessionId;
	
	private String userAccount;

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
	
}
