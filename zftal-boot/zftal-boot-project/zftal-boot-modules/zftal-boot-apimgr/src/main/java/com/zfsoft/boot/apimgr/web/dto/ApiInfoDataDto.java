/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.dto;


import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.SafeHtml;

/**
 * 
 * @className	： ApiInfoDto
 * @description	：  接口信息Dto
 * @author 		：万大龙（743）
 * @date		： 2017年10月20日 下午12:24:29
 * @version 	V1.0
 */
public class ApiInfoDataDto {
	
	/**
	 * 接口ID编号
	 */
	private String id;
	/**
	 * 接口名称
	 */
	@NotBlank(message="接口名称必填")  
	@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
	private String name;
	/**
	 * 数据库ID编号
	 */
	@NotBlank(message="数据源必填")  
	@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
	private String dbid;
	/**
	 * 数据库名称
	 */
	private String dbname;
	/**
	 * 数据库中文名称
	 */
	private String dbcnname;
	/**
	 * 数据交换类型：com.zfsoft.boot.dsb.util.DataExchangeTypeEnum
	 */
	@NotBlank(message="交换类型必填")  
	@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
	private String exchType;
	/**
	 * 数据交换方式：com.zfsoft.boot.dsb.util.DataExchangeMethodEnum
	 */
	@NotBlank(message="交换方式必填")  
	@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
	private String exchMethod;
	/**
	 * 数据表
	 */
	private String table;
	/**
	 * 自定义SQL
	 */
	private String sql;
	/**
	 * 接口描述
	 */
	@NotBlank(message="接口描述必填")
	@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
	private String info;
	/**
	 * 接口详细说明
	 */
	@NotBlank(message="接口详细说明必填")
	private String detail;
	/**
	 * 接口类型：biz、data；用于区别业务接口和数据源接口，不显示
	 */
	private String type;
	/**
	 * 接口创建时间
	 */
	private String time;
	/**
	 * 接口创建用户
	 */
	private String owner;
	/**
	 * 接口发布次数
	 */
	private String deploynum;
	/**是否启用连续调用配置*/
	private String invokeEnable;
	/**接口发布状态(上线/下线)*/
	private String deployStatus;
	/**
	 * 接口返回类型
	 */
	private String outType;
	/**
	 * 接口输出示例
	 */
	private String outCase;
	/**消息主题*/
	private String topic;
	/**消息主题标记*/
	private String tag;
	/**
	 * 输入参数
	 */
	private List<ApiParam> paramList = new ArrayList<ApiParam>();
	/**
	 * 变更字段
	 */
	private List<ApiParam> updateList = new ArrayList<ApiParam>();
	/**
	 * 输出参数
	 */
	private List<ApiField> fieldList = new ArrayList<ApiField>();

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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDbid() {
		return dbid;
	}

	public void setDbid(String dbid) {
		this.dbid = dbid;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getDbcnname() {
		return dbcnname;
	}

	public void setDbcnname(String dbcnname) {
		this.dbcnname = dbcnname;
	}

	public String getExchType() {
		return exchType;
	}

	public void setExchType(String exchType) {
		this.exchType = exchType;
	}

	public String getExchMethod() {
		return exchMethod;
	}

	public void setExchMethod(String exchMethod) {
		this.exchMethod = exchMethod;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOutType() {
		return outType;
	}

	public void setOutType(String outType) {
		this.outType = outType;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getOutCase() {
		return outCase;
	}

	public void setOutCase(String outCase) {
		this.outCase = outCase;
	}

	public String getDeploynum() {
		return deploynum;
	}

	public void setDeploynum(String deploynum) {
		this.deploynum = deploynum;
	}

	public String getInvokeEnable() {
		return invokeEnable;
	}

	public void setInvokeEnable(String invokeEnable) {
		this.invokeEnable = invokeEnable;
	}

	public String getDeployStatus() {
		return deployStatus;
	}

	public void setDeployStatus(String deployStatus) {
		this.deployStatus = deployStatus;
	}

	public List<ApiParam> getParamList() {
		return paramList;
	}

	public void setParamList(List<ApiParam> paramList) {
		this.paramList = paramList;
	}
	
	public List<ApiParam> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<ApiParam> updateList) {
		this.updateList = updateList;
	}

	public List<ApiField> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<ApiField> fieldList) {
		this.fieldList = fieldList;
	}

}
