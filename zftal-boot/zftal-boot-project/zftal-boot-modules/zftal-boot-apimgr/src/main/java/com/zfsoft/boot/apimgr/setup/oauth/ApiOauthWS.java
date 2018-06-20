/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.setup.oauth;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zfsoft.api.utils.ResultUtils;
import com.zfsoft.api.web.WebContext;

@Component
@WebService(name = "accessToken", targetNamespace = "http://dsb.zfsoft.com/")
public class ApiOauthWS {
	
	@Autowired
	private OauthProperties oauth;
	@Autowired
	private RestTemplateUtils rest;
	@Resource(name = "messageSource")
	protected MessageSource messageSource;
	
	@WebMethod(action = "http://dsb.zfsoft.com/onlyAccessToken", operationName = "onlyAccessToken")
	public String onlyAccessToken(@WebParam(name = "appKey", targetNamespace = "http://dsb.zfsoft.com/", mode = Mode.IN) String appKey,
			@WebParam(name = "appSecret", targetNamespace = "http://dsb.zfsoft.com/", mode = Mode.IN) String appSecret) {
		
		if(!oauth.isEnabled()) {
			return JSON.toJSONString(ResultUtils.tokenMap("fail", messageSource.getMessage("API-I100010",null,WebContext.getLocale())));
		}
		
		/**验证appKey和appSecret*/
		boolean flag = false;
		Map<String, Object> params = new HashMap<>();params.put("clientId", appKey);params.put("clientSecret", appSecret);
		JSONObject res = JSONObject.parseObject(rest.post(oauth.getCheckClientUrl(), params, "json"));
		if(res.getInteger("code") == 200) {
			flag = res.getBooleanValue("data");
		}else{
			return JSON.toJSONString(ResultUtils.tokenMap("fail", res.getString("message")));
		}
		
		String accessToken = "";
		
		if(flag) {
			/**获取Token*/
			params = new HashMap<>();params.put("clientId", appKey);
			res = JSONObject.parseObject(rest.post(oauth.getAccessTokenByIdUrl(), params, "json"));
			if(res.getInteger("code") == 200) {
				accessToken = res.getJSONObject("data").getString("accessToken");
			}else{
				return JSON.toJSONString(ResultUtils.tokenMap("fail", res.getString("message")));
			}
		}
		
		return accessToken;
	}
	
	@WebMethod(action = "http://dsb.zfsoft.com/accessToken", operationName = "accessToken")
	public String accessToken(@WebParam(name = "appKey", targetNamespace = "http://dsb.zfsoft.com/", mode = Mode.IN) String appKey,
			@WebParam(name = "appSecret", targetNamespace = "http://dsb.zfsoft.com/", mode = Mode.IN) String appSecret) {
		
		if(!oauth.isEnabled()) {
			return JSON.toJSONString(ResultUtils.tokenMap("fail", messageSource.getMessage("API-I100010",null,WebContext.getLocale())));
		}
		
		/**验证appKey和appSecret*/
		boolean flag = false;
		Map<String, Object> params = new HashMap<>();params.put("clientId", appKey);params.put("clientSecret", appSecret);
		JSONObject res = JSONObject.parseObject(rest.post(oauth.getCheckClientUrl(), params, "json"));
		if(res.getInteger("code") == 200) {
			flag = res.getBooleanValue("data");
		}else{
			return JSON.toJSONString(ResultUtils.tokenMap("fail", res.getString("message")));
		}
		
		Object data = null;
		if(flag) {
			/**获取Token*/
			params = new HashMap<>();params.put("clientId", appKey);
			res = JSONObject.parseObject(rest.post(oauth.getAccessTokenByIdUrl(), params, "json"));
			if(res.getInteger("code") == 200) {
				data = res.get("data");
			}else{
				return JSON.toJSONString(ResultUtils.tokenMap("fail", res.getString("message")));
			}
		}
		
		return JSON.toJSONString(ResultUtils.tokenMap("success", JSONObject.toJSONString(data)));
	}
	
	@WebMethod(action = "http://dsb.zfsoft.com/accessTokenByParamOrder", operationName = "accessTokenByParamOrder")
	public String accessTokenByParamOrder(String appKey, String appSecret) {
		
		if(!oauth.isEnabled()) {
			return JSON.toJSONString(ResultUtils.tokenMap("fail", messageSource.getMessage("API-I100010",null,WebContext.getLocale())));
		}
		
		/**验证appKey和appSecret*/
		boolean flag = false;
		Map<String, Object> params = new HashMap<>();params.put("clientId", appKey);params.put("clientSecret", appSecret);
		JSONObject res = JSONObject.parseObject(rest.post(oauth.getCheckClientUrl(), params, "json"));
		if(res.getInteger("code") == 200) {
			flag = res.getBooleanValue("data");
		}else{
			return JSON.toJSONString(ResultUtils.tokenMap("fail", res.getString("message")));
		}
		
		Object data = null;
		if(flag) {
			/**获取Token*/
			params = new HashMap<>();params.put("clientId", appKey);
			res = JSONObject.parseObject(rest.post(oauth.getAccessTokenByIdUrl(), params, "json"));
			if(res.getInteger("code") == 200) {
				data = res.get("data");
			}else{
				return JSON.toJSONString(ResultUtils.tokenMap("fail", res.getString("message")));
			}
		} else {
			return JSON.toJSONString(ResultUtils.tokenMap("fail", messageSource.getMessage("API-I100110",null,WebContext.getLocale())));
		}
		
		return JSON.toJSONString(ResultUtils.tokenMap("success", JSONObject.toJSONString(data)));
	}
}
