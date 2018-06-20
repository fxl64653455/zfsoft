package com.zfsoft.boot.metrics.factory;

import javax.sql.DataSource;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.zfsoft.boot.metrics.ext.reporter.DatabaseReporter;
import com.zfsoft.boot.metrics.property.DatabaseReporterProperties;

public class DatabaseReporterFactoryBean extends AbstractScheduledReporterFactoryBean<DatabaseReporter,DatabaseReporterProperties> {

	private final DataSource dataSource;
	
	public DatabaseReporterFactoryBean(DatabaseReporterProperties properties, DataSource dataSource) {
		super(properties);
		this.dataSource = dataSource;
	}
 

	@Override
	public Class<DatabaseReporter> getObjectType() {
		return DatabaseReporter.class;
	}

	@Override
	protected DatabaseReporter createInstance(DatabaseReporterProperties properties) {
		
		final DatabaseReporter.Builder reporter = DatabaseReporter.forRegistry(getMetricRegistry())
				.convertDurationsTo(properties.getDurationUnit())
				.convertRatesTo(properties.getRateUnit())
				.filter(getMetricFilter())
				.closeOnCommit(properties.isCloseOnCommit())
				.shutdownExecutorOnStop(properties.isRollbackOnException())
				.rollbackOnException(properties.isRollbackOnException());

		if (!ObjectUtils.isEmpty(getClock())) {
			reporter.withClock(getClock());
		}

		if (StringUtils.hasText(properties.getCaugeTable())) {
			reporter.caugeTable(properties.getCaugeTable());
			reporter.allowCauge(properties.isAllowCauge());
		}
		
		if (StringUtils.hasText(properties.getCounterTable())) {
			reporter.counterTable(properties.getCounterTable());
			reporter.allowCounter(properties.isAllowCounter());
		}
		
		if (StringUtils.hasText(properties.getHistogramTable())) {
			reporter.histogramTable(properties.getHistogramTable());
			reporter.allowHistogram(properties.isAllowHistogram());
		}
		
		if (StringUtils.hasText(properties.getMeterTable())) {
			reporter.meterTable(properties.getMeterTable());
			reporter.allowMeter(properties.isAllowMeter());
		}
		
		if (StringUtils.hasText(properties.getTimerTable())) {
			reporter.timerTable(properties.getTimerTable());
			reporter.allowTimer(properties.isAllowTimer());
		}

		return reporter.build(dataSource);
	}

}
