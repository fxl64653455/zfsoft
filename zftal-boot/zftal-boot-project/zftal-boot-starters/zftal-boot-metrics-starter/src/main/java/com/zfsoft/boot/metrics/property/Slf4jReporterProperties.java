package com.zfsoft.boot.metrics.property;

import com.zfsoft.boot.metrics.MetricsReportProperties;

public class Slf4jReporterProperties extends ReporterProperties  {

	public static final String PREFIX = MetricsReportProperties.PREFIX + ".slf4j";
	
	// Optional
	private String marker;
	private String logger;
	private String level = "DEBUG";

	public String getMarker() {
		return marker;
	}

	public void setMarker(String marker) {
		this.marker = marker;
	}

	public String getLogger() {
		return logger;
	}

	public void setLogger(String logger) {
		this.logger = logger;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}
