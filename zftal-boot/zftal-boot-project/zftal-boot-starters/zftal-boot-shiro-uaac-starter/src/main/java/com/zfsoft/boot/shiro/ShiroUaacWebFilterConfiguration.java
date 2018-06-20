/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.shiro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zfsoft.authz.uaac.shiro.filter.UaacShiroSsoLogoutShiroFilter;
import com.zfsoft.authz.uaac.shiro.filter.UaacSsoShiroFilter;

/**
 * 
 * @className	： ShiroUaacWebFilterConfiguration
 * @description	： 创建并注册uaac认证相关的Filter
 * @author 		：魏广跃（1571）
 * @date		： 2018年5月16日 上午11:19:56
 * @version 	V1.0
 */
@Configuration
@AutoConfigureBefore( name = {
	"org.apache.shiro.spring.config.web.autoconfigure.ShiroWebFilterConfiguration",  // shiro-spring-boot-web-starter
	"com.zfsoft.boot.shiro.ShiroBizWebAutoConfiguration" // zftal-boot-shiro-starter
})
@ConditionalOnProperty(prefix = ShiroUaacProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ ShiroUaacProperties.class })
public class ShiroUaacWebFilterConfiguration {
	
	private static final Logger log = LoggerFactory.getLogger(ShiroUaacWebAutoConfiguration.class);

	@Autowired
	private ShiroUaacProperties shiroUaacProperties;
	
	/**
	 * @description	：uaac认证的登录Filter的Bean
	 * @author 		： 魏广跃（1571）
	 * @date 		：2018年5月16日 上午10:47:35
	 * @return
	 */
	@Bean(name="uaacSsoShiroFilter")
	public FilterRegistrationBean<UaacSsoShiroFilter> uaacSsoShiroFilter() {
		String filterName = "uaacSsoShiroFilter";
		UaacSsoShiroFilter filter = new UaacSsoShiroFilter();
		filter.setName(filterName);
		filter.setAuthServerUrl(this.shiroUaacProperties.getAuthServerUrl());
		filter.setSuccessUrl(this.shiroUaacProperties.getSuccessUrl());
		
		if(log.isDebugEnabled()) {
			log.debug("启用uaac认证，初始化并注册uaac的Filter Bean[name="+ filterName +",class="+ filter.getClass().getName() +"]");
		}
		
		FilterRegistrationBean<UaacSsoShiroFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(filter);
		filterRegistrationBean.setEnabled(false);
		filterRegistrationBean.setName(filterName);
		return filterRegistrationBean;
	}
	
	/**
	 * 
	 * @description	： uaac的注销的Filter的Bean
	 * @author 		： 魏广跃（1571）
	 * @date 		：2018年5月18日 上午11:00:00
	 * @return
	 */
	@Bean(name="uaacShiroSsoLogoutShiroFilter")
	public FilterRegistrationBean<UaacShiroSsoLogoutShiroFilter> uaacShiroSsoLogoutShiroFilter() {
		String filterName = "uaacShiroSsoLogoutShiroFilter";
		UaacShiroSsoLogoutShiroFilter filter = new UaacShiroSsoLogoutShiroFilter();
		filter.setName(filterName);
		filter.setLogoutUrl(this.shiroUaacProperties.getLogoutUrl());
		if(log.isDebugEnabled()) {
			log.debug("启用Zjucookie认证，初始化并注册Zjucookie的Filter Bean[name="+ filterName +",class="+ filter.getClass().getName() +"]");
		}
		
		FilterRegistrationBean<UaacShiroSsoLogoutShiroFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(filter);
		filterRegistrationBean.setEnabled(false);
		filterRegistrationBean.setName(filterName);
		return filterRegistrationBean;
	}
	
}
