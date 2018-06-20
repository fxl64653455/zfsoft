/**
 * 
 */
package com.zfsoft.api.cache;

/**
 *  @author zxb
 *
 *	系统各个模块缓存名称定义
 *
 */
public abstract class CacheName {

	/*基础缓存*/
	public static final String CACHE_BASIC = "ZFTAL_BASIC";
	
	/*标签缓存*/
	public static final String CACHE_TAGS = "ZFTAL_TAGS";
	
	/*高级查询缓存*/
	public static final String CACHE_SUPERSEARCH = "ZFTAL_SUPERSEARCH";
	
	/*统计查询缓存*/
	public static final String CACHE_TJCX = "ZFTAL_TJCX";
	
	/*自定义表单缓存*/
	public static final String CACHE_ZDYBD = "ZFTAL_ZDYBD";
	
	/*其他*/
	public static final String CACHE_OTHER = "ZFTAL_OTHER";
	
	
	public static final String CACHE_MENU = "ZFTAL_USER_MENU";
	
	
	public static final String USED_MENU = "ZFTAL_USED_MENU";
	
	/**
	 * shiro控制的缓存
	 */
	public static final String ZFTAL_SHIRO_CACHE = "ZFTAL_SHIRO";
	
	/**
	 * SHIRO ZFTAL_SHIRO_AUTHENTICATION_CACHE
	 */
	public static final String ZFTAL_SHIRO_AUTHENTICATION_CACHE = "ZFTAL_SHIRO_AUTHENTICATION_CACHE";
	
	/**
	 * SHIRO ZFTAL_SHIRO_AUTHORIZATION_CACHE
	 */
	public static final String ZFTAL_SHIRO_AUTHORIZATION_CACHE = "ZFTAL_SHIRO_AUTHORIZATION_CACHE";
	
	/**
	 * SHIRO ZFTAL_SHIRO_PASSWORD_RETRRY_CACHE
	 */
	public static final String ZFTAL_SHIRO_PASSWORD_RETRRY_CACHE = "ZFTAL_SHIRO_PASSWORD_RETRRY_CACHE";
	
	/**
	 * SHIRO ZFTAL_SHIRO_KICKOUT_SESSION_CONTROL_CACHE
	 */
	public static final String ZFTAL_SHIRO_KICKOUT_SESSION_CONTROL_CACHE = "ZFTAL_SHIRO_KICKOUT_SESSION_CONTROL_CACHE";
	
}
