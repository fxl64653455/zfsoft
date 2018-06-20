package com.zfsoft.boot.metrics.property;

import com.zfsoft.boot.metrics.MetricsReportProperties;

public class ZabbixReporterProperties extends ReporterProperties {

	public static final String PREFIX = MetricsReportProperties.PREFIX + ".zabbix";
	
	private String name = "zabbix-reporter";
	private String hostName;
	private String suffix = "";

	private String host;
	private int port;
	private int connectTimeout = 3 * 1000;
	private int socketTimeout = 3 * 1000;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

}
