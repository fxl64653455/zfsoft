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
 * @className	： BizApiTypeEnum
 * @description	： 业务接口类型：主指提供的第三方接口实现方式
 * @author 		：万大龙（743）
 * @date		： 2017年10月24日 下午12:20:43
 * @version 	V1.0
 */
public enum BizApiTypeEnum {
	
	HTTP("Http", "HTTP"),
	HTTPS("Https", "HTTPS"),
	AXIS("Axis", "WS（Axis）"),
//	AXIS2("Axis2", "WS（Axis2）"),
	CXF("CXF", "WS（CXF）");
	
	private String key;
	private String desc;

	private BizApiTypeEnum(String key, String desc) {
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
	
	public static BizApiTypeEnum valueOfIgnoreCase(String key) {
		for (BizApiTypeEnum bizApiType : BizApiTypeEnum.values()) {
			if(bizApiType.getKey().equalsIgnoreCase(key)) {
				return bizApiType;
			}
		}
    	throw new NoSuchElementException("Cannot found BizApiType with key '" + key + "'.");
    }
	
	public static List<Map<String, String>> typeList() {
		List<Map<String, String>> driverList = new LinkedList<Map<String, String>>();
		for (BizApiTypeEnum typeEnum : BizApiTypeEnum.values()) {
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
