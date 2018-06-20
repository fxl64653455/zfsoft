/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.safety.property;

public class HttpRequestMethodsFilterProperties {

	/** 过滤器启用状态 **/
	private Boolean enabled = true;
	/**
	 * 应许访问的HTTP方法
	 */
	private String[] allowedHttpMethods = new String[] { "GET", "POST", "PUT" };

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String[] getAllowedHttpMethods() {
		return allowedHttpMethods;
	}

	public void setAllowedHttpMethods(String[] allowedHttpMethods) {
		this.allowedHttpMethods = allowedHttpMethods;
	}

}
