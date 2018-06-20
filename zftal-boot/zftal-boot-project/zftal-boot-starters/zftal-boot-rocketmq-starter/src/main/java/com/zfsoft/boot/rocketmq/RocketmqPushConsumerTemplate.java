package com.zfsoft.boot.rocketmq;

import org.apache.rocketmq.client.consumer.MQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.selector.SelectMessageQueueByHash;
import org.apache.rocketmq.client.producer.selector.SelectMessageQueueByRandom;
import org.springframework.beans.factory.annotation.Autowired;

import com.zfsoft.boot.rocketmq.enums.ConsumeMode;
import com.zfsoft.boot.rocketmq.event.RocketmqEvent;
import com.zfsoft.boot.rocketmq.handler.EventHandler;
import com.zfsoft.boot.rocketmq.handler.chain.HandlerChainManager;
import com.zfsoft.boot.rocketmq.handler.chain.def.PathMatchingHandlerChainResolver;
import com.zfsoft.boot.rocketmq.handler.impl.RocketmqEventMessageConcurrentlyHandler;
import com.zfsoft.boot.rocketmq.handler.impl.RocketmqEventMessageOrderlyHandler;
import com.zfsoft.boot.rocketmq.util.StringUtils;

public class RocketmqPushConsumerTemplate {

	public final MessageQueueSelector HASH_SELECTOR = new SelectMessageQueueByHash();
	public final MessageQueueSelector RANDOOM_SELECTOR = new SelectMessageQueueByRandom();
	public final String SELECTOR_EXPRESSS_EPARATOR = " || ";
	@Autowired
	private RocketmqEventMessageOrderlyHandler messageOrderlyHandler;
	@Autowired
	private RocketmqEventMessageConcurrentlyHandler messageConcurrentlyHandler;
	@Autowired
	private RocketmqPushConsumerProperties pushConsumerProperties;
	
	private MQPushConsumer consumer;

	public RocketmqPushConsumerTemplate(MQPushConsumer consumer) {
		this.consumer = consumer;
	}
	
	public void subscribe(String topic, String tags, String handlerName, EventHandler<RocketmqEvent> handler) throws MQClientException {

		PathMatchingHandlerChainResolver chainResolver = getChainResolver();
		if(chainResolver == null) {
			return;
		}
		HandlerChainManager<RocketmqEvent> chainManager = chainResolver.getHandlerChainManager();

		//构造一个独一无二的handler名称
		String chainDefinition = handlerName;
		//创建一个新的Handler实例
		chainManager.addHandler(chainDefinition, handler);
		
		//拆分
		String[] tagArr = StringUtils.tokenizeToStringArray(tags, ",");
		for (String tag : tagArr) {
			// 构造一个消息分发规则对应的handler责任链
			// /topic/tags/keys
			String rule = new StringBuilder().append("/").append(topic).append("/").append(tag).append("/*").toString();
			chainManager.createChain(rule, chainDefinition);
		}
		
		// 调用消费端，订阅消息
		String selectorExpress = StringUtils.join(tagArr, SELECTOR_EXPRESSS_EPARATOR);
		switch (pushConsumerProperties.getSelectorType()) {
            case TAG:{
                consumer.subscribe(topic, selectorExpress);
			};break;
            case SQL92:{
                consumer.subscribe(topic, MessageSelector.bySql(selectorExpress));
            };break;
            default:{
                throw new IllegalArgumentException("Property 'selectorType' was wrong.");
            }
        }
		
	}
	
	public void unsubscribe(String topic, String tags, String handlerName) {
		
		PathMatchingHandlerChainResolver chainResolver = getChainResolver();
		if(chainResolver == null) {
			return;
		}
		
		HandlerChainManager<RocketmqEvent> chainManager = chainResolver.getHandlerChainManager();
		
		chainManager.getHandlers().remove(handlerName);
		
		//拆分
		String[] tagArr = StringUtils.tokenizeToStringArray(tags, ",");
		for (String tag : tagArr) {
			// topic/tags/keys
			String rule = new StringBuilder().append(topic).append("/").append(tag).append("/*").toString();
			chainManager.getHandlerChains().remove(rule);
		}
		
		// 调用消费端，取消消息订阅
		consumer.unsubscribe(topic);
		
	}

	public void registerMessageListener(final MessageListenerConcurrently messageListener){
		consumer.registerMessageListener(messageListener);
	}

	public void registerMessageListener(final MessageListenerOrderly messageListener){
		consumer.registerMessageListener(messageListener);
	}
	
	protected PathMatchingHandlerChainResolver getChainResolver() {
		PathMatchingHandlerChainResolver chainResolver = null;
		if( pushConsumerProperties != null && pushConsumerProperties.isEnabled() ) {
			//根据不同的消费模式创建对应的handler
			if (ConsumeMode.ORDERLY.compareTo(pushConsumerProperties.getConsumeMode()) == 0) {
				chainResolver = (PathMatchingHandlerChainResolver) getMessageOrderlyHandler().getHandlerChainResolver();
			}else {
				chainResolver = (PathMatchingHandlerChainResolver) getMessageConcurrentlyHandler().getHandlerChainResolver();
			}
		}
		return chainResolver;
	}

	public RocketmqEventMessageOrderlyHandler getMessageOrderlyHandler() {
		return messageOrderlyHandler;
	}

	public void setMessageOrderlyHandler(RocketmqEventMessageOrderlyHandler messageOrderlyHandler) {
		this.messageOrderlyHandler = messageOrderlyHandler;
	}

	public RocketmqEventMessageConcurrentlyHandler getMessageConcurrentlyHandler() {
		return messageConcurrentlyHandler;
	}

	public void setMessageConcurrentlyHandler(RocketmqEventMessageConcurrentlyHandler messageConcurrentlyHandler) {
		this.messageConcurrentlyHandler = messageConcurrentlyHandler;
	}

	public MQPushConsumer getConsumer() {
		return consumer;
	}

	public void setConsumer(MQPushConsumer consumer) {
		this.consumer = consumer;
	}
	
}
