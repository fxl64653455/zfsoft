package com.zfsoft.boot.demo.setup.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.zfsoft.boot.websocket.handler.BroadcastWebSocketsHandler;
import com.zfsoft.boot.websocket.handler.MessageEventWebSocketHandler;
import com.zfsoft.boot.websocket.interceptor.HandshakeSessionInterceptor;

@Configuration
public class DefaultWebSocketConfig implements WebSocketConfigurer {

	@Autowired
	@Qualifier("broadcastWebSocketsHandler")
	private BroadcastWebSocketsHandler broadcastWebSocketsHandler;
	@Autowired
	private MessageEventWebSocketHandler messageEventWebSocketHandler;
	@Autowired
	private HandshakeSessionInterceptor handshakeSessionInterceptor;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		
		registry.addHandler(messageEventWebSocketHandler, "/message").addInterceptors(handshakeSessionInterceptor).setAllowedOrigins("*").withSockJS();
		
	}

	public BroadcastWebSocketsHandler getBroadcastWebSocketsHandler() {
		return broadcastWebSocketsHandler;
	}

	public void setBroadcastWebSocketsHandler(BroadcastWebSocketsHandler broadcastWebSocketsHandler) {
		this.broadcastWebSocketsHandler = broadcastWebSocketsHandler;
	}

	public MessageEventWebSocketHandler getMessageEventWebSocketHandler() {
		return messageEventWebSocketHandler;
	}

	public void setMessageEventWebSocketHandler(MessageEventWebSocketHandler messageEventWebSocketHandler) {
		this.messageEventWebSocketHandler = messageEventWebSocketHandler;
	}
	
}
