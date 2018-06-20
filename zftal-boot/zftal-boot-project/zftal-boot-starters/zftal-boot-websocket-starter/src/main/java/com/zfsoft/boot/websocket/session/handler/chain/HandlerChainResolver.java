package com.zfsoft.boot.websocket.session.handler.chain;

import com.zfsoft.boot.websocket.event.WebSocketMessageEvent;

public interface HandlerChainResolver<T extends WebSocketMessageEvent> {

	HandlerChain<T> getChain(T event , HandlerChain<T> originalChain);
	
}
