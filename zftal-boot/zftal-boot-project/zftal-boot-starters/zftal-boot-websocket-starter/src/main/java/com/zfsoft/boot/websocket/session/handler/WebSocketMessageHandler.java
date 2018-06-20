package com.zfsoft.boot.websocket.session.handler;

import com.zfsoft.boot.websocket.event.WebSocketMessageEvent;
import com.zfsoft.boot.websocket.session.handler.chain.HandlerChain;

public interface WebSocketMessageHandler<T extends WebSocketMessageEvent> {

	public void doHandler(T event, HandlerChain<T> handlerChain) throws Exception;
	
}
