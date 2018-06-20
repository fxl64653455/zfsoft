package com.zfsoft.boot.rocketmq.handler.impl;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lmax.disruptor.dsl.Disruptor;
import com.zfsoft.boot.rocketmq.disruptor.RocketmqDataConcurrentlyEventTranslator;
import com.zfsoft.boot.rocketmq.event.RocketmqDisruptorEvent;
import com.zfsoft.boot.rocketmq.handler.MessageConcurrentlyHandler;

public class DisruptorEventMessageConcurrentlyHandler implements MessageConcurrentlyHandler {

	private static final Logger LOG = LoggerFactory.getLogger(DisruptorEventMessageConcurrentlyHandler.class);
	
	private Disruptor<RocketmqDisruptorEvent> disruptor;
	
	@Override
	public boolean preHandle(MessageExt msgExt, ConsumeConcurrentlyContext context) throws Exception {
		return true;
	}

	@Override
	public void handleMessage(MessageExt msgExt, ConsumeConcurrentlyContext context) throws Exception {
		// 生产消息
		disruptor.publishEvent(new RocketmqDataConcurrentlyEventTranslator(context), msgExt);
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
	

}