package com.zfsoft.boot.rocketmq.event;

import java.io.UnsupportedEncodingException;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.springframework.context.ApplicationEvent;

@SuppressWarnings("serial")
public class RocketmqEvent extends ApplicationEvent {

	private MessageQueue messageQueue;
	private MessageExt messageExt;
	private String topic;
	private String tag;
	private String keys;
	private byte[] body;
	/** Route Expression*/
	private String routeExpression;

	public RocketmqEvent(MessageExt msgExt, MessageQueue messageQueue) throws Exception {
		super(msgExt);
		this.topic = msgExt.getTopic();
		this.tag = msgExt.getTags();
		this.body = msgExt.getBody();
		this.keys = msgExt.getKeys();
		this.messageExt = msgExt;
		this.messageQueue = messageQueue;
		this.routeExpression = this.buildRouteExpression(msgExt);
	}
	
	private String buildRouteExpression(MessageExt msgExt) {
		return new StringBuilder("/").append(msgExt.getTopic()).append("/").append(msgExt.getTags()).append("/")
				.append(msgExt.getKeys()).toString();
	}

	public String getMsgBody() {
		try {
			return new String(this.body, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	public String getMsgBody(String code) {
		try {
			return new String(this.body, code);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	public MessageExt getMessageExt() {
		return messageExt;
	}

	public MessageQueue getMessageQueue() {
		return messageQueue;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}

	public String getRouteExpression() {
		return routeExpression;
	}

	public void setRouteExpression(String routeExpression) {
		this.routeExpression = routeExpression;
	}
	
}