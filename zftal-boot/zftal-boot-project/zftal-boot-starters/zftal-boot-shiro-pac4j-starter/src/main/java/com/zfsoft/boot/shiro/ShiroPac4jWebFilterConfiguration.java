package com.zfsoft.boot.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.AbstractShiroWebFilterConfiguration;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.core.authorization.authorizer.CheckHttpMethodAuthorizer;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.core.context.HttpConstants.HTTP_METHOD;
import org.pac4j.core.context.J2EContext;
import org.pac4j.core.context.session.SessionStore;
import org.pac4j.core.http.HttpActionAdapter;
import org.pac4j.core.http.J2ENopHttpActionAdapter;
import org.pac4j.http.authorization.authorizer.IpRegexpAuthorizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import com.zfsoft.boot.shiro.pac4j.ShiroPac4jFilterFactoryBean;
import com.zfsoft.boot.shiro.pac4j.ShiroPac4jPathBuilder;
import com.zfsoft.boot.shiro.pac4j.ext.filter.Pac4jUserFilter;
import com.zfsoft.boot.shiro.pac4j.utils.Pac4jUrlUtils;
import com.zfsoft.boot.shiro.utils.StringUtils;

import io.buji.pac4j.context.ShiroSessionStore;
import io.buji.pac4j.filter.CallbackFilter;
import io.buji.pac4j.filter.LogoutFilter;
import io.buji.pac4j.filter.SecurityFilter;

@Configuration
@AutoConfigureBefore(name = { "org.apache.shiro.spring.config.web.autoconfigure.ShiroWebFilterConfiguration", // shiro-spring-boot-web-starter
		"com.zfsoft.boot.shiro.ShiroCasWebFilterConfiguration", // zftal-boot-shiro-cas-starter
		"com.zfsoft.boot.shiro.ShiroBizWebFilterConfiguration" // zftal-boot-shiro-starter
})
@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = ShiroPac4jProperties.PREFIX, value = "enabled", havingValue = "true")
@ConditionalOnClass({ CallbackFilter.class, SecurityFilter.class, SecurityFilter.class, CasConfiguration.class })
@EnableConfigurationProperties({ ShiroPac4jCasProperties.class, ShiroPac4jProperties.class, ShiroBizProperties.class, ServerProperties.class })
public class ShiroPac4jWebFilterConfiguration extends AbstractShiroWebFilterConfiguration {

	@Autowired
	private ShiroPac4jProperties pac4jProperties;
	@Autowired
	private ShiroPac4jCasProperties casProperties;
	@Autowired
	private ShiroBizProperties bizProperties;
	@Autowired
	private ServerProperties serverProperties;
	@Autowired
	private ShiroPac4jPathBuilder pac4jPathBuilder;
	
	@Bean
	@ConditionalOnMissingBean
	protected SessionStore<J2EContext> sessionStore() {
		return new ShiroSessionStore();
	}

	@Bean
	@ConditionalOnMissingBean
	protected HttpActionAdapter<Object, J2EContext> httpActionAdapter() {
		return J2ENopHttpActionAdapter.INSTANCE;
	}
	
	@Bean
	@ConditionalOnMissingBean
	protected ShiroPac4jPathBuilder pac4jPathBuilder() {
		return new ShiroPac4jPathBuilder(bizProperties, pac4jProperties, casProperties);
	}

	@Bean
	public Config config(Clients clients, HttpActionAdapter<Object, J2EContext> httpActionAdapter,
			SessionStore<J2EContext> sessionStore) {

		final Config config = new Config(clients);

		if (StringUtils.hasText(pac4jProperties.getAllowedIpRegexpPattern())) {
			config.addAuthorizer("isIPAuthenticated",
					new IpRegexpAuthorizer(pac4jProperties.getAllowedIpRegexpPattern()));
		}
		if (ArrayUtils.isNotEmpty(pac4jProperties.getAllowedHttpMethods())) {
			String[] allowedHttpMethods = pac4jProperties.getAllowedHttpMethods();
			List<HTTP_METHOD> methods = new ArrayList<HTTP_METHOD>();
			for (String method : allowedHttpMethods) {
				methods.add(HTTP_METHOD.valueOf(method));
			}
			config.addAuthorizer("isMethodAuthenticated", new CheckHttpMethodAuthorizer(methods));
		}

		/*
		 * excludePath excludeRegex excludeBranch
		 * 
		 * [] methods private String headerName; private String expectedValue;
		 */

		// config.addMatcher("path", new
		// AntPathMatcher().excludePath("").excludeBranch("").excludeRegex(""));
		// config.addMatcher("header", new HeaderMatcher());
		// config.addMatcher("method", new HttpMethodMatcher());

		config.setClients(clients);
		config.setHttpActionAdapter(httpActionAdapter);
		config.setSessionStore(sessionStore);

		return config;
	}

	/**
	 * 账号注销过滤器 ：处理账号注销
	 */
	@Bean("logout")
	public FilterRegistrationBean<LogoutFilter> logoutFilter(Config config) {

		FilterRegistrationBean<LogoutFilter> filterRegistration = new FilterRegistrationBean<LogoutFilter>();

		LogoutFilter logoutFilter = new LogoutFilter();

		// Whether the centralLogout must be performed（是否注销统一身份认证）
		logoutFilter.setCentralLogout(pac4jProperties.isCentralLogout());
		// Security Configuration
		logoutFilter.setConfig(config);
		// Default logourl url
		logoutFilter.setDefaultUrl(pac4jPathBuilder.getLogoutURL(serverProperties.getServlet().getContextPath()));
		// Whether the application logout must be performed（是否注销本地应用身份认证）
		logoutFilter.setLocalLogout(pac4jProperties.isLocalLogout());
		// Pattern that logout urls must match（注销登录路径规则，用于匹配登录请求操作）
		logoutFilter.setLogoutUrlPattern(pac4jProperties.getLogoutUrlPattern());

		filterRegistration.setFilter(logoutFilter);

		filterRegistration.setEnabled(false);
		return filterRegistration;
	}

	/**
	 * 权限控制过滤器 ：实现权限认证
	 */
	@Bean("authc")
	public FilterRegistrationBean<SecurityFilter> casSecurityFilter(Config config) {

		FilterRegistrationBean<SecurityFilter> filterRegistration = new FilterRegistrationBean<SecurityFilter>();

		SecurityFilter securityFilter = new SecurityFilter();
		// List of authorizers
		securityFilter.setAuthorizers(pac4jProperties.getAuthorizers());
		// List of clients for authentication
		securityFilter.setClients(pac4jProperties.getClients());
		// Security configuration
		securityFilter.setConfig(config);
		securityFilter.setMatchers(pac4jProperties.getMatchers());
		// Whether multiple profiles should be kept
		securityFilter.setMultiProfile(pac4jProperties.isMultiProfile());

		filterRegistration.setFilter(securityFilter);
		filterRegistration.setEnabled(false);

		return filterRegistration;
	}

	@Bean("user")
	public FilterRegistrationBean<Pac4jUserFilter> userFilter() {
		FilterRegistrationBean<Pac4jUserFilter> registration = new FilterRegistrationBean<Pac4jUserFilter>();
		Pac4jUserFilter userFilter = new Pac4jUserFilter();
		userFilter.setLoginUrl(pac4jPathBuilder.getLoginURL(serverProperties.getServlet().getContextPath()));
		registration.setFilter(userFilter);
		registration.setEnabled(false);
		return registration;
	}

	/**
	 * 回调过滤器 ：处理登录后的回调访问
	 */
	@Bean("pac4j")
	public FilterRegistrationBean<CallbackFilter> callbackFilter(Config config) {

		FilterRegistrationBean<CallbackFilter> filterRegistration = new FilterRegistrationBean<CallbackFilter>();

		CallbackFilter callbackFilter = new CallbackFilter();

		// Security Configuration
		callbackFilter.setConfig(config);
		// Default url after login if none was requested（登录成功后的重定向地址，等同于shiro的successUrl）
		callbackFilter.setDefaultUrl(Pac4jUrlUtils.constructCallbackUrl( serverProperties.getServlet().getContextPath(), bizProperties.getSuccessUrl()));
		// Whether multiple profiles should be kept
		callbackFilter.setMultiProfile(pac4jProperties.isMultiProfile());

		filterRegistration.setFilter(callbackFilter);
		filterRegistration.setEnabled(false);

		return filterRegistration;
	}

	@Bean
    @Override
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
		
		ShiroFilterFactoryBean filterFactoryBean = new ShiroPac4jFilterFactoryBean();
		
		// 登录地址：会话不存在时访问的地址
		filterFactoryBean.setLoginUrl(pac4jPathBuilder.getLoginURL(serverProperties.getServlet().getContextPath()));
		// 系统主页：登录成功后跳转路径
		filterFactoryBean.setSuccessUrl(bizProperties.getSuccessUrl());
		// 异常页面：无权限时的跳转路径
		filterFactoryBean.setUnauthorizedUrl(bizProperties.getUnauthorizedUrl());

		// 必须设置 SecurityManager
		filterFactoryBean.setSecurityManager(securityManager);
		// 拦截规则
		filterFactoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition.getFilterChainMap());

		return filterFactoryBean;
        
    }

	@Bean(name = "filterShiroFilterRegistrationBean")
	public FilterRegistrationBean<AbstractShiroFilter> filterShiroFilterRegistrationBean() throws Exception {

        FilterRegistrationBean<AbstractShiroFilter> filterRegistration = new FilterRegistrationBean<AbstractShiroFilter>();
        filterRegistration.setFilter((AbstractShiroFilter) shiroFilterFactoryBean().getObject());
        filterRegistration.setOrder(Ordered.LOWEST_PRECEDENCE);

        return filterRegistration;
    }

}
