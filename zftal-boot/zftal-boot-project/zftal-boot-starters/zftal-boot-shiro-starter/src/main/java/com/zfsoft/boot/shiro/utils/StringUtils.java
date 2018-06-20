/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.shiro.utils;

public class StringUtils extends org.apache.shiro.util.StringUtils{

	/**
	 * Any number of these characters are considered delimiters between multiple
	 * context config paths in a single String value.
	 */
	public static String CONFIG_LOCATION_DELIMITERS = ",; \t\n";
	
	public static String[] tokenizeToStringArray(String str) {
		return tokenizeToStringArray(str, CONFIG_LOCATION_DELIMITERS);
	}
	
}
