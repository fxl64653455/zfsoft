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
 * @className	： TopicAuditStatusEnum
 * @description	： 审核状态:-1:审核不通过、0:待提交、1:待审核、2：审核通过
 * @author 		：万大龙（743）
 * @date		： 2017年11月18日 下午4:30:26
 * @version 	V1.0
 */
public enum TopicAuditStatusEnum {

	NO_PASS("-1", "审核不通过"),
	WAIT_SUBMIT("0", "待提交"), 
	WAIT_AUTID("1", "待审核"), 
	PASS("2", "审核通过");
	
	private String key;
	private String desc;

	private TopicAuditStatusEnum(String key, String desc) {
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
	
	public static TopicAuditStatusEnum valueOfIgnoreCase(String key) {
		for (TopicAuditStatusEnum fromEnum : TopicAuditStatusEnum.values()) {
			if(fromEnum.getKey().equalsIgnoreCase(key)) {
				return fromEnum;
			}
		}
    	throw new NoSuchElementException("Cannot found Audit Status with key '" + key + "'.");
    }
	
	public static List<Map<String, String>> statusList() {
		List<Map<String, String>> fromList = new LinkedList<Map<String, String>>();
		for (TopicAuditStatusEnum typeEnum : TopicAuditStatusEnum.values()) {
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
