package com.zfsoft.boot.metrics.factory.support;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionListener;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.ServletContextAttributeExporter;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.servlet.InstrumentedFilter;
import com.zfsoft.boot.metrics.EnableInstrumentedMetrics;
import com.zfsoft.boot.metrics.ext.listener.HttpServletContextAttributeMetricsListener;
import com.zfsoft.boot.metrics.ext.listener.HttpServletRequestAttributeMetricsListener;
import com.zfsoft.boot.metrics.ext.listener.HttpServletRequestMetricsListener;
import com.zfsoft.boot.metrics.ext.listener.HttpSessionAttributeMetricsListener;
import com.zfsoft.boot.metrics.ext.listener.HttpSessionMetricsListener;

@Configuration
@ConditionalOnClass({ EnableInstrumentedMetrics.class })
public class MetricsInstrumentedRegistrar {

	/**
	 * 
	 * @description	： 通过该方式将metricRegistry注入到对应的属性值中以便各个组件使用
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月24日 上午10:00:39
	 * @param registry
	 * @return
	 */
	@Bean("filterAttributeExporter")
	public ServletContextAttributeExporter filterAttributeExporter(MetricRegistry registry) {
		
		ServletContextAttributeExporter attributeExporter = new ServletContextAttributeExporter();
		
		Map<String, Object> attributes = new HashMap<String, Object>();
		
		attributes.put(InstrumentedFilter.REGISTRY_ATTRIBUTE, registry);
		
		attributeExporter.setAttributes(attributes);
		
		return attributeExporter;
	}
 
	@Bean
	public FilterRegistrationBean instrumentedFilter() {
		
		InstrumentedFilter filter = new InstrumentedFilter();
		
		
		//<property name="name-prefix" ref="authentication"></property>
		
		return new FilterRegistrationBean(filter);
	}

	@Bean
	public ServletListenerRegistrationBean<ServletContextAttributeListener> httpServletContextAttributeMetricsListener() {
		HttpServletContextAttributeMetricsListener linstener = new HttpServletContextAttributeMetricsListener();
		return new ServletListenerRegistrationBean<ServletContextAttributeListener>(linstener);
	}
	
	@Bean
	public ServletListenerRegistrationBean<ServletRequestAttributeListener> httpServletRequestAttributeMetricsListener() {
		HttpServletRequestAttributeMetricsListener linstener = new HttpServletRequestAttributeMetricsListener();
		return new ServletListenerRegistrationBean<ServletRequestAttributeListener>(linstener);
	}
	
	@Bean
	public ServletListenerRegistrationBean<ServletRequestListener> httpServletRequestMetricsListener() {
		HttpServletRequestMetricsListener linstener = new HttpServletRequestMetricsListener();
		return new ServletListenerRegistrationBean<ServletRequestListener>(linstener);
	}
	
	@Bean
	public ServletListenerRegistrationBean<HttpSessionAttributeMetricsListener> httpSessionAttributeMetricsListener() {
		HttpSessionAttributeMetricsListener linstener = new HttpSessionAttributeMetricsListener();
		return new ServletListenerRegistrationBean<HttpSessionAttributeMetricsListener>(linstener);
	}
	
	@Bean
	public ServletListenerRegistrationBean<HttpSessionListener> httpSessionMetricsListener() {
		HttpSessionMetricsListener linstener = new HttpSessionMetricsListener();
		return new ServletListenerRegistrationBean<HttpSessionListener>(linstener);
	}
	
}