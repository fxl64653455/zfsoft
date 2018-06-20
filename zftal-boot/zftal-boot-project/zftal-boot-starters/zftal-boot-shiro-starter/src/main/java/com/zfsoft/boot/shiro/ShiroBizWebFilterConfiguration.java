package com.zfsoft.boot.shiro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.AbstractShiroWebFilterConfiguration;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.util.ObjectUtils;

import com.zfsoft.boot.shiro.biz.ShiroBizFilterFactoryBean;
import com.zfsoft.shiro.filter.LoginListener;
import com.zfsoft.shiro.filter.LogoutListener;
import com.zfsoft.shiro.filter.ZFLogoutFilter;
import com.zfsoft.shiro.filter.session.SessionExpiredFilter;
import com.zfsoft.shiro.realm.RealmListener;

/**
 * 自定义Filter通过@Bean注解后，被Spring Boot自动注册到了容器的Filter chain中，这样导致的结果是，所有URL都会被自定义Filter过滤，而不是Shiro中配置的一部分URL。
 * https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-disable-registration-of-a-servlet-or-filter
 * http://www.jianshu.com/p/bf79fdab9c19
 * @className	： ShiroBizWebFilterConfiguration
 * @description	： TODO(描述这个类的作用)
 * @author 		：万大龙（743）
 * @date		： 2017年11月7日 下午10:09:21
 * @version 	V1.0
 */
@Configuration
@AutoConfigureBefore( name = {
	"org.apache.shiro.spring.config.web.autoconfigure.ShiroWebFilterConfiguration"  // shiro-spring-boot-web-starter
})
@ConditionalOnProperty(prefix = ShiroBizProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ ShiroBizProperties.class })
public class ShiroBizWebFilterConfiguration extends AbstractShiroWebFilterConfiguration implements ApplicationContextAware {

	private ApplicationContext applicationContext;
	
	@Autowired
	private ShiroBizProperties properties;
	
	/**
	 * 登录监听：实现该接口可监听账号登录失败和成功的状态，从而做业务系统自己的事情，比如记录日志
	 */
	@Bean("loginListeners")
	@ConditionalOnMissingBean(name = "loginListeners")
	public List<LoginListener> loginListeners() {

		List<LoginListener> loginListeners = new ArrayList<LoginListener>();
		
		Map<String, LoginListener> beansOfType = getApplicationContext().getBeansOfType(LoginListener.class);
		if (!ObjectUtils.isEmpty(beansOfType)) {
			Iterator<Entry<String, LoginListener>> ite = beansOfType.entrySet().iterator();
			while (ite.hasNext()) {
				loginListeners.add(ite.next().getValue());
			}
		}
		
		return loginListeners;
	}
	
	/**
	 * Realm 执行监听：实现该接口可监听认证失败和成功的状态，从而做业务系统自己的事情，比如记录日志
	 */
	@Bean("realmListeners")
	@ConditionalOnMissingBean(name = "realmListeners")
	public List<RealmListener> realmListeners() {

		List<RealmListener> realmListeners = new ArrayList<RealmListener>();
		
		Map<String, RealmListener> beansOfType = getApplicationContext().getBeansOfType(RealmListener.class);
		if (!ObjectUtils.isEmpty(beansOfType)) {
			Iterator<Entry<String, RealmListener>> ite = beansOfType.entrySet().iterator();
			while (ite.hasNext()) {
				realmListeners.add(ite.next().getValue());
			}
		}
		
		return realmListeners;
	}
	
	/**
	 * 注销监听：实现该接口可监听账号注销失败和成功的状态，从而做业务系统自己的事情，比如记录日志
	*/
	@Bean("logoutListeners")
	@ConditionalOnMissingBean(name = "logoutListeners")
	public List<LogoutListener> logoutListeners() {

		List<LogoutListener> logoutListeners = new ArrayList<LogoutListener>();
		
		Map<String, LogoutListener> beansOfType = getApplicationContext().getBeansOfType(LogoutListener.class);
		if (!ObjectUtils.isEmpty(beansOfType)) {
			Iterator<Entry<String, LogoutListener>> ite = beansOfType.entrySet().iterator();
			while (ite.hasNext()) {
				logoutListeners.add(ite.next().getValue());
			}
		}
		
		return logoutListeners;
	} 
	
	/**
	 * 默认的Session过期过滤器 ：解决Ajax请求期间会话过期异常处理
	 */
	@Bean("sessionExpired")
	@ConditionalOnMissingBean(name = "sessionExpired")
	public FilterRegistrationBean<SessionExpiredFilter> sessionExpiredFilter(){
		FilterRegistrationBean<SessionExpiredFilter> registration = new FilterRegistrationBean<SessionExpiredFilter>(new SessionExpiredFilter()); 
	    registration.setEnabled(false); 
	    return registration;
	}
	
	/**
	 * 系统登录注销过滤器；默认：com.zfsoft.shiro.filter.ZFLogoutFilter
	 */
	@Bean("logout")
	@ConditionalOnMissingBean(name = "logout")
	public FilterRegistrationBean<ZFLogoutFilter> logoutFilter(List<LogoutListener> logoutListeners){
		
		FilterRegistrationBean<ZFLogoutFilter> filterRegistration = new FilterRegistrationBean<ZFLogoutFilter>(); 
		
		ZFLogoutFilter logoutFilter = new ZFLogoutFilter();
			
		//登录注销后的重定向地址：直接进入登录页面
		logoutFilter.setRedirectUrl(properties.getRedirectUrl());
		//注销监听：实现该接口可监听账号注销失败和成功的状态，从而做业务系统自己的事情，比如记录日志
		logoutFilter.setLogoutListeners(logoutListeners);
		
		filterRegistration.setFilter(logoutFilter);
	    
	    filterRegistration.setEnabled(false); 
	    return filterRegistration;
	}
	
	
	@Bean
    @ConditionalOnMissingBean
    @Override
	protected ShiroFilterFactoryBean shiroFilterFactoryBean() {

		ShiroFilterFactoryBean filterFactoryBean = new ShiroBizFilterFactoryBean();
		
		//登录地址：会话不存在时访问的地址
        filterFactoryBean.setLoginUrl(properties.getLoginUrl());
		//系统主页：登录成功后跳转路径
        filterFactoryBean.setSuccessUrl(properties.getSuccessUrl());
        //异常页面：无权限时的跳转路径
        filterFactoryBean.setUnauthorizedUrl(properties.getUnauthorizedUrl());
		
		// 必须设置 SecurityManager
		filterFactoryBean.setSecurityManager(securityManager);
		// 拦截规则
		filterFactoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition.getFilterChainMap());
		
		return filterFactoryBean;
	}

	/**
	 * 权限控制过滤器 ：权限过滤链的入口
	 */
    @Bean(name = "filterShiroFilterRegistrationBean")
    @ConditionalOnMissingBean
    protected FilterRegistrationBean<AbstractShiroFilter> filterShiroFilterRegistrationBean() throws Exception {

        FilterRegistrationBean<AbstractShiroFilter> filterRegistrationBean = new FilterRegistrationBean<AbstractShiroFilter>();
        filterRegistrationBean.setFilter((AbstractShiroFilter) shiroFilterFactoryBean().getObject());
        filterRegistrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);

        return filterRegistrationBean;
    }
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
}
