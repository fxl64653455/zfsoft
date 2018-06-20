package com.zfsoft.boot.cxf;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(CxfJaxwsProperties.PREFIX)
public class CxfJaxwsProperties {

	public static final String PREFIX = "cxf.api";

	/**
	 * Enable CXF.
	 */
	private boolean enabled = false;
	 
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}