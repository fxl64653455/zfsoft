package com.zfsoft.boot.websocket.property;

public class WebsocketStompProperties {

	/**
	 * Register a STOMP over WebSocket endpoint at the given mapping path.
	 */
	private String paths  = "*";
	private String allowedOrigins = "*";
	private WebsocketSockJSProperties sockjs = new WebsocketSockJSProperties();

	public String getPaths() {
		return paths;
	}

	public void setPaths(String paths) {
		this.paths = paths;
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
