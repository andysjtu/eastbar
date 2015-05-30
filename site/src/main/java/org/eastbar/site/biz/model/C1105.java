package org.eastbar.site.biz.model;

import java.io.Serializable;

/**
 * BREQ_INFORM_TERM_STATUS
 * 通知客户端的状态
 * @author Gxx
 *
 */
public class C1105 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int type;
	
	private String version;
	
	private String sessionId;
	
	private String ip;
	
	private String state;//“1”	使用 –〉一般不直接调用（login接口自动实现） “2”	锁定 –〉一般不直接调用（lock接口自动实现） “4”	空闲    “8”	维护

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
