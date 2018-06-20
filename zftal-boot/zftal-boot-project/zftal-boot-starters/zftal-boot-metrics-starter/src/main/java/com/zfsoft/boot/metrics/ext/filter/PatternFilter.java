package com.zfsoft.boot.metrics.ext.filter;

import java.util.regex.Pattern;

import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricFilter;

public class PatternFilter implements MetricFilter {

	final Pattern filter;

	public PatternFilter(String pattern) {
		filter = Pattern.compile(pattern);
	}

	@Override
	public boolean matches(String name, Metric metric) {
		return filter.matcher(name).matches();
	}

	@Override
	public String toString() {
		return "[PatternFilter regex=" + filter.pattern() + "]";
	}

}