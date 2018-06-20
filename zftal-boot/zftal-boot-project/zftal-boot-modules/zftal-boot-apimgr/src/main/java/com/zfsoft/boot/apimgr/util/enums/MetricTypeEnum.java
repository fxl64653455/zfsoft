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
 * @className	： MetricTypeEnum
 * @description	： API度量类型
 * @author 		：万大龙（743）
 * @date		： 2017年11月27日 下午4:50:23
 * @version 	V1.0
 */
public enum MetricTypeEnum {
	
	CAUGE("cauge", "仪表盘"), 
	COUNTER("counter", "计数器"), 
	HISTOGRAM("histogram", "直方图"), 
	METER("meter", "平台订阅"), 
	TIMER("timer", "定时器");

	private String key;
	private String desc;

	private MetricTypeEnum(String key, String desc) {
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
	
	public boolean equals(MetricTypeEnum subType){
		return this.compareTo(subType) == 0;
	}
	
	public boolean equals(String subType){
		return this.compareTo(MetricTypeEnum.valueOfIgnoreCase(subType)) == 0;
	}
	
	public static MetricTypeEnum valueOfIgnoreCase(String key) {
		for (MetricTypeEnum subTypeEnum : MetricTypeEnum.values()) {
			if(subTypeEnum.getKey().equalsIgnoreCase(key)) {
				return subTypeEnum;
			}
		}
    	throw new NoSuchElementException("Cannot found SubType with key '" + key + "'.");
    }
	
	public static List<Map<String, String>> subTypeList() {
		List<Map<String, String>> fromList = new LinkedList<Map<String, String>>();
		for (MetricTypeEnum typeEnum : MetricTypeEnum.values()) {
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
