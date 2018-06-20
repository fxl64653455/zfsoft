package com.zfsoft.boot.metrics.property;

import java.util.NoSuchElementException;

import com.zfsoft.boot.metrics.MetricsReportProperties;


public class DatadogReporterProperties extends ReporterProperties {

	public static final String PREFIX = MetricsReportProperties.PREFIX + ".datadog";
	public enum TransportEnum {

		HTTP("http"), UDP("udp"), STATSD("statsd");

		private final String transport;

		TransportEnum(String transport) {
			this.transport = transport;
		}

		public String get() {
			return transport;
		}
		
		public boolean equals(TransportEnum transport){
			return this.compareTo(transport) == 0;
		}
		
		public boolean equals(String transport){
			return this.compareTo(TransportEnum.valueOfIgnoreCase(transport)) == 0;
		}
		
		public static TransportEnum valueOfIgnoreCase(String key) {
			for (TransportEnum transport : TransportEnum.values()) {
				if(transport.get().equalsIgnoreCase(key)) {
					return transport;
				}
			}
	    	throw new NoSuchElementException("Cannot found transport with key '" + key + "'.");
	    }
		
	}
	
	// Required

	/**
	 * http,udp,statsd
	 */
	private TransportEnum transport = TransportEnum.HTTP;

	// HTTP Transport
	private String apiKey;
	private String connectTimeout;
	private String socketTimeout;

	// UDP Transport
	private String statsdHost;
	private String statsdPort;
	private String statsdPrefix;

	// Optional
	private String host;
	private boolean useEc2Host = false;
	private String expansions;
	private String tags;

	public TransportEnum getTransport() {
		return transport;
	}

	public void setTransport(TransportEnum transport) {
		this.transport = transport;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(String connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public String getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(String socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public String getStatsdHost() {
		return statsdHost;
	}

	public void setStatsdHost(String statsdHost) {
		this.statsdHost = statsdHost;
	}

	public String getStatsdPort() {
		return statsdPort;
	}

	public void setStatsdPort(String statsdPort) {
		this.statsdPort = statsdPort;
	}

	public String getStatsdPrefix() {
		return statsdPrefix;
	}

	public void setStatsdPrefix(String statsdPrefix) {
		this.statsdPrefix = statsdPrefix;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public boolean isUseEc2Host() {
		return useEc2Host;
	}

	public void setUseEc2Host(boolean useEc2Host) {
		this.useEc2Host = useEc2Host;
	}

	public String getExpansions() {
		return expansions;
	}

	public void setExpansions(String expansions) {
		this.expansions = expansions;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
}
