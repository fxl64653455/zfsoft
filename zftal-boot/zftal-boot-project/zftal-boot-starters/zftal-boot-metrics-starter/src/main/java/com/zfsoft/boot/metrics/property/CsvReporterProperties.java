package com.zfsoft.boot.metrics.property;

import com.zfsoft.boot.metrics.MetricsReportProperties;

public class CsvReporterProperties extends ReporterProperties {
	
	public static final String PREFIX = MetricsReportProperties.PREFIX + ".csv";
	
	// Optional
	private String directory;

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

}
