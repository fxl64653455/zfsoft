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
 * @className	： WsAxis2Faults
 * @description	： TODO(描述这个类的作用)
 * @author 		：万大龙（743）
 * @date		： 2017年11月2日 下午6:29:51
 * @version 	V1.0
 */
public enum WsAxis2Faults {
	
    // --- 4xx Client Error ---
    //HTTP Status 4xx(客户端错误，请求包含语法错误或无法完成请求)   ->这些状态代码表示请求可能出错，妨碍了服务器的处理。 

	/**
	 * 异常：HTTP Status 400（错误请求）    -> 服务器不理解请求的语法。</br>
	 * TypeMismatchException                   400 (Bad Request)</br>
	 * HttpMessageNotReadableException         400 (Bad Request)</br>
	 * MissingServletRequestParameterException 400 (Bad Request)</br>
	 */
	SC_BAD_REQUEST("HTTP-400", "错误请求", HttpStatus.BAD_REQUEST),
	/**
	 * HTTP Status 401（未授权）     -> 请求要求身份验证。 对于需要登录的网页，服务器可能返回此响应
	 */
	SC_UNAUTHORIZED("HTTP-401", "请求要求身份验证", HttpStatus.UNAUTHORIZED);
	
	private final String code;

	private final String reasonPhrase;
	
	private final HttpStatus httpStatus;

	WsAxis2Faults(String code, String reasonPhrase, HttpStatus httpStatus) {
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
	public static WsAxis2Faults valueOfIgnoreCase(String exCode) {
		for (WsAxis2Faults status : values()) {
			if (status.code.equalsIgnoreCase(exCode)) {
				return status;
			}
		}
		throw new IllegalArgumentException("No matching constant for [" + exCode + "]");
	}
	
	public static List<ApiError> errors() {
		List<ApiError> errorList = new LinkedList<ApiError>();
		for (WsAxis2Faults fault : WsAxis2Faults.values()) {
			errorList.add(new ApiError(fault.getCode(), fault.getReasonPhrase()));
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
