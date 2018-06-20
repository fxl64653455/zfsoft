package com.zfsoft.boot.websocket.session.handler.chain;

import com.zfsoft.boot.websocket.event.WebSocketMessageEvent;

public interface HandlerChain<T extends WebSocketMessageEvent>{

	void doHandler(T event) throws Exception;
	
}
