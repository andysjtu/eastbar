package org.eastbar.site.biz.model;

import java.io.Serializable;

/**
 * 2.1	BREQ_CONNECT
 * 监管系统对用户名/密码进行身份认证
 * @author Gxx
 *
 */
public class C1001 implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int type;
	
	private String version;
	
	private String userName;
	
	private String passWord;


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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@Override
	public String toString() {
		return "C1001{" +
				"passWord='" + passWord + '\'' +
				", type=" + type +
				", version='" + version + '\'' +
				", userName='" + userName + '\'' +
				'}';
	}
}
