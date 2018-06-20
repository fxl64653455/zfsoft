package com.zfsoft.boot.rocketmq.handler;

import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.common.message.MessageExt;

public abstract class MessageOrderlyHandlerAdapter implements MessageOrderlyHandler {

	/**
	 * This implementation always returns {@code true}.
	 */
	@Override
	public boolean preHandle(MessageExt msgExt, ConsumeOrderlyContext context) throws Exception {
		return true;
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void handleMessage(MessageExt msgExt, ConsumeOrderlyContext context) throws Exception {
		
	}
	
	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(MessageExt msgExt, ConsumeOrderlyContext context) throws Exception {
		
	}
	
	/**
	 * This implementation is empty.
	 */
	@Override
	public void afterCompletion(MessageExt msgExt, ConsumeOrderlyContext context, Exception ex) throws Exception {
		
	}

}
