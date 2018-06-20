/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.shiro.pac4j;

import com.zfsoft.boot.shiro.ShiroBizProperties;
import com.zfsoft.boot.shiro.ShiroPac4jCasProperties;
import com.zfsoft.boot.shiro.ShiroPac4jProperties;
import com.zfsoft.boot.shiro.pac4j.utils.CasUrlUtils;
import com.zfsoft.boot.shiro.pac4j.utils.Pac4jUrlUtils;

public class ShiroPac4jPathBuilder {

	private final ShiroBizProperties bizProperties;
	private final ShiroPac4jProperties pac4jProperties;
	private final ShiroPac4jCasProperties casProperties;
	
	public ShiroPac4jPathBuilder(ShiroBizProperties bizProperties,ShiroPac4jProperties pac4jProperties,
			ShiroPac4jCasProperties casProperties) {
		this.bizProperties = bizProperties;
		this.pac4jProperties = pac4jProperties;
		this.casProperties = casProperties;
	}
	
	public String getLoginURL(String contextPath) {
		// 如果是Cas认证，则构造Cas登录跳转地址
		if(casProperties != null && (casProperties.isCasClient() || casProperties.isDirectCasClient() || casProperties.isDirectCasProxyClient())) {
			// 回调URL, i.e. /callback?client_name=cas
			String callbackUrl = Pac4jUrlUtils.constructCallbackUrl(pac4jProperties);
			// 最终回调URL,i.e. https://localhost:8443/cas/login?service=https://localhost:8080/myapp/callback?client_name=cas
			return CasUrlUtils.constructLoginRedirectUrl(casProperties, contextPath, callbackUrl);
		}
		// 常规的登录地址
		return Pac4jUrlUtils.constructCallbackUrl(contextPath, bizProperties.getLoginUrl());
	}
	
	public String getLogoutURL(String contextPath) {
		// 如果是Cas认证，则构造Cas注销跳转地址：注销后进入Cas登录界面，登录后重新跳转回来进行cas认证
		if(casProperties != null && (casProperties.isCasClient() || casProperties.isDirectCasClient() || casProperties.isDirectCasProxyClient())) {
			// 回调URL, i.e. /callback?client_name=cas
			String callbackUrl = Pac4jUrlUtils.constructCallbackUrl(pac4jProperties);
			// 最终回调URL,i.e. https://localhost:8443/cas/login?service=https://localhost:8080/myapp/callback?client_name=cas
			return CasUrlUtils.constructLoginRedirectUrl(casProperties, contextPath, callbackUrl);
		}
		// 常规的注销地址：注销后进入本地登录页面
		return Pac4jUrlUtils.constructCallbackUrl(contextPath, bizProperties.getLoginUrl());
	}
	
}
