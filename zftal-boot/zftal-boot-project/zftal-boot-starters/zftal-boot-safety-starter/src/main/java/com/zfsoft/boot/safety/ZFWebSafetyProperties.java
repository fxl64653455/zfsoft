package com.zfsoft.boot.safety;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import com.zfsoft.boot.safety.property.HttpCorsFilterProperties;
import com.zfsoft.boot.safety.property.HttpRequestHeaderFilterProperties;
import com.zfsoft.boot.safety.property.HttpRequestMethodsFilterProperties;
import com.zfsoft.boot.safety.property.HttpRichtextXssFilterProperties;
import com.zfsoft.boot.safety.property.HttpXssFilterProperties;

@ConfigurationProperties(ZFWebSafetyProperties.PREFIX)
public class ZFWebSafetyProperties {

	public static final String PREFIX = "zftal.http.safety";

	protected Boolean enabled = false;
	
	/**
	 * 处理器链定义
	 */
	private String urlPatterns;
	private String definitions = null;
	private Map<String /* rule */, String /* handler names */> definitionMap = new LinkedHashMap<String, String>();
	
	@NestedConfigurationProperty
	private HttpCorsFilterProperties httpCorsFilter = new HttpCorsFilterProperties();
	@NestedConfigurationProperty
	private HttpRequestHeaderFilterProperties httpHeaderFilter = new HttpRequestHeaderFilterProperties();
	@NestedConfigurationProperty
	private HttpRequestMethodsFilterProperties httpMethodsFilter = new HttpRequestMethodsFilterProperties();
	@NestedConfigurationProperty
	private HttpRichtextXssFilterProperties httpRichtextXssFilter = new HttpRichtextXssFilterProperties();
	@NestedConfigurationProperty
	private HttpXssFilterProperties httpXssFilter = new HttpXssFilterProperties();
	
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getUrlPatterns() {
		return urlPatterns;
	}

	public void setUrlPatterns(String urlPatterns) {
		this.urlPatterns = urlPatterns;
	}

	public String getDefinitions() {
		return definitions;
	}

	public void setDefinitions(String definitions) {
		this.definitions = definitions;
	}

	public Map<String, String> getDefinitionMap() {
		return definitionMap;
	}

	public void setDefinitionMap(Map<String, String> definitionMap) {
		this.definitionMap = definitionMap;
	}

	public HttpCorsFilterProperties getHttpCorsFilter() {
		return httpCorsFilter;
	}

	public void setHttpCorsFilter(HttpCorsFilterProperties httpCorsFilter) {
		this.httpCorsFilter = httpCorsFilter;
	}

	public HttpRequestHeaderFilterProperties getHttpHeaderFilter() {
		return httpHeaderFilter;
	}

	public void setHttpHeaderFilter(HttpRequestHeaderFilterProperties httpHeaderFilter) {
		this.httpHeaderFilter = httpHeaderFilter;
	}

	public HttpRequestMethodsFilterProperties getHttpMethodsFilter() {
		return httpMethodsFilter;
	}

	public void setHttpMethodsFilter(HttpRequestMethodsFilterProperties httpMethodsFilter) {
		this.httpMethodsFilter = httpMethodsFilter;
	}

	public HttpRichtextXssFilterProperties getHttpRichtextXssFilter() {
		return httpRichtextXssFilter;
	}

	public void setHttpRichtextXssFilter(HttpRichtextXssFilterProperties httpRichtextXssFilter) {
		this.httpRichtextXssFilter = httpRichtextXssFilter;
	}

	public HttpXssFilterProperties getHttpXssFilter() {
		return httpXssFilter;
	}

	public void setHttpXssFilter(HttpXssFilterProperties httpXssFilter) {
		this.httpXssFilter = httpXssFilter;
	}
	
}
