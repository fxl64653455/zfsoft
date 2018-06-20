/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.setup.event.bo;

import com.zfsoft.boot.apimgr.util.enums.AuditStatus;

public class ApiAudit {
	
	public enum AuditType {
		/**接口申请审核*/
		apply,
		/**变更申请审核*/
		change
	}
	
	/**接口申请审批ID编号*/
	private String auditId;
	/**审批时间*/
	private String auditTime;
	/**审批的接口申请ID*/
	private String auditFor;
	/**审批人ID*/
	private String auditUser;
	/**审批意见*/
	private String auditOpinion;
	/**审核状态*/
	private AuditStatus auditStatus;
	/**key*/
	private String apiKey;
	/**访问者IP*/
	private String invokeIp;
	/**访问频率(次/日)*/
	private Integer invokeFrequency;
	/**接口发布ID*/
	private String deployId;
	/** 接口名称 */
	private String apiName;
	/**事件类型*/
	private AuditType auditType;
	
	public String getAuditId() {
		return auditId;
	}
	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}
	public String getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	public String getAuditFor() {
		return auditFor;
	}
	public void setAuditFor(String auditFor) {
		this.auditFor = auditFor;
	}
	public String getAuditUser() {
		return auditUser;
	}
	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}
	public String getAuditOpinion() {
		return auditOpinion;
	}
	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}
	public AuditStatus getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(AuditStatus auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getInvokeIp() {
		return invokeIp;
	}
	public void setInvokeIp(String invokeIp) {
		this.invokeIp = invokeIp;
	}
	public Integer getInvokeFrequency() {
		return invokeFrequency;
	}
	public void setInvokeFrequency(Integer invokeFrequency) {
		this.invokeFrequency = invokeFrequency;
	}
	public String getDeployId() {
		return deployId;
	}
	public void setDeployId(String deployId) {
		this.deployId = deployId;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public AuditType getAuditType() {
		return auditType;
	}
	public void setAuditType(AuditType auditType) {
		this.auditType = auditType;
	}
}
