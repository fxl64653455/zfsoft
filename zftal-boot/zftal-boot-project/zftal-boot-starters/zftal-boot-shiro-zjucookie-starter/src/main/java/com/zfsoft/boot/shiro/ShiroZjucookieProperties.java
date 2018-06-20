package com.zfsoft.boot.shiro;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * @className	： ShiroZjucookieProperties
 * @description	：Zjucookie认证属性类
 * @author 		：魏广跃（1571）
 * @date		： 2018年5月18日 上午11:00:00
 * @version 	V1.0
 */
@ConfigurationProperties(ShiroZjucookieProperties.PREFIX)
public class ShiroZjucookieProperties{
	
	public static final String PREFIX = "shiro.zjucookie";
	
	private boolean enabled;
	
	//登录地址
	private String loginUrl;
	
	//登录成功后地址
	private String successUrl;
	
	//登录地址
	private String sessionURL;
	
	//登录成功后地址
	private String userURL;
	
	//学校代码
	private String appUid;
	
	//时间戳过期时间
	private String appPwd;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getSessionURL() {
		return sessionURL;
	}

	public void setSessionURL(String sessionURL) {
		this.sessionURL = sessionURL;
	}

	public String getUserURL() {
		return userURL;
	}

	public void setUserURL(String userURL) {
		this.userURL = userURL;
	}

	public String getAppUid() {
		return appUid;
	}

	public void setAppUid(String appUid) {
		this.appUid = appUid;
	}

	public String getAppPwd() {
		return appPwd;
	}

	public void setAppPwd(String appPwd) {
		this.appPwd = appPwd;
	}
}
