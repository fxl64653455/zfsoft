/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.dao.entities;

import com.zfsoft.basemodel.BaseModel;
import com.zfsoft.boot.apimgr.util.enums.AuditStatus;

/**
 * 接口申请审批信息
 * @className	： ApiAudit
 * @description	： 接口申请审批信息
 * @author 		：樊新亮（1505）
 * @date		： 2017年9月28日 下午3:23:37
 * @version 	V1.0
 */
public class ApiAuditModel extends BaseModel{

	private static final long serialVersionUID = 5637587248729577648L;
	
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
}
