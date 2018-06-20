/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.authz.setup.config;

import java.util.List;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.Realm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zfsoft.basicutils.StringUtils;
import com.zfsoft.boot.authz.shiro.filter.DefaultAuthenticationFilter;
import com.zfsoft.boot.authz.shiro.filter.DefaultSessionControlFilter;
import com.zfsoft.boot.authz.shiro.listener.ZFLoginListener;
import com.zfsoft.boot.authz.shiro.listener.ZFLogoutListener;
import com.zfsoft.boot.authz.shiro.listener.ZFRealmListener;
import com.zfsoft.boot.kaptcha.ext.CaptchaResolver;
import com.zfsoft.boot.shiro.ShiroBizProperties;
import com.zfsoft.boot.shiro.captcha.ShiroSessionCaptchaResolver;
import com.zfsoft.shiro.authc.credential.DefaultCredentialsMatcher;
import com.zfsoft.shiro.filter.LoginListener;
import com.zfsoft.shiro.realm.DefaultAccountRealm;
import com.zfsoft.shiro.realm.RealmListener;
import com.zfsoft.shiro.service.AccountService;

@Configuration
public class WebShiroConfig{
	
	//Shiro SSO 过滤器
	/*@Bean
	@Order()
    public Filter shiroSsoFilter() {
		
		DelegatingFilterProxy filterProxy = new DelegatingFilterProxy("shiroSsoFilter");
		
		filterProxy.setTargetFilterLifecycle(true);
		
        return filterProxy;
    }*/
	
	/**
	 * 默认的凭证匹配器：该对象主要做密码校验
	 */
	@Bean("defaultCredentialsMatcher")
	public CredentialsMatcher credentialsMatcher() {
		return new DefaultCredentialsMatcher();
	}
	
	@Bean
	public RealmListener realmListener() {
		return new ZFRealmListener();
	}
	
	@Bean
	public ZFLogoutListener logoutListener() {
		return new ZFLogoutListener();
	}
	
	@Bean
	public ZFLoginListener loginListener() {
		return new ZFLoginListener();
	}
	
	/**
	 * 默认的登录认证Realm实现 
	 */
	@Bean
	public Realm defaultRealm(@Qualifier("defaultShiroService") AccountService accountService,
			@Qualifier("defaultCredentialsMatcher") CredentialsMatcher credentialsMatcher,
			List<RealmListener> realmsListeners, ShiroBizProperties properties) {
		
		DefaultAccountRealm defaultRealm = new DefaultAccountRealm();
		//认证账号信息提供实现：认证信息、角色信息、权限信息；业务系统需要自己实现该接口
		defaultRealm.setAccountService(accountService);
		//凭证匹配器：该对象主要做密码校验
		defaultRealm.setCredentialsMatcher(credentialsMatcher);
		//Realm 执行监听：实现该接口可监听认证失败和成功的状态，从而做业务系统自己的事情，比如记录日志
		defaultRealm.setRealmsListeners(realmsListeners);
		//缓存相关的配置：采用提供的默认配置即可
		defaultRealm.setCachingEnabled(properties.isCachingEnabled());
		//认证缓存配置
		defaultRealm.setAuthenticationCachingEnabled(properties.isAuthenticationCachingEnabled());
		defaultRealm.setAuthenticationCacheName(properties.getAuthenticationCacheName());
		//授权缓存配置
		defaultRealm.setAuthorizationCachingEnabled(properties.isAuthorizationCachingEnabled());
		defaultRealm.setAuthorizationCacheName(properties.getAuthorizationCacheName());
		
		return defaultRealm;
	}
	
	/**
	 * 默认的登录验证过滤器
	 */
	@Bean("authc")
	@ConditionalOnMissingBean(name = "authc")
	public FilterRegistrationBean<DefaultAuthenticationFilter> authenticationFilter(List<LoginListener> loginListeners,
			@Autowired(required = false) CaptchaResolver captchaResolver, ShiroBizProperties properties){
		
		DefaultAuthenticationFilter authcFilter = new DefaultAuthenticationFilter();
		authcFilter.setCaptchaResolver(captchaResolver);
		//是否验证验证码
		authcFilter.setValidateCaptcha(properties.isValidateCaptcha());
		//登录监听：实现该接口可监听账号登录失败和成功的状态，从而做业务系统自己的事情，比如记录日志
		authcFilter.setLoginListeners(loginListeners);
		
		/* 
		 * 自定义Filter通过@Bean注解后，被Spring Boot自动注册到了容器的Filter chain中，这样导致的结果是，所有URL都会被自定义Filter过滤，
		 * 而不是Shiro中配置的一部分URL。下面方式可以解决该问题
		 */
		FilterRegistrationBean<DefaultAuthenticationFilter> registration = new FilterRegistrationBean<DefaultAuthenticationFilter>(authcFilter); 
	    registration.setEnabled(false); 
	    return registration;
	}
	
	/**
	 * 默认的Session控制过滤器 ：只允许用户在一个地方登录
	 */
	@Bean("sessionControl")
	@ConditionalOnMissingBean(name = "sessionControl")
	public FilterRegistrationBean<DefaultSessionControlFilter> sessionControlFilter(CacheManager cacheManager,List<LoginListener> loginListeners, ShiroBizProperties properties){
		
		DefaultSessionControlFilter sessionControlFilter = new DefaultSessionControlFilter();
		
		//缓存管理器
		sessionControlFilter.setCacheManager(cacheManager);
		//登录监听：实现该接口可监听账号登录失败和成功的状态，从而做业务系统自己的事情，比如记录日志
		sessionControlFilter.setSessionControlCacheName(properties.getSessionControlCacheName());
		//上次回话踢出成功后的重定向地址：直接进入登录页面
		sessionControlFilter.setKickoutUrl(StringUtils.getSafeStr(properties.getRedirectUrl(), properties.getLoginUrl()));
		
		/* 
		 * 自定义Filter通过@Bean注解后，被Spring Boot自动注册到了容器的Filter chain中，这样导致的结果是，所有URL都会被自定义Filter过滤，
		 * 而不是Shiro中配置的一部分URL。下面方式可以解决该问题
		 */
		FilterRegistrationBean<DefaultSessionControlFilter> registration = new FilterRegistrationBean<DefaultSessionControlFilter>(sessionControlFilter); 
	    registration.setEnabled(false); 
	    return registration;
	}
	
	@Bean
	public ShiroSessionCaptchaResolver captchaResolver() {
		return new ShiroSessionCaptchaResolver();
	}
	 
}
