/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.dao.entities;

import java.util.HashMap;
import java.util.Map;

import com.zfsoft.basemodel.BaseModel;

/**
 * 接口申请信息
 * @className	： ApiApply
 * @description	： TODO(描述这个类的作用)
 * @author 		：樊新亮（1505）
 * @date		： 2017年9月28日 下午3:18:58
 * @version 	V1.0
 */
public class ApiApplyModel extends BaseModel{
	
	private static final long serialVersionUID = 305966290166661369L;
	
	public enum ApplyStatus{
		/**待审核*/
		wait,
		/**审核通过*/
		pass,
		/**审核不通过*/
		unpass
	}
	
	/**接口申请ID编号*/
	private String applyId;
	/**接口申请时间*/
	private String applyTime;
	/**申请的接口ID*/
	private String applyFor;
	/**接口应用场景*/
	private String applyTo;
	/**申请人ID*/
	private String applyUser;
	/**申请状态：0:待审核、1：审核通过、2：审核不通过*/
	private String applyStatus;
	/**key*/
	private String apiKey;
	/**Secret*/
	private String apiSecret;
	/**访问者IP*/
	private String invokeIp;
	/**访问频率(次/日)*/
	private Integer invokeFrequency;
	/**申请材料*/
	private byte[] applyFile;
	
	/**接口名称*/
	private String apiName;
	/**接口简单描述信息*/
	private String apiInfo;
	/**接口状态*/
	private String apiStatus;
	/**应用名称*/
	private String appName;
	/**审批意见*/
	private String auditOpinion;
	/**审核人*/
	private String auditUser;
	
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public String getApplyFor() {
		return applyFor;
	}
	public void setApplyFor(String applyFor) {
		this.applyFor = applyFor;
	}
	public String getApplyTo() {
		return applyTo;
	}
	public void setApplyTo(String applyTo) {
		this.applyTo = applyTo;
	}
	public String getApplyUser() {
		return applyUser;
	}
	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}
	public String getApplyStatus() {
		return applyStatus;
	}
	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getApiSecret() {
		return apiSecret;
	}
	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
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
	public byte[] getApplyFile() {
		return applyFile;
	}
	public void setApplyFile(byte[] applyFile) {
		this.applyFile = applyFile;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public String getApiInfo() {
		return apiInfo;
	}
	public void setApiInfo(String apiInfo) {
		this.apiInfo = apiInfo;
	}
	public String getApiStatus() {
		return apiStatus;
	}
	public void setApiStatus(String apiStatus) {
		this.apiStatus = apiStatus;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAuditOpinion() {
		return auditOpinion;
	}
	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}
	public String getAuditUser() {
		return auditUser;
	}
	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}
	public Map<String, String> toMap(){
		Map<String, String> map = new HashMap<>();
		map.put("applyId", applyId);map.put("applyTime", applyTime);map.put("applyFor", applyFor);
		map.put("applyTo", applyTo);map.put("applyUser", applyUser);map.put("applyStatus", applyStatus);
		map.put("apiName", apiName);map.put("apiInfo", apiInfo);map.put("apiStatus", apiStatus);
		map.put("appName", appName);map.put("auditOpinion", auditOpinion);map.put("auditUser", auditUser);map.put("invokeIp", invokeIp);
		map.put("invokeFrequency", invokeFrequency.toString());map.put("apiKey", apiKey);map.put("apiSecret", apiSecret);
		return map;
	}
}
