package com.zfsoft.boot.socketio;

import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(SocketioRedisProperties.PREFIX)
public class SocketioRedisProperties extends Config {

	public static final String PREFIX = "socketio.redis";

	/**
	 * Enable Socketio Redis Store.
	 */
	private boolean enabled = false;
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
