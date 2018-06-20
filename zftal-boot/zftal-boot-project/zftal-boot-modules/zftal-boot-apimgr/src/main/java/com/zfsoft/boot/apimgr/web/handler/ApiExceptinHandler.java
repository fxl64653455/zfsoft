/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.handler;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfsoft.api.utils.MessageUtil;
import com.zfsoft.api.utils.ResultUtils;
import com.zfsoft.api.web.WebContext;
import com.zfsoft.boot.apimgr.web.exceptions.DeleteApiInfoException;
import com.zfsoft.boot.apimgr.web.exceptions.OAuthException;

/**
 * 异常增强，以JSON的形式返回给客服端
 * 异常增强类型：NullPointerException,RunTimeException,ClassCastException,
 * NoSuchMethodException,IOException,IndexOutOfBoundsException
 *
 */
@ControllerAdvice
public class ApiExceptinHandler {

	protected static final String DEFAULT_ERROR_VIEW = "exception/500";
	public static final String STATUS_FAIL = "fail";
	public static final String STATUS_ERROR = "error";
	
	protected static final Logger Logger = LoggerFactory.getLogger(ApiExceptinHandler.class);

	protected void logException(Exception ex) {
		if (Logger.isErrorEnabled()){
			Logger.error(ex.getMessage(), ex);
		}
	}
	
	@Autowired
	protected ConfigurableEnvironment environment;
	
	@Resource(name = "messageSource")
	protected MessageSource messageSource;
	
	protected String getMessage(String key) {
		try {
			return messageSource.getMessage(key, null, WebContext.getLocale());
		} catch (Exception e) {
			return MessageUtil.getText(key);
		}
	}
	
	protected boolean isSet(ConfigurableEnvironment environment, String property) {
		String value = environment.getProperty(property);
		return (value != null && !value.equals("false"));
	}
	
	protected boolean isDebug() {
		return isSet(environment, "debug");
	}
	
	/**
	 * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	}

	@ExceptionHandler(OAuthException.class)
	public @ResponseBody ResponseEntity<Map<String, Object>> oauthException(OAuthException ex) {
		return new ResponseEntity<Map<String, Object>>(ResultUtils.statusMap(STATUS_FAIL, ex.getMessage()),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DeleteApiInfoException.class)
	public @ResponseBody Map<String, Object> deleteApiInfoException(DeleteApiInfoException ex) {
		return ResultUtils.statusMap(STATUS_FAIL, getMessage(ex.getMessage()));
	}
}
