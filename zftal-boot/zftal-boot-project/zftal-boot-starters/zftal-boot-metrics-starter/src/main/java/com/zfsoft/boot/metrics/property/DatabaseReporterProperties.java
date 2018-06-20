package com.zfsoft.boot.metrics.property;

import com.zfsoft.boot.metrics.MetricsReportProperties;

public class DatabaseReporterProperties extends ReporterProperties {
	
	public static final String PREFIX = MetricsReportProperties.PREFIX + ".database";
	/**
	 * Whether to roll back the transaction when an exception is thrown.
	 */
	private boolean rollbackOnException;
	/**
	 * Whether to close the connection when a statement is commit.
	 */
	private boolean closeOnCommit;

	private String caugeTable = "cauge_metrics";
	private String counterTable = "counter_metrics";
	private String histogramTable = "histogram_metrics";
	private String meterTable = "meter_metrics";
	private String timerTable = "timer_metrics";

	private boolean allowCauge = false;
	private boolean allowCounter = true;
	private boolean allowHistogram = false;
	private boolean allowMeter = false;
	private boolean allowTimer = false;

	public boolean isRollbackOnException() {
		return rollbackOnException;
	}

	public void setRollbackOnException(boolean rollbackOnException) {
		this.rollbackOnException = rollbackOnException;
	}

	public boolean isCloseOnCommit() {
		return closeOnCommit;
	}

	public void setCloseOnCommit(boolean closeOnCommit) {
		this.closeOnCommit = closeOnCommit;
	}

	public String getCaugeTable() {
		return caugeTable;
	}

	public void setCaugeTable(String caugeTable) {
		this.caugeTable = caugeTable;
	}

	public String getCounterTable() {
		return counterTable;
	}

	public void setCounterTable(String counterTable) {
		this.counterTable = counterTable;
	}

	public String getHistogramTable() {
		return histogramTable;
	}

	public void setHistogramTable(String histogramTable) {
		this.histogramTable = histogramTable;
	}

	public String getMeterTable() {
		return meterTable;
	}

	public void setMeterTable(String meterTable) {
		this.meterTable = meterTable;
	}

	public String getTimerTable() {
		return timerTable;
	}

	public void setTimerTable(String timerTable) {
		this.timerTable = timerTable;
	}

	public boolean isAllowCauge() {
		return allowCauge;
	}

	public void setAllowCauge(boolean allowCauge) {
		this.allowCauge = allowCauge;
	}

	public boolean isAllowCounter() {
		return allowCounter;
	}

	public void setAllowCounter(boolean allowCounter) {
		this.allowCounter = allowCounter;
	}

	public boolean isAllowHistogram() {
		return allowHistogram;
	}

	public void setAllowHistogram(boolean allowHistogram) {
		this.allowHistogram = allowHistogram;
	}

	public boolean isAllowMeter() {
		return allowMeter;
	}

	public void setAllowMeter(boolean allowMeter) {
		this.allowMeter = allowMeter;
	}

	public boolean isAllowTimer() {
		return allowTimer;
	}

	public void setAllowTimer(boolean allowTimer) {
		this.allowTimer = allowTimer;
	}

}
