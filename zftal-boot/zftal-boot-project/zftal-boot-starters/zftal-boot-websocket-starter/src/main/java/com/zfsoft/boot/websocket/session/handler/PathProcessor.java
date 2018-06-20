package com.zfsoft.boot.websocket.session.handler;

import com.zfsoft.boot.websocket.event.WebSocketMessageEvent;

/**
 * 给Handler设置路径
 */
public interface PathProcessor<T extends WebSocketMessageEvent> {
	
	WebSocketMessageHandler<T> processPath(String path);

}
