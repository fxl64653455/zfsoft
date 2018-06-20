package com.zfsoft.api.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.zfsoft.api.cache.CacheName;
import com.zfsoft.api.utils.MessageUtil;
import com.zfsoft.basicutils.BooleanUtils;

public class BaseAwareService implements InitializingBean, ApplicationContextAware{
	
	protected ApplicationContext context = null;
	
	@Autowired(required = false)
	protected CacheManager cacheManager;
	protected String cacheName;
	//是否支持缓存
	protected boolean cacheSupport = true;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.cacheSupport = cacheManager!=null && cacheManager.getCache(getCacheName()) != null;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}

	public ApplicationContext getContext() {
		return context;
	}
	
	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public String getCacheName() {
		return cacheName == null ? CacheName.CACHE_BASIC : cacheName;
	}
	
	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}
	
	public boolean isCacheSupport() {
		return cacheSupport && BooleanUtils.toBoolean(MessageUtil.getText("zftal.cache.support"));
	}

	public void setCacheSupport(boolean cacheSupport) {
		this.cacheSupport = cacheSupport;
	}
	
	public Cache getCache() {
		return getCacheManager().getCache(getCacheName());
	}
	
}
