package com.zfsoft.boot.web;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(ZFWebProperties.PREFIX)
public class ZFWebProperties {

	public static final String PREFIX = "zftal.http";
	
	/** 唯一验证-需要验证字段表元素定义：定义表的别名；暴露在页面更安全 */
	private Map<String /* alias */, String /* table names */> tableMap = new LinkedHashMap<String, String>();

	public Map<String, String> getTableMap() {
		return tableMap;
	}

	public void setTableMap(Map<String, String> tableMap) {
		this.tableMap = tableMap;
	}
	
}