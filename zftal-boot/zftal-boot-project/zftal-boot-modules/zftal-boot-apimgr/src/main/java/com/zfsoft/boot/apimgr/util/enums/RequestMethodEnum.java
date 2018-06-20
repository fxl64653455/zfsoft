/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.util.enums;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public enum RequestMethodEnum {
	
	GET("GET", "GET"), 
	POST("POST", "POST");
	
	private String key;
	private String desc;

	private RequestMethodEnum(String key, String desc) {
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
	
	public static RequestMethodEnum valueOfIgnoreCase(String key) {
		for (RequestMethodEnum methodEnum : RequestMethodEnum.values()) {
			if(methodEnum.getKey().equalsIgnoreCase(key)) {
				return methodEnum;
			}
		}
    	throw new NoSuchElementException("Cannot found Request Method with key '" + key + "'.");
    }
	
	public static List<Map<String, String>> methodList() {
		List<Map<String, String>> driverList = new LinkedList<Map<String, String>>();
		for (RequestMethodEnum typeEnum : RequestMethodEnum.values()) {
			driverList.add(typeEnum.toMap());
		}
		return driverList;
	}

	public  Map<String, String> toMap() {
		Map<String, String> driverMap = new HashMap<String, String>();
		driverMap.put("key", this.getKey());
		driverMap.put("desc", this.getDesc());
		return driverMap;
	}
	
}
