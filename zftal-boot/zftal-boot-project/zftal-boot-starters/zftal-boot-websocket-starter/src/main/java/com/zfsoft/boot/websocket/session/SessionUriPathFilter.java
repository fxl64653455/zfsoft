/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.websocket.session;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.socket.WebSocketSession;

public class SessionUriPathFilter implements SessionFilter {

	private PathMatcher matcher = new AntPathMatcher();
	private String uriPattern = "/**";
	
	public SessionUriPathFilter(String pattern) {
		this.uriPattern = pattern;
	}
	
	@Override
	public boolean matches(WebSocketSession session) {
		return matcher.match(uriPattern, session.getUri().getPath());
	}
	
}
