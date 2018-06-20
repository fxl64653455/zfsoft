/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.dao.entities;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class ApiApplyChangeModel extends ApiApplyModel{
	
	private static final long serialVersionUID = 5095846557545395222L;
	/**申请变更记录ID*/
	private String applyChangeId;
	/**申请记录ID*/
	private String applyId;
	/**操作人*/
	private String changeUser;
	/**操作时间*/
	private String changeTime;
	/**变更内容*/
	private String changeContent;
	/**变更申请文件*/
	private byte[] changeFile;
	/**变更状态*/
	private String auditStatus;
	
	public String getApplyChangeId() {
		return applyChangeId;
	}
	public void setApplyChangeId(String applyChangeId) {
		this.applyChangeId = applyChangeId;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public String getChangeUser() {
		return changeUser;
	}
	public void setChangeUser(String changeUser) {
		this.changeUser = changeUser;
	}
	public String getChangeTime() {
		return changeTime;
	}
	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}
	public String getChangeContent() {
		return changeContent;
	}
	public void setChangeContent(String changeContent) {
		this.changeContent = changeContent;
	}
	public byte[] getChangeFile() {
		return changeFile;
	}
	public void setChangeFile(byte[] changeFile) {
		this.changeFile = changeFile;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	public Map<String, String> toMap(){
		Map<String, String> map = new HashMap<>();
		map.put("applyId", applyId);map.put("applyTime", super.getApplyTime());map.put("applyFor", super.getApplyFor());
		map.put("applyTo", super.getApplyTo());map.put("applyUser", super.getApplyUser());map.put("applyStatus", super.getApplyStatus());
		map.put("apiName", super.getApiName());map.put("apiInfo", super.getApiInfo());map.put("apiStatus", super.getApiStatus());
		map.put("appName", super.getAppName());map.put("auditOpinion", super.getAuditOpinion());
		map.put("apiKey", super.getApiKey());map.put("apiSecret", super.getApiSecret());
		map.put("auditUser", super.getAuditUser());
		map.put("applyChangeId", applyChangeId);map.put("changeUser", changeUser);map.put("changeTime", changeTime);
		map.put("auditStatus", auditStatus);//map.put("changeContent", changeContent);
		JSONObject cc = JSONObject.parseObject(changeContent);
		map.put("srcInvokeIp", cc.getJSONObject("src").getString("invokeIp"));map.put("srcInvokeFrequency", cc.getJSONObject("src").getString("invokeFrequency"));
		map.put("targetInvokeIp", cc.getJSONObject("target").getString("invokeIp"));map.put("targetInvokeFrequency", cc.getJSONObject("target").getString("invokeFrequency"));
		return map;
	}
}
