/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.shiro.pac4j.utils;

import org.springframework.util.StringUtils;

import com.zfsoft.boot.shiro.ShiroPac4jProperties;

public class Pac4jUrlUtils {
	
	/**
	 * 
	 * @description	：  构造回调URL, i.e. myapp/serverUrl
	 * @author 		： 万大龙（743）
	 * @date 		：2018年2月9日 上午9:38:46
	 * @param contextPath
	 * @param serverUrl
	 * @return
	 */
	public static String constructCallbackUrl(String contextPath, String serverUrl) {
		contextPath = StringUtils.hasText(contextPath) ? contextPath : "/";
		if (contextPath.endsWith("/")) {
			contextPath = contextPath.substring(0, contextPath.length() - 1);
		}
		StringBuilder callbackUrlBuilder = new StringBuilder(contextPath).append(serverUrl);
		return callbackUrlBuilder.toString();
	}
	
	/**
	 * 
	 * @description	：  构造回调URL, i.e. /callback?client_name=cas
	 * @author 		： 万大龙（743）
	 * @date 		：2018年2月9日 上午9:38:37
	 * @param pac4jProperties
	 * @return
	 */
	public static String constructCallbackUrl(ShiroPac4jProperties pac4jProperties) {
		String callbackUrl = pac4jProperties.getCallbackUrl();
		StringBuilder callbackUrlBuilder = new StringBuilder(callbackUrl).append((callbackUrl.contains("?") ? "&" : "?")).append(pac4jProperties.getClientParameterName()).append("=").append(pac4jProperties.getClientName());
		return callbackUrlBuilder.toString();
	}
	
}
