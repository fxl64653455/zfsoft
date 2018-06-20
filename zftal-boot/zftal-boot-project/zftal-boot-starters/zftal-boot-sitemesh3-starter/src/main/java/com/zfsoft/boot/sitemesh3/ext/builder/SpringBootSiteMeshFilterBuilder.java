package com.zfsoft.boot.sitemesh3.ext.builder;

import javax.servlet.Filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.springframework.web.servlet.ViewResolver;

import com.zfsoft.boot.sitemesh3.ext.SpringBootSiteMeshFilter;
import com.zfsoft.boot.sitemesh3.ext.SpringBootViewSiteMeshFilter;

public class SpringBootSiteMeshFilterBuilder extends SiteMeshFilterBuilder {

	/**
	 * Create the SiteMesh Filter.
	 */
	public Filter create() {
		return new SpringBootSiteMeshFilter(
				getSelector(), 
				getContentProcessor(),
				getDecoratorSelector(), 
				isIncludeErrorPages());
	}

	/**
	 * Create the SiteMesh Filter.
	 */
	public Filter create(ViewResolver viewResolver) {
		return new SpringBootViewSiteMeshFilter(
				viewResolver, 
				getSelector(), 
				getContentProcessor(),
				getDecoratorSelector(), 
				isIncludeErrorPages());
	}

}

