/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.shiro.pac4j.cas;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.shiro.util.AntPathMatcher;
import org.jasig.cas.client.authentication.UrlPatternMatcherStrategy;

import com.zfsoft.boot.shiro.utils.StringUtils;

public class AntPatternMatcherStrategy implements UrlPatternMatcherStrategy {

	private AntPathMatcher matcher = new AntPathMatcher();
	private String[] patterns;

	@Override
	public boolean matches(String url) {
		try {
			System.out.println(new URL(url).getHost());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		
		for (String pattern : patterns) {
			if (matcher.match(pattern, url)) {
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
