/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.webv5.web.handler;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 
 * @className	： ServerExceptions
 * @description	： TODO(描述这个类的作用)
 * @author 		：万大龙（743）
 * @date		： 2017年11月2日 下午8:26:34
 * @version 	V1.0
 */
public enum ServerExceptions {
    
	/**
	 * RuntimeException					       500 (Internal Server Error)
	 */
	SC_RUNTIME_EXCEPTION("1009", "服务器运行时异常", HttpStatus.INTERNAL_SERVER_ERROR),
	/**
	 * NullPointerException				       500 (Internal Server Error)
	 */
	SC_NULL_POINTER_EXCEPTION("1010", "服务器空值异常", HttpStatus.INTERNAL_SERVER_ERROR),
	/**
	 * ClassCastException				       500 (Internal Server Error)
	 */
	SC_CLASS_CAST_EXCEPTION("1011", "服务器数据类型转换异常", HttpStatus.INTERNAL_SERVER_ERROR),
	/**
	 * IOException						       500 (Internal Server Error)
	 */
	SC_IO_EXCEPTION("1012", "服务器IO异常", HttpStatus.INTERNAL_SERVER_ERROR),
	/**
	 * NoSuchMethodException			       500 (Internal Server Error)
	 */
	SC_NO_SUCH_METHOD_EXCEPTION("1013", "服务器未知方法异常", HttpStatus.INTERNAL_SERVER_ERROR),
	/**
	 * IndexOutOfBoundsException		       500 (Internal Server Error)
	 */
	SC_INDEX_OUT_OF_BOUNDS_EXCEPTION("1014", "服务器数组越界异常", HttpStatus.INTERNAL_SERVER_ERROR),
	/**
	 * NetworkException					       500 (Internal Server Error)
	 */
	SC_NETWORK_EXCEPTION("1015", "服务器网络异常", HttpStatus.INTERNAL_SERVER_ERROR);
	
	private final String code;

	private final String reasonPhrase;
	
	private final HttpStatus httpStatus;

	ServerExceptions(String code, String reasonPhrase, HttpStatus httpStatus) {
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
	public static ServerExceptions valueOfIgnoreCase(String exCode) {
		for (ServerExceptions status : values()) {
			if (status.code.equalsIgnoreCase(exCode)) {
				return status;
			}
		}
		throw new IllegalArgumentException("No matching constant for [" + exCode + "]");
	}
	
	public static List<ErrorPair> errors() {
		List<ErrorPair> errorList = new LinkedList<ErrorPair>();
		for (ServerExceptions ex : ServerExceptions.values()) {
			errorList.add(new ErrorPair(ex.getCode(), ex.getReasonPhrase()));
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
