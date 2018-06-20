package com.zfsoft.boot.metrics.property;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import com.zfsoft.boot.metrics.ext.filter.FilterType;

public abstract class ReporterProperties {

	protected Boolean enabled = false;
	
	// Required
	private String period = "10s";
	
	// Optional

	private String prefix = "";
	private TimeUnit durationUnit = TimeUnit.MILLISECONDS;
	private TimeUnit rateUnit = TimeUnit.SECONDS;
	private String locale = Locale.SIMPLIFIED_CHINESE.toString();
	
	private FilterType filterType = FilterType.PATTERN;
	private String filterValue = null;
	
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public TimeUnit getRateUnit() {
		return rateUnit;
	}

	public void setRateUnit(TimeUnit rateUnit) {
		this.rateUnit = rateUnit;
	}

	public TimeUnit getDurationUnit() {
		return durationUnit;
	}

	public void setDurationUnit(TimeUnit durationUnit) {
		this.durationUnit = durationUnit;
	}
	
	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public FilterType getFilterType() {
		return filterType;
	}

	public void setFilterType(FilterType filterType) {
		this.filterType = filterType;
	}

	public String getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}
	
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
}
