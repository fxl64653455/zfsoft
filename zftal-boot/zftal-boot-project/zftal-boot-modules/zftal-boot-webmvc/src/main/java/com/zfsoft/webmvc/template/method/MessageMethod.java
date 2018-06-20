package com.zfsoft.webmvc.template.method;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.zfsoft.api.utils.MessageUtil;
import com.zfsoft.api.web.WebContext;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * 
 * <p>
 *   <h3>zftal框架<h3>
 *   说明：自定义Freemarker方法，读取properties文件内容
 * <p>
 * @author <a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a>
 * @version 2016年9月13日上午9:19:43
 */
@Component("messageMethod")
public class MessageMethod implements TemplateMethodModelEx {

	@Resource(name = "messageSource")
	protected MessageSource messageSource;
	
	@SuppressWarnings("rawtypes")
	public Object exec(List arguments) throws TemplateModelException {
		if (arguments != null && !arguments.isEmpty() && arguments.get(0) != null && StringUtils.isNotEmpty(arguments.get(0).toString())) {
			String message = null;
			String code = arguments.get(0).toString();
			if (arguments.size() > 1) {
				Object[] args = arguments.subList(1, arguments.size()).toArray();
				message = getMessage(code, args);
			} else {
				message = getMessage(code);
			}
			return new SimpleScalar(message);
		}
		return null;
	}
	
	protected String getMessage(String key, Object[] args){
		try {
			String rt = null;
			if(getMessageSource() != null){
				rt = getMessageSource().getMessage(key, args, WebContext.getLocale());
			}
			if(StringUtils.isEmpty(rt)){
				rt = MessageUtil.getLocaleText(key, args);
			}
			return rt;
		} catch (Exception e) {
			return MessageUtil.getLocaleText(key, args);
		}
	}
	
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

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
}
