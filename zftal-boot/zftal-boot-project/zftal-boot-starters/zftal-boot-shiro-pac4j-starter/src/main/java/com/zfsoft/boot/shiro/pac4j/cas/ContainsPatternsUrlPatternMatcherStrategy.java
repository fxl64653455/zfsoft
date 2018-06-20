/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.shiro.pac4j.cas;

import org.jasig.cas.client.authentication.UrlPatternMatcherStrategy;

import com.zfsoft.boot.shiro.utils.StringUtils;

public class ContainsPatternsUrlPatternMatcherStrategy implements UrlPatternMatcherStrategy {

    private String[] patterns;

	@Override
	public boolean matches(String url) {
		for (String pattern : patterns) {
			if (url.contains(pattern)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void setPattern(String pattern) {
		this.patterns = StringUtils.tokenizeToStringArray(pattern);
	}
}