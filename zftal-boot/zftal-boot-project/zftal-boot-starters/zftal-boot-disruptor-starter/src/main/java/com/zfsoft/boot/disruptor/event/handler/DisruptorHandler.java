package com.zfsoft.boot.disruptor.event.handler;

import com.zfsoft.boot.disruptor.event.DisruptorEvent;
import com.zfsoft.boot.disruptor.event.handler.chain.HandlerChain;

public interface DisruptorHandler<T extends DisruptorEvent> {

	public void doHandler(T event, HandlerChain<T> handlerChain) throws Exception;
	
}
