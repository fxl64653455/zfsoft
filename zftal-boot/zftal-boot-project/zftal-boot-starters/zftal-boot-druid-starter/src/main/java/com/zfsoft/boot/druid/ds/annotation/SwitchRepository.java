package com.zfsoft.boot.druid.ds.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.zfsoft.boot.druid.ds.DataSourceContextHolder;

/**
 * 
 * @className	： DruidRepository
 * @description	： 数据源自动切换注解
 * @author 		：万大龙（743）
 * @date		： 2017年11月9日 下午12:32:26
 * @version 	V1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface SwitchRepository {

	/**
	 * 数据源名称
	 */
	public String value() default DataSourceContextHolder.DEFAULT_DATASOURCE;
	
}
