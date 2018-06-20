/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.safety.property;

public class HttpCorsFilterProperties {

	/** 过滤器启用状态 **/
	private Boolean enabled = true;
	private String accessControlAllowOrigin = "*";
	private String accessControlAllowCredentials = "true";
	private String accessControlAllowHeaders = "Origin, X-Requested-With, Content-Type, Accept";
	private String accessControlAllowMethods = "POST, GET, PUT, OPTIONS, DELETE";

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getAccessControlAllowOrigin() {
		return accessControlAllowOrigin;
	}

	public void setAccessControlAllowOrigin(String accessControlAllowOrigin) {
		this.accessControlAllowOrigin = accessControlAllowOrigin;
	}

	public String getAccessControlAllowCredentials() {
		return accessControlAllowCredentials;
	}

	public void setAccessControlAllowCredentials(String accessControlAllowCredentials) {
		this.accessControlAllowCredentials = accessControlAllowCredentials;
	}

	public String getAccessControlAllowHeaders() {
		return accessControlAllowHeaders;
	}

	public void setAccessControlAllowHeaders(String accessControlAllowHeaders) {
		this.accessControlAllowHeaders = accessControlAllowHeaders;
	}

	public String getAccessControlAllowMethods() {
		return accessControlAllowMethods;
	}

	public void setAccessControlAllowMethods(String accessControlAllowMethods) {
		this.accessControlAllowMethods = accessControlAllowMethods;
	}

}
