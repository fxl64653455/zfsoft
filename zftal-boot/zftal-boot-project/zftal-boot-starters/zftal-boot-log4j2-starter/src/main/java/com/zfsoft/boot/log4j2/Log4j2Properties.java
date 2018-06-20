package com.zfsoft.boot.log4j2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * @className	： Log4j2Properties
 * @description	： TODO(描述这个类的作用)
 * @author 		：万大龙（743）
 * @date		： 2017年10月16日 下午12:20:39
 * @version 	V1.0
 */
@ConfigurationProperties(Log4j2Properties.PREFIX)
public class Log4j2Properties {

	public static final String PREFIX = "logging.log4j";

	/** 是否启用 **/
	protected Boolean enabled = false;
	/** 需要写日志到数据库的包名 **/
	protected String logger = "JDBC-Logger";
	/** 是否当前上下文 **/
	protected boolean currentContext = false;
	/** 是否异步记录日志 **/
	protected boolean async = false;
	/** 是否忽略异常信息 **/
	protected boolean ignoreExceptions = true;
	/** Appender名称 **/
	protected String appender = "databaseAppender";
	/** 日志表名称 **/
	protected String tableName = "LOG4j_BIZ";
	/** 数据库列与表达式对应关系 **/
	private List<Log4j2ColumnConfig> columnConfigs = new ArrayList<Log4j2ColumnConfig>();
	/** 过滤器名称 **/
	protected String filter = "dblog";
	/** 日志批量缓冲大小；小于0，则批量提交无效 **/
	protected int bufferSize = 10;

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getLogger() {
		return logger;
	}

	public void setLogger(String logger) {
		this.logger = logger;
	}

	public boolean isCurrentContext() {
		return currentContext;
	}

	public void setCurrentContext(boolean currentContext) {
		this.currentContext = currentContext;
	}
	
	public boolean isAsync() {
		return async;
	}

	public void setAsync(boolean async) {
		this.async = async;
	}

	public boolean isIgnoreExceptions() {
		return ignoreExceptions;
	}

	public void setIgnoreExceptions(boolean ignoreExceptions) {
		this.ignoreExceptions = ignoreExceptions;
	}
	
	public String getAppender() {
		return appender;
	}

	public void setAppender(String appender) {
		this.appender = appender;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<Log4j2ColumnConfig> getColumnConfigs() {
		return columnConfigs;
	}

	public void setColumnConfigs(List<Log4j2ColumnConfig> columnConfigs) {
		this.columnConfigs = columnConfigs;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public int getBufferSize() {
		return bufferSize;
	}

	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}
	
	

}
