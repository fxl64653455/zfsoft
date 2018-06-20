/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.safety.property;

import java.util.HashMap;
import java.util.Map;

import com.zfsoft.safety.SafetyParameter;

public class HttpRichtextXssFilterProperties {

	/** 过滤器启用状态 **/
	private Boolean enabled = true;
	/** 扫描器类型，0：DOM类型扫描器,1:SAX类型扫描器；两者的区别如同XML解析中DOM解析与Sax解析区别相同，实际上就是对两种解析方式的实现 */
	private int scanType = 1;
	/** 请求路径的正则匹配表达式，匹配的路径会被检测XSS */
	private String[] includePatterns = null;
	/** 不进行过滤请求路径的正则匹配表达式，匹配的路径不会被检测XSS */
	private String[] excludePatterns = null;
	/** XSS攻击的模块对应的规则配置:通过该配置可实现不同路径采用不同的处理规则 */
	private String policyDefinitions = null;
	private Map<String, String> policyMappings = new HashMap<String, String>();
	/** 需要进行Xss检查的Header */
	private String[] policyHeaders = null;
	/** 默认的防XSS攻击的规则配置 */
	private String defaultPolicy = SafetyParameter.SAFETY_XSS_DEFAULT_POLICY.getDefault();

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public int getScanType() {
		return scanType;
	}

	public void setScanType(int scanType) {
		this.scanType = scanType;
	}

	public String[] getIncludePatterns() {
		return includePatterns;
	}

	public void setIncludePatterns(String[] includePatterns) {
		this.includePatterns = includePatterns;
	}

	public String[] getExcludePatterns() {
		return excludePatterns;
	}

	public void setExcludePatterns(String[] excludePatterns) {
		this.excludePatterns = excludePatterns;
	}

	public String getPolicyDefinitions() {
		return policyDefinitions;
	}

	public void setPolicyDefinitions(String policyDefinitions) {
		this.policyDefinitions = policyDefinitions;
	}

	public Map<String, String> getPolicyMappings() {
		return policyMappings;
	}

	public void setPolicyMappings(Map<String, String> policyMappings) {
		this.policyMappings = policyMappings;
	}

	public String[] getPolicyHeaders() {
		return policyHeaders;
	}

	public void setPolicyHeaders(String[] policyHeaders) {
		this.policyHeaders = policyHeaders;
	}

	public String getDefaultPolicy() {
		return defaultPolicy;
	}

	public void setDefaultPolicy(String defaultPolicy) {
		this.defaultPolicy = defaultPolicy;
	}

}
