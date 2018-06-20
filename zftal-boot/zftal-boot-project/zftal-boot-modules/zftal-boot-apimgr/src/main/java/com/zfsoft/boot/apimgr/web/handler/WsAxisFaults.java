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
 * @className	： WsAxisFaults
 * @description	： TODO(描述这个类的作用)
 * @author 		：万大龙（743）
 * @date		： 2017年11月2日 下午6:29:46
 * @version 	V1.0
 */
public enum WsAxisFaults {
	
    // --- 4xx Client Error ---
    //Axis Fault 4xx(客户端错误，请求包含语法错误或无法完成请求)   ->这些状态代码表示请求可能出错，妨碍了服务器的处理。 

	/**
	 * 异常：AxisFault 400（错误请求）    -> 服务器不理解请求的语法。</br>
	 */
	SC_BAD_REQUEST("WS-400", "错误请求", HttpStatus.BAD_REQUEST),
	/**
	 * AxisFault 401（未授权）     -> 请求要求身份验证。 对于需要登录的网页，服务器可能返回此响应
	 */
	SC_UNAUTHORIZED("WS-401", "请求要求身份验证", HttpStatus.UNAUTHORIZED),
	/**
	 * AxisFault 402（ 	保留，将来使用）     ->
	 */
	SC_PAYMENT_REQUIRED("WS-402", "保留状态码", HttpStatus.PAYMENT_REQUIRED),
	/**
	 * AxisFault 403（禁止）    -> 服务器理解请求客户端的请求，但是拒绝执行此请求。
	 */
	SC_FORBIDDEN("WS-403", "服务器拒绝请求", HttpStatus.FORBIDDEN),
	/**
	 * 异常：AxisFault 404（未找到）     -> 注册的服务不存在No service was found.
	 */
	SC_NOT_FOUND("WS-404", "注册的服务不存在", HttpStatus.NOT_FOUND),
	/**
	 * 异常：AxisFault 405（方法禁用）    ->禁用请求中指定的方法。
	 * HttpRequestMethodNotSupportedException  405 (Method Not Allowed)
	 */
	SC_METHOD_NOT_ALLOWED("WS-405", "客户端请求中的方法被禁止", HttpStatus.METHOD_NOT_ALLOWED),
	/**
	 * 异常：AxisFault 406（不接受）     ->服务器无法根据客户端请求的内容特性完成请求。
	 * HttpMediaTypeNotAcceptableException     406 (Not Acceptable)
	 */
	SC_NOT_ACCEPTABLE("WS-406", "服务器无法根据客户端请求的内容特性完成请求", HttpStatus.NOT_ACCEPTABLE),
	/**
	 * 异常：AxisFault 407（需要代理授权）     ->此状态代码与 401（未授权）类似，但指定请求者应当授权使用代理。
	 */
	SC_PROXY_AUTHENTICATION_REQUIRED("WS-407", "要求进行代理身份验证", HttpStatus.PROXY_AUTHENTICATION_REQUIRED),
	/**
	 * 异常：AxisFault 408（请求超时）    ->服务器等待客户端发送的请求时间过长，超时。
	 */
	SC_REQUEST_TIMEOUT("WS-408", "服务器等候请求时发生超时", HttpStatus.REQUEST_TIMEOUT),
	/**
	 * 异常：AxisFault 409（冲突）     ->服务器在完成请求时发生冲突。 服务器必须在响应中包含有关冲突的信息。
	 */
	SC_CONFLICT("WS-409", "服务器找不到请求的地址", HttpStatus.CONFLICT),
	/**
	 * 异常：AxisFault 410（已删除）    -> 如果请求的资源已永久删除，服务器就会返回此响应。
	 */
	SC_GONE("WS-410", "服务器找不到请求的地址", HttpStatus.GONE),
	/**
	 * 异常：AxisFault 411（需要有效长度）     ->服务器无法处理客户端发送的不带Content-Length的请求信息。
	 */
	SC_LENGTH_REQUIRED("WS-411", "服务器拒绝接受不带Content-Length请求头的客户端请求", HttpStatus.LENGTH_REQUIRED),
	/**
	 * 异常：AxisFault 412（未满足前提条件）     ->服务器未满足请求者在请求中设置的其中一个前提条件。
	 */
	SC_PRECONDITION_FAILED("WS-412", "客户端请求信息的先决条件错误", HttpStatus.PRECONDITION_FAILED),
	/**
	 * 异常：AxisFault 413（请求实体过大）     ->服务器无法处理请求，因为请求实体过大，超出服务器的处理能力。为防止客户端的连续请求，服务器可能会关闭连接。如果只是服务器暂时无法处理，则会包含一个Retry-After的响应信息。
	 */
	SC_REQUEST_TOO_LONG("WS-413", "服务器无法处理请求，因为请求实体过大，超出服务器的处理能力", HttpStatus.URI_TOO_LONG),
	/**
	 * 异常：AxisFault 414（请求的 URI 过长） 请求的 URI（通常为网址）过长，服务器无法处理。
	 */
	@SuppressWarnings("deprecation")
	SC_REQUEST_URI_TOO_LONG("WS-414", "请求的URI过长（URI通常为网址），服务器无法处理", HttpStatus.REQUEST_URI_TOO_LONG),
	/**
	 * 异常：AxisFault 415（不支持的媒体类型）    ->请求的格式不受请求页面的支持。
	 * HttpMediaTypeNotSupportedException      415 (Unsupported Media Type)
	 */
	SC_UNSUPPORTED_MEDIA_TYPE("WS-415", "服务器无法处理请求附带的媒体格式", HttpStatus.UNSUPPORTED_MEDIA_TYPE),
	/**
	 * 异常：AxisFault 416（请求范围不符合要求）     ->如果页面无法提供请求的范围，则服务器会返回此状态代码。
	 */
	SC_REQUESTED_RANGE_NOT_SATISFIABLE("WS-416", "客户端请求的范围无效", HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE),
	/**
	 * 异常：AxisFault 417（未满足期望值）     ->服务器未满足”Expect”请求标头字段的要求。
	 */
	SC_EXPECTATION_FAILED("WS-417", "服务器无法满足Expect的请求头信息", HttpStatus.EXPECTATION_FAILED),
	/**
	 * 异常：AxisFault 422（无法处理的请求实体） ->请求格式正确，但是由于含有语义错误，无法响应。
	 */
	SC_UNPROCESSABLE_ENTITY("WS-422", "无法处理的请求实体", HttpStatus.UNPROCESSABLE_ENTITY),
	/**
	 * 异常：AxisFault 423（当前资源被锁定）
	 */
	SC_LOCKED("WS-423", "当前资源被锁定 ", HttpStatus.LOCKED),
	/**
	 * 异常：AxisFault 424（依赖导致的失败）->由于之前的某个请求发生的错误，导致当前请求失败，例如 PROPPATCH。
	 */
	SC_FAILED_DEPENDENCY("WS-424", "依赖导致的失败", HttpStatus.FAILED_DEPENDENCY),
	/**
	 * 异常：AxisFault 424（客户端应当切换到TLS/1.0）
	 */
	SC_UPGRADE_REQUIRED("WS-426", "客户端应当切换到TLS/1.0", HttpStatus.UPGRADE_REQUIRED),
	/**
	 * 异常：AxisFault 428（要求先决条件）    -> 先决条件是客户端发送 WS 请求时，如果想要请求能成功必须满足一些预设的条件。
	 */
	SC_PRECONDITION_REQUIRED("WS-428", "要求先决条件", HttpStatus.PRECONDITION_REQUIRED),
	/**
	 * 异常：AxisFault 429（太多请求）    -> 当你需要限制客户端请求某个服务数量时，该状态码就很有用，也就是请求速度限制。
	 */
	SC_TOO_MANY_REQUESTS("WS-429", "太多请求", HttpStatus.TOO_MANY_REQUESTS),
	/**
	 * 异常：AxisFault 431（请求头字段太大）    -> 某些情况下，客户端发送 WS 请求头会变得很大，那么服务器可发送 431 Request Header Fields Too Large 来指明该问题。
	 */
	SC_REQUEST_HEADER_FIELDS_TOO_LARGE("WS-431", "请求头字段太大", HttpStatus.REQUEST_HEADER_FIELDS_TOO_LARGE),
	/**
	 * 异常：AxisFault 451（因法律原因不可用）    ->
	 */
	SC_UNAVAILABLE_FOR_LEGAL_REASONS("WS-451", "该请求因法律原因不可用", HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS),
    
	// --- 5xx Server Error ---
    //AxisFault 5xx（服务器错误，服务器在处理请求的过程中发生了错误）    -> 这些状态代码表示服务器在尝试处理请求时发生内部错误。 这些错误可能是服务器本身的错误，而不是请求出错。
    
	/**
	 * 异常：AxisFault 500（服务器内部错误）    ->服务器内部错误，无法完成请求。
	 * ConversionNotSupportedException         500 (Internal Server Error)
	 * HttpMessageNotWritableException         500 (Internal Server Error)
	 */
	SC_INTERNAL_SERVER_ERROR("WS-500", "服务器内部错误，无法完成请求", HttpStatus.INTERNAL_SERVER_ERROR),
	/**
	 * 异常：AxisFault 501（尚未实施）     ->服务器不具备完成请求的功能。 例如，服务器无法识别请求方法时可能会返回此代码。
	 */
	SC_NOT_IMPLEMENTED("WS-501", "服务器不支持请求的功能，无法完成请求", HttpStatus.NOT_IMPLEMENTED),
	/**
	 * 异常：AxisFault 502（错误网关）     -> 作为网关或者代理工作的服务器尝试执行请求时，从上游服务器接收到无效的响应。
	 */
	SC_BAD_GATEWAY("WS-502", "错误网关", HttpStatus.BAD_GATEWAY),
	/**
	 * 异常：AxisFault 503（服务不可用）    -> 由于临时的服务器维护或者过载，服务器当前无法处理请求。这个状况是临时的，并且将在一段时间以后恢复。如果能够预计延迟时间，那么响应中可以包含一个 Retry-After 头用以标明这个延迟时间。如果没有给出这个 Retry-After 信息，那么客户端应当以处理500响应的方式处理它。
	 * 									注意：503状态码的存在并不意味着服务器在过载的时候必须使用它。某些服务器只不过是希望拒绝客户端的连接。
	 * ConnectException: Connection refused: connect
	 */
	SC_SERVICE_UNAVAILABLE("WS-503", "接口服务目前无法使用（超载或停机维护）", HttpStatus.SERVICE_UNAVAILABLE),
	/**
	 * 异常：AxisFault 504（网关访问超时）     -> 作为网关或者代理工作的服务器尝试执行请求时，未能及时从上游服务器（URI标识出的服务器，例如WS、FTP、LDAP）或者辅助服务器（例如DNS）收到响应。
　　	 *								        注意：某些代理服务器在DNS查询超时时会返回400或者500错误
	 */
	SC_GATEWAY_TIMEOUT("WS-504", "网关访问超时", HttpStatus.GATEWAY_TIMEOUT),
	/**
	 * 异常：AxisFault 505（WS 版本不受支持）    -> 服务器不支持请求的WS协议的版本，无法完成处理。这暗示着服务器不能或不愿使用与客户端相同的版本。响应中应当包含一个描述了为何版本不被支持以及服务器支持哪些协议的实体。
	 */
	SC_HTTP_VERSION_NOT_SUPPORTED("WS-505", "WS 版本不受支持", HttpStatus.HTTP_VERSION_NOT_SUPPORTED),
	/**
	 * 异常：AxisFault 506（服务器内部配置错误）->  由《透明内容协商协议》（RFC 2295）扩展，代表服务器存在内部配置错误：被请求的协商变元资源被配置为在透明内容协商中使用自己，因此在一个协商处理中不是一个合适的重点。
	 */
	SC_VARIANT_ALSO_NEGOTIATES("WS-506", "服务器内部配置错误", HttpStatus.VARIANT_ALSO_NEGOTIATES),
	/**
	 * 异常：AxisFault 507（服务器无法存储完成请求所必须的内容）-> 这个状况被认为是临时的。WebDAV (RFC 4918)
	 */
	SC_INSUFFICIENT_STORAGE("WS-507", "服务器无法存储完成请求所必须的内容", HttpStatus.INSUFFICIENT_STORAGE),
	/**
	 * 异常：AxisFault 508（存储空间不足）
	 */
	SC_LOOP_DETECTED("WS-508", "服务器存储空间不足", HttpStatus.LOOP_DETECTED),
	/**
	 * 异常：AxisFault 509（服务器达到带宽限制）->这不是一个官方的状态码，但是仍被广泛使用。
	 */
	SC_BANDWIDTH_LIMIT_EXCEEDED("WS-509", "服务器达到带宽限制", HttpStatus.BANDWIDTH_LIMIT_EXCEEDED),
	/**
	 * 异常：AxisFault 510（要求网络认证）
	 */
	SC_NOT_EXTENDED("WS-510", "要求网络认证", HttpStatus.NOT_EXTENDED),
	/**
	 * 异常：AxisFault 511（要求网络认证）
	 */
	SC_NETWORK_AUTHENTICATION_REQUIRED("WS-511", "要求网络认证", HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
	
	private final String code;

	private final String reasonPhrase;
	
	private final HttpStatus httpStatus;

	WsAxisFaults(String code, String reasonPhrase, HttpStatus httpStatus) {
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
	public static WsAxisFaults valueOfIgnoreCase(String exCode) {
		for (WsAxisFaults status : values()) {
			if (status.code.equalsIgnoreCase(exCode)) {
				return status;
			}
		}
		throw new IllegalArgumentException("No matching constant for [" + exCode + "]");
	}
	
	public static List<ApiError> errors() {
		List<ApiError> errorList = new LinkedList<ApiError>();
		for (WsAxisFaults fault : WsAxisFaults.values()) {
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
