/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.handler;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.zfsoft.boot.apimgr.web.dto.ApiError;

/**
 * 
 * @className	： DataApiExceptions
 * @description	： TODO(描述这个类的作用)
 * @author 		：万大龙（743）
 * @date		： 2017年11月3日 下午2:49:01
 * @version 	V1.0
 */
public enum DataApiExceptions {
    
	/**
	 * SQLSyntaxErrorException				    500 (Internal Server Error)
	 */
	SC_SQL_SYNTAX_ERROR_EXCEPTION("1001", "SQL表达式错误：{0}", HttpStatus.INTERNAL_SERVER_ERROR),
	
	/**
	 * NonNumericalException				    500 (Internal Server Error)
	 * ParseException				       		500 (Internal Server Error)
	 * InvalidReferenceException				500 (Internal Server Error)
	 */
	SC_FREEMARKER_EXCEPTION("1002", "SQL语句中的Freemarker语法错误：{0}", HttpStatus.INTERNAL_SERVER_ERROR),
	/**
	 * JSQLParserException			       500 (Internal Server Error)
	 */
	SC_SQL_PARSER_EXCEPTION("1003", "SQL语法解析错误：{0}", HttpStatus.INTERNAL_SERVER_ERROR);
	
	private final String code;

	private final String reasonPhrase;
	
	private final HttpStatus httpStatus;

	DataApiExceptions(String code, String reasonPhrase, HttpStatus httpStatus) {
		this.code = code;
		this.reasonPhrase = reasonPhrase;
		this.httpStatus = httpStatus;
	}

	/**
	 * Return the integer value of this status code.
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * Return the reason phrase of this status code.
	 */
	public String getReasonPhrase() {
		return this.reasonPhrase;
	}
	
	/**
	 * Return the http Status of this status code.
	 */
	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}
	
	/**
	 * Return a string representation of this status code.
	 */
	@Override
	public String toString() {
		return this.code;
	}
	
	/**
	 * Return the enum constant of this type with the specified numeric value.
	 * @param exCode the numeric value of the enum to be returned
	 * @return the enum constant with the specified numeric value
	 * @throws IllegalArgumentException if this enum has no constant for the specified numeric value
	 */
	public static DataApiExceptions valueOfIgnoreCase(String exCode) {
		for (DataApiExceptions status : values()) {
			if (status.code.equalsIgnoreCase(exCode)) {
				return status;
			}
		}
		throw new IllegalArgumentException("No matching constant for [" + exCode + "]");
	}
	
	public static List<ApiError> errors() {
		List<ApiError> errorList = new LinkedList<ApiError>();
		for (DataApiExceptions ex : DataApiExceptions.values()) {
			errorList.add(new ApiError(ex.getCode(), ex.getReasonPhrase()));
		}
		return errorList;
	}
	
	public <T extends Throwable>  ResponseEntity<Map<String, Object>> toResponseEntity(T ex) {
		return this.toResponseEntity(ex, null, false);
	}
	
	public <T extends Throwable>  ResponseEntity<Map<String, Object>> toResponseEntity(T ex, boolean debug) {
		return this.toResponseEntity(ex, null, debug);
	}

	public <T extends Throwable>  ResponseEntity<Map<String, Object>> toResponseEntity(T ex, Map<String, Object> detail, boolean debug) {
		
		Map<String, Object> rtMap = new HashMap<String, Object>();
		
		rtMap.put("status", httpStatus.toString());
		rtMap.put("message", getReasonPhrase());
		if(debug) {
			rtMap.put("exception", ex.getMessage());
		}
		if(detail != null && !detail.isEmpty()) {
			rtMap.put("detail", detail);
		}
		
		return new ResponseEntity<Map<String, Object>>(rtMap, this.getHttpStatus());
	}

}
