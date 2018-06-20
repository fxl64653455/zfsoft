package com.zfsoft.boot.demo.setup.listener;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.PingListener;

public class SocketPingListener implements PingListener {

	@Override
	public void onPing(SocketIOClient client) {
		System.out.printf("Ping: Session ID %s", client.getSessionId());
	}

}
