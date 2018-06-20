package com.zfsoft.boot.shiro;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * @className	： ShiroTicketProperties
 * @description	：Ticket认证属性类
 * @author 		：魏广跃（1571）
 * @date		： 2018年5月15日 下午2:16:30
 * @version 	V1.0
 */
@ConfigurationProperties(ShiroTicketProperties.PREFIX)
public class ShiroTicketProperties{
	
	public static final String PREFIX = "shiro.ticket";
	
	private boolean enabled;
	
	//登录地址
	private String loginUrl;
	
	//登录成功后地址
	private String successUrl;
	
	//学校代码
	private String xxdm;
	
	//时间戳过期时间
	private String timeStampPeriod;
	
	//token有效时间
	private String tokenPeriod;
	
	//时间提供者实现类
	private String timeProviderClass;

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getXxdm() {
		return xxdm;
	}

	public void setXxdm(String xxdm) {
		this.xxdm = xxdm;
	}

	public String getTimeStampPeriod() {
		return timeStampPeriod;
	}

	public void setTimeStampPeriod(String timeStampPeriod) {
		this.timeStampPeriod = timeStampPeriod;
	}

	public String getTokenPeriod() {
		return tokenPeriod;
	}

	public void setTokenPeriod(String tokenPeriod) {
		this.tokenPeriod = tokenPeriod;
	}

	public String getTimeProviderClass() {
		return timeProviderClass;
	}

	public void setTimeProviderClass(String timeProviderClass) {
		this.timeProviderClass = timeProviderClass;
	}

	
}
