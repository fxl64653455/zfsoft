package com.zfsoft.boot.rocketmq.handler;

import com.zfsoft.boot.rocketmq.event.RocketmqEvent;
import com.zfsoft.boot.rocketmq.handler.chain.HandlerChain;

/**
 */
public interface EventHandler<T extends RocketmqEvent> {

	public void doHandler(T event, HandlerChain<T> handlerChain) throws Exception;
	
}
