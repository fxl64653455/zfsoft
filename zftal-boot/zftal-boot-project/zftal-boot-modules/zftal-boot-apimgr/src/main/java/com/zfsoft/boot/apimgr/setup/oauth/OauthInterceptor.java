/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.setup.oauth;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.zfsoft.boot.apimgr.setup.count.InvokeCount;
import com.zfsoft.boot.apimgr.util.JwtToken;
import com.zfsoft.boot.apimgr.web.exceptions.OAuthException;
import com.zfsoft.boot.webv5.setup.constant.RequestAttributes;
import com.zfsoft.web.utils.RemoteAddrUtils;

/**
 * 资源拦截器
 * @className	： OauthInterceptor
 * @description	： TODO(描述这个类的作用)
 * @author 		：樊新亮（1505）
 * @date		： 2017年9月24日 下午3:05:01
 * @version 	V1.0
 */
public class OauthInterceptor implements HandlerInterceptor{

	private OauthProperties oauth;
	
	private RestTemplateUtils rest;
	
	private InvokeCount invokeCount;
	
	public OauthInterceptor(OauthProperties oauth, RestTemplateUtils rest, InvokeCount invokeCount) {
		this.oauth = oauth;this.rest = rest;this.invokeCount = invokeCount;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(!oauth.isEnabled()) {return true;}
		String headStr = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(headStr == null || headStr.indexOf(" ") <= 0) {
			throw new OAuthException("token为空或token格式不正确!");
		}
		String accessToken = headStr.split(" ")[1];
		Map<String, Object> params = new HashMap<>();params.put("token", accessToken);
		params.put("path", request.getRequestURI().replaceAll(request.getContextPath(), ""));
		params.put("invokeIp", RemoteAddrUtils.getRemoteAddr(request));
		params.put("invokeFrequency", invokeCount.get(JwtToken.getClientId(accessToken)));
		JSONObject res = JSONObject.parseObject(rest.post(oauth.getCheckAccessTokenUrl(), params, "json"));
		if(res.getInteger("code") == 200) {
			request.setAttribute(RequestAttributes.ATTR_ACCESS_TOKEN, accessToken);
			return res.getBooleanValue("data");
		} else {
			throw new OAuthException("errorcode:" + res.getString("code") + "--" + res.getString("message"));
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
	
}
