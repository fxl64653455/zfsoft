package com.zfsoft.boot.rocketmq.handler.impl;

import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lmax.disruptor.dsl.Disruptor;
import com.zfsoft.boot.rocketmq.disruptor.RocketmqDataOrderlyEventTranslator;
import com.zfsoft.boot.rocketmq.event.RocketmqDisruptorEvent;
import com.zfsoft.boot.rocketmq.handler.MessageOrderlyHandler;

public class DisruptorEventMessageOrderlyHandler implements MessageOrderlyHandler {

	private static final Logger LOG = LoggerFactory.getLogger(DisruptorEventMessageOrderlyHandler.class);
	
	private Disruptor<RocketmqDisruptorEvent> disruptor;
		
	@Override
	public boolean preHandle(MessageExt msgExt, ConsumeOrderlyContext context) throws Exception {
		return true;
	}
	
	@Override
	public void handleMessage(MessageExt msgExt, ConsumeOrderlyContext context) throws Exception {
		// 生产消息
		disruptor.publishEvent(new RocketmqDataOrderlyEventTranslator(context), msgExt);
	}
	
	@Override
	public void postHandle(MessageExt msgExt, ConsumeOrderlyContext context) throws Exception {
		
	}

	@Override
	public void afterCompletion(MessageExt msgExt, ConsumeOrderlyContext context, Exception ex) throws Exception {
		if(ex != null) {
			LOG.warn("Consume message failed. messageExt:{}", msgExt, ex);
		}
	}

}