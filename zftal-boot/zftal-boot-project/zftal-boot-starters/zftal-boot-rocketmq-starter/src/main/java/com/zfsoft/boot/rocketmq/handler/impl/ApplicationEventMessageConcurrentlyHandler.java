package com.zfsoft.boot.rocketmq.handler.impl;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import com.zfsoft.boot.rocketmq.event.RocketmqEvent;
import com.zfsoft.boot.rocketmq.handler.MessageConcurrentlyHandler;

public class ApplicationEventMessageConcurrentlyHandler implements MessageConcurrentlyHandler, ApplicationEventPublisherAware {

	private static final Logger LOG = LoggerFactory.getLogger(ApplicationEventMessageConcurrentlyHandler.class);
	private ApplicationEventPublisher eventPublisher;
	
	@Override
	public boolean preHandle(MessageExt msgExt, ConsumeConcurrentlyContext context) throws Exception {
		return true;
	}

	@Override
	public void handleMessage(MessageExt msgExt, ConsumeConcurrentlyContext context) throws Exception {
		// 发布消息到达的事件，以便分发到每个tag的监听方法
		getEventPublisher().publishEvent(new RocketmqEvent(msgExt, context.getMessageQueue()));
	}
	
	@Override
	public void postHandle(MessageExt msgExt, ConsumeConcurrentlyContext context) throws Exception {
		
	}

	@Override
	public void afterCompletion(MessageExt msgExt, ConsumeConcurrentlyContext context, Exception ex) throws Exception {
		if(ex != null) {
			LOG.warn("Consume message failed. messageExt:{}", msgExt, ex);
		}
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.eventPublisher = applicationEventPublisher;
	}

	public ApplicationEventPublisher getEventPublisher() {
		return eventPublisher;
	}

}