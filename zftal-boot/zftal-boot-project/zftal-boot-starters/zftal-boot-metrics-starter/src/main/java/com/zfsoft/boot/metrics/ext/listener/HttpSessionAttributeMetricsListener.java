package com.zfsoft.boot.metrics.ext.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.codahale.metrics.MetricRegistry;
import com.zfsoft.boot.metrics.ext.MetricsFactory;

/**
 * 
 * @className	： HttpSessionAttributeMetricsListener
 * @description	：Session属性绑定、移除、更新速率监控
 * @author 		：万大龙（743）
 * @date		： 2017年11月24日 上午10:00:09
 * @version 	V1.0
 */
public class HttpSessionAttributeMetricsListener implements HttpSessionAttributeListener {

	/**
     * 实例化一个registry，最核心的一个模块，相当于一个应用程序的metrics系统的容器，维护一个Map
     */
	protected MetricRegistry registry = MetricsFactory.getMetricRegistry("http-session-attribute");
	
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {

		String prefix = MetricRegistry.name(this.getClass(), event.getSession().getServletContext().getContextPath(), "session", "attributeAdded" );
		registry.meter(prefix).mark();
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		
		String prefix = MetricRegistry.name(this.getClass(), event.getSession().getServletContext().getContextPath(), "session", "attributeRemoved" );
		registry.meter(prefix).mark();

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {

		String prefix = MetricRegistry.name(this.getClass(), event.getSession().getServletContext().getContextPath(), "session", "attributeReplaced" );
		registry.meter(prefix).mark();

	}

}
