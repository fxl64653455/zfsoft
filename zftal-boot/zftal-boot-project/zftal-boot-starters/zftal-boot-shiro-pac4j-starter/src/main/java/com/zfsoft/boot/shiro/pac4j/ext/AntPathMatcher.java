/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.shiro.pac4j.ext;

import org.pac4j.core.context.WebContext;
import org.pac4j.core.matching.PathMatcher;

public class AntPathMatcher extends PathMatcher {
	
	private org.apache.shiro.util.AntPathMatcher matcher = new org.apache.shiro.util.AntPathMatcher();
	 
	@Override
	public boolean matches(WebContext context) {
		for (String pattern : getExcludedPaths()) {
			if (matcher.match(pattern, context.getPath())) {
				return true;
			}
		}
		return super.matches(context);
	}

}

