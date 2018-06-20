/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.shiro;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(ShiroPac4jOidcProperties.PREFIX)
public class ShiroPac4jOidcProperties {

	public static final String PREFIX = "shiro.pac4j.oidc";
	
	/** Whether Enable Pac4j Oidc. */
	private boolean enabled = false;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
