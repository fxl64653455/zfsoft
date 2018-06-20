package com.zfsoft.boot.websocket.property;

public class WebSocketHandlerProperties {

	/** WebSocketHandler at the specified URL paths. */
	private String paths  = "*";
	/**
	 * WebSocketHandler 的名称. 通过 注解 @SocketHandler
	 * 指定名称的Bean可在此处使用，默认有2种实现：broadcast、dispatch，默认值 dispatch
	 */
	private String handler = "dispatch";
	private String allowedOrigins = "*";
	private WebsocketSockJSProperties sockjs = new WebsocketSockJSProperties();

	public String getPaths() {
		return paths;
	}

	public void setPaths(String paths) {
		this.paths = paths;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getAllowedOrigins() {
		return allowedOrigins;
	}

	public void setAllowedOrigins(String allowedOrigins) {
		this.allowedOrigins = allowedOrigins;
	}

	public WebsocketSockJSProperties getSockjs() {
		return sockjs;
	}

	public void setSockjs(WebsocketSockJSProperties sockjs) {
		this.sockjs = sockjs;
	}

}
