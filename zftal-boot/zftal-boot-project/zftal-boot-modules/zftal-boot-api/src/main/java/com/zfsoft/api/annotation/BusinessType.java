package com.zfsoft.api.annotation;

/**
 * 
 * <p>
 *   <h3>zftal框架<h3>
 *   说明：日志操作类型-枚举
 * <p>
 * @author <a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a>
 * @version 2016年10月13日下午2:30:38
 */
public enum BusinessType {

	/**
	 * 登录操作
	 */
	LOGIN("login"),
	/**
	 * 登出操作
	 */
	LOGOUT("logout"),
	/**
	 * 查询操作
	 */
	SELECT("select"),
	/**
	 * 更改操作
	 */
	UPDATE("update"),
	/**
	 * 刪除 操作
	 */
	DELETE("delete"),
	/**
	 * 写入操作
	 */
	INSERT("insert"),
	/**
	 * 系统操作描述确认
	 */
	OP_SURE("sure");
	
	private String key;
	
	BusinessType(String key){
		this.key=key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
