/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.safety.property;

public class HttpXssFilterProperties {

	/** 过滤器启用状态 **/
	private Boolean enabled = true;
	/**需要进行Xss检查的Header*/
	private String[] policyHeaders = null;
	
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public String[] getPolicyHeaders() {
		return policyHeaders;
	}
	public void setPolicyHeaders(String[] policyHeaders) {
		this.policyHeaders = policyHeaders;
	}

	

}
