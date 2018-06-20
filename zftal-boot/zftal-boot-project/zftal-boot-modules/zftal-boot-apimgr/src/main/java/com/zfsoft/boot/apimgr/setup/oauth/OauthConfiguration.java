/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.setup.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

import com.zfsoft.boot.apimgr.setup.count.InvokeCount;

@Configuration
@PropertySource("api-oauth.properties")
public class OauthConfiguration {
	
	@Autowired(required=false)
	private InvokeCount invokeCount;
	
	@Bean("restTemplate")
	public RestTemplate oauthRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	@ConfigurationProperties("shiro.oauth")
	public OauthProperties oauthProperties() {
		return new OauthProperties();
	}
	
	@Bean
	public RestTemplateUtils restTemplateUtils(OauthProperties oauthProperties,RestTemplate restTemplate) {
		return new RestTemplateUtils(oauthProperties, restTemplate);
	}
	
	@Bean
	public OauthInterceptor oauthInterceptor(OauthProperties oauth,RestTemplateUtils rest) {
		return new OauthInterceptor(oauth, rest, invokeCount);
	}
	
}
