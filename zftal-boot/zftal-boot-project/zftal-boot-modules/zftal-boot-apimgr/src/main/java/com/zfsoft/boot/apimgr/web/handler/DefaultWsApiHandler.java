/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.handler;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.zfsoft.api.service.BaseAwareService;
import com.zfsoft.api.utils.WebUtils;
import com.zfsoft.api.web.WebContext;
import com.zfsoft.boot.apimgr.setup.count.InvokeCount;
import com.zfsoft.boot.apimgr.setup.event.ApiInvokedEvent;
import com.zfsoft.boot.apimgr.setup.oauth.OauthProperties;
import com.zfsoft.boot.apimgr.setup.oauth.RestTemplateUtils;
import com.zfsoft.boot.apimgr.util.JwtToken;
import com.zfsoft.boot.apimgr.web.exceptions.OAuthException;
import com.zfsoft.web.utils.RemoteAddrUtils;
import com.zfsoft.boot.webv5.setup.constant.RequestAttributes;

@Component
public class DefaultWsApiHandler extends BaseAwareService implements WsApiHandler {
	
	@Autowired
	private OauthProperties oauth;
	@Autowired
	private RestTemplateUtils rest;
	@Autowired(required=false)
	private InvokeCount invokeCount;
	
	@Value("${api.ip.blacklist}")
	private String blacklist;
	
	@Value("${cxf.path}")
	private String cxfRootPath;
	
	@Autowired
	private ApiHandlerExec handler;
	
	@Override
	public boolean before(String deployId, String type, String[] pKeys,Object[] values) throws Exception{
		HttpServletRequest req = WebUtils.toHttp(WebContext.getRequest());
		/**WS客户端验证token*/
		if(type.equals("Axis") && oauth.isEnabled()) {
			if(values[values.length - 1] == null) {
				throw new OAuthException("token不能为空!");
			}
			String accessToken = values[values.length - 1].toString();
			Map<String, Object> params = new HashMap<>();
			params.put("token", accessToken);
			params.put("path", req.getRequestURI().replaceAll(req.getContextPath()+cxfRootPath, ""));
			params.put("invokeIp", RemoteAddrUtils.getRemoteAddr(req));
			params.put("invokeFrequency", invokeCount.get(JwtToken.getClientId(accessToken)));
			JSONObject res = JSONObject.parseObject(rest.post(oauth.getCheckAccessTokenUrl(), params, "json"));
			if(res.getInteger("code") == 200) {
				req.setAttribute(RequestAttributes.ATTR_ACCESS_TOKEN, accessToken);
				return res.getBooleanValue("data");
			} else {
				throw new OAuthException(res.getString("message"));
			}
		}
		
		/**黑名单(内部用)*/
		String[] blacklists = blacklist.split(",");
		for (String regex : blacklists) {
			if(Pattern.matches(regex, RemoteAddrUtils.getRemoteAddr(req))) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public Object exec(String deployId, String type, String[] pKeys,Object[] values) throws Exception {
		Map<String, Object> par = new HashMap<>();
		int i = 0;
		for (String key : pKeys) {
			par.put(key, values[i]);
			i ++;
		}
		return handler.exec(deployId, par);
	}

	@Override
	public void after(String deployId, String type, String[] pKeys,Object[] values, Object res, Exception ex, long startTime) {
		/**发送调用通知*/
		Map<String, Object> par = new HashMap<>();
		int i = 0;
		for (String key : pKeys) {
			par.put(key, values[i]);
			i ++;
		}
		
		String token = values[values.length - 1] != null ? values[values.length - 1].toString() : "";
		String appkey = "proxy";
		if(!StringUtils.isEmpty(token)) {
			try {
				appkey = JwtToken.getClientId(token);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		/**发送调用完成后的事件实现异步处理,提供接口访问速度*/
		getContext().publishEvent(new ApiInvokedEvent(appkey, WebContext.getRequest(), deployId, par, res, ex, startTime));
	}
	
}
