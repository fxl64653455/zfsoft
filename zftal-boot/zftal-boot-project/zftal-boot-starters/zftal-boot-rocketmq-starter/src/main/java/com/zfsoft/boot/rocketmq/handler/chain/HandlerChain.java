package com.zfsoft.boot.rocketmq.handler.chain;

import com.zfsoft.boot.rocketmq.event.RocketmqEvent;

public interface HandlerChain<T extends RocketmqEvent>{

	void doHandler(T event) throws Exception;
	
}
