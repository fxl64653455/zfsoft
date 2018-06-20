/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.util.enums;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.alibaba.fastjson.JSONObject;

public enum TopicTagNameEnum {

	TAG_NOTICE_INPUT("Topic-MBus-Output", "Notice-Output", "消息通知"), 
	TAG_API_INVOKED("Topic-MBus-Output", "API-Invoked", "接口调用事件"),
	TAG_DSB_INVOKED("Topic-DSB-Output", "API-Invoked", "接口调用事件");

	private String topic;
	private String key;
	private String desc;

	private TopicTagNameEnum(String topic, String key, String desc) {
		this.topic = topic;
		this.key = key;
		this.desc = desc;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
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

	public static TopicTagNameEnum valueOfIgnoreCase(String key) {
		for (TopicTagNameEnum tagEnum : TopicTagNameEnum.values()) {
			if (tagEnum.getKey().equalsIgnoreCase(key)) {
				return tagEnum;
			}
		}
		throw new NoSuchElementException("Cannot found Tag Name with key '" + key + "'.");
	}

	public static String tags(String topic) {
		List<Map<String, String>> tagList = new LinkedList<Map<String, String>>();
		for (TopicTagNameEnum tagEnum : TopicTagNameEnum.values()) {
			if (tagEnum.getTopic().equals(topic)) {
				tagList.add(tagEnum.toMap());
			}
		}
		return JSONObject.toJSONString(tagList);
	}

	public Map<String, String> toMap() {
		Map<String, String> driverMap = new HashMap<String, String>();
		driverMap.put("key", this.getKey());
		driverMap.put("desc", this.getDesc());
		return driverMap;
	}

}
