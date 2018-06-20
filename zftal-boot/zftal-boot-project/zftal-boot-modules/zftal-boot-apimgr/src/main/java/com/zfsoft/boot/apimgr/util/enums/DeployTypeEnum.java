/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.util.enums;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * 
 * @className	： DeployTypeEnum
 * @description	： 接口发布类型：主指接口平台的接口发布方式
 * @author 		：万大龙（743）
 * @date		： 2017年10月24日 下午12:22:22
 * @version 	V1.0
 */
public enum DeployTypeEnum {
	
	REST("Rest", "/api/","REST"), 
	CXF("WS", "/services/","WS（CXF）");
	
	private String key;
	private String prefix;
	private String desc;

	private DeployTypeEnum(String key, String prefix, String desc) {
		this.key = key;
		this.prefix = prefix;
		this.desc = desc;
	}

	public String getKey() {
		return key;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getDesc() {
		return desc;
	}

	public static DeployTypeEnum valueOfIgnoreCase(String key) {
		for (DeployTypeEnum typeEnum : DeployTypeEnum.values()) {
			if(typeEnum.getKey().equalsIgnoreCase(key)) {
				return typeEnum;
			}
		}
    	throw new NoSuchElementException("Cannot found Deploy Type with key '" + key + "'.");
    }
	
	public static List<Map<String, String>> typeList() {
		List<Map<String, String>> driverList = new LinkedList<Map<String, String>>();
		for (DeployTypeEnum typeEnum : DeployTypeEnum.values()) {
			driverList.add(typeEnum.toMap());
		}
		return driverList;
	}

	public  Map<String, String> toMap() {
		Map<String, String> driverMap = new HashMap<String, String>();
		driverMap.put("key", this.getKey());
		driverMap.put("prefix", this.getPrefix());
		driverMap.put("desc", this.getDesc());
		return driverMap;
	}
	
}
