package com.zfsoft.boot.shiro;

import java.util.List;

import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zfsoft.authz.zjucookie.shiro.realm.ZjuCookieSsoRealm;
import com.zfsoft.authz.zjucookie.utils.CookieSsoApi;
import com.zfsoft.authz.zjucookie.utils.impl.DefaultCookieSsoApi;
import com.zfsoft.shiro.realm.RealmListener;
import com.zfsoft.shiro.service.AccountService;


/**
 * 
 * @className	： ShiroZjucookieWebAutoConfiguration
 * @description	： zjuCookie认证自动配置类
 * @author 		：魏广跃（1571）
 * @date		： 2018年5月18日 上午11:00:00
 * @version 	V1.0
 */
@Configuration
@AutoConfigureBefore( name = {
	"org.apache.shiro.spring.config.web.autoconfigure.ShiroWebAutoConfiguration",  // shiro-spring-boot-web-starter
	"com.zfsoft.boot.shiro.ShiroBizWebAutoConfiguration" // zftal-boot-shiro-starter
})
@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = ShiroZjucookieProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ ShiroZjucookieProperties.class })
public class ShiroZjucookieWebAutoConfiguration{
	
	private static final Logger log = LoggerFactory.getLogger(ShiroZjucookieWebAutoConfiguration.class);
	
	@Autowired
	private ShiroZjucookieProperties shiroZjucookieProperties;
	/**
	 * zjuCookie的Shiro的Realm Bean
	 */
	@Bean
	public Realm zjuCookieSsoRealm(//
			@Qualifier("ssoAccountServiceImpl") AccountService accountService,//
			@Qualifier("defaultCredentialsMatcher") CredentialsMatcher credentialsMatcher,//
			List<RealmListener> realmsListeners,//
			ShiroBizProperties properties//
			) {
		
		ZjuCookieSsoRealm realm = new ZjuCookieSsoRealm();
		//认证账号信息提供实现：认证信息、角色信息、权限信息；业务系统需要自己实现该接口
		realm.setAccountService(accountService);
		//凭证匹配器：该对象主要做密码校验
		realm.setCredentialsMatcher(new AllowAllCredentialsMatcher());
		//Realm 执行监听：实现该接口可监听认证失败和成功的状态，从而做业务系统自己的事情，比如记录日志
		realm.setRealmsListeners(realmsListeners);
		//缓存相关的配置：采用提供的默认配置即可
		realm.setCachingEnabled(properties.isCachingEnabled());
		//认证缓存配置
		realm.setAuthenticationCachingEnabled(properties.isAuthenticationCachingEnabled());
		realm.setAuthenticationCacheName(properties.getAuthenticationCacheName());
		//授权缓存配置
		realm.setAuthorizationCachingEnabled(properties.isAuthorizationCachingEnabled());
		realm.setAuthorizationCacheName(properties.getAuthorizationCacheName());

		if(log.isDebugEnabled()) {
			log.debug("启用Zjucookie认证，初始化Zjucookie认证的Realm Bean["+ realm +"]");
		}
		return realm;
	}
	
	@Bean
	public CookieSsoApi cookieSsoApi() {
		DefaultCookieSsoApi cookieSsoApi = new DefaultCookieSsoApi();
		cookieSsoApi.setAppUid(this.shiroZjucookieProperties.getAppUid());
		cookieSsoApi.setAppPwd(this.shiroZjucookieProperties.getAppPwd());
		cookieSsoApi.setSessionURL(this.shiroZjucookieProperties.getSessionURL());
		cookieSsoApi.setUserURL(this.shiroZjucookieProperties.getUserURL());
		return cookieSsoApi;
	}
	

}
