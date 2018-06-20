/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.druid.ds;

public class DataSourceContextHolder {

	public static final String DEFAULT_DATASOURCE = "defaultDataSource";
	
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>() {
		
		protected String initialValue() {
			return DEFAULT_DATASOURCE;
		}
		
	};

	public static void setDatabaseName(String name) {
		contextHolder.set(name);
	}

	public static String getDatabaseName() {
		return contextHolder.get();
	}
	
}
