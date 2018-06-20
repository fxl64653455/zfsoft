package com.zfsoft.boot.disruptor.event.handler.chain;

import com.zfsoft.boot.disruptor.event.DisruptorEvent;

public interface HandlerChainResolver<T extends DisruptorEvent> {

	HandlerChain<T> getChain(T event , HandlerChain<T> originalChain);
	
}
