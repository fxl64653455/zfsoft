/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.setup.event;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationEvent;

import com.zfsoft.api.utils.WebUtils;
import com.zfsoft.web.utils.WebRequestUtils;

@SuppressWarnings("serial")
public class ApiInvokedEvent extends ApplicationEvent {

	private String appkey;
	private String deployId;
	private Map<String, Object> params;
	private String remoteAddr;
	private String requestURI;
	private String userAgent;
	private Object result;
	private Exception ex;
	private Long startTime;

	public ApiInvokedEvent(String appkey, ServletRequest request, String deployId, Map<String, Object> params, Object res, Exception ex, Long startTime) {
		super(deployId);
		
		this.appkey = appkey;
		
		this.deployId = deployId;
		this.params = params;
		this.result = res;
		this.ex = ex;
		this.startTime = startTime;
		
		HttpServletRequest req = WebUtils.toHttp(request);
		// 获取客户端IP地址，支持代理服务器
		this.remoteAddr = WebRequestUtils.getRemoteAddr(req);
		this.requestURI = req.getRequestURI();
		this.userAgent = req.getHeader("User-Agent");
	}
	
	public String getAppkey() {
		return appkey;
	}

	public String getDeployId() {
		return deployId;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public String getRequestURI() {
		return requestURI;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public Object getResult() {
		return result;
	}
	
	public Exception getEx() {
		return ex;
	}

	public Long getStartTime() {
		return startTime;
	}

}
