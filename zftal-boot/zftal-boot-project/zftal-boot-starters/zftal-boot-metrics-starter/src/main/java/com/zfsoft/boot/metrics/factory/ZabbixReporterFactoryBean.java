package com.zfsoft.boot.metrics.factory;

import org.springframework.util.StringUtils;

import com.zfsoft.boot.metrics.property.ZabbixReporterProperties;

import io.github.hengyunabc.metrics.ZabbixReporter;
import io.github.hengyunabc.zabbix.sender.ZabbixSender;

public class ZabbixReporterFactoryBean
		extends AbstractScheduledReporterFactoryBean<ZabbixReporter, ZabbixReporterProperties> {

	public ZabbixReporterFactoryBean(ZabbixReporterProperties properties) {
		super(properties);
	}

	@Override
	public Class<ZabbixReporter> getObjectType() {
		return ZabbixReporter.class;
	}

	@Override
	protected ZabbixReporter createInstance(ZabbixReporterProperties properties) {

		final ZabbixReporter.Builder reporter = ZabbixReporter.forRegistry(getMetricRegistry())
				.convertDurationsTo(properties.getDurationUnit()).convertRatesTo(properties.getRateUnit())
				.filter(getMetricFilter()).prefix(getPrefix());

		if (StringUtils.hasText(properties.getName())) {
			reporter.name(properties.getName());
		}

		if (StringUtils.hasText(properties.getHostName())) {
			reporter.hostName(properties.getHostName());
		}

		if (StringUtils.hasText(properties.getSuffix())) {
			reporter.suffix(properties.getSuffix());
		}

		if (StringUtils.hasText(properties.getName())) {
			reporter.name(properties.getName());
		}

		return reporter.build(new ZabbixSender(properties.getHost(), properties.getPort(),
				properties.getConnectTimeout(), properties.getSocketTimeout()));
	}

}
