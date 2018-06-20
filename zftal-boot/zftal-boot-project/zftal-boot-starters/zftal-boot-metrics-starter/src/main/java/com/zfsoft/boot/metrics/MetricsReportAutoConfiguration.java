package com.zfsoft.boot.metrics;

import javax.management.MBeanServer;
import javax.sql.DataSource;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.codahale.metrics.Clock;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import com.zfsoft.boot.metrics.factory.ConsoleReporterFactoryBean;
import com.zfsoft.boot.metrics.factory.DatabaseReporterFactoryBean;
import com.zfsoft.boot.metrics.factory.JmxReporterFactoryBean;
import com.zfsoft.boot.metrics.factory.Slf4jReporterFactoryBean;
import com.zfsoft.boot.metrics.property.ConsoleReporterProperties;
import com.zfsoft.boot.metrics.property.DatabaseReporterProperties;
import com.zfsoft.boot.metrics.property.JmxReporterProperties;
import com.zfsoft.boot.metrics.property.Slf4jReporterProperties;
import com.zfsoft.boot.metrics.utils.SystemClock;

/**
 * 
 * @className ： MetricsReportAutoConfiguration
 * @description ： TODO(描述这个类的作用)
 * @author ：万大龙（743）
 * @date ： 2017年11月24日 上午9:55:33
 * @version V1.0
 */
@Configuration
@ConditionalOnClass({ MetricRegistry.class, ScheduledReporter.class })
@EnableConfigurationProperties(MetricsReportProperties.class)
@AutoConfigureAfter(MetricsAutoConfiguration.class)
public class MetricsReportAutoConfiguration implements DisposableBean {

	@Bean
	@ConditionalOnMissingBean
	public Clock clock() {
		return SystemClock.instance();
	}

	@Bean
	@ConditionalOnProperty(prefix = ConsoleReporterProperties.PREFIX, value = "enabled", havingValue = "true")
	public ConsoleReporterFactoryBean consoleReporterFactoryBean(MetricsReportProperties properties, Clock clock,
			MetricRegistry metricRegistry) {
		ConsoleReporterFactoryBean factoryBean = new ConsoleReporterFactoryBean(properties.getConsole());
		factoryBean.setClock(clock);
		factoryBean.setMetricRegistry(metricRegistry);
		return factoryBean;
	}

	@Bean
	@ConditionalOnProperty(prefix = Slf4jReporterProperties.PREFIX, value = "enabled", havingValue = "true")
	public Slf4jReporterFactoryBean slf4jReporterFactoryBean(MetricsReportProperties properties, Clock clock,
			MetricRegistry metricRegistry) {

		Slf4jReporterFactoryBean factoryBean = new Slf4jReporterFactoryBean(properties.getSlf4j());
		factoryBean.setClock(clock);
		factoryBean.setMetricRegistry(metricRegistry);

		return factoryBean;

	}

	@Bean
	@ConditionalOnProperty(prefix = JmxReporterProperties.PREFIX, value = "enabled", havingValue = "true")
	public JmxReporterFactoryBean jmxReporterFactoryBean(MetricsReportProperties properties, Clock clock,
			MetricRegistry metricRegistry, @Autowired(required = false) MBeanServer mBeanServer) {

		JmxReporterFactoryBean factoryBean = new JmxReporterFactoryBean(properties.getJmx());
		factoryBean.setClock(clock);
		factoryBean.setMetricRegistry(metricRegistry);
		factoryBean.setmBeanServer(mBeanServer);

		return factoryBean;
	}

	@Bean
	@ConditionalOnProperty(prefix = DatabaseReporterProperties.PREFIX , value = "enabled", havingValue = "true")
	@ConditionalOnBean(DataSource.class)
	public DatabaseReporterFactoryBean databaseReporterFactoryBean(MetricsReportProperties properties,
			DataSource dataSource, Clock clock, MetricRegistry metricRegistry) {

		DatabaseReporterFactoryBean factoryBean = new DatabaseReporterFactoryBean(properties.getDatabase(), dataSource);
		factoryBean.setClock(clock);
		factoryBean.setMetricRegistry(metricRegistry);

		return factoryBean;
	}

	@Override
	public void destroy() throws Exception {

	}

}