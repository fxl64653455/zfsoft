package com.zfsoft.boot.sitemesh3;

import org.sitemesh.SiteMeshContext;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import com.zfsoft.basicutils.StringUtils;
import com.zfsoft.boot.sitemesh3.ext.config.ParamConfigurableSiteMeshFilter;
import com.zfsoft.web.ExtParameter;

@Configuration
@ConditionalOnClass({ SiteMeshContext.class })
@ConditionalOnProperty(prefix = Sitemesh3Properties.PREFIX, value = "enabled", havingValue = "true", matchIfMissing = false)
@EnableConfigurationProperties({ Sitemesh3Properties.class })
@AutoConfigureAfter({ WebMvcAutoConfiguration.class, FreeMarkerAutoConfiguration.class,
		ThymeleafAutoConfiguration.class })
public class Sitemesh3AutoConfiguration implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Bean
	public FilterRegistrationBean<ParamConfigurableSiteMeshFilter> siteMeshFilter(Sitemesh3Properties properties) {

		FilterRegistrationBean<ParamConfigurableSiteMeshFilter> filterRegistrationBean = new FilterRegistrationBean<ParamConfigurableSiteMeshFilter>();

		filterRegistrationBean.setFilter(new ParamConfigurableSiteMeshFilter(getApplicationContext(), properties));
		// 用于获取装饰器名称参数的取值Key
		filterRegistrationBean.addInitParameter(ExtParameter.REQUEST_DECORATOR_NAME.getName(), properties.getParamName());
		// 不同参数对应的装饰页信息
		filterRegistrationBean.addInitParameter(ExtParameter.REQUEST_DECORATOR_PATH.getName(), properties.getLayoutFile());
		// 设置初始参数
		filterRegistrationBean.addUrlPatterns(StringUtils.tokenizeToStringArray(properties.getUrlPatterns()));
		// 是否每个请求都重新加载配置文件：默认 true,当值为true且配置文件有改动则会重新加载配置文件
		filterRegistrationBean.addInitParameter(Sitemesh3Param.PARAM_AUTORELOAD_KEY,
				Boolean.toString(properties.isAutoReload()));
		// 基于XML的配置文件路径
		filterRegistrationBean.addInitParameter(Sitemesh3Param.PARAM_CONFIGFILE_KEY, properties.getConfigFile());
		filterRegistrationBean.setOrder(Ordered.LOWEST_PRECEDENCE - 10);
		
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
