package com.zfsoft.boot.metrics.ext.filter;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricFilter;

public class AntExpressFilter implements MetricFilter {

	final PathMatcher matcher = new AntPathMatcher();
	final String pattern;
	
	public AntExpressFilter(String pattern) {
		this.pattern = pattern;
	}
	
	@Override
	public boolean matches(String name, Metric metric) {
		return matcher.match(pattern, name);
	}

	@Override
	public String toString() {
		return "[AntExpressFilter pattern=" + pattern + "]";
	}

}