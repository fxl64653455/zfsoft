package com.zfsoft.boot.metrics.ext.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

import com.codahale.metrics.MetricRegistry;
import com.zfsoft.boot.metrics.ext.MetricsFactory;

/**
 * 
 * @className	： HttpServletContextAttributeMetricsListener
 * @description	： ServletContext上下文属性绑定、移除、更新速率监控
 * @author 		：万大龙（743）
 * @date		： 2017年11月24日 上午9:59:49
 * @version 	V1.0
 */
public class HttpServletContextAttributeMetricsListener implements ServletContextAttributeListener {

	/**
     * 实例化一个registry，最核心的一个模块，相当于一个应用程序的metrics系统的容器，维护一个Map
     */
	protected MetricRegistry registry = MetricsFactory.getMetricRegistry("http-servletcontext-attribute");
	
	@Override
	public void attributeAdded(ServletContextAttributeEvent event) {
		
		String prefix = MetricRegistry.name(this.getClass(), event.getServletContext().getContextPath(), "ServletContext", "attributeAdded" );
		registry.meter(prefix).mark();

	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent event) {

		String prefix = MetricRegistry.name(this.getClass(), event.getServletContext().getContextPath(), "ServletContext", "attributeRemoved" );
		registry.meter(prefix).mark();

	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent event) {
		
		String prefix = MetricRegistry.name(this.getClass(), event.getServletContext().getContextPath(), "ServletContext", "attributeReplaced" );
		registry.meter(prefix).mark();

	}
	
}
