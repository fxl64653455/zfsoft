/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.zfsoft.api.utils.ResultUtils;
import com.zfsoft.boot.apimgr.setup.oauth.OauthProperties;
import com.zfsoft.boot.apimgr.setup.oauth.RestTemplateUtils;
import com.zfsoft.webmvc.controller.BaseController;

@RestController
@RequestMapping("/oauth2")
public class ApiOauthController extends BaseController{
	
	@Autowired
	private OauthProperties oauth;
	@Autowired
	private RestTemplateUtils rest;
	
	@PostMapping("/accessToken")
	public Object accessToken(@RequestParam("appKey") String appKey, @RequestParam("appSecret") String appSecret){
		
		if(!oauth.isEnabled()) {
			return ResultUtils.tokenMap(STATUS_FAIL, getMessage("API-I100010"));
		}
		
		/**验证appKey和appSecret*/
		boolean flag = false;
		Map<String, Object> params = new HashMap<>();params.put("clientId", appKey);params.put("clientSecret", appSecret);
		JSONObject res = JSONObject.parseObject(rest.post(oauth.getCheckClientUrl(), params, "json"));
		if(res.getInteger("code") == 200) {
			flag = res.getBooleanValue("data");
		}else{
			return ResultUtils.tokenMap(STATUS_FAIL, res.getString("message"));
		}
		
		Object data = null;
		if(flag) {
			/**获取Token*/
			params = new HashMap<>();params.put("clientId", appKey);
			res = JSONObject.parseObject(rest.post(oauth.getAccessTokenByIdUrl(), params, "json"));
			if(res.getInteger("code") == 200) {
				data = res.get("data");
			}else{
				return ResultUtils.tokenMap(STATUS_FAIL, res.getString("message"));
			}
		} else {
			return ResultUtils.tokenMap(STATUS_FAIL, getMessage("API-I100110"));
		}
		
		return ResultUtils.tokenMap(STATUS_SUCCESS, JSONObject.toJSONString(data));
	}
	
}
