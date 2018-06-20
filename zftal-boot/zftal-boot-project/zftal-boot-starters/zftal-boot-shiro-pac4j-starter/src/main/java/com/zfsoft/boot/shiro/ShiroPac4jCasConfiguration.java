/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.shiro;

import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.CommonUtils;
import org.pac4j.cas.client.CasClient;
import org.pac4j.cas.client.direct.DirectCasClient;
import org.pac4j.cas.client.direct.DirectCasProxyClient;
import org.pac4j.cas.client.rest.CasRestBasicAuthClient;
import org.pac4j.cas.client.rest.CasRestFormClient;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.cas.logout.CasLogoutHandler;
import org.pac4j.cas.logout.DefaultCasLogoutHandler;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.http.UrlResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zfsoft.boot.shiro.pac4j.ShiroPac4jRelativeUrlResolver;
import com.zfsoft.boot.shiro.pac4j.utils.CasClientUtils;
import com.zfsoft.boot.shiro.utils.StringUtils;

@Configuration
@AutoConfigureBefore( name = {
	"com.zfsoft.boot.shiro.ShiroPac4jClientsConfiguration"
})
@ConditionalOnWebApplication
@ConditionalOnClass({ SingleSignOutHttpSessionListener.class, CasConfiguration.class})
@ConditionalOnProperty(prefix = ShiroPac4jCasProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ ShiroPac4jCasProperties.class, ShiroPac4jProperties.class, ServerProperties.class })
public class ShiroPac4jCasConfiguration {

	@Autowired
	private ShiroPac4jCasProperties casProperties;
	@Autowired
	private ShiroPac4jProperties pac4jProperties;
	@Autowired
	private ServerProperties serverProperties;
	
	/**
	 * 单点登录Session监听器
	 */
	@Bean(name = "singleSignOutHttpSessionListener")
	public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> singleSignOutHttpSessionListener() {
		ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> registration = new ServletListenerRegistrationBean<SingleSignOutHttpSessionListener>(
				new SingleSignOutHttpSessionListener());
		registration.setOrder(1);
		return registration;
	}
	
	@Bean
	@ConditionalOnMissingBean
    public CasLogoutHandler<WebContext> logoutHandler(){
		return new DefaultCasLogoutHandler<WebContext>();
	}
	
	@Bean
	@ConditionalOnMissingBean
	protected UrlResolver urlResolver(ServerProperties serverProperties) {
		return new ShiroPac4jRelativeUrlResolver(serverProperties.getServlet().getContextPath());
	}
	
	@Bean
    public CasConfiguration casConfiguration(CasLogoutHandler<WebContext> logoutHandler, UrlResolver urlResolver) {
		
		CasConfiguration configuration = new CasConfiguration(casProperties.getCasServerLoginUrl(), casProperties.getCasProtocol() );
		
		if(casProperties.isAcceptAnyProxy() && StringUtils.hasText(casProperties.getAllowedProxyChains())) {	
			configuration.setAcceptAnyProxy(casProperties.isAcceptAnyProxy());
			configuration.setAllowedProxyChains(CommonUtils.createProxyList(casProperties.getAllowedProxyChains()));
		}
		
		if(StringUtils.hasText(casProperties.getEncoding())) {	
			configuration.setEncoding(casProperties.getEncoding());
		}
		configuration.setGateway(casProperties.isGateway());
		configuration.setLoginUrl(casProperties.getCasServerLoginUrl());
		configuration.setLogoutHandler(logoutHandler);
		if(StringUtils.hasText(casProperties.getServiceParameterName())) {	
			configuration.setPostLogoutUrlParameter(casProperties.getServiceParameterName());
		}
		configuration.setPrefixUrl(casProperties.getCasServerUrlPrefix());
		configuration.setProtocol(casProperties.getCasProtocol());
		//configuration.setRenew(casProperties.isRenew());
		configuration.setRestUrl(casProperties.getCasServerRestUrl());
		configuration.setTimeTolerance(casProperties.getTolerance());
		configuration.setUrlResolver(urlResolver);
		
		return configuration;
	}

	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jCasProperties.PREFIX, value = "cas-client", havingValue = "true")
	public CasClient casClient(CasConfiguration configuration) {
		return CasClientUtils.casClient(configuration, pac4jProperties, casProperties);
	}
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jCasProperties.PREFIX, value = "direct-cas-client", havingValue = "true")
	public DirectCasClient directCasClient(CasConfiguration configuration) {
		return CasClientUtils.directCasClient(configuration, casProperties);
	}
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jCasProperties.PREFIX, value = "direct-cas-proxy-client", havingValue = "true")
	public DirectCasProxyClient directCasProxyClient(CasConfiguration configuration) {
		return CasClientUtils.directCasProxyClient(configuration, casProperties, casProperties.getCasServerUrlPrefix());
	}
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jCasProperties.PREFIX, value = "cas-rest-basic-auth-client", havingValue = "true")
	public CasRestBasicAuthClient casRestBasicAuthClient(CasConfiguration configuration) {
		return CasClientUtils.casRestBasicAuthClient(configuration, casProperties);
	}
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jCasProperties.PREFIX, value = "cas-rest-form-client", havingValue = "true")
	public CasRestFormClient casRestFormClient(CasConfiguration configuration) {
		return CasClientUtils.casRestFormClient(configuration, casProperties);
	}
    
}