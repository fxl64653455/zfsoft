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
 * @className	： SubTypeEnum
 * @description	： 订阅类型
 * @author 		：樊新亮（1505）
 * @date		： 2017年11月21日 下午4:12:22
 * @version 	V1.0
 */
public enum SubTypeEnum {
	
	APP("app", "应用订阅"), 
	dsb("dsb", "平台订阅");

	private String key;
	private String desc;

	private SubTypeEnum(String key, String desc) {
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
	
	public boolean equals(SubTypeEnum subType){
		return this.compareTo(subType) == 0;
	}
	
	public boolean equals(String subType){
		return this.compareTo(SubTypeEnum.valueOfIgnoreCase(subType)) == 0;
	}
	
	public static SubTypeEnum valueOfIgnoreCase(String key) {
		for (SubTypeEnum subTypeEnum : SubTypeEnum.values()) {
			if(subTypeEnum.getKey().equalsIgnoreCase(key)) {
				return subTypeEnum;
			}
		}
    	throw new NoSuchElementException("Cannot found SubType with key '" + key + "'.");
    }
	
	public static List<Map<String, String>> subTypeList() {
		List<Map<String, String>> fromList = new LinkedList<Map<String, String>>();
		for (SubTypeEnum typeEnum : SubTypeEnum.values()) {
			fromList.add(typeEnum.toMap());
		}
		return fromList;
	}

	public  Map<String, String> toMap() {
		Map<String, String> fromMap = new HashMap<String, String>();
		fromMap.put("key", this.getKey());
		fromMap.put("desc", this.getDesc());
		return fromMap;
	}
	
}
