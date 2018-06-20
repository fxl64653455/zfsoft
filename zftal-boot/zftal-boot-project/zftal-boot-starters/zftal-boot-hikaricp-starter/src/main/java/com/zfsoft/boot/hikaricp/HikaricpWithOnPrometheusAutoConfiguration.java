package com.zfsoft.boot.hikaricp;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.metrics.MetricsTrackerFactory;
import com.zaxxer.hikari.metrics.prometheus.PrometheusMetricsTrackerFactory;

import io.prometheus.client.CollectorRegistry;

/**
 * 
 * @className	： HikaricpWithOnPrometheusAutoConfiguration
 * @description	： 基于Prometheus监控平台的HikariDataSource监控
 * @author 		：万大龙（743）
 * @date		： 2017年11月28日 上午9:59:32
 * @version 	V1.0
 */
@Configuration
@ConditionalOnBean( HikariDataSource.class )
@ConditionalOnClass({ HikariDataSource.class, CollectorRegistry.class })
@ConditionalOnProperty(prefix = HikaricpWithMetricProperties.PREFIX, value = "type", havingValue = "prometheus", matchIfMissing = false)
@EnableConfigurationProperties({ HikaricpWithMetricProperties.class })
public class HikaricpWithOnPrometheusAutoConfiguration {
	
	@Bean
	@ConditionalOnMissingBean(value = MetricsTrackerFactory.class)
	public MetricsTrackerFactory duridFilterRegistrationBean() {
		MetricsTrackerFactory metricsTrackerFactory = new PrometheusMetricsTrackerFactory();
		return metricsTrackerFactory;
	}
	

}
