/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.webv5.web.handler;

public class ErrorPair {

	/**
	 * 错误编码
	 */
	private String key;
	/**
	 * 错误描述
	 */
	private String desc;

	public ErrorPair() {

	}
	public ErrorPair(String key, String desc) {
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
