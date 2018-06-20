package com.zfsoft.boot.metrics.factory.support;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.ServletContextAttributeExporter;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.servlets.HealthCheckServlet;
import com.codahale.metrics.servlets.MetricsServlet;
import com.zfsoft.boot.metrics.EnableServletMetrics;

@Configuration
@ConditionalOnClass({ EnableServletMetrics.class,MetricsServlet.class, HealthCheckServlet.class})
public class MetricsServletRegistrar {

	/**
	 * 
	 * @description	： 通过该方式将metricRegistry注入到对应的属性值中以便各个组件使用
	 * @author 		： <a href="https://github.com/vindell">vindell</a>
	 * @date 		：2017年11月21日 下午8:18:50
	 * @param registry
	 * @return
	 */
	@Bean("servletsAttributeExporter")
	public ServletContextAttributeExporter servletsAttributeExporter(MetricRegistry registry) {
		
		ServletContextAttributeExporter attributeExporter = new ServletContextAttributeExporter();
		
		Map<String, Object> attributes = new HashMap<String, Object>();
		
		attributes.put(MetricsServlet.METRICS_REGISTRY, registry);
		attributes.put(HealthCheckServlet.HEALTH_CHECK_REGISTRY, registry);
		
		attributeExporter.setAttributes(attributes);
		
		return attributeExporter;
	}
 
	
}