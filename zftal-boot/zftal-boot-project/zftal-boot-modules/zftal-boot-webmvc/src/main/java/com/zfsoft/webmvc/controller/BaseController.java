package com.zfsoft.webmvc.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.cache.CacheManager;
import org.springframework.context.MessageSource;

import com.alibaba.fastjson.JSONObject;
import com.zfsoft.api.utils.MessageUtil;
import com.zfsoft.api.utils.ResultUtils;
import com.zfsoft.api.web.WebContext;
import com.zfsoft.api.web.session.User;
import com.zfsoft.spring.web.servlet.mvc.AbstractBaseController;

public abstract class BaseController extends AbstractBaseController{

	protected static final String ERROR_VIEW = "/exception/busiError";
	
	protected static final String STATUS_SUCCESS = "success";
	protected static final String STATUS_FAIL = "fail";
	protected static final String STATUS_ERROR = "error";
	
	@Resource(name = "messageSource")
	protected MessageSource messageSource;
	
	@Resource(name = "cacheManager")
	protected CacheManager cacheManager;
	
	public static final Object[] EMPTY_ARGS = new Object[]{};
	
	protected User getUser(){
		return WebContext.getUser();
	}
	
	/**
	 * 
	 * @description	： 获取国际化信息
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月18日 下午4:05:45
	 * @param key
	 * @param params
	 * @return
	 */
	protected String getMessage(String key, Object... params){
		try {
			String rt = null;
			if(getMessageSource() != null){
				rt = getMessageSource().getMessage(key, params, WebContext.getLocale());
			}
			if(StringUtils.isEmpty(rt)){
				rt = MessageUtil.getLocaleText(key, params);
			}
			return rt;
		} catch (Exception e) {
			return MessageUtil.getLocaleText(key, params);
		}
	}
	
	/**
	 * 
	 * @description	： 获取国际化信息
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月18日 下午4:06:03
	 * @param key
	 * @return
	 */
	protected String getMessage(String key){
		try {
			String rt = null;
			if(getMessageSource() != null){
				rt = getMessageSource().getMessage(key, null, WebContext.getLocale());
			}
			if(StringUtils.isEmpty(rt)){
				rt = MessageUtil.getLocaleText(key);
			}
			return rt;
		} catch (Exception e) {
			return MessageUtil.getLocaleText(key);
		}
	}
	
	protected JSONObject getJSONMessage(String status, String key, Object[] params){
		JSONObject json = new JSONObject();
		json.put("message", getMessage(key, params));
		json.put("status", status);
		return json;
	}
	
	protected JSONObject getJSONMessage(String status, String key){
		return getJSONMessage(status, key, null);
	}

	protected Map<String, Object> successStatus(String key){
		return ResultUtils.statusMap(STATUS_SUCCESS, getMessage(key));
	}
	
	protected Map<String, Object> failStatus(String key){
		return ResultUtils.statusMap(STATUS_FAIL, getMessage(key));
	}
	
	protected Map<String, Object> errorStatus(){
		return ResultUtils.statusMap(STATUS_ERROR, getMessage("S00001"));
	}
	
	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}


	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	
}
