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

import com.zfsoft.authz.ticket.shiro.filter.ZFTicketAuthcShiroFilter;
import com.zfsoft.authz.ticket.shiro.filter.ZFTicketLoginShiroFilter;
import com.zfsoft.authz.ticket.shiro.filter.ZFTicketTokenShiroFilter;
import com.zfsoft.authz.ticket.utils.TicketTokenUtils;

/**
 * 
 * @className	： ShiroTicketWebFilterConfiguration
 * @description	： 创建并注册Ticket认证相关的Filter
 * @author 		：魏广跃（1571）
 * @date		： 2018年5月16日 上午11:19:56
 * @version 	V1.0
 */
@Configuration
@AutoConfigureBefore( name = {
	"org.apache.shiro.spring.config.web.autoconfigure.ShiroWebFilterConfiguration",  // shiro-spring-boot-web-starter
	"com.zfsoft.boot.shiro.ShiroBizWebAutoConfiguration" // zftal-boot-shiro-starter
})
@ConditionalOnProperty(prefix = ShiroTicketProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ ShiroTicketProperties.class })
public class ShiroTicketWebFilterConfiguration {
	
	private static final Logger log = LoggerFactory.getLogger(ShiroTicketWebAutoConfiguration.class);

	@Autowired
	private ShiroTicketProperties shiroTicketProperties;
	
	/**
	 * @description	： Ticket认证的登录Filter的Bean
	 * @author 		： 魏广跃（1571）
	 * @date 		：2018年5月16日 上午10:47:35
	 * @return
	 */
	@Bean(name="ticketLoginFilter")
	public FilterRegistrationBean<ZFTicketLoginShiroFilter> ticketLoginFilter(TicketTokenUtils ticketTokenUtils) {
		String filterName = "ticketLoginFilter";
		ZFTicketLoginShiroFilter zfTicketLoginShiroFilter = new ZFTicketLoginShiroFilter();
		zfTicketLoginShiroFilter.setName("ticketLoginFilter");
		zfTicketLoginShiroFilter.setLoginUrl(this.shiroTicketProperties.getLoginUrl());
		zfTicketLoginShiroFilter.setSuccessUrl(this.shiroTicketProperties.getSuccessUrl());
		zfTicketLoginShiroFilter.setTicketTokenUtils(ticketTokenUtils);
		
		if(log.isDebugEnabled()) {
			log.debug("启用Ticket认证，初始化并注册Ticket的Filter Bean[name="+ filterName +",class="+ zfTicketLoginShiroFilter.getClass().getName() +"]");
		}
		
		FilterRegistrationBean<ZFTicketLoginShiroFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(zfTicketLoginShiroFilter);
		filterRegistrationBean.setEnabled(false);
		filterRegistrationBean.setName(filterName);
		return filterRegistrationBean;
	}
	
	/**
	 * 
	 * @description	： Ticket认证的AuthcFilter的Bean
	 * @author 		： 魏广跃（1571）
	 * @date 		：2018年5月16日 上午10:49:42
	 * @return
	 */
	@Bean(name="ticketAuthcFilter")
	public FilterRegistrationBean<ZFTicketAuthcShiroFilter> ticketAuthcFilter(TicketTokenUtils ticketTokenUtils) {
		String filterName = "ticketAuthcFilter";
		ZFTicketAuthcShiroFilter zfTicketAuthcShiroFilter = new ZFTicketAuthcShiroFilter();
		zfTicketAuthcShiroFilter.setName("ticketAuthcFilter");
		zfTicketAuthcShiroFilter.setLoginUrl(this.shiroTicketProperties.getLoginUrl());
		zfTicketAuthcShiroFilter.setSuccessUrl(this.shiroTicketProperties.getSuccessUrl());
		zfTicketAuthcShiroFilter.setTicketTokenUtils(ticketTokenUtils);
		
		if(log.isDebugEnabled()) {
			log.debug("启用Ticket认证，初始化并注册Ticket的Filter Bean[name="+ filterName +",class="+ zfTicketAuthcShiroFilter.getClass().getName() +"]");
		}
		
		FilterRegistrationBean<ZFTicketAuthcShiroFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(zfTicketAuthcShiroFilter);
		filterRegistrationBean.setEnabled(false);
		filterRegistrationBean.setName(filterName);
		return filterRegistrationBean;
	}
	
	/**
	 * @description	： Ticket认证的TokenFilter的Bean
	 * @author 		： 魏广跃（1571）
	 * @date 		：2018年5月16日 上午10:50:35
	 * @return
	 */
	@Bean(name="ticketTokenFilter")
	public FilterRegistrationBean<ZFTicketTokenShiroFilter> ticketTokenFilter(TicketTokenUtils ticketTokenUtils) {
		String filterName = "ticketTokenFilter";
		ZFTicketTokenShiroFilter zfTicketTokenShiroFilter = new ZFTicketTokenShiroFilter();
		zfTicketTokenShiroFilter.setName(filterName);
		zfTicketTokenShiroFilter.setTicketTokenUtils(ticketTokenUtils);
		
		if(log.isDebugEnabled()) {
			log.debug("启用Ticket认证，初始化并注册Ticket的Filter Bean[name="+filterName+",class="+ zfTicketTokenShiroFilter.getClass().getName() +"]");
		}
		
		FilterRegistrationBean<ZFTicketTokenShiroFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(zfTicketTokenShiroFilter);
		filterRegistrationBean.setEnabled(false);
		filterRegistrationBean.setName(filterName);
		
		return filterRegistrationBean;
	}
	
}
