/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.dto;

/**
 * 资源信息
 * @className	： Resources
 * @description	： TODO(描述这个类的作用)
 * @author 		：樊新亮（1505）
 * @date		： 2017年9月20日 下午8:29:44
 * @version 	V1.0
 */
public class Resources {
	/**标识*/
	private String resourceId;
	/**资源名称*/
	private String resourceName;
	/**资源类型*/
	private String resourceType;
	/**请求地址*/
	private String requestUrl;
	/**描述信息*/
	private String description;
	
	private String totalResult = "0";
	public String getTotalResult() {
		return totalResult;
	}
	public void setTotalResult(String totalResult) {
		this.totalResult = totalResult;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public String getRequestUrl() {
		return requestUrl;
	}
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
