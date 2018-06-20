package com.zfsoft.boot.rocketmq.handler.impl;

import java.util.List;

import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.zfsoft.boot.rocketmq.handler.MessageOrderlyHandler;

/**
 * 
 * @className	： NestedMessageOrderlyHandler
 * @description	：  嵌套的顺序消息处理器：解决统一消息交由多个处理实现处理问题
 * @author 		：万大龙（743）
 * @date		： 2017年11月14日 下午4:39:12
 * @version 	V1.0
 */
public class NestedMessageOrderlyHandler implements MessageOrderlyHandler {

	private static final Logger LOG = LoggerFactory.getLogger(NestedMessageOrderlyHandler.class);
	private final List<MessageOrderlyHandler> handlers;

	public NestedMessageOrderlyHandler(List<MessageOrderlyHandler> handlers) {
		this.handlers = handlers;
	}
	
	@Override
	public boolean preHandle(MessageExt msgExt, ConsumeOrderlyContext context) throws Exception {
		return true;
	}
	
	@Override
	public void handleMessage(MessageExt msgExt, ConsumeOrderlyContext context) throws Exception {
		if(isNested()){
			for (MessageOrderlyHandler handler : getHandlers()) {
				handler.handleMessage(msgExt, context);
			}
		} else {
			 throw new IllegalArgumentException(" Not Found MessageOrderlyHandler .");
		}
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
	
	protected boolean isNested() {
		if(CollectionUtils.isEmpty(getHandlers())){
			return false;
		}
		return true;
	}
	
	public List<MessageOrderlyHandler> getHandlers() {
		return handlers;
	}
	
}
