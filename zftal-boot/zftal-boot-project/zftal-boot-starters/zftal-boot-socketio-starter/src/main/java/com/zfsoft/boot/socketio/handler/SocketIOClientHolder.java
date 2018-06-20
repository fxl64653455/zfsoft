/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.socketio.handler;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.corundumstudio.socketio.SocketIOClient;

public class SocketIOClientHolder {

	private static final ConcurrentHashMap<String, Map<UUID, SocketIOClient>> COMPLIED_CLIENTS = new ConcurrentHashMap<String, Map<UUID, SocketIOClient>>();

	public static Map<UUID, SocketIOClient> getClients(String group) {
		if (group != null) {
			Map<UUID, SocketIOClient> ret = COMPLIED_CLIENTS.get(group);
			if (ret != null) {
				return ret;
			}
			ret = new ConcurrentHashMap<UUID, SocketIOClient>();
			Map<UUID, SocketIOClient> existing = COMPLIED_CLIENTS.putIfAbsent(group, ret);
			if (existing != null) {
				ret = existing;
			}
			
			return ret;
		}
		return null;
	}
	
}
