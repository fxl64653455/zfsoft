package com.zfsoft.boot.metrics.property;

import com.zfsoft.boot.metrics.MetricsReportProperties;

public class NewRelicReporterProperties extends ReporterProperties  {
   
	public static final String PREFIX = MetricsReportProperties.PREFIX + ".newrelic";
	private String name = "NewRelic reporter";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}
