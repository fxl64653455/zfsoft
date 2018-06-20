package com.zfsoft.boot.disruptor.event.handler;

import java.util.List;

import com.zfsoft.boot.disruptor.event.DisruptorEvent;
import com.zfsoft.boot.disruptor.event.handler.chain.HandlerChain;


public interface NamedHandlerList<T extends DisruptorEvent> extends List<DisruptorHandler<T>> {
	 
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
