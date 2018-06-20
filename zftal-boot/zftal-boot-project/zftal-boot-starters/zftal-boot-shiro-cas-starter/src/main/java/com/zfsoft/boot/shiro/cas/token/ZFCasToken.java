/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.shiro.cas.token;

import java.util.Map;

import com.zfsoft.shiro.token.ZfSsoToken;

@SuppressWarnings("serial")
public class ZFCasToken extends ZfSsoToken {
	
	/** The service ticket returned by the CAS server */
    private String ticket = null;
	/** 其他参数 */
	private Map<String, Object> attrs;

	public ZFCasToken(String host) {
		this.host = host;
	}
	
	@Override
	public Object getCredentials() {
		return ticket;
	}
	
	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public Map<String, Object> getAttrs() {
		return attrs;
	}

	public void setAttrs(Map<String, Object> attrs) {
		this.attrs = attrs;
	}
	
}
