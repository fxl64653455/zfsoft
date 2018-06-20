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

import com.zfsoft.authz.zjucookie.shiro.filter.ZjuCookieAuthcShiroFilter;
import com.zfsoft.authz.zjucookie.shiro.filter.ZjuCookieLogoutShiroFilter;
import com.zfsoft.authz.zjucookie.utils.CookieSsoApi;

/**
 * 
 * @className	： ShiroZjucookieWebFilterConfiguration
 * @description	： 创建并注册zjuCookie相关的Filter
 * @author 		：魏广跃（1571）
 * @date		： 2018年5月16日 上午11:19:56
 * @version 	V1.0
 */
@Configuration
@AutoConfigureBefore( name = {
	"org.apache.shiro.spring.config.web.autoconfigure.ShiroWebFilterConfiguration",  // shiro-spring-boot-web-starter
	"com.zfsoft.boot.shiro.ShiroBizWebAutoConfiguration" // zftal-boot-shiro-starter
})
@ConditionalOnProperty(prefix = ShiroZjucookieProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ ShiroZjucookieProperties.class })
public class ShiroZjucookieWebFilterConfiguration {
	
	private static final Logger log = LoggerFactory.getLogger(ShiroZjucookieWebAutoConfiguration.class);

	@Autowired
	private ShiroZjucookieProperties shiroZjucookieProperties;
	
	/**
	 * @description	：zjuCookie认证的登录Filter的Bean
	 * @author 		： 魏广跃（1571）
	 * @date 		：2018年5月16日 上午10:47:35
	 * @return
	 */
	@Bean(name="zjuCookieAuthcShiroFilter")
	public FilterRegistrationBean<ZjuCookieAuthcShiroFilter> zjuCookieAuthcShiroFilter(CookieSsoApi cookieSsoApi) {
		String filterName = "zjuCookieAuthcShiroFilter";
		ZjuCookieAuthcShiroFilter filter = new ZjuCookieAuthcShiroFilter();
		filter.setName(filterName);
		filter.setLoginUrl(this.shiroZjucookieProperties.getLoginUrl());
		filter.setSuccessUrl(this.shiroZjucookieProperties.getSuccessUrl());
		filter.setCookieSsoApi(cookieSsoApi);
		
		if(log.isDebugEnabled()) {
			log.debug("启用Zjucookie认证，初始化并注册Zjucookie的Filter Bean[name="+ filterName +",class="+ filter.getClass().getName() +"]");
		}
		
		FilterRegistrationBean<ZjuCookieAuthcShiroFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(filter);
		filterRegistrationBean.setEnabled(false);
		filterRegistrationBean.setName(filterName);
		return filterRegistrationBean;
	}
	
	/**
	 * 
	 * @description	： zjuCookie的注销的Filter的Bean
	 * @author 		： 魏广跃（1571）
	 * @date 		：2018年5月18日 上午11:00:00
	 * @return
	 */
	@Bean(name="zjuCookieLogoutShiroFilter")
	public FilterRegistrationBean<ZjuCookieLogoutShiroFilter> zjuCookieLogoutShiroFilter(CookieSsoApi cookieSsoApi) {
		String filterName = "zjuCookieLogoutShiroFilter";
		ZjuCookieLogoutShiroFilter filter = new ZjuCookieLogoutShiroFilter();
		filter.setName(filterName);
		filter.setCookieSsoApi(cookieSsoApi);
		
		if(log.isDebugEnabled()) {
			log.debug("启用Zjucookie认证，初始化并注册Zjucookie的Filter Bean[name="+ filterName +",class="+ filter.getClass().getName() +"]");
		}
		
		FilterRegistrationBean<ZjuCookieLogoutShiroFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(filter);
		filterRegistrationBean.setEnabled(false);
		filterRegistrationBean.setName(filterName);
		return filterRegistrationBean;
	}
	
}
