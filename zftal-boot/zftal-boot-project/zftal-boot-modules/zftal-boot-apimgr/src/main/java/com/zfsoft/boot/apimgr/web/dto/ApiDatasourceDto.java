/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.dto;

import java.util.Map;

import javax.validation.constraints.NotBlank;


public class ApiDatasourceDto {

	public static final String DEFAULT_DATASOURCE = "current";
	
	/**
	 * 接口数据ID编号
	 */
	private String id;
	/**
	 * 接口数据名称
	 */
	@NotBlank(message="接口数据名称必填")  
	private String name;
	/**
	 * 接口数据详细描述
	 */
	@NotBlank(message="接口数据描述必填")  
	private String info;
	/**
	 * 数据交换类型：com.zfsoft.boot.dsb.util.DataExchangeTypeEnum
	 */
	@NotBlank(message="数据交换类型")  
	private String type;
	/**
	 * 数据交换方式：com.zfsoft.boot.dsb.util.DataExchangeMethodEnum
	 */
	@NotBlank(message="数据交换方式")  
	private String method;
	/**
	 * 接口数据来源：0: 数据中心标准表 、1： SQL语句
	 */
	@NotBlank(message="接口数据来源必填")  
	private String dbid;
	/**
	 * 接口数据来源表
	 */
	private String table;
	/**
	 * 接口数据来源SQL
	 */
	private String sql;
	/**
	 * 接口数据来源表列名
	 */
	private Map<String, String> columns;
	/**
	 * 接口数据来源表列别名
	 */
	private Map<String, String> alias;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getDbid() {
		return dbid;
	}

	public void setDbid(String dbid) {
		this.dbid = dbid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}
	
	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	
	public Map<String, String> getColumns() {
		return columns;
	}

	public void setColumns(Map<String, String> columns) {
		this.columns = columns;
	}

	public Map<String, String> getAlias() {
		return alias;
	}

	public void setAlias(Map<String, String> alias) {
		this.alias = alias;
	}

}
