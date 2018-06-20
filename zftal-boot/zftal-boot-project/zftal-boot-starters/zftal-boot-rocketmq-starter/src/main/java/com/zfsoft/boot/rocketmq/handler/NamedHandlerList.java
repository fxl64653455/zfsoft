package com.zfsoft.boot.rocketmq.handler;

import java.util.List;

import com.zfsoft.boot.rocketmq.event.RocketmqEvent;
import com.zfsoft.boot.rocketmq.handler.chain.HandlerChain;


public interface NamedHandlerList<T extends RocketmqEvent> extends List<EventHandler<T>> {
	 
	/**
     * Returns the configuration-unique name assigned to this {@code Handler} list.
     */
    String getName();

    /**
     * Returns a new {@code HandlerChain<T>} instance that will first execute this list's {@code Handler}s (in list order)
     * and end with the execution of the given {@code handlerChain} instance.
     */
    HandlerChain<T> proxy(HandlerChain<T> handlerChain);
    
}
