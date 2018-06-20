/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.dto;

public class ApiError {

	/**
	 * 错误编码
	 */
	private String key;
	/**
	 * 错误描述
	 */
	private String desc;

	public ApiError() {

	}
	public ApiError(String key, String desc) {
		this.key = key;
		this.desc = desc;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
