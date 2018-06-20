/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.shiro.pac4j.utils;

import org.pac4j.cas.client.CasClient;
import org.pac4j.cas.client.direct.DirectCasClient;
import org.pac4j.cas.client.direct.DirectCasProxyClient;
import org.pac4j.cas.client.rest.CasRestBasicAuthClient;
import org.pac4j.cas.client.rest.CasRestFormClient;
import org.pac4j.cas.config.CasConfiguration;

import com.zfsoft.boot.shiro.ShiroPac4jCasProperties;
import com.zfsoft.boot.shiro.ShiroPac4jProperties;
import com.zfsoft.boot.shiro.utils.StringUtils;

public class CasClientUtils {

	public static CasClient casClient(CasConfiguration configuration,ShiroPac4jProperties pac4jProperties, ShiroPac4jCasProperties casProperties) {

		CasClient casClient = new CasClient(configuration);
		casClient.setCallbackUrl( pac4jProperties.getCallbackUrl());
		casClient.setName(StringUtils.hasText(casProperties.getCasClientName()) ? casProperties.getCasClientName() : "CasClient");
		
		return casClient;
	}

	public static DirectCasClient directCasClient(CasConfiguration configuration,ShiroPac4jCasProperties casProperties) {
		
		DirectCasClient casClient = new DirectCasClient();
		
		casClient.setConfiguration(configuration);
		casClient.setName(StringUtils.hasText(casProperties.getDirectCasClientName()) ? casProperties.getDirectCasClientName() : "DirectCasClient");
		
		return casClient;
	}

	public static DirectCasProxyClient directCasProxyClient(CasConfiguration configuration,ShiroPac4jCasProperties casProperties, String serverUrl) {
		
		DirectCasProxyClient casClient = new DirectCasProxyClient();
		
		casClient.setConfiguration(configuration);
		casClient.setName(StringUtils.hasText(casProperties.getDirectCasProxyClientName()) ? casProperties.getDirectCasProxyClientName() : "DirectCasProxyClient");
		casClient.setServiceUrl(serverUrl);
		
		return casClient;
	}

	public static CasRestBasicAuthClient casRestBasicAuthClient(CasConfiguration configuration,ShiroPac4jCasProperties casProperties) {
		
		CasRestBasicAuthClient casClient = new CasRestBasicAuthClient();
		
		casClient.setConfiguration(configuration);
		casClient.setName(StringUtils.hasText(casProperties.getCasRestBasicAuthClientName()) ? casProperties.getCasRestBasicAuthClientName() : "RestBasicAuthClient");
		if(StringUtils.hasText(casProperties.getHeaderName())) {	
			casClient.setHeaderName(casProperties.getHeaderName());
		}
		if(StringUtils.hasText(casProperties.getPrefixHeader())) {	
			casClient.setPrefixHeader(casProperties.getPrefixHeader());
		}
		
		return casClient;
	}

	/**
	 * 通过rest接口可以获取tgt，获取service ticket，甚至可以获取CasProfile
	 * @return
	 */
	public static CasRestFormClient casRestFormClient(CasConfiguration configuration,ShiroPac4jCasProperties casProperties) {
		
		CasRestFormClient casClient = new CasRestFormClient();
		
		casClient.setConfiguration(configuration);
		casClient.setName(StringUtils.hasText(casProperties.getCasRestFormClientName()) ? casProperties.getCasRestFormClientName() : "RestFormClient");
		if(StringUtils.hasText(casProperties.getUsernameParameterName())) {	
			casClient.setUsernameParameter(casProperties.getUsernameParameterName());
		}
		if(StringUtils.hasText(casProperties.getPasswordParameterName())) {	
			casClient.setPasswordParameter(casProperties.getPasswordParameterName());
		}

		return casClient;
	}
	
}
