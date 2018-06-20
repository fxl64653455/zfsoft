/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.dao.entities;

import java.util.HashMap;
import java.util.Map;

import com.zfsoft.basemodel.BaseModel;

/**
 * 我的应用信息
 * @className	： ApiAppModel
 * @description	： TODO(描述这个类的作用)
 * @author 		：樊新亮（1505）
 * @date		： 2017年9月28日 下午3:16:25
 * @version 	V1.0
 */
public class ApiAppModel extends BaseModel{
	
	private static final long serialVersionUID = 8391275602485027840L;
	
	/**应用ID*/
	private String appId;
	/**应用名称*/
	private String appName;
	/**应用描述*/
	private String appDesc;
	/**应用Key*/
	private String appKey;
	/**应用Secret*/
	private String appSecret;
	/**应用所属人ID*/
	private String appOwner;
	
	/**申请状态*/
	private String applyStatus;
	/**接口发布ID*/
	private String deployId;
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppDesc() {
		return appDesc;
	}
	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public String getAppOwner() {
		return appOwner;
	}
	public void setAppOwner(String appOwner) {
		this.appOwner = appOwner;
	}
	public String getApplyStatus() {
		return applyStatus;
	}
	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}
	public String getDeployId() {
		return deployId;
	}
	public void setDeployId(String deployId) {
		this.deployId = deployId;
	}
	public Map<String, String> toMap(){
		Map<String, String> map = new HashMap<>();
		map.put("appId", appId);map.put("appName", appName);map.put("appDesc", appDesc);
		map.put("appOwner", appOwner);map.put("appKey", appKey);map.put("appSecret", appSecret);
		map.put("applyStatus", applyStatus);map.put("deployId", deployId);
		return map;
	}
}
