/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.dto;

public class ApiParam {

	/**
	 * 参数编码
	 */
	private String key;
	/**
	 * 参数名称
	 */
	private String name;
	/**
	 * 字段类型：  String、Number、Boolean
	 */
	private String type;
	/**
	 * 是否必填
	 */
	private String required;
	/**
	 * 参数描述
	 */
	private String desc;
	
	
	public ApiParam() {
		
	}

	public ApiParam(String key, String name, String type, String required, String desc) {
		this.key = key;
		this.name = name;
		this.type = type;
		this.required = required;
		this.desc = desc;
	}

	public ApiParam(String key, String name, String type, String desc) {
		this.key = key;
		this.name = name;
		this.type = type;
		this.desc = desc;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
