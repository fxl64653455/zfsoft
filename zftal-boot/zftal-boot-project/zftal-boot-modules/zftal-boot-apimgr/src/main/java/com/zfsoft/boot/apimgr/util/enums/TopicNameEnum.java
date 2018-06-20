/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.util.enums;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public enum TopicNameEnum {
	
	/**
	 * 数据总线消息
	 */
	TOPIC_MBUS_OUTPUT( "Topic-MBus-Output" , "MBus消息主题"),
	/**DSB消息主题*/
	TOPIC_DSB_OUTPUT( "Topic-DSB-Output" , "DSB消息主题");
	
	private String key;
	private String desc;

	private TopicNameEnum(String key, String desc) {
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
	
	public static TopicNameEnum valueOfIgnoreCase(String key) {
		for (TopicNameEnum topicEnum : TopicNameEnum.values()) {
			if(topicEnum.getKey().equalsIgnoreCase(key)) {
				return topicEnum;
			}
		}
    	throw new NoSuchElementException("Cannot found Topic Name with key '" + key + "'.");
    }
	
	public static List<Map<String, String>> topicList() {
		List<Map<String, String>> topicList = new LinkedList<Map<String, String>>();
		for (TopicNameEnum topicEnum : TopicNameEnum.values()) {
			topicList.add(topicEnum.toMap());
		}
		return topicList;
	}

	public  Map<String, String> toMap() {
		Map<String, String> topicMap = new HashMap<String, String>();
		topicMap.put("key", this.getKey());
		topicMap.put("desc", this.getDesc());
		topicMap.put("tags", TopicTagNameEnum.tags(this.getKey()));
		return topicMap;
	}
	
}
