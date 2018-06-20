package com.zfsoft.boot.pac4j;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.CommonUtils;
import org.pac4j.cas.client.CasClient;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.cas.logout.CasLogoutHandler;
import org.pac4j.cas.logout.DefaultCasLogoutHandler;
import org.pac4j.core.authorization.authorizer.CheckHttpMethodAuthorizer;
import org.pac4j.core.client.Client;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.core.context.HttpConstants.HTTP_METHOD;
import org.pac4j.core.context.J2EContext;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.context.session.SessionStore;
import org.pac4j.core.http.AjaxRequestResolver;
import org.pac4j.core.http.DefaultAjaxRequestResolver;
import org.pac4j.core.http.HttpActionAdapter;
import org.pac4j.core.http.J2ENopHttpActionAdapter;
import org.pac4j.core.http.UrlResolver;
import org.pac4j.http.authorization.authorizer.IpRegexpAuthorizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
@Configuration
@ConditionalOnProperty(prefix = Pac4jProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ Pac4jProperties.class, ServerProperties.class })
public class Pac4jCasAutoConfiguration{
	
	@Autowired
	private Pac4jProperties casPac4jProperties;
	@Autowired
	private ServerProperties serverProperties;
	
	/**
	 * 单点登录Session监听器
	 */
	@Bean(name = "singleSignOutHttpSessionListener")
	@ConditionalOnMissingBean(name = "singleSignOutHttpSessionListener")
	@ConditionalOnProperty(prefix = Pac4jProperties.PREFIX, value = "enabled", havingValue = "true")
	@ConditionalOnClass({CasConfiguration.class})
	public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> singleSignOutHttpSessionListener() {
		ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> registration = new ServletListenerRegistrationBean<SingleSignOutHttpSessionListener>(
				new SingleSignOutHttpSessionListener());
		registration.setOrder(1);
		return registration;
	}
	
	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(prefix = Pac4jProperties.PREFIX, value = "enabled", havingValue = "true")
    public CasLogoutHandler<WebContext> logoutHandler(){
		return new DefaultCasLogoutHandler<WebContext>();
	}
	
	/*@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(prefix = Pac4jProperties.PREFIX, value = "enabled", havingValue = "true")
	protected UrlResolver urlResolver(ServerProperties serverProperties) {
		return new ShiroRelativeUrlResolver(serverProperties.getContextPath());
	}
	
	@Bean
	@ConditionalOnProperty(prefix = Pac4jProperties.PREFIX, value = "enabled", havingValue = "true")
    public CasConfiguration casConfiguration(CasLogoutHandler<WebContext> logoutHandler, UrlResolver urlResolver) {

		// 完整的cas登录地址,比如client项目的https://passport.zfsoft.com/login?service=https://client.zfsoft.com
		String serverLoginUrl = CasUrlUtils.constructLoginRedirectUrl(casPac4jProperties, serverProperties.getContextPath(), casPac4jProperties.getServerCallbackUrl());
		
		CasConfiguration configuration = new CasConfiguration(serverLoginUrl, casPac4jProperties.getCasProtocol() );
		
		if(casPac4jProperties.isAcceptAnyProxy() && StringUtils.hasText(casPac4jProperties.getAllowedProxyChains())) {	
			configuration.setAcceptAnyProxy(casPac4jProperties.isAcceptAnyProxy());
			configuration.setAllowedProxyChains(CommonUtils.createProxyList(casPac4jProperties.getAllowedProxyChains()));
		}
		
		if(StringUtils.hasText(casPac4jProperties.getEncoding())) {	
			configuration.setEncoding(casPac4jProperties.getEncoding());
		}
		configuration.setGateway(casPac4jProperties.isGateway());
		configuration.setLoginUrl(casPac4jProperties.getCasServerLoginUrl());
		configuration.setLogoutHandler(logoutHandler);
		if(StringUtils.hasText(casPac4jProperties.getServiceParameterName())) {	
			configuration.setPostLogoutUrlParameter(casPac4jProperties.getServiceParameterName());
		}
		configuration.setPrefixUrl(casPac4jProperties.getCasServerUrlPrefix());
		configuration.setProtocol(casPac4jProperties.getCasProtocol());
		//configuration.setRenew(casPac4jProperties.isRenew());
		configuration.setRestUrl(casPac4jProperties.getCasServerRestUrl());
		configuration.setTimeTolerance(casPac4jProperties.getTolerance());
		configuration.setUrlResolver(urlResolver);
		
		return configuration;
	}

	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(prefix = Pac4jProperties.PREFIX, value = "enabled", havingValue = "true")
	protected AjaxRequestResolver ajaxRequestResolver() {
		return new DefaultAjaxRequestResolver();
	}
	
	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(prefix = Pac4jProperties.PREFIX, value = "enabled", havingValue = "true")
	protected SessionStore<J2EContext> sessionStore() {
		return new ShiroSessionStore();
	}
	
	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(prefix = Pac4jProperties.PREFIX, value = "enabled", havingValue = "true")
	protected HttpActionAdapter<Object, J2EContext> httpActionAdapter() {
		return J2ENopHttpActionAdapter.INSTANCE;
	}
	
	@SuppressWarnings("rawtypes")
	@Bean
	@ConditionalOnProperty(prefix = Pac4jProperties.PREFIX, value = "enabled", havingValue = "true")
	public Config casConfig(CasConfiguration configuration, AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver,
			HttpActionAdapter<Object, J2EContext> httpActionAdapter,SessionStore<J2EContext> sessionStore) {

		final Clients clients = new Clients();
		final List<Client> clientList = new ArrayList<Client>();
		CasClient casClient = CasClientUtils.casClient(configuration, casPac4jProperties, serverProperties);
		clientList.add(casClient);
		if(casPac4jProperties.isDirectCasClient()) {
			clientList.add(CasClientUtils.directCasClient(configuration, casPac4jProperties));
		}
		if(casPac4jProperties.isDirectCasProxyClient()) {
			clientList.add(CasClientUtils.directCasProxyClient(configuration, casPac4jProperties, casPac4jProperties.getCasServerUrlPrefix()));
		}
		if(casPac4jProperties.isCasRestBasicAuthClient()) {
			clientList.add(CasClientUtils.casRestBasicAuthClient(configuration, casPac4jProperties));
		}
		if(casPac4jProperties.isCasRestFormClient()) {
			clientList.add(CasClientUtils.casRestFormClient(configuration, casPac4jProperties));
		}
		
		clients.setAjaxRequestResolver(ajaxRequestResolver);
		clients.setCallbackUrl(casPac4jProperties.getCallbackUrl());
		clients.setClients(clientList);
		clients.setClientNameParameter(casPac4jProperties.getClientParameterName());
		clients.setDefaultClient(casClient);
		clients.setUrlResolver(urlResolver);
		
		final Config config = new Config(clients);
		
		if(StringUtils.hasText(casPac4jProperties.getAllowedIpRegexpPattern())) {	
			config.addAuthorizer("isIPAuthenticated", new IpRegexpAuthorizer(casPac4jProperties.getAllowedIpRegexpPattern()));
		}
		if(ArrayUtils.isNotEmpty(casPac4jProperties.getAllowedHttpMethods())) {	
			String[] allowedHttpMethods = casPac4jProperties.getAllowedHttpMethods();
			List<HTTP_METHOD> methods = new ArrayList<HTTP_METHOD>();
			for (String method : allowedHttpMethods) {
				methods.add(HTTP_METHOD.valueOf(method));
			}
			config.addAuthorizer("isMethodAuthenticated", new CheckHttpMethodAuthorizer(methods));
		}
		
		excludePath
		excludeRegex
		excludeBranch
		
		[] methods
		private String headerName;
	    private String expectedValue;
	    
	    
		//config.addMatcher("path", new AntPathMatcher().excludePath("").excludeBranch("").excludeRegex(""));
		//config.addMatcher("header", new HeaderMatcher());
		//config.addMatcher("method", new HttpMethodMatcher());
		
		config.setClients(clients);
		config.setHttpActionAdapter(httpActionAdapter);
		config.setSessionStore(sessionStore);
		
		return config;
	}
	
	*//**
	 * 账号注销过滤器 ：处理账号注销
	 *//*
	@Bean("logout")
	@ConditionalOnMissingBean(name = "logout")
	@ConditionalOnProperty(prefix = Pac4jProperties.PREFIX, value = "enabled", havingValue = "true")
	public FilterRegistrationBean logoutFilter(Config config, List<LogoutListener> logoutListeners,
			Pac4jProperties casPac4jProperties, 
			ShiroBizProperties properties, ServerProperties serverProperties){
		
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean(); 
		
		
		if(casPac4jProperties != null && casPac4jProperties.isEnabled()) {
			
			LogoutFilter logoutFilter = new LogoutFilter();
		    
			// Whether the centralLogout must be performed（是否注销统一身份认证）
	        logoutFilter.setCentralLogout(casPac4jProperties.isCentralLogout());
			// Security Configuration
	        logoutFilter.setConfig(config);
	        
	        // Default logourl url
	        logoutFilter.setDefaultUrl( CasUrlUtils.constructLogoutRedirectUrl(casPac4jProperties, serverProperties.getContextPath(), properties.getLoginUrl()) );
	        // Whether the application logout must be performed（是否注销本地应用身份认证）
	        logoutFilter.setLocalLogout(casPac4jProperties.isLocalLogout());
	        // Pattern that logout urls must match（注销登录路径规则，用于匹配登录请求操作）
	        logoutFilter.setLogoutUrlPattern(casPac4jProperties.getLogoutUrlPattern());
	        
	        filterRegistration.setFilter(logoutFilter);
			
		} else {
			
			ZFLogoutFilter logoutFilter = new ZFLogoutFilter();
			//登录注销后的重定向地址：直接进入登录页面
			logoutFilter.setRedirectUrl(properties.getRedirectUrl());
			//注销监听：实现该接口可监听账号注销失败和成功的状态，从而做业务系统自己的事情，比如记录日志
			logoutFilter.setLogoutListeners(logoutListeners);
			
			filterRegistration.setFilter(logoutFilter);
			
		}
	    
		filterRegistration.setEnabled(false); 
	    return filterRegistration;
	}
	
	*//**
	 * 权限控制过滤器 ：实现权限认证
	 *//*
	@Bean("authc")
	@ConditionalOnMissingBean(name = "authc")
	@ConditionalOnProperty(prefix = Pac4jProperties.PREFIX, value = "enabled", havingValue = "true")
	public FilterRegistrationBean casSecurityFilter(Config config){
		
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		
		SecurityFilter securityFilter = new SecurityFilter();  
		
		// List of authorizers
		securityFilter.setAuthorizers(casPac4jProperties.getAuthorizers());
		// List of clients for authentication
		securityFilter.setClients(casPac4jProperties.getClients());
		// Security configuration
		securityFilter.setConfig(config);
		securityFilter.setMatchers(casPac4jProperties.getMatchers());
		// Whether multiple profiles should be kept
		securityFilter.setMultiProfile(casPac4jProperties.isMultiProfile());
		
        filterRegistration.setFilter(securityFilter);
	    filterRegistration.setEnabled(false); 
	    
	    return filterRegistration;
	}
	
	
	@Bean("user")
	@ConditionalOnMissingBean(name = "user")
	@ConditionalOnProperty(prefix = Pac4jProperties.PREFIX, value = "enabled", havingValue = "true")
	public FilterRegistrationBean casSsoFilter(ShiroBizProperties properties){
		FilterRegistrationBean registration = new FilterRegistrationBean(); 
		ZFPac4jUserFilter userFilter = new ZFPac4jUserFilter();
		userFilter.setLoginUrl(CasUrlUtils.constructLoginRedirectUrl(casPac4jProperties, serverProperties.getContextPath(), casPac4jProperties.getServerCallbackUrl()));
		registration.setFilter(userFilter);
	    registration.setEnabled(false); 
	    return registration;
	}
	
	
	*//**
	 * 回调过滤器 ：处理登录后的回调访问
	 *//*
	@Bean("cas")
	@ConditionalOnMissingBean(name = "cas")
	@ConditionalOnProperty(prefix = Pac4jProperties.PREFIX, value = "enabled", havingValue = "true")
	public FilterRegistrationBean callbackFilter(Config config){
		
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		
	    CallbackFilter callbackFilter = new CallbackFilter();
	    
	    // Security Configuration
        callbackFilter.setConfig(config);
        // Default url after login if none was requested（登录成功后的重定向地址，等同于shiro的successUrl）
        callbackFilter.setDefaultUrl(CasUrlUtils.constructCallbackUrl( serverProperties.getContextPath(), properties.getSuccessUrl()));
        // Whether multiple profiles should be kept
        callbackFilter.setMultiProfile(casPac4jProperties.isMultiProfile());
        
        filterRegistration.setFilter(callbackFilter);
	    filterRegistration.setEnabled(false); 
	    
	    return filterRegistration;
	}*/
	
    
}
