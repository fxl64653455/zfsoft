package com.zfsoft.boot.disruptor.event.handler.chain;

import com.zfsoft.boot.disruptor.event.DisruptorEvent;

public interface HandlerChain<T extends DisruptorEvent>{

	void doHandler(T event) throws Exception;
	
}
