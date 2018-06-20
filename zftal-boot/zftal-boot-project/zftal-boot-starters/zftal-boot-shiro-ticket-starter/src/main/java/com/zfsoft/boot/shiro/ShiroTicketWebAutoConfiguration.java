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

import com.zfsoft.authz.ticket.shiro.realm.ZFTicketRealm;
import com.zfsoft.authz.ticket.time.impl.DefaultTimeProvider;
import com.zfsoft.authz.ticket.utils.TicketTokenUtils;
import com.zfsoft.shiro.realm.RealmListener;
import com.zfsoft.shiro.service.AccountService;


/**
 * 
 * @className	： ShiroTicketWebAutoConfiguration
 * @description	： Ticket认证自动配置类
 * @author 		：魏广跃（1571）
 * @date		： 2018年5月15日 下午1:51:45
 * @version 	V1.0
 */
@Configuration
@AutoConfigureBefore( name = {
	"org.apache.shiro.spring.config.web.autoconfigure.ShiroWebAutoConfiguration",  // shiro-spring-boot-web-starter
	"com.zfsoft.boot.shiro.ShiroBizWebAutoConfiguration" // zftal-boot-shiro-starter
})
@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = ShiroTicketProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ ShiroTicketProperties.class })
public class ShiroTicketWebAutoConfiguration{
	
	private static final Logger log = LoggerFactory.getLogger(ShiroTicketWebAutoConfiguration.class);
	
	@Autowired
	private ShiroTicketProperties shiroTicketProperties;
	/**
	 * Ticket的Shiro的Realm Bean
	 */
	@Bean
	public Realm ticketRealm(//
			@Qualifier("ssoAccountServiceImpl") AccountService accountService,//
			@Qualifier("defaultCredentialsMatcher") CredentialsMatcher credentialsMatcher,//
			List<RealmListener> realmsListeners,//
			ShiroBizProperties properties//
			) {
		
		ZFTicketRealm realm = new ZFTicketRealm();
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
			log.debug("启用Ticket认证，初始化Ticket认证的Realm Bean["+ realm +"]");
		}
		return realm;
	}
	
	@Bean
	public TicketTokenUtils ticketTokenUtils() {
		TicketTokenUtils ticketTokenUtils = new TicketTokenUtils();
		ticketTokenUtils.setTimeProvider(new DefaultTimeProvider());
		ticketTokenUtils.setXxdm(this.shiroTicketProperties.getXxdm());
		ticketTokenUtils.setTokenPeriod(this.shiroTicketProperties.getTokenPeriod());
		ticketTokenUtils.setTimeStampPeriod(this.shiroTicketProperties.getTimeStampPeriod());
		ticketTokenUtils.setTimeProviderClass(this.shiroTicketProperties.getTimeProviderClass());
		ticketTokenUtils.checkAndDisplaySelfParam();
		return ticketTokenUtils;
	}
	

}
