package com.zfsoft.api;

import java.util.HashMap;
import java.util.Map;

import com.zfsoft.api.utils.MessageUtil;


/**
 * 常用消息辅助对象
 */
public enum ZFMessage {
	
	DO_SUCCESS("I99007","success"),
	DO_FAIL("I99008","fail"),
	
	SAVE_SUCCESS("I99001","success"),
	SAVE_FAIL("I99002","fail"),
	
	MODIFY_SUCCESS("I99003","success"),
	MODIFY_FAIL("I99004","fail"),
	
	DELETE_SUCCESS("I99005","success"),
	DELETE_FAIL("I99006","fail"),
	
	ROLE_DELETE_FAIL("I99009","fail"),
	
	SYSTEM_ERROR("S00001","error"),
	
	PASSWORD_INIT_SUCCESS("I99010","success"),
	PASSWORD_INIT_FAIL("I99011","fail"),
	PASSWORD_UPDATE_FAIL("I99012","fail"),
	
	CACHE_REFRESH_SUCCESS("C99001","success"),
	CACHE_REFRESH_NONE("C99002","success"),
	
	PARAMATER_ERROR("S00002", "fail"),
	;
	
	private String key;
	private String status;
	
	ZFMessage(String key,String status){
		this.key = key;
		this.status = status;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 返回状态封装对象；客户端接收格式如：{status : "success", message : "xxxx"}
	 */
	public Map<String, String> status(Object[] params) {
		String message = MessageUtil.getText(key,params);
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", message);
		map.put("status", status);
		return map;
	}
	
	public Map<String, String> status() {
		return status(null);
	}
}
