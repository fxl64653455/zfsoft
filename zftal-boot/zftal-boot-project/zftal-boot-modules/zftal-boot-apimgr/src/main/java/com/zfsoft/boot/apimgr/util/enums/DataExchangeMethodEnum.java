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
 * @className	： DataExchangeMethodEnum
 * @description	： 数据交换方式：0: 数据表 、1： 自定义SQL语句
 * @author 		：万大龙（743）
 * @date		： 2017年10月24日 下午7:07:26
 * @version 	V1.0
 */
public enum DataExchangeMethodEnum {
	
	TABLE("0", "数据表"), 
	SQL("1", "自定义SQL语句");

	private String key;
	private String desc;

	private DataExchangeMethodEnum(String key, String desc) {
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
	
	public static DataExchangeMethodEnum valueOfIgnoreCase(String key) {
		for (DataExchangeMethodEnum fromEnum : DataExchangeMethodEnum.values()) {
			if(fromEnum.getKey().equalsIgnoreCase(key)) {
				return fromEnum;
			}
		}
    	throw new NoSuchElementException("Cannot found Data Exchange Method From with key '" + key + "'.");
    }
	
	public static List<Map<String, String>> exchangeMethodList() {
		List<Map<String, String>> fromList = new LinkedList<Map<String, String>>();
		for (DataExchangeMethodEnum typeEnum : DataExchangeMethodEnum.values()) {
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
