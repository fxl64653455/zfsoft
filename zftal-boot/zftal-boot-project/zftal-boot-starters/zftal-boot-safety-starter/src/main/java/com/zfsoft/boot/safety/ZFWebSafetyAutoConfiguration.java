package com.zfsoft.boot.safety;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;

import org.owasp.html.PolicyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.zfsoft.basicutils.StringUtils;
import com.zfsoft.boot.safety.property.HttpCorsFilterProperties;
import com.zfsoft.boot.safety.property.HttpRequestHeaderFilterProperties;
import com.zfsoft.boot.safety.property.HttpRequestMethodsFilterProperties;
import com.zfsoft.boot.safety.property.HttpRichtextXssFilterProperties;
import com.zfsoft.boot.safety.property.HttpXssFilterProperties;
import com.zfsoft.boot.safety.property.Ini;
import com.zfsoft.cache.core.CacheManager;
import com.zfsoft.safety.xss.HttpServletRequestCorsFilter;
import com.zfsoft.safety.xss.HttpServletRequestHeaderFilter;
import com.zfsoft.safety.xss.HttpServletRequestMethodControlFilter;
import com.zfsoft.safety.xss.HttpServletRequestRichTextXssFilter;
import com.zfsoft.safety.xss.HttpServletRequestXssFilter;
import com.zfsoft.web.servlet.filter.AbstractPathMatchFilter;
import com.zfsoft.web.servlet.filter.AbstractRouteableFilter;
import com.zfsoft.web.servlet.filter.Nameable;
import com.zfsoft.web.servlet.filter.OncePerRequestFilter;
import com.zfsoft.web.servlet.filter.cache.CacheSupport;
import com.zfsoft.web.servlet.filter.chain.FilterChainManager;
import com.zfsoft.web.servlet.filter.chain.FilterChainResolver;
import com.zfsoft.web.servlet.filter.chain.impl.DefaultFilterChainManager;
import com.zfsoft.web.servlet.filter.chain.impl.PathMatchingFilterChainResolver;
import com.zfsoft.web.servlet.filter.impl.HttpServletRequestNoneFilter;

@Configuration
@ConditionalOnClass({ OncePerRequestFilter.class })
@ConditionalOnProperty(prefix = ZFWebSafetyProperties.PREFIX, value = "enabled", havingValue = "true", matchIfMissing = false)
@EnableConfigurationProperties({ ZFWebSafetyProperties.class })
public class ZFWebSafetyAutoConfiguration implements ApplicationContextAware {

	private ApplicationContext applicationContext;
	
	/**
	 * 
	 * @description	： 不做任何操作的拦截器
	 * @author 		： 万大龙（743）
	 * @date 		：2017年9月18日 下午2:29:10
	 * @return
	 */
	@Bean("noneControl")
	@ConditionalOnMissingBean(name = "noneControl")
	public FilterRegistrationBean noneFilter() {
		/* 
		 * 自定义Filter通过@Bean注解后，被Spring Boot自动注册到了容器的Filter chain中，这样导致的结果是，所有URL都会被自定义Filter过滤，
		 * 而不是责任链配置的一部分URL。下面方式可以解决该问题
		 */
		FilterRegistrationBean registration = new FilterRegistrationBean(new HttpServletRequestNoneFilter()); 
	    registration.setEnabled(false); 
	    return registration;
	}
	
	/**
	 * 
	 * @description	： http跨域安全配置拦截器
	 * @author 		： 万大龙（743）
	 * @date 		：2017年9月18日 下午2:25:18
	 * @param safetyProperties
	 * @return
	 */
	@Bean("httpCrosControl")
	@ConditionalOnMissingBean(name = "httpCrosControl")
	@ConditionalOnProperty(prefix = ZFWebSafetyProperties.PREFIX + ".http-cors-filter", value = "enabled", havingValue = "true")
	public FilterRegistrationBean httpCrosFilter(ZFWebSafetyProperties safetyProperties) {
		HttpCorsFilterProperties properties = safetyProperties.getHttpCorsFilter();
		HttpServletRequestCorsFilter filter = new HttpServletRequestCorsFilter();
		filter.setEnabled(properties.getEnabled());
		filter.setAllowCredentials(properties.getAccessControlAllowCredentials());
		filter.setAllowOrigin(properties.getAccessControlAllowOrigin());
		filter.setAllowHeaders(properties.getAccessControlAllowHeaders());
		filter.setAllowMethods(properties.getAccessControlAllowMethods());
		
		/* 
		 * 自定义Filter通过@Bean注解后，被Spring Boot自动注册到了容器的Filter chain中，这样导致的结果是，所有URL都会被自定义Filter过滤，
		 * 而不是责任链配置的一部分URL。下面方式可以解决该问题
		 */
		FilterRegistrationBean registration = new FilterRegistrationBean(filter); 
	    registration.setEnabled(false); 
	    return registration;
	}

	
	/**
	 * 
	 * @description	： http响应头安全配置拦截器
	 * @author 		： 万大龙（743）
	 * @date 		：2017年9月18日 下午2:25:18
	 * @param safetyProperties
	 * @return
	 */
	@Bean("httpHeaderControl")
	@ConditionalOnMissingBean(name = "httpHeaderControl")
	@ConditionalOnProperty(prefix = ZFWebSafetyProperties.PREFIX + ".http-header-filter", value = "enabled", havingValue = "true")
	public FilterRegistrationBean httpHeaderFilter(ZFWebSafetyProperties safetyProperties) {
		HttpRequestHeaderFilterProperties properties = safetyProperties.getHttpHeaderFilter();
		HttpServletRequestHeaderFilter filter = new HttpServletRequestHeaderFilter();
		filter.setEnabled(properties.getEnabled());
		filter.setX_content_type_options(properties.getXContentTypeOptions());
		filter.setX_frame_options(properties.getXFrameOptions());
		filter.setX_xss_protection(properties.getXXssProtection());
		
		/* 
		 * 自定义Filter通过@Bean注解后，被Spring Boot自动注册到了容器的Filter chain中，这样导致的结果是，所有URL都会被自定义Filter过滤，
		 * 而不是责任链配置的一部分URL。下面方式可以解决该问题
		 */
		FilterRegistrationBean registration = new FilterRegistrationBean(filter); 
	    registration.setEnabled(false); 
	    return registration;
	}
	
	/**
	 * 
	 * @description	： http请求方法拦截器
	 * @author 		： 万大龙（743）
	 * @date 		：2017年9月18日 下午2:25:11
	 * @param safetyProperties
	 * @return
	 */
	@Bean("httpMethodControl")
	@ConditionalOnMissingBean(name = "httpMethodControl")
	@ConditionalOnProperty(prefix = ZFWebSafetyProperties.PREFIX + ".http-methods-filter", value = "enabled", havingValue = "true")
	public FilterRegistrationBean httpMethodControlFilter(ZFWebSafetyProperties safetyProperties) {
		HttpRequestMethodsFilterProperties properties = safetyProperties.getHttpMethodsFilter();
		HttpServletRequestMethodControlFilter filter = new HttpServletRequestMethodControlFilter();
		filter.setEnabled(properties.getEnabled());
		filter.setAllowedHTTPMethods(properties.getAllowedHttpMethods());
		/* 
		 * 自定义Filter通过@Bean注解后，被Spring Boot自动注册到了容器的Filter chain中，这样导致的结果是，所有URL都会被自定义Filter过滤，
		 * 而不是责任链配置的一部分URL。下面方式可以解决该问题
		 */
		FilterRegistrationBean registration = new FilterRegistrationBean(filter); 
	    registration.setEnabled(false); 
	    return registration;
	}
	
	/**
	 * 
	 * @description	： 基于 AntiSamy组件的XSS(Cross Site Scripting)，即跨站脚本攻击请求过滤器（主要用于富文本）
	 * @author 		： 万大龙（743）
	 * @date 		：2017年9月18日 下午2:31:45
	 * @param safetyProperties
	 * @return
	 */
	@Bean("httpRichtextXssControl")
	@ConditionalOnMissingBean(name = "httpRichtextXssControl")
	@ConditionalOnProperty(prefix = ZFWebSafetyProperties.PREFIX + ".http-richtext-xss-filter", value = "enabled", havingValue = "true")
	public FilterRegistrationBean httpRichtextXssFilter(ZFWebSafetyProperties safetyProperties) {
		
		HttpRichtextXssFilterProperties properties = safetyProperties.getHttpRichtextXssFilter();
		HttpServletRequestRichTextXssFilter filter = new HttpServletRequestRichTextXssFilter();
		filter.setEnabled(properties.getEnabled());
		// 扫描器类型，0：DOM类型扫描器,1:SAX类型扫描器；两者的区别如同XML解析中DOM解析与Sax解析区别相同，实际上就是对两种解析方式的实现 
		filter.setScanType(properties.getScanType());
		// 默认的防XSS攻击的规则配置 -->
		filter.setDefaultPolicy(properties.getDefaultPolicy());
		// 进行过滤请求路径的正则匹配表达式，匹配的路径会被检测XSS;多个表达式可以用",; \t\n"中任意字符分割
		filter.setIncludePatterns(properties.getIncludePatterns());
		// 不进行过滤请求路径的正则匹配表达式，匹配的路径不会被检测XSS;多个表达式可以用",; \t\n"中任意字符分割 
		filter.setExcludePatterns(properties.getExcludePatterns());
		// XSS攻击的模块对应的规则配置:通过该配置可实现不同路径采用不同的处理规则 
		if(!StringUtils.isEmpty(properties.getPolicyDefinitions())) {
			filter.setPolicyDefinitions(properties.getPolicyDefinitions());
		}else {
			filter.setPolicyMappings(properties.getPolicyMappings());
		}
		/* 
		 * 自定义Filter通过@Bean注解后，被Spring Boot自动注册到了容器的Filter chain中，这样导致的结果是，所有URL都会被自定义Filter过滤，
		 * 而不是责任链配置的一部分URL。下面方式可以解决该问题
		 */
		FilterRegistrationBean registration = new FilterRegistrationBean(filter); 
	    registration.setEnabled(false); 
	    return registration;
	}
	
	/**
	 * 
	 * @description	： 基于owasp-java-html-sanitizer的XSS(Cross Site Scripting)，即跨站脚本攻击请求过滤器 
	 * @author 		： 万大龙（743）
	 * @date 		：2017年9月18日 下午2:58:09
	 * @param safetyProperties
	 * @return
	 */
	@Bean("httpXssControl")
	@ConditionalOnMissingBean(name = "httpXssControl")
	@ConditionalOnProperty(prefix = ZFWebSafetyProperties.PREFIX + ".http-xss-filter", value = "enabled", havingValue = "true")
	public FilterRegistrationBean httpXssFilter(ZFWebSafetyProperties safetyProperties,
			@Autowired(required = false) PolicyFactory policy) {
		HttpXssFilterProperties properties = safetyProperties.getHttpXssFilter();
		HttpServletRequestXssFilter filter = new HttpServletRequestXssFilter();
		filter.setEnabled(properties.getEnabled());
		filter.setPolicyHeaders(properties.getPolicyHeaders());
		//Xss检查策略工厂
		if(policy != null) {
			filter.setPolicy(policy);
		}
		
		/* 
		 * 自定义Filter通过@Bean注解后，被Spring Boot自动注册到了容器的Filter chain中，这样导致的结果是，所有URL都会被自定义Filter过滤，
		 * 而不是责任链配置的一部分URL。下面方式可以解决该问题
		 */
		FilterRegistrationBean registration = new FilterRegistrationBean(filter); 
	    registration.setEnabled(false); 
	    return registration;
	}
	
	
	/**
	 * Filter实现集合
	 */
	@Bean("httpFilters")
	public Map<String, AbstractPathMatchFilter> httpFilters() {

		Map<String, AbstractPathMatchFilter> httpFilters = new LinkedHashMap<String, AbstractPathMatchFilter>();

		Map<String, FilterRegistrationBean> beansOfType = getApplicationContext().getBeansOfType(FilterRegistrationBean.class);
		if (!ObjectUtils.isEmpty(beansOfType)) {
			Iterator<Entry<String, FilterRegistrationBean>> ite = beansOfType.entrySet().iterator();
			while (ite.hasNext()) {
				Entry<String, FilterRegistrationBean> entry = ite.next();
				if (entry.getValue().getFilter() instanceof AbstractPathMatchFilter) {
					httpFilters.put(entry.getKey(), (AbstractPathMatchFilter) entry.getValue().getFilter());
				}
			}
		}

		return httpFilters;
	}
	
	
	/**
	 * <p>
	 *   <h3>zftal框架<h3>
	 *   说明：静态内部类
	 * <p>
	 * @author <a href="#">Zhangxiaobin[1036]<a>
	 * @version 2016年7月19日上午11:22:15
	 */
	private static class SpringZfsoftFilter extends AbstractRouteableFilter{

		public SpringZfsoftFilter(FilterChainResolver filterChainResolver) {
			super(filterChainResolver);
		}
	}
	
	/**
	 * 注册一个：FilterRegistrationBean,添加请求过滤规则
	 * @throws IOException 
	 */
	@Bean
	public FilterRegistrationBean zfsoftFilterRegistrationBean(ZFWebSafetyProperties safetyProperties,
			@Qualifier("httpFilters") Map<String, AbstractPathMatchFilter> httpFilters,
			@Autowired(required = false) CacheManager cacheManager) throws IOException {
		
		Map<String, String> handlerChainDefinitionMap = null;
		if (StringUtils.isNotEmpty(safetyProperties.getDefinitions())) {
			handlerChainDefinitionMap = this.parseHandlerChainDefinitions(safetyProperties.getDefinitions());
		} else if (!CollectionUtils.isEmpty(safetyProperties.getDefinitionMap())) {
			handlerChainDefinitionMap = safetyProperties.getDefinitionMap();
		}
		
		FilterChainManager manager = createFilterChainManager(httpFilters, handlerChainDefinitionMap, cacheManager);
        PathMatchingFilterChainResolver chainResolver = new PathMatchingFilterChainResolver();
        chainResolver.setFilterChainManager(manager);
		
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new SpringZfsoftFilter(chainResolver));
		// 添加过滤规则.
		if (StringUtils.isEmpty(safetyProperties.getUrlPatterns())) {
			safetyProperties.setUrlPatterns("/*");
		}

		return filterRegistrationBean;
	}


	protected Map<String, String> parseHandlerChainDefinitions(String definitions) throws IOException {
		Ini ini = new Ini();
		ini.load(definitions);
		Ini.Section section = ini.getSection("urls");
		if (CollectionUtils.isEmpty(section)) {
			section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		}
		return section;
	}
	
	protected FilterChainManager createFilterChainManager(
			Map<String, AbstractPathMatchFilter> httpFilters,
			Map<String, String> handlerChainDefinitionMap,
			CacheManager cacheManager) {
        FilterChainManager manager = new DefaultFilterChainManager();
        if (!CollectionUtils.isEmpty(httpFilters)) {
            for (Entry<String, AbstractPathMatchFilter> entry : httpFilters.entrySet()) {
                String name = entry.getKey();
                Filter filter = entry.getValue();
                if (filter instanceof Nameable) {
                    ((Nameable) filter).setName(name);
                }
                
                //try to set cachemanager if possiable
                if(filter instanceof CacheSupport){
                	((CacheSupport)filter).setCacheManager(cacheManager);
                }
                
                manager.addFilter(name, filter, false);
            }
        }

        Map<String, String> chains = handlerChainDefinitionMap;
        if (!CollectionUtils.isEmpty(chains)) {
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue();
                manager.createChain(url, chainDefinition);
            }
        }

        return manager;
    }
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}
