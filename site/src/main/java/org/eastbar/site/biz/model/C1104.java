package org.eastbar.site.biz.model;

import java.io.Serializable;

/**
 * BREQ_UNLOCK
 * 解锁客户端
 * @author Gxx
 *
 */
public class C1104 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int type;
	
	private String version;
	
	private String sessionId;
	
	private String ip;

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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
