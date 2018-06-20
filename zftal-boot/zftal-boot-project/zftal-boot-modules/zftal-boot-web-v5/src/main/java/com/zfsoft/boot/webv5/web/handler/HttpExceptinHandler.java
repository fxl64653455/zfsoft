/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.webv5.web.handler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.PropertyAccessException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.ModelAndView;

import com.zfsoft.api.exception.BizAccessLimitedException;
import com.zfsoft.api.exception.BusinessCheckException;
import com.zfsoft.api.exception.BusinessException;
import com.zfsoft.api.exception.BusinessRuntimeException;
import com.zfsoft.api.utils.ResultUtils;
import com.zfsoft.basicutils.StringUtils;

/**
 * 异常增强，以JSON的形式返回给客服端
 * 异常增强类型：NullPointerException,RunTimeException,ClassCastException,
 * NoSuchMethodException,IOException,IndexOutOfBoundsException
 *
 */
@ControllerAdvice
public class HttpExceptinHandler {

	protected static final String DEFAULT_ERROR_VIEW = "exception/500";
	public static final String STATUS_ERROR = "error";
	
	protected static final Logger Logger = LoggerFactory.getLogger(HttpExceptinHandler.class);

	protected void logException(Exception ex) {
		if (Logger.isErrorEnabled()){
			Logger.error(ex.getMessage(), ex);
		}
	}
	
	@Autowired
	protected ConfigurableEnvironment environment;
	
	protected boolean isSet(ConfigurableEnvironment environment, String property) {
		String value = environment.getProperty(property);
		return (value != null && !value.equals("false"));
	}
	
	protected boolean isDebug() {
		return isSet(environment, "debug");
	}
	
	/**
	 * 全局异常捕捉处理
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception ex) throws Exception {
		ex.printStackTrace();
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", ex);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName(DEFAULT_ERROR_VIEW);
		return mav;
	}
	
	@ExceptionHandler({ BizAccessLimitedException.class })
	@ResponseBody
	public ResponseEntity<Map<String, Object>> bizAccessLimitedException(BizAccessLimitedException ex) {
		ex.printStackTrace();
		return new ResponseEntity<Map<String, Object>>(ResultUtils.statusMap(STATUS_ERROR, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ BusinessException.class })
	@ResponseBody
	public ResponseEntity<Map<String, Object>> businessException(BusinessException ex) {
		ex.printStackTrace();
		return new ResponseEntity<Map<String, Object>>(ResultUtils.statusMap(STATUS_ERROR, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ BusinessRuntimeException.class })
	@ResponseBody
	public ResponseEntity<Map<String, Object>> businessRuntimeException(BusinessRuntimeException ex) {
		ex.printStackTrace();
		return new ResponseEntity<Map<String, Object>>(ResultUtils.statusMap(STATUS_ERROR, ex.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 运行时异常
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> runtimeExceptionHandler(RuntimeException ex) {
		ex.printStackTrace();
		return ServerExceptions.SC_RUNTIME_EXCEPTION.toResponseEntity(ex, isDebug());
	}

	// 空指针异常
	@ExceptionHandler(NullPointerException.class)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> nullPointerExceptionHandler(NullPointerException ex) {
		ex.printStackTrace();
		return ServerExceptions.SC_NULL_POINTER_EXCEPTION.toResponseEntity(ex, isDebug());
	}

	// 类型转换异常
	@ExceptionHandler(ClassCastException.class)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> classCastExceptionHandler(ClassCastException ex) {
		ex.printStackTrace();
		return ServerExceptions.SC_CLASS_CAST_EXCEPTION.toResponseEntity(ex, isDebug());
	}

	// IO异常
	@ExceptionHandler(IOException.class)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> iOExceptionHandler(IOException ex) {
		ex.printStackTrace();
		return ServerExceptions.SC_IO_EXCEPTION.toResponseEntity(ex, isDebug());
	}

	// 未知方法异常
	@ExceptionHandler(NoSuchMethodException.class)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> noSuchMethodExceptionHandler(NoSuchMethodException ex) {
		ex.printStackTrace();
		return ServerExceptions.SC_NO_SUCH_METHOD_EXCEPTION.toResponseEntity(ex, isDebug());
	}

	// 数组越界异常
	@ExceptionHandler(IndexOutOfBoundsException.class)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
		ex.printStackTrace();
		return ServerExceptions.SC_INDEX_OUT_OF_BOUNDS_EXCEPTION.toResponseEntity(ex, isDebug());
	}

	@ExceptionHandler({ BusinessCheckException.class })
	@ResponseBody
	public ResponseEntity<Map<String, Object>> businessCheckException(BusinessCheckException ex) {
		ex.printStackTrace();
		return new ResponseEntity<Map<String, Object>>(ResultUtils.statusMap(STATUS_ERROR, ex.getMessage()), HttpStatus.OK);
	}

	/**
	 * 400 (Bad Request)
	 */
	@ExceptionHandler({ TypeMismatchException.class })
	@ResponseBody
	public ResponseEntity<Map<String, Object>> typeMismatchException(TypeMismatchException ex) {
		 
		Map<String, Object> detailMap = new HashMap<String, Object>();
		detailMap.put("message", String.format("[%s] type mismatch. The type should be [%s].", ex.getPropertyName(), ex.getRequiredType()));
		detailMap.put("errorcode", ex.getErrorCode());
		
		return HttpExceptions.SC_BAD_REQUEST.toResponseEntity(ex, detailMap, isDebug());
	}

	/**
	 * 400 (Bad Request)
	 */
	@ExceptionHandler({ HttpMessageNotReadableException.class })
	@ResponseBody
	public ResponseEntity<Map<String, Object>> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
		ex.printStackTrace();
		return HttpExceptions.SC_BAD_REQUEST.toResponseEntity(ex, isDebug());
	}
	
	/**
	 * 400 (Bad Request)
	 */
	@ExceptionHandler({ MissingServletRequestParameterException.class })
	@ResponseBody
	public ResponseEntity<Map<String, Object>> missingServletRequestParameterException(MissingServletRequestParameterException ex) {
		ex.printStackTrace();
		
		Map<String, Object> detailMap = new HashMap<String, Object>();
		detailMap.put("message", String.format("Parameter [%s] Can't be empty.", ex.getParameterName()));
		
		return HttpExceptions.SC_BAD_REQUEST.toResponseEntity(ex, detailMap, isDebug());
	}
	
	@ExceptionHandler({ MissingServletRequestPartException.class })
	@ResponseBody
	public ResponseEntity<Map<String, Object>> missingServletRequestPartException(MissingServletRequestPartException ex) {
		ex.printStackTrace();
		
		Map<String, Object> detailMap = new HashMap<String, Object>();
		detailMap.put("message", String.format("RequestPart [%s] Can't be empty.", ex.getRequestPartName()));
		
		return HttpExceptions.SC_BAD_REQUEST.toResponseEntity(ex, detailMap, isDebug());	
	}
	
	/**
	 * 405 (Method Not Allowed)
	 */
	@ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
	@ResponseBody
	public ResponseEntity<Map<String, Object>> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
		ex.printStackTrace();

		Map<String, Object> rtMap = new HashMap<String, Object>();
		rtMap.put("message", String.format("%s, Support only [%s].", ex.getMessage(), StringUtils.join(ex.getSupportedMethods())));

		return new ResponseEntity<Map<String, Object>>(rtMap, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	/**
	 * 406 (Not Acceptable) 
	 */
	@ExceptionHandler({ HttpMediaTypeNotAcceptableException.class })
	@ResponseBody
	public ResponseEntity<Map<String, Object>> httpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException ex) {
		ex.printStackTrace();

		Map<String, Object> rtMap = new HashMap<String, Object>();
		rtMap.put("status", ex.getMessage());
		
		String[] supportedMediaTypes = new String[ex.getSupportedMediaTypes().size()];
		for (int i = 0; i < ex.getSupportedMediaTypes().size(); i++) {
			MediaType mediaType = ex.getSupportedMediaTypes().get(i);
			supportedMediaTypes[i] = mediaType.toString();
		}
		rtMap.put("message", String.format("%s, Support only [%s].", ex.getMessage(), StringUtils.join(supportedMediaTypes)));
		
		return new ResponseEntity<Map<String, Object>>(rtMap, HttpStatus.NOT_ACCEPTABLE);
	}

	/**
	 * 415 (Unsupported Media Type) 
	 */
	@ExceptionHandler({ HttpMediaTypeNotSupportedException.class })
	@ResponseBody
	public ResponseEntity<Map<String, Object>> httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
		ex.printStackTrace();

		Map<String, Object> rtMap = new HashMap<String, Object>();
		rtMap.put("status", STATUS_ERROR);
		rtMap.put("SupportedMediaTypes", ex.getSupportedMediaTypes());
		rtMap.put("message", "HTTP Status 415（不支持的媒体类型）    ->请求的格式不受请求页面的支持。");

		return new ResponseEntity<Map<String, Object>>(rtMap, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}
	
	/**
	 * 500 (Internal Server Error)
	 */
	@ExceptionHandler({ HttpMessageNotWritableException.class })
	@ResponseBody
	public ResponseEntity<Map<String, Object>> httpMessageNotWritableException(HttpMessageNotWritableException ex) {

		ex.printStackTrace();

		Map<String, Object> rtMap = new HashMap<String, Object>();
		rtMap.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
		rtMap.put("message", "服务器遇到错误，无法完成请求。");

		return new ResponseEntity<Map<String, Object>>(rtMap, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * 500 (Internal Server Error)
	 */
	@ExceptionHandler({ ConversionNotSupportedException.class })
	@ResponseBody
	public ResponseEntity<Map<String, Object>> conversionNotSupportedException(ConversionNotSupportedException ex) {
		ex.printStackTrace();

		Map<String, Object> rtMap = rtMap(HttpStatus.INTERNAL_SERVER_ERROR, ex);
		rtMap.put("message", "SQL 语法错误.");

		/*
		 * ex.getPropertyName() ex.getRequiredType()
		 * 
		 * ex.getErrorCode()
		 * 
		 * Map<String, Object> body = ResultUtils.statusMap(STATUS_ERROR, "缺少必要参数,参数名称为"
		 * + ex.getParameterName());
		 */
		return new ResponseEntity<Map<String, Object>>(rtMap, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	
	protected <T extends PropertyAccessException> Map<String, Object> rtMap(HttpStatus status, T ex) {
		Map<String, Object> rtMap = new HashMap<String, Object>();

		Map<String, Object> exMap = new HashMap<String, Object>(2);

		exMap.put("code", ex.getMessage());
		exMap.put("reason", ex.getErrorCode());

		rtMap.put("status", status.toString());
		rtMap.put("message", status.getReasonPhrase());
		rtMap.put("exception", exMap);

		return rtMap;
	}

	@ExceptionHandler({ BindingException.class })
	@ResponseBody
	public ResponseEntity<Map<String, Object>> bindingException(BindingException ex) {
		Logger.error("Exception:", ex);
		return new ResponseEntity<Map<String, Object>>(ResultUtils.statusMap(STATUS_ERROR, ex.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ BindException.class })
	@ResponseBody
	public ResponseEntity<Map<String, Object>> bindException(BindException ex) {
		Logger.error("Exception:", ex);
		BindingResult result = ex.getBindingResult();
		if( result.getGlobalErrorCount() > 0) {
			ObjectError error = result.getGlobalError();
			return new ResponseEntity<Map<String, Object>>(
					ResultUtils.statusMap(STATUS_ERROR, error.getDefaultMessage()), HttpStatus.OK);
		} else{
			return new ResponseEntity<Map<String, Object>>(
					ResultUtils.statusMap(STATUS_ERROR, result.getFieldError().getDefaultMessage()), HttpStatus.OK);
		}
	}

	@ExceptionHandler(SQLSyntaxErrorException.class)
	public ResponseEntity<Map<String, Object>> sqlSyntaxErrorException(SQLSyntaxErrorException ex) {
		ex.printStackTrace();
		Map<String, Object> rtMap = new HashMap<String, Object>();
		rtMap.put("status", STATUS_ERROR);
		rtMap.put("ErrorCode", ex.getErrorCode());
		rtMap.put("SQLState ", ex.getSQLState());
		rtMap.put("message", "SQL 语法错误.");
		return new ResponseEntity<Map<String, Object>>(rtMap, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<Map<String, Object>> dataAccessException(DataAccessException ex) {
		ex.printStackTrace();
		Map<String, Object> rtMap = new HashMap<String, Object>();
		rtMap.put("status", STATUS_ERROR);
		rtMap.put("message", ex.getRootCause().getMessage());
		return new ResponseEntity<Map<String, Object>>(rtMap, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<Map<String, Object>> sqlIntegrityConstraintViolationException(SQLSyntaxErrorException ex) {
		ex.printStackTrace();
		Map<String, Object> rtMap = new HashMap<String, Object>();
		rtMap.put("status", STATUS_ERROR);
		rtMap.put("ErrorCode", ex.getErrorCode());
		rtMap.put("SQLState ", ex.getSQLState());
		rtMap.put("message", "SQL 语法错误.");
		return new ResponseEntity<Map<String, Object>>(rtMap, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ IllegalArgumentException.class, UnsupportedEncodingException.class })
	public ResponseEntity<Map<String, Object>> jwtTokenErr() {
		
		return new ResponseEntity<Map<String, Object>>(ResultUtils.statusMap(STATUS_ERROR, ""),
				HttpStatus.BAD_REQUEST);
		
		//return new JsonResult(ResultCode.UNKNOWN_ERROR, ResultCode.UNKNOWN_ERROR.msg());
	}
	
}
