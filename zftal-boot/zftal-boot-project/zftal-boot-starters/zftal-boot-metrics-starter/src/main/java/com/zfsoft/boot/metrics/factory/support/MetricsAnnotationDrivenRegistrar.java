package com.zfsoft.boot.metrics.factory.support;

import org.springframework.aop.framework.ProxyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.zfsoft.boot.metrics.EnableAnnotationMetrics;
import com.zfsoft.boot.metrics.MetricsProperties;
import com.zfsoft.boot.metrics.ext.MetricsBeanPostProcessorFactory;
import com.zfsoft.boot.metrics.factory.config.AdvisingBeanPostProcessor;
import com.zfsoft.boot.metrics.factory.config.CachedGaugeAnnotationBeanPostProcessor;
import com.zfsoft.boot.metrics.factory.config.GaugeFieldAnnotationBeanPostProcessor;
import com.zfsoft.boot.metrics.factory.config.GaugeMethodAnnotationBeanPostProcessor;
import com.zfsoft.boot.metrics.factory.config.HealthCheckBeanPostProcessor;
import com.zfsoft.boot.metrics.factory.config.MetricAnnotationBeanPostProcessor;

/**
 * 
 * @className	： MetricsAnnotationDrivenRegistrar
 * @description	： Registers BeanPostProcessors with Spring which proxy beans and capture metrics
 * @author 		：万大龙（743）
 * @date		： 2017年11月24日 上午10:00:55
 * @version 	V1.0
 */
@Configuration
@ConditionalOnClass(EnableAnnotationMetrics.class)
public class MetricsAnnotationDrivenRegistrar implements ImportBeanDefinitionRegistrar {

	@Autowired
	private MetricsProperties properties;
	
	@Bean
	public AdvisingBeanPostProcessor exceptionMeteredPostProcessor(MetricRegistry metricRegistry) {

		final ProxyConfig proxyConfig = new ProxyConfig();

		proxyConfig.setExposeProxy(properties.isExposeProxy());
		proxyConfig.setProxyTargetClass(properties.isProxyTargetClass());
		
		return MetricsBeanPostProcessorFactory.exceptionMetered(metricRegistry, proxyConfig);
	}
	
	@Bean
	public AdvisingBeanPostProcessor meteredPostProcessor(MetricRegistry metricRegistry) {

		final ProxyConfig proxyConfig = new ProxyConfig();

		proxyConfig.setExposeProxy(properties.isExposeProxy());
		proxyConfig.setProxyTargetClass(properties.isProxyTargetClass());
		
		return MetricsBeanPostProcessorFactory.metered(metricRegistry, proxyConfig);
	}
	
	@Bean
	public AdvisingBeanPostProcessor timedPostProcessor(MetricRegistry metricRegistry) {

		final ProxyConfig proxyConfig = new ProxyConfig();

		proxyConfig.setExposeProxy(properties.isExposeProxy());
		proxyConfig.setProxyTargetClass(properties.isProxyTargetClass());
		
		return MetricsBeanPostProcessorFactory.timed(metricRegistry, proxyConfig);
	}
	
	@Bean
	public AdvisingBeanPostProcessor countedPostProcessor(MetricRegistry metricRegistry) {

		final ProxyConfig proxyConfig = new ProxyConfig();

		proxyConfig.setExposeProxy(properties.isExposeProxy());
		proxyConfig.setProxyTargetClass(properties.isProxyTargetClass());
		
		return MetricsBeanPostProcessorFactory.counted(metricRegistry, proxyConfig);
	}
	
	@Bean
	public GaugeFieldAnnotationBeanPostProcessor gaugeFieldPostProcessor(MetricRegistry metricRegistry) {
		return MetricsBeanPostProcessorFactory.gaugeField(metricRegistry);
	}
	
	@Bean
	public GaugeMethodAnnotationBeanPostProcessor gaugeMethodPostProcessor(MetricRegistry metricRegistry) {
		return MetricsBeanPostProcessorFactory.gaugeMethod(metricRegistry);
	}
	
	@Bean
	public CachedGaugeAnnotationBeanPostProcessor cachedGaugePostProcessor(MetricRegistry metricRegistry) {
		return MetricsBeanPostProcessorFactory.cachedGauge(metricRegistry);
	}

	@Bean
	public MetricAnnotationBeanPostProcessor metricPostProcessor(MetricRegistry metricRegistry) {
		return MetricsBeanPostProcessorFactory.metric(metricRegistry);
	}
	
	@Bean
	public HealthCheckBeanPostProcessor healthCheckBeanPostProcessor(HealthCheckRegistry healthRegistry) {
		return MetricsBeanPostProcessorFactory.healthCheck(healthRegistry);
	}

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		
		
		
	}
	
	
	/*private BeanDefinitionBuilder build(Class<?> klazz, Object source, int role) {
		final BeanDefinitionBuilder beanDefBuilder = BeanDefinitionBuilder.rootBeanDefinition(klazz);
		beanDefBuilder.setRole(role);
		beanDefBuilder.getRawBeanDefinition().setSource(source);
		return beanDefBuilder;
	}

	private String registerComponent(BeanDefinitionBuilder beanDefBuilder, BeanDefinitionRegistry registry) {
		final AbstractBeanDefinition beanDef = beanDefBuilder.getBeanDefinition();
		final String beanName = BeanDefinitionReaderUtils.registerWithGeneratedName(beanDef, registry);
		registry.registerBeanDefinition(beanName, beanDef);
		return beanName;
	}

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		String metricsBeanName = properties.getMetricRegistry();
		String healthCheckBeanName = properties.getHealthCheckRegistry();

		final ProxyConfig proxyConfig = new ProxyConfig();

		proxyConfig.setExposeProxy(properties.isExposeProxy());
		proxyConfig.setProxyTargetClass(properties.isProxyTargetClass());
		
		MetricsBeanPostProcessorFactory.cachedGauge(metricRegistry);
		
		// @formatter:off

		registerComponent(this.build(MetricsBeanPostProcessorFactory.class, this, ROLE_INFRASTRUCTURE)
				.setFactoryMethod("exceptionMetered").addConstructorArgReference(metricsBeanName)
				.addConstructorArgValue(proxyConfig));

		registerComponent(this.build(MetricsBeanPostProcessorFactory.class, this, ROLE_INFRASTRUCTURE)
				.setFactoryMethod("metered").addConstructorArgReference(metricsBeanName)
				.addConstructorArgValue(proxyConfig));

		registerComponent(
				this.build(MetricsBeanPostProcessorFactory.class, this, ROLE_INFRASTRUCTURE).setFactoryMethod("timed")
						.addConstructorArgReference(metricsBeanName).addConstructorArgValue(proxyConfig));

		registerComponent(this.build(MetricsBeanPostProcessorFactory.class, this, ROLE_INFRASTRUCTURE)
				.setFactoryMethod("counted").addConstructorArgReference(metricsBeanName)
				.addConstructorArgValue(proxyConfig));

		registerComponent(this.build(MetricsBeanPostProcessorFactory.class, this, ROLE_INFRASTRUCTURE)
				.setFactoryMethod("gaugeField").addConstructorArgReference(metricsBeanName));

		registerComponent(this.build(MetricsBeanPostProcessorFactory.class, this, ROLE_INFRASTRUCTURE)
				.setFactoryMethod("gaugeMethod").addConstructorArgReference(metricsBeanName));

		registerComponent(this.build(MetricsBeanPostProcessorFactory.class, this, ROLE_INFRASTRUCTURE)
				.setFactoryMethod("cachedGauge").addConstructorArgReference(metricsBeanName));

		registerComponent(this.build(MetricsBeanPostProcessorFactory.class, this, ROLE_INFRASTRUCTURE)
				.setFactoryMethod("metric").addConstructorArgReference(metricsBeanName));

		registerComponent(this.build(MetricsBeanPostProcessorFactory.class, this, ROLE_INFRASTRUCTURE)
				.setFactoryMethod("healthCheck").addConstructorArgReference(healthCheckBeanName));

		
	}*/

}