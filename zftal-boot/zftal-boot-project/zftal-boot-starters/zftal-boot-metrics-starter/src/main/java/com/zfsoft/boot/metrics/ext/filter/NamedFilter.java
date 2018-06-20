package com.zfsoft.boot.metrics.ext.filter;


import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricFilter;

public class NamedFilter implements MetricFilter {

	protected String metricKey;
	
	public NamedFilter(String metric) {
		this.metricKey = metric;
	}
	
	@Override
	public boolean matches(String name, Metric metric) {
		return name.equals(metricKey);
	}
	
	public String getMetric() {
		return metricKey;
	}

	public void setMetric(String metric) {
		this.metricKey = metric;
	}

	@Override
	public String toString() {
		return "[NamedFilter metric=" + metricKey + "]";
	}
	
}