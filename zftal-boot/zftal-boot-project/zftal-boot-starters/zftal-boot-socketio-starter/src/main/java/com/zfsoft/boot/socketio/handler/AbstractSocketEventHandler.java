/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.socketio.handler;

import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;

public class AbstractSocketEventHandler {

	private static Logger LOG = LoggerFactory.getLogger(AbstractSocketEventHandler.class);
	
	// 添加connect事件，当客户端发起连接时调用，本文中将clientid与sessionid存入数据库
	// 方便后面发送消息时查找到对应的目标client,
	@OnConnect
	public void onConnect(SocketIOClient client) {
		LOG.debug("Connect OK.");
		LOG.debug("Session ID  : %s", client.getSessionId());
		LOG.debug("HttpHeaders : %s", client.getHandshakeData().getHttpHeaders());
		LOG.debug("UrlParams   : %s", client.getHandshakeData().getUrlParams());
		
		client.sendEvent("welcome", "ok");
	}
	
	// 添加@OnDisconnect事件，客户端断开连接时调用，刷新客户端信息
	@OnDisconnect
	public void onDisconnect(SocketIOClient client) {
		LOG.debug("Disconnect OK.");
		LOG.debug("Session ID  : %s", client.getSessionId());
	}

	public Map<UUID, SocketIOClient> getClients(String group) {
		return SocketIOClientHolder.getClients(group);
	}

}
