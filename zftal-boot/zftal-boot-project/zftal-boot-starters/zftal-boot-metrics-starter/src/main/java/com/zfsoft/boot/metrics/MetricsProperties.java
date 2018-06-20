package com.zfsoft.boot.metrics;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * @className	： MetricsProperties
 * @description	： TODO(描述这个类的作用)
 * @author 		：万大龙（743）
 * @date		： 2017年11月24日 上午9:55:50
 * @version 	V1.0
 */
@ConfigurationProperties(prefix = MetricsProperties.PREFIX)
public class MetricsProperties {

	public static final String PREFIX = "dropwizard.metrics";

	private boolean exposeProxy = false;
	private boolean proxyTargetClass = false;
	
	/**
	 *jvm.gc : com.codahale.metrics.jvm.GarbageCollectorMetricSet </br>
     *jvm.memory : com.codahale.metrics.jvm.MemoryUsageGaugeSet  </br>
     *jvm.thread-states : com.codahale.metrics.jvm.ThreadStatesGaugeSet </br>
     *jvm.fd.usage : com.codahale.metrics.jvm.FileDescriptorRatioGauge </br>
	 */
	private Map<String /* name */, String /* class */> metrics = new LinkedHashMap<String, String>();

	public boolean isExposeProxy() {
		return exposeProxy;
	}

	public void setExposeProxy(boolean exposeProxy) {
		this.exposeProxy = exposeProxy;
	}

	public boolean isProxyTargetClass() {
		return proxyTargetClass;
	}

	public void setProxyTargetClass(boolean proxyTargetClass) {
		this.proxyTargetClass = proxyTargetClass;
	}

	public Map<String, String> getMetrics() {
		return metrics;
	}

	public void setMetrics(Map<String, String> metrics) {
		this.metrics = metrics;
	}

	public String getMetricRegistry() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getHealthCheckRegistry() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
