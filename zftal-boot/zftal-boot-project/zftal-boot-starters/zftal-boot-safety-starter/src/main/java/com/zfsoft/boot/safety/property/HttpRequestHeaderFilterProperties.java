/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.safety.property;

public class HttpRequestHeaderFilterProperties {

	/** 过滤器启用状态 **/
	private Boolean enabled = true;
	/** iframe策略 **/
	private String XFrameOptions = "SAMEORIGIN";
	/**
	 * X-XSS-Protection：主要是用来防止浏览器中的反射性xss，IE，chrome和safari（webkit）支持这个header,有以下两种方式：
	 * 1; mode=block – 开启xss防护并通知浏览器阻止而不是过滤用户注入的脚本； 1; report=http://site.com/report
	 * – 这个只有chrome和webkit内核的浏览器支持，这种模式告诉浏览器当 发现疑似xss攻击的时候就将这部分数据post到指定地址。
	 */
	private String XXssProtection = "1; mode=block";
	/** 防止在IE9、chrome和safari中的MIME类型混淆攻击 */
	private String XContentTypeOptions = "nosniff";

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getXFrameOptions() {
		return XFrameOptions;
	}

	public void setXFrameOptions(String xFrameOptions) {
		XFrameOptions = xFrameOptions;
	}

	public String getXXssProtection() {
		return XXssProtection;
	}

	public void setXXssProtection(String xXssProtection) {
		XXssProtection = xXssProtection;
	}

	public String getXContentTypeOptions() {
		return XContentTypeOptions;
	}

	public void setXContentTypeOptions(String xContentTypeOptions) {
		XContentTypeOptions = xContentTypeOptions;
	}

}
