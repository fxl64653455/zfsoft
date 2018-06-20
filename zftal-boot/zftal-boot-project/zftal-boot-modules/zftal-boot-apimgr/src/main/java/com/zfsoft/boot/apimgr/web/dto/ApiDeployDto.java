/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.SafeHtml;


/**
 * 
 * @className ： ApiDeployDto
 * @description ： TODO(描述这个类的作用)
 * @author ：万大龙（743）
 * @date ： 2017年10月24日 上午10:24:16
 * @version V1.0
 */
public class ApiDeployDto {

	/**
	 * 接口ID编号
	 */
	private String id;
	/**
	 * 接口状态：0:不可用、1：可用
	 */
	private String status;
	/**
	 * 接口发布类型： com.zfsoft.boot.dsb.util.DeployTypeEnum
	 */
	@NotBlank(message = "接口发布类型必填")
	private String type;
	/**
	 * Ws接口需要指定命名空间,通常指接口所在域名的倒叙，如：zfsot.com
	 */
	private String namespace;
	/**
	 * 接口请求方式：com.zfsoft.boot.dsb.util.RequestMethodEnum
	 */
//	@NotBlank(message = "接口请求方式必填")
	private String method;
	/**
	 * 接口数据是否分页查询: true: 分页，false :不分页
	 */
	private boolean pageable;
	/**
	 * 接口发布URL:指对外暴露的访问地址
	 */
	@NotBlank(message = "接口发布地址必填")
	@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
	private String addr;
	/**
	 * 接口个性标签
	 */
	@NotEmpty(message = "接口个性标签不能为空")
	private String[] tags;
	/**
	 * 语言版本：Java、JavaScript、PHP、Python...等 ；
	 * com.zfsoft.boot.dsb.util.LanguageTypeEnum
	 */
	@NotEmpty(message = "语音版本不能为空")
	private String[] language;

	/* ==============以下为接口其他参数========================= */

	/** 数据库ID编号 */
	private String dbid;
	/** 数据交换类型：com.zfsoft.boot.dsb.util.DataExchangeTypeEnum */
	private String exchType;
	/** 数据交换方式：com.zfsoft.boot.dsb.util.DataExchangeMethodEnum */
	private String exchMethod;
	/** 数据表 */
	private String table;
	/** 自定义SQL */
	private String sql;
	
	/** 源接口类型(http,https,ws(axis),ws(axis2),ws(cxf)) */
	private String srcApiType;

	/** 源接口http请求方式(get,post,put,delete) */
	private String srcMethod;
	/**源接口命名空间*/
	private String srcNamespace;
	/**webService方法名称*/
	private String srcOperName;
	/**
	 * 源接口认证插件ID
	 */
	private String pluginId;
	/**
	 * 源接口认证实现唯一标记
	 */
	private String extensionId;
	/**消息主题*/
	private String topic;
	/**消息主题标记*/
	private String tag;
	/**
	 * 接口ID编号
	 */
	private String apiId;
	/**
	 * 接口类型：biz、data；用于区别业务接口和数据源接口，不显示
	 */
	private String apiType;
	
	/**
	 * 接口URL
	 */
	private String apiUrl;
	/**
	 * 接口名称
	 */
	private String apiName;
	/**
	 * 接口描述
	 */
	private String apiInfo;
	/**
	 * 接口详细说明
	 */
	private String apiDetail;
	/**
	 * 接口返回类型
	 */
	private String outType;
	/**
	 * 接口输出示例
	 */
	private String outCase;
	
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
	/**
	 * 输出错误
	 */
	private List<ApiError> errorList = new ArrayList<ApiError>();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getDbid() {
		return dbid;
	}

	public void setDbid(String dbid) {
		this.dbid = dbid;
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

	public String getSrcNamespace() {
		return srcNamespace;
	}

	public void setSrcNamespace(String srcNamespace) {
		this.srcNamespace = srcNamespace;
	}

	public String getSrcOperName() {
		return srcOperName;
	}

	public void setSrcOperName(String srcOperName) {
		this.srcOperName = srcOperName;
	}

	public boolean isPageable() {
		return pageable;
	}

	public void setPageable(boolean pageable) {
		this.pageable = pageable;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public String[] getLanguage() {
		return language;
	}

	public void setLanguage(String[] language) {
		this.language = language;
	}

	public String getSrcApiType() {
		return srcApiType;
	}

	public void setSrcApiType(String srcApiType) {
		this.srcApiType = srcApiType;
	}

	public String getSrcMethod() {
		return srcMethod;
	}

	public void setSrcMethod(String srcMethod) {
		this.srcMethod = srcMethod;
	}

	public String getPluginId() {
		return pluginId;
	}

	public void setPluginId(String pluginId) {
		this.pluginId = pluginId;
	}

	public String getExtensionId() {
		return extensionId;
	}

	public void setExtensionId(String extensionId) {
		this.extensionId = extensionId;
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

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	public String getApiType() {
		return apiType;
	}

	public void setApiType(String apiType) {
		this.apiType = apiType;
	}


	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getApiInfo() {
		return apiInfo;
	}

	public void setApiInfo(String apiInfo) {
		this.apiInfo = apiInfo;
	}

	public String getApiDetail() {
		return apiDetail;
	}

	public void setApiDetail(String apiDetail) {
		this.apiDetail = apiDetail;
	}

	public String getOutType() {
		return outType;
	}

	public void setOutType(String outType) {
		this.outType = outType;
	}

	public String getOutCase() {
		return outCase;
	}

	public void setOutCase(String outCase) {
		this.outCase = outCase;
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

	public List<ApiError> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<ApiError> errorList) {
		this.errorList = errorList;
	}
	
}
