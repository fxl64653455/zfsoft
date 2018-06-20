/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.shiro;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(ShiroPac4jKerberosProperties.PREFIX)
public class ShiroPac4jKerberosProperties {

	public static final String PREFIX = "shiro.pac4j.kerberos";
	
	/** Whether Enable Pac4j Kerberos. */
	private boolean enabled = false;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
