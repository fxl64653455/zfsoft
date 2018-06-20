package com.zfsoft.boot.metrics.factory;

import org.springframework.util.StringUtils;

import com.zfsoft.boot.metrics.property.KafkaReporterProperties;

import io.github.hengyunabc.metrics.KafkaReporter;
import kafka.producer.ProducerConfig;

public class KafkaReporterFactoryBean extends AbstractScheduledReporterFactoryBean<KafkaReporter,KafkaReporterProperties> {

	private ProducerConfig producerConfig;
	
	public KafkaReporterFactoryBean(KafkaReporterProperties properties) {
		super(properties);
	}

	@Override
	public Class<KafkaReporter> getObjectType() {
		return KafkaReporter.class;
	}

	@Override
	protected KafkaReporter createInstance(KafkaReporterProperties properties) {
		
		final KafkaReporter.Builder reporter = KafkaReporter.forRegistry(getMetricRegistry())
				.convertDurationsTo(properties.getDurationUnit())
				.convertRatesTo(properties.getRateUnit())
				.filter(getMetricFilter())
				.prefix(getPrefix());

		reporter.showSamples(properties.isShowSamples());

		if (StringUtils.hasText(properties.getIp())) {
			reporter.ip(properties.getIp());
		}
		
		if (StringUtils.hasText(properties.getHostName())) {
			reporter.hostName(properties.getHostName());
		}
		
		if (StringUtils.hasText(properties.getName())) {
			reporter.name(properties.getName());
		}
		
		if (StringUtils.hasText(properties.getTopic())) {
			reporter.topic(properties.getTopic());
		}
		

		if (getProducerConfig() != null) {
			reporter.config(getProducerConfig());
		}

		return reporter.build();
	}

	public ProducerConfig getProducerConfig() {
		return producerConfig;
	}

	public void setProducerConfig(ProducerConfig producerConfig) {
		this.producerConfig = producerConfig;
	}

}
