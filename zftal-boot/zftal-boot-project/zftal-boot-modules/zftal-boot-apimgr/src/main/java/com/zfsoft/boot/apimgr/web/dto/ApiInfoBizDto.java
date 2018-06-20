/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.dto;


import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;


/**
 * 
 * @className	： ApiInfoDto
 * @description	：  接口信息Dto
 * @author 		：万大龙（743）
 * @date		： 2017年10月20日 下午12:24:29
 * @version 	V1.0
 */
public class ApiInfoBizDto {
	
	/**
	 * 接口ID编号
	 */
	private String id;
	/**
	 * 接口名称
	 */
	@NotBlank(message="接口名称必填")  
	private String name;
	/**
	 * 接口URL
	 */
	@NotBlank(message="接口地址必填")
	private String url;
	/**
	 * 接口类型： com.zfsoft.boot.dsb.util.BizApiTypeEnum
	 */
	@NotBlank(message="接口类型必填")
	private String type;
	/**
	 * 请求方式
	 */
//	@NotBlank(message="请求方式必填")
	private String method;
	/**命名空间*/
	private String namespace;
	/**webService方法名称*/
	private String operName;
	/**
	 * 认证插件ID
	 */
	private String pluginId;
	/**
	 * 认证实现唯一标记
	 */
	private String extensionId;
	/**消息主题*/
	private String topic;
	/**消息主题标记*/
	private String tag;
	/**
	 * 接口用途描述
	 */
	@NotBlank(message="接口描述必填")
	private String info;
	/**
	 * 接口详细说明
	 */
	@NotBlank(message="接口详细说明必填")
	private String detail;
	/**
	 * 接口创建用户
	 */
	private String owner;
	/**
	 * 接口创建时间
	 */
	private String time;
	/**
	 * 接口发布次数
	 */
	private String deploynum;
	/**是否启用连续调用配置*/
	private String invokeEnable;
	/**
	 * 接口返回类型
	 */
	private String outType;
	/**
	 * 接口输出示例
	 */
	private String outCase;
	/**
	 * 接口输入参数
	 */
	private List<ApiParam> paramList = new ArrayList<ApiParam>();
	/**
	 * 接口输出参数
	 */
	private List<ApiField> fieldList = new ArrayList<ApiField>();
	/**
	 * 接口输出错误
	 */
	private List<ApiError> errorList = new ArrayList<ApiError>();
	
	
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
	
	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public List<ApiParam> getParamList() {
		return paramList;
	}

	public void setParamList(List<ApiParam> paramList) {
		this.paramList = paramList;
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
