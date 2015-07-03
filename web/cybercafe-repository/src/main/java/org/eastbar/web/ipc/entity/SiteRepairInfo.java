package org.eastbar.web.ipc.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Created by yangyd on 15-5-21.
 */
public class SiteRepairInfo implements Serializable{
	private int id;
	private String monitorCode;
	private String siteCode;
	private String siteName;
	private Integer type;
	private Integer status;
	private String siteReportTime;
	private String finishedTime;
	private String siteResponseTime;
	private String beforeProcess;
	private String description;
	private String processSituation;
	private String result;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMonitorCode() {
		return monitorCode;
	}

	public void setMonitorCode(String monitorCode) {
		this.monitorCode = monitorCode;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSiteReportTime() {
		return siteReportTime;
	}

	public void setSiteReportTime(String siteReportTime) {
		this.siteReportTime = siteReportTime;
	}

	public String getFinishedTime() {
		return finishedTime;
	}

	public void setFinishedTime(String finishedTime) {
		this.finishedTime = finishedTime;
	}

	public String getSiteResponseTime() {
		return siteResponseTime;
	}

	public void setSiteResponseTime(String siteResponseTime) {
		this.siteResponseTime = siteResponseTime;
	}

	public String getBeforeProcess() {
		return beforeProcess;
	}

	public void setBeforeProcess(String beforeProcess) {
		this.beforeProcess = beforeProcess;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProcessSituation() {
		return processSituation;
	}

	public void setProcessSituation(String processSituation) {
		this.processSituation = processSituation;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
