package com.zfsoft.boot.shiro;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * @className	： ShiroUaacProperties
 * @description	：Uaac认证属性类
 * @author 		：魏广跃（1571）
 * @date		： 2018年5月18日 上午11:00:00
 * @version 	V1.0
 */
@ConfigurationProperties(ShiroUaacProperties.PREFIX)
public class ShiroUaacProperties{
	
	public static final String PREFIX = "shiro.uaac";
	
	private boolean enabled;
	
	//认证地址
	private String authServerUrl;
	
	//登录成功后地址
	private String successUrl;
	
	//登出地址
	private String logoutUrl;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAuthServerUrl() {
		return authServerUrl;
	}

	public void setAuthServerUrl(String authServerUrl) {
		this.authServerUrl = authServerUrl;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getLogoutUrl() {
		return logoutUrl;
	}

	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

}
