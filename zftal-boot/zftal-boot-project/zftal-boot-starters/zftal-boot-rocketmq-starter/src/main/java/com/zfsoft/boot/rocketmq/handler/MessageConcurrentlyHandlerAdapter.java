package com.zfsoft.boot.rocketmq.handler;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.common.message.MessageExt;

public abstract class MessageConcurrentlyHandlerAdapter implements MessageConcurrentlyHandler {

	/**
	 * This implementation always returns {@code true}.
	 */
	@Override
	public boolean preHandle(MessageExt msgExt, ConsumeConcurrentlyContext context) throws Exception {
		return true;
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void handleMessage(MessageExt msgExt, ConsumeConcurrentlyContext context) throws Exception {
		
	}
	
	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(MessageExt msgExt, ConsumeConcurrentlyContext context) throws Exception {
		
	}
	
	/**
	 * This implementation is empty.
	 */
	@Override
	public void afterCompletion(MessageExt msgExt, ConsumeConcurrentlyContext context, Exception ex) throws Exception {
		
	}

}
