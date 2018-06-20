package com.zfsoft.boot.rocketmq.handler.impl;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zfsoft.boot.rocketmq.event.RocketmqEvent;
import com.zfsoft.boot.rocketmq.handler.AbstractRouteableMessageHandler;
import com.zfsoft.boot.rocketmq.handler.MessageConcurrentlyHandler;
import com.zfsoft.boot.rocketmq.handler.chain.HandlerChain;
import com.zfsoft.boot.rocketmq.handler.chain.HandlerChainResolver;
import com.zfsoft.boot.rocketmq.handler.chain.ProxiedHandlerChain;

public class RocketmqEventMessageConcurrentlyHandler extends AbstractRouteableMessageHandler<RocketmqEvent> implements MessageConcurrentlyHandler {

	private static final Logger LOG = LoggerFactory.getLogger(RocketmqEventMessageConcurrentlyHandler.class);
	
	public RocketmqEventMessageConcurrentlyHandler(HandlerChainResolver<RocketmqEvent> filterChainResolver) {
		super(filterChainResolver);
	}
	
	@Override
	public boolean preHandle(MessageExt msgExt, ConsumeConcurrentlyContext context) throws Exception {
		return true;
	}
	
	@Override
	public void handleMessage(MessageExt msgExt, ConsumeConcurrentlyContext context) throws Exception {
		//构造原始链对象
		HandlerChain<RocketmqEvent>	originalChain = new ProxiedHandlerChain();
		//执行事件处理链
		this.doHandler(new RocketmqEvent(msgExt, context.getMessageQueue()), originalChain);
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