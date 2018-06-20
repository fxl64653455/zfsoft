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
 * @className ： DataExchangeTypeEnum {
 * @description ： 数据交换类型：0: 新增数据 、1： 删除数据、2： 更新数据、3：查询数据
 * @author ：万大龙（743）
 * @date ： 2017年10月24日 下午7:07:26
 * @version V1.0
 */
public enum DataExchangeTypeEnum {

	INSERT("0", "insert", "新增数据"), 
	DELETE("1", "delete", "删除数据"), 
	UPDATE("2", "update", "更新数据"), 
	QUERY("3",	"query", "查询数据");

	private String key;
	private String tag;
	private String desc;

	private DataExchangeTypeEnum(String key, String tag, String desc) {
		this.key = key;
		this.tag = tag;
		this.desc = desc;
	}

	public String getKey() {
		return key;
	}

	public String getTag() {
		return tag;
	}

	public String getDesc() {
		return desc;
	}

	public boolean equals(DataExchangeTypeEnum key){
		return this.compareTo(key) == 0;
	}
	
	public boolean equals(String key){
		return this.compareTo(DataExchangeTypeEnum.valueOfIgnoreCase(key)) == 0;
	}
	
	public static DataExchangeTypeEnum valueOfIgnoreCase(String key) {
		for (DataExchangeTypeEnum fromEnum : DataExchangeTypeEnum.values()) {
			if (fromEnum.getKey().equalsIgnoreCase(key)) {
				return fromEnum;
			}
		}
		throw new NoSuchElementException("Cannot found Data Exchange Type with key '" + key + "'.");
	}

	public static List<Map<String, String>> exchangeTypeList() {
		List<Map<String, String>> fromList = new LinkedList<Map<String, String>>();
		for (DataExchangeTypeEnum typeEnum : DataExchangeTypeEnum.values()) {
			fromList.add(typeEnum.toMap());
		}
		return fromList;
	}

	public Map<String, String> toMap() {
		Map<String, String> fromMap = new HashMap<String, String>();
		fromMap.put("key", this.getKey());
		fromMap.put("desc", this.getDesc());
		return fromMap;
	}

}
