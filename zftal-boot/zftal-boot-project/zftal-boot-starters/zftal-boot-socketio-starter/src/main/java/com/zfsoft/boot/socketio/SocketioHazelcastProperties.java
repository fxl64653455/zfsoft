package com.zfsoft.boot.socketio;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.hazelcast.client.config.ClientConfig;

@ConfigurationProperties(SocketioHazelcastProperties.PREFIX)
public class SocketioHazelcastProperties extends ClientConfig {

	public static final String PREFIX = "socketio.hazelcast";

	/**
	 * Enable Socketio Hazelcast Store .
	 */
	private boolean enabled = false;
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
