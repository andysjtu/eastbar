package org.eastbar.site.biz.model;

import java.io.Serializable;

/**
 * 2.2 BREQ_CLOSE
 * 场所业务服务器关闭到监管系统的会话过程
 * @author Gxx
 *
 */
public class C1002 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int type;
	
	private String version;
	
	private String sessionId;

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
	
}
