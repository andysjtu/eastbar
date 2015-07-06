package org.eastbar.log2db.entity;




import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by AndySJTU on 2015/6/4.
 */
@Entity
@Table(name = "T_URL_HISTORY")
public class SiteUrlLog  {
	private String siteCode;
    @Column(name="CUSTOMER_ID")
    private String customerId;
    
    @Column(name="CUSTOMER_NAME")
    private String customerName;
    
    @Column(name = "HOST_IP")
    private String hostIp;
    
    @Column(name = "ACCESS_TIME")
    private Date recordTime;
    
    @Column(name = "IS_BLOCK")
    private boolean blocked;
    
    @Column(name = "CUSTOMER_TYPE")
    private String customerType="1";
    
    @Column(name = "URL")
    private String url;




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

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	@Override
	public String toString() {
		return "SiteUrlLog{" +
				"siteCode='" + siteCode + '\'' +
				", customerId='" + customerId + '\'' +
				", customerName='" + customerName + '\'' +
				", hostIp='" + hostIp + '\'' +
				", recordTime=" + recordTime +
				", blocked=" + blocked +
				", customerType='" + customerType + '\'' +
				", url='" + url + '\'' +
				'}';
	}
}
