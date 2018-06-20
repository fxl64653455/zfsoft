package com.zfsoft.boot.metrics.property;

import com.zfsoft.boot.metrics.MetricsReportProperties;

public class JmxReporterProperties extends ReporterProperties  {

	public static final String PREFIX = MetricsReportProperties.PREFIX + ".jmx";
	
	// Optional
	private String domain;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	
}
