package com.zfsoft.boot.socketio;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.corundumstudio.socketio.Configuration;

@ConfigurationProperties(SocketioServerProperties.PREFIX)
public class SocketioServerProperties extends Configuration {

	public static final String PREFIX = "socketio.server";

	/**
	 * Enable Socketio Server.
	 */
	private boolean enabled = false;
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
