package org.eastbar.site.biz.model;

import java.io.Serializable;

/**
 * BREQ_LOGOUT
 * 当用户退出客户端的时候，场所服务器通过该命令通知客户端监管系统
 * @author Gxx
 *
 */
public class C1102 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String version;
	
	private String sessionId;
	
	private String account;
	
	private String name;
	
	private String idType;
	
	private String id;
	
	private String authOrg;
	
	private String ip;
	
	private String loginTime;
	
	private String logoutTime;


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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthOrg() {
		return authOrg;
	}

	public void setAuthOrg(String authOrg) {
		this.authOrg = authOrg;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}

	@Override
	public String toString() {
		return "C1102{" +
				"id='" + id + '\'' +
				", version='" + version + '\'' +
				", sessionId='" + sessionId + '\'' +
				", account='" + account + '\'' +
				", name='" + name + '\'' +
				", idType='" + idType + '\'' +
				", authOrg='" + authOrg + '\'' +
				", ip='" + ip + '\'' +
				", loginTime='" + loginTime + '\'' +
				", logoutTime='" + logoutTime + '\'' +
				'}';
	}
}
