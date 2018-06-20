package com.zfsoft.boot.metrics;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import com.zfsoft.boot.metrics.property.ConsoleReporterProperties;
import com.zfsoft.boot.metrics.property.CsvReporterProperties;
import com.zfsoft.boot.metrics.property.DatabaseReporterProperties;
import com.zfsoft.boot.metrics.property.DatadogReporterProperties;
import com.zfsoft.boot.metrics.property.GangliaReporterProperties;
import com.zfsoft.boot.metrics.property.GraphiteReporterProperties;
import com.zfsoft.boot.metrics.property.InfluxdbReporterProperties;
import com.zfsoft.boot.metrics.property.JmxReporterProperties;
import com.zfsoft.boot.metrics.property.KafkaReporterProperties;
import com.zfsoft.boot.metrics.property.LibratoReporterProperties;
import com.zfsoft.boot.metrics.property.NewRelicReporterProperties;
import com.zfsoft.boot.metrics.property.RocketmqReporterProperties;
import com.zfsoft.boot.metrics.property.Slf4jReporterProperties;
import com.zfsoft.boot.metrics.property.ZabbixReporterProperties;

/**
 * 
 * @className	： MetricsReportProperties
 * @description	： TODO(描述这个类的作用)
 * @author 		：万大龙（743）
 * @date		： 2017年11月24日 上午9:55:45
 * @version 	V1.0
 */
@ConfigurationProperties(prefix = MetricsReportProperties.PREFIX)
public class MetricsReportProperties {

	public static final String PREFIX = "dropwizard.metrics.report";

	// Required
	protected String types = "console";
	
	/* 各类报表输出配置 */
	
	@NestedConfigurationProperty
	private ConsoleReporterProperties console;
	@NestedConfigurationProperty
	private CsvReporterProperties csv;
	@NestedConfigurationProperty
	private DatabaseReporterProperties database;
	@NestedConfigurationProperty
	private DatadogReporterProperties datadog;
	@NestedConfigurationProperty
	private GangliaReporterProperties ganglia;
	@NestedConfigurationProperty
	private GraphiteReporterProperties graphite;
	@NestedConfigurationProperty
	private InfluxdbReporterProperties influxdb;
	@NestedConfigurationProperty
	private JmxReporterProperties jmx;
	@NestedConfigurationProperty
	private KafkaReporterProperties kafka;
	@NestedConfigurationProperty
	private LibratoReporterProperties librato;
	@NestedConfigurationProperty
	private NewRelicReporterProperties newrelic;
	@NestedConfigurationProperty
	private RocketmqReporterProperties rocketmq;
	@NestedConfigurationProperty
	private Slf4jReporterProperties slf4j;
	@NestedConfigurationProperty
	private ZabbixReporterProperties zabbix;

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public ConsoleReporterProperties getConsole() {
		return console;
	}

	public void setConsole(ConsoleReporterProperties console) {
		this.console = console;
	}

	public CsvReporterProperties getCsv() {
		return csv;
	}

	public void setCsv(CsvReporterProperties csv) {
		this.csv = csv;
	}

	public DatabaseReporterProperties getDatabase() {
		return database;
	}

	public void setDatabase(DatabaseReporterProperties database) {
		this.database = database;
	}

	public DatadogReporterProperties getDatadog() {
		return datadog;
	}

	public void setDatadog(DatadogReporterProperties datadog) {
		this.datadog = datadog;
	}

	public GangliaReporterProperties getGanglia() {
		return ganglia;
	}

	public void setGanglia(GangliaReporterProperties ganglia) {
		this.ganglia = ganglia;
	}

	public GraphiteReporterProperties getGraphite() {
		return graphite;
	}

	public void setGraphite(GraphiteReporterProperties graphite) {
		this.graphite = graphite;
	}

	public InfluxdbReporterProperties getInfluxdb() {
		return influxdb;
	}

	public void setInfluxdb(InfluxdbReporterProperties influxdb) {
		this.influxdb = influxdb;
	}

	public JmxReporterProperties getJmx() {
		return jmx;
	}

	public void setJmx(JmxReporterProperties jmx) {
		this.jmx = jmx;
	}

	public KafkaReporterProperties getKafka() {
		return kafka;
	}

	public void setKafka(KafkaReporterProperties kafka) {
		this.kafka = kafka;
	}

	public LibratoReporterProperties getLibrato() {
		return librato;
	}

	public void setLibrato(LibratoReporterProperties librato) {
		this.librato = librato;
	}

	public NewRelicReporterProperties getNewrelic() {
		return newrelic;
	}

	public void setNewrelic(NewRelicReporterProperties newrelic) {
		this.newrelic = newrelic;
	}

	public RocketmqReporterProperties getRocketmq() {
		return rocketmq;
	}

	public void setRocketmq(RocketmqReporterProperties rocketmq) {
		this.rocketmq = rocketmq;
	}

	public Slf4jReporterProperties getSlf4j() {
		return slf4j;
	}

	public void setSlf4j(Slf4jReporterProperties slf4j) {
		this.slf4j = slf4j;
	}

	public ZabbixReporterProperties getZabbix() {
		return zabbix;
	}

	public void setZabbix(ZabbixReporterProperties zabbix) {
		this.zabbix = zabbix;
	}

	
}
