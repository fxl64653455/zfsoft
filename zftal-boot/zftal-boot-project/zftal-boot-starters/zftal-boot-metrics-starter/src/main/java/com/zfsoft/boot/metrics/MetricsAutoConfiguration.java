package com.zfsoft.boot.metrics;

import java.util.Iterator;
import java.util.Map.Entry;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.MetricSet;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.zfsoft.boot.metrics.ext.MetricsFactory;

/**
 * 
 * @className	： MetricsAutoConfiguration
 * @description	： TODO(描述这个类的作用)
 * @author 		：万大龙（743）
 * @date		： 2017年11月24日 上午9:42:43
 * @version 	V1.0
 */
@Configuration
@ConditionalOnClass(MetricRegistry.class)
@EnableConfigurationProperties(MetricsProperties.class)
public class MetricsAutoConfiguration implements DisposableBean {

	//private Logger logger = LoggerFactory.getLogger(MetricsAutoConfiguration.class);

	@Bean
	@ConditionalOnMissingBean
	public MetricRegistry metricRegistry(MetricsProperties properties) {
		MetricRegistry metricRegistry = new MetricRegistry();
		
		Iterator<Entry<String, String>>  ite = properties.getMetrics().entrySet().iterator();
		while (ite.hasNext()) {
			
			Entry<String, String> entry = ite.next();
			final String name = entry.getKey();
			try {
				
				AbstractBeanDefinition metricDef = BeanDefinitionReaderUtils.createBeanDefinition(MetricSet.class.getName(), entry.getValue(), this.getClass().getClassLoader());
				
				Object metric = BeanUtils.instantiateClass(metricDef.getBeanClass());
				
				if (StringUtils.hasText(name) && (metric instanceof MetricSet)) {
					metricRegistry.register(name, (MetricSet) metric);
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return metricRegistry;
	}
	
	@Bean
	@ConditionalOnMissingBean
	public HealthCheckRegistry healthCheckRegistry(MetricsProperties properties) {
		return new HealthCheckRegistry();
	}
	
	@Bean
	@ConditionalOnMissingBean
	public MetricsFactory metricsFactory(MetricRegistry metricRegistry) {
		MetricsFactory metricsFactory = new MetricsFactory();
		if(metricRegistry != null) {
			metricsFactory.setRegistry(metricRegistry);
		}
		return metricsFactory;
	}
	
	
	
	
	@Override
	public void destroy() throws Exception {
		 

	}

}