package com.zfsoft.boot.rocketmq.handler.chain;

import com.zfsoft.boot.rocketmq.event.RocketmqEvent;

public interface HandlerChainResolver<T extends RocketmqEvent> {

	HandlerChain<T> getChain(T event , HandlerChain<T> originalChain);
	
}
