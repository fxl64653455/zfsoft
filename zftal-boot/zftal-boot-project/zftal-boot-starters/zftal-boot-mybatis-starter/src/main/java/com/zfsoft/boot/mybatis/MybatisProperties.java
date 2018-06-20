package com.zfsoft.boot.mybatis;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * Configuration properties for MyBatis.
 */
@ConfigurationProperties(prefix = MybatisProperties.MYBATIS_PREFIX)
public class MybatisProperties {

	public static final String MYBATIS_PREFIX = "mybatis";

	private Class<? extends Annotation> annotationClass;

	/**
	 * Location of MyBatis xml config file.
	 */
	private String configLocation;

	/**
	 * Locations of MyBatis mapper files.
	 */
	private String[] mapperLocations;

	/**
	 * Packages to search type aliases. (Package delimiters are ",; \t\n")
	 */
	private String typeAliasesPackage;

	/**
	 * Packages to search for type handlers. (Package delimiters are ",; \t\n")
	 */
	private String typeHandlersPackage;

	/**
	 * Indicates whether perform presence check of the MyBatis xml config file.
	 */
	private boolean checkConfigLocation = false;

	/**
	 * Execution mode for {@link org.mybatis.spring.SqlSessionTemplate}.
	 */
	private ExecutorType executorType;

	/**
	 * Externalized properties for MyBatis configuration.
	 */
	private Properties configurationProperties;

	private String basePackage;

	/**
	 * A Configuration object for customize default settings. If
	 * {@link #configLocation} is specified, this property is not used.
	 */
	@NestedConfigurationProperty
	private Configuration configuration;

	/**
	 * This property specifies the annotation that the scanner will search for.
	 * <p>
	 * The scanner will register all interfaces in the base package that also have
	 * the specified annotation.
	 * <p>
	 * Note this can be combined with markerInterface.
	 *
	 * @param annotationClass
	 *            annotation class
	 */
	public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
		this.annotationClass = annotationClass;
	}

	public Class<? extends Annotation> getAnnotationClass() {
		return annotationClass;
	}

	/**
	 * @since 1.1.0
	 */
	public String getConfigLocation() {
		return this.configLocation;
	}

	/**
	 * @since 1.1.0
	 */
	public void setConfigLocation(String configLocation) {
		this.configLocation = configLocation;
	}

	/**
	 * This property lets you set the base package for your mapper interface files.
	 * <p>
	 * You can set more than one package by using a semicolon or comma as a
	 * separator.
	 * <p>
	 * Mappers will be searched for recursively starting in the specified
	 * package(s).
	 *
	 * @param basePackage
	 *            base package name
	 */
	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public String[] getMapperLocations() {
		return this.mapperLocations;
	}

	public void setMapperLocations(String[] mapperLocations) {
		this.mapperLocations = mapperLocations;
	}

	public String getTypeHandlersPackage() {
		return this.typeHandlersPackage;
	}

	public void setTypeHandlersPackage(String typeHandlersPackage) {
		this.typeHandlersPackage = typeHandlersPackage;
	}

	public String getTypeAliasesPackage() {
		return this.typeAliasesPackage;
	}

	public void setTypeAliasesPackage(String typeAliasesPackage) {
		this.typeAliasesPackage = typeAliasesPackage;
	}

	public boolean isCheckConfigLocation() {
		return this.checkConfigLocation;
	}

	public void setCheckConfigLocation(boolean checkConfigLocation) {
		this.checkConfigLocation = checkConfigLocation;
	}

	public ExecutorType getExecutorType() {
		return this.executorType;
	}

	public void setExecutorType(ExecutorType executorType) {
		this.executorType = executorType;
	}

	/**
	 * @since 1.2.0
	 */
	public Properties getConfigurationProperties() {
		return configurationProperties;
	}

	/**
	 * @since 1.2.0
	 */
	public void setConfigurationProperties(Properties configurationProperties) {
		this.configurationProperties = configurationProperties;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public Resource[] resolveMapperLocations() {
		ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
		List<Resource> resources = new ArrayList<Resource>();
		if (this.mapperLocations != null) {
			for (String mapperLocation : this.mapperLocations) {
				try {
					Resource[] mappers = resourceResolver.getResources(mapperLocation);
					resources.addAll(Arrays.asList(mappers));
				} catch (IOException e) {
					// ignore
				}
			}
		}
		return resources.toArray(new Resource[resources.size()]);
	}
}
