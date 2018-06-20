/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.dto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 客户端信息
 * @className	： Client
 * @description	： TODO(描述这个类的作用)
 * @author 		：樊新亮（1505）
 * @date		： 2017年9月20日 下午8:29:32
 * @version 	V1.0
 */
public class Client {
	/**客户端ID*/
	private String clientId;
	/**客户端密码*/
	private String clientSecret;
	/**客户端类型*/
	private String clientType;
	/**RSA公钥*/
	private String publicKey;
	
	/**拥有的资源组*/
	private Set<ResourceGroup> resourceGroups = new HashSet<>();
	/**拥有的资源*/
	private Set<Resources> resources = new HashSet<>();
	
	private String totalResult = "0";
	public String getTotalResult() {
		return totalResult;
	}
	public void setTotalResult(String totalResult) {
		this.totalResult = totalResult;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	public Set<ResourceGroup> getResourceGroups() {
		return resourceGroups;
	}
	public void setResourceGroups(Set<ResourceGroup> resourceGroups) {
		this.resourceGroups = resourceGroups;
	}
	public Set<Resources> getResources() {
		return resources;
	}
	public void setResources(Set<Resources> resources) {
		this.resources = resources;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public Map<String, Object> toMap(){
		Map<String, Object> map = new HashMap<>();
		map.put("clientId", clientId);map.put("clientSecret", clientSecret);map.put("clientType", clientType);
		map.put("publicKey", publicKey);map.put("resourceGroups", resourceGroups);map.put("resources", resources);
		return map;
	}
}
