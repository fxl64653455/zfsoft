package com.zfsoft.boot.hikaricp.ds.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.zfsoft.boot.hikaricp.ds.DataSourceContextHolder;

/**
 * 
 * @className	： SwitchRepository
 * @description	： 用于方法注释；是否切换数据源及切换的数据源名称
 * @author 		：万大龙（743）
 * @date		： 2017年11月28日 上午10:05:43
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
