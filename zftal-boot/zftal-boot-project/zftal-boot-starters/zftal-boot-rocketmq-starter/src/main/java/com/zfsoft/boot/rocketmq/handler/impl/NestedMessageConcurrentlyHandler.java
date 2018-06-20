package com.zfsoft.boot.rocketmq.handler.impl;

import java.util.List;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.zfsoft.boot.rocketmq.handler.MessageConcurrentlyHandler;

/**
 * 
 * @className	： NestedMessageConcurrentlyHandler
 * @description	： 嵌套的多路消息处理器：解决统一消息交由多个处理实现处理问题
 * @author 		：万大龙（743）
 * @date		： 2017年11月14日 下午4:39:04
 * @version 	V1.0
 */
public class NestedMessageConcurrentlyHandler implements MessageConcurrentlyHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(NestedMessageConcurrentlyHandler.class);
	private final List<MessageConcurrentlyHandler> handlers;

	public NestedMessageConcurrentlyHandler(List<MessageConcurrentlyHandler> handlers) {
		this.handlers = handlers;
	}

	@Override
	public boolean preHandle(MessageExt msgExt, ConsumeConcurrentlyContext context) throws Exception {
		return true;
	}
	
	@Override
	public void handleMessage(MessageExt msgExt, ConsumeConcurrentlyContext context) throws Exception {
		if(isNested()){
			for (MessageConcurrentlyHandler handler : getHandlers()) {
				handler.handleMessage(msgExt, context);
			}
		} else {
			 throw new IllegalArgumentException(" Not Found MessageConcurrentlyHandler .");
		}
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
	
	protected boolean isNested() {
		if(CollectionUtils.isEmpty(getHandlers())){
			return false;
		}
		return true;
	}
	
	public List<MessageConcurrentlyHandler> getHandlers() {
		return handlers;
	}

}
