/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.shiro.pac4j.utils;

import java.net.MalformedURLException;
import java.net.URL;

import org.jasig.cas.client.util.CommonUtils;
import org.springframework.util.StringUtils;

import com.zfsoft.boot.shiro.ShiroPac4jCasProperties;
import com.zfsoft.boot.shiro.pac4j.cas.CasClientProperties;

public class CasUrlUtils {
	
	/**
	 * 
	 * @description	： 构造完整的Cas注销URL,比如client项目的 https://localhost:8443/cas/logout?service=https://localhost:8080/myapp/callback?client_name=cas
	 * @author 		： 万大龙（743）
	 * @date 		：2018年2月9日 上午10:15:50
	 * @param casProperties
	 * @param contextPath
	 * @param serverUrl
	 * @return
	 */
	public static String constructLogoutRedirectUrl(ShiroPac4jCasProperties casProperties, String contextPath, String serverUrl){
		// 构造完整的回调URL,i.e. https://localhost:8080/myapp/callback?client_name=cas
		String callbackUrl = CasUrlUtils.constructCallbackUrl(casProperties, contextPath, serverUrl);
		// 使用 casServerLogoutUrl 构造完整的Cas登录URL
		return CommonUtils.constructRedirectUrl(casProperties.getCasServerLogoutUrl(), casProperties.getServiceParameterName(), callbackUrl, casProperties.isRenew(), casProperties.isGateway());
	}
	
	/**
	 * 
	 * @description	： 构造完整的Cas登录URL,i.e. https://localhost:8443/cas/login?service=https://localhost:8080/myapp/callback?client_name=cas
	 * @author 		： 万大龙（743）
	 * @date 		：2018年2月9日 上午10:15:42
	 * @param casProperties
	 * @param contextPath
	 * @param serverUrl
	 * @return
	 */
	public static String constructLoginRedirectUrl(ShiroPac4jCasProperties casProperties, String contextPath, String serverUrl){
		// 构造完整的回调URL,i.e. https://localhost:8080/myapp/callback?client_name=cas
		String callbackUrl = CasUrlUtils.constructCallbackUrl(casProperties, contextPath, serverUrl);
		// 使用 casServerLoginUrl 构造完整的Cas登录URL
		return CommonUtils.constructRedirectUrl(casProperties.getCasServerLoginUrl(), casProperties.getServiceParameterName(), callbackUrl, casProperties.isRenew(), casProperties.isGateway());
	}

	/**
	 * 
	 * @description	： 构造完整的回调URL,i.e. https://localhost:8080/myapp/callback?client_name=cas
	 * @author 		： 万大龙（743）
	 * @date 		：2018年2月9日 上午10:16:36
	 * @param casProperties
	 * @param contextPath
	 * @param serverUrl
	 * @return
	 */
	public static String constructCallbackUrl(CasClientProperties casProperties, String contextPath, String serverUrl) {

		contextPath = StringUtils.hasText(contextPath) ? contextPath : "/";
		if (contextPath.endsWith("/")) {
			contextPath = contextPath.substring(0, contextPath.length() - 1);
		}
		
		try {
			
			URL url = new URL(StringUtils.hasText(casProperties.getService()) ? casProperties.getService() : casProperties.getServerName());
			
			// 重定向地址：用于重新回到业务系统
			StringBuilder callbackUrl = new StringBuilder(url.getProtocol()).append("://").append(url.getHost())
					.append( url.getPort() != -1 ? ":" + url.getPort() : "").append(contextPath).append(serverUrl);

			return callbackUrl.toString();
			
		} catch (MalformedURLException e) {
			// 重定向地址：用于重新回到业务系统
			StringBuilder callbackUrl = new StringBuilder(casProperties.getServerName()).append(contextPath).append(serverUrl);
			return callbackUrl.toString();
		}

	}
}
