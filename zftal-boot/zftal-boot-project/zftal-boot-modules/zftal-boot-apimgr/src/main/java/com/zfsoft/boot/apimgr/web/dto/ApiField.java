/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.dto;

public class ApiField {

	/**
	 * 字段编码
	 */
	private String key;
	/**
	 * 字段名称
	 */
	private String name;
	/**
	 * 字段类型： String、Number、Boolean
	 */
	private String type;
	/**
	 * 字段别名
	 */
	private String alias;
	/**
	 * 字段描述
	 */
	private String desc;

	public ApiField() {

	}

	public ApiField(String key, String name, String type, String alias, String desc) {
		this.key = key;
		this.name = name;
		this.type = type;
		this.alias = alias;
		this.desc = desc;
	}
	
	public ApiField(String key, String name, String type, String desc) {
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

}
