package com.zfsoft.boot.metrics.factory.support;

import org.springframework.beans.factory.support.AbstractBeanDefinition;

import com.zfsoft.boot.metrics.MetricsReportProperties;

public interface ReporterBeanDefinitionParser {

	String getType();

	AbstractBeanDefinition parseReporter(MetricsReportProperties properties);
	
}
