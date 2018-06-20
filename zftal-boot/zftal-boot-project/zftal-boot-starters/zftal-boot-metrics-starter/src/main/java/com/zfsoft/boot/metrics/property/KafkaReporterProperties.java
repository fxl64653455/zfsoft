package com.zfsoft.boot.metrics.property;

import com.zfsoft.boot.metrics.MetricsReportProperties;

public class KafkaReporterProperties extends ReporterProperties {

	public static final String PREFIX = MetricsReportProperties.PREFIX + ".kafka";
	
	private String name = "kafka-reporter";
	private boolean showSamples;
	private String hostName;
	private String ip;
	private String topic;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isShowSamples() {
		return showSamples;
	}

	public void setShowSamples(boolean showSamples) {
		this.showSamples = showSamples;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

}
