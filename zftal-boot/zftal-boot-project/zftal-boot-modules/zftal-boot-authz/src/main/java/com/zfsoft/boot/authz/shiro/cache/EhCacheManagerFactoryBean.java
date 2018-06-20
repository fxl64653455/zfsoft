/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.authz.shiro.cache;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;

import net.sf.ehcache.CacheManager;

public class EhCacheManagerFactoryBean implements FactoryBean<net.sf.ehcache.CacheManager> {

	@Autowired
	org.springframework.cache.CacheManager cacheManager;
	
	@Override
	public CacheManager getObject() throws Exception {
		// 强制类型转换
		EhCacheCacheManager ehCacheCacheManager = (EhCacheCacheManager) cacheManager;
		return ehCacheCacheManager.getCacheManager();
	}

	@Override
	public Class<?> getObjectType() {
		return CacheManager.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
	
}
