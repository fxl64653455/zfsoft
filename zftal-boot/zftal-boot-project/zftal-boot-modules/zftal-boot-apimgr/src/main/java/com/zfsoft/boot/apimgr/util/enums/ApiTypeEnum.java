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
 * @className	： ApiTypeEnum
 * @description	： 接口分类：指接口平台的接口类型，是数据源接口还是业务型接口
 * @author 		：万大龙（743）
 * @date		： 2017年10月24日 下午12:22:56
 * @version 	V1.0
 */
public enum ApiTypeEnum {
	
	BIZ("biz", "业务型接口（第三方系统接口）"), 
	DATA("data", "数据源接口（基于创建的数据源连接的接口）");

	private String key;
	private String desc;

	private ApiTypeEnum(String key, String desc) {
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
	
	public static ApiTypeEnum valueOfIgnoreCase(String key) {
		for (ApiTypeEnum apiType : ApiTypeEnum.values()) {
			if(apiType.getKey().equalsIgnoreCase(key)) {
				return apiType;
			}
		}
    	throw new NoSuchElementException("Cannot found ApiType with key '" + key + "'.");
    }
	
	public static List<Map<String, String>> typeList() {
		List<Map<String, String>> driverList = new LinkedList<Map<String, String>>();
		for (ApiTypeEnum typeEnum : ApiTypeEnum.values()) {
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
