/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.setup.oauth;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

/**
 * @className	： RestTemplateUtils
 * @description	： TODO(描述这个类的作用)
 * @author 		：樊新亮（1505）
 * @date		： 2018年3月8日 上午10:21:42
 * @version 	V1.0
 */
public class RestTemplateUtils {

	private RestTemplate restTemplate;
	private OauthProperties oauthProperties;
	private String accessToken;
	private Long tokenExpiryTime;
	
	public RestTemplateUtils(OauthProperties oauthProperties, RestTemplate restTemplate) {
		this.oauthProperties = oauthProperties;
		this.restTemplate = restTemplate;
	}

	/**
	 * @description	： 发送GET请求
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年3月8日 上午10:21:47
	 * @param url
	 * @param params 
	 * @param bodytype default form; form/json
	 * @return
	 */
	public String get(String url, Map<String, ?> params, String bodytype) {
		if(bodytype != null || "json".equals(bodytype)) {
			return jsonBodyRequest(url, HttpMethod.GET, params, getHeader()).getBody();
		}
		return formBodyRequest(url, HttpMethod.GET, params, getHeader()).getBody();
	}
	
	/**
	 * @description	： 发送POST请求
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年3月8日 上午10:22:50
	 * @param url
	 * @param params
	 * @param bodytype default form; form/json
	 * @return
	 */
	public String post(String url, Map<String, ?> params, String bodytype) {
		if(bodytype != null || "json".equals(bodytype)) {
			return jsonBodyRequest(url, HttpMethod.POST, params, getHeader()).getBody();
		}
		return formBodyRequest(url, HttpMethod.POST, params, getHeader()).getBody();
	}
	
	/**
	 * @description	： 发送PUT请求
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年3月8日 上午10:23:41
	 * @param url
	 * @param params
	 * @param bodytype default form; form/json
	 * @return
	 */
	public String put(String url, Map<String, ?> params, String bodytype) {
		if(bodytype != null || "json".equals(bodytype)) {
			return jsonBodyRequest(url, HttpMethod.PUT, params, getHeader()).getBody();
		}
		return formBodyRequest(url, HttpMethod.PUT, params, getHeader()).getBody();
	}
	
	/**
	 * @description	： 发送DELETE请求
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年3月8日 上午10:23:57
	 * @param url
	 * @param params
	 * @param bodytype default form; form/json
	 * @return
	 */
	public String delete(String url, Map<String, ?> params, String bodytype) {
		if(bodytype != null || "json".equals(bodytype)) {
			return jsonBodyRequest(url, HttpMethod.DELETE, params, getHeader()).getBody();
		}
		return formBodyRequest(url, HttpMethod.DELETE, params, getHeader()).getBody();
	}
	
	private String getAccessToken() {
		if(accessToken == null && tokenExpiryTime == null) {
			refreshToken();
		}
		if(System.currentTimeMillis() >= tokenExpiryTime - 10000) {
			refreshToken();
		}
		return accessToken;
	}
	
	private void refreshToken() {
		String state = "security_" + new Random().nextInt(999_999);
		String url = oauthProperties.getAuthorizeUrl() + "?response_type=code";
		url += "&client_id=" + oauthProperties.getClientId();
		url += "&state="+state;
		String authCodeRes = restTemplate.getForObject(url,String.class);
		String[] pars = authCodeRes.split("&");
		Map<String, String> parameters = new HashMap<>();
		for (String par : pars) {
			String[] str = par.split("=");
			parameters.put(str[0], str[1]);
		}
		if(!state.equals(parameters.get("state"))) {
			System.out.println("state value does match!");
			return;
		}
		String authorizationCode = parameters.get("authorizationCode");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded");
		String httpBody = "grant_type=authorization_code";
		httpBody += "&redirect_uri=blank";httpBody += "&code=" + authorizationCode;
		httpBody += "&client_id=" + oauthProperties.getClientId();httpBody += "&client_secret=" + oauthProperties.getClientSecret();
		HttpEntity<String> requestEntity = new HttpEntity<String>(httpBody, headers);
		String tokenRes = restTemplate.postForObject(oauthProperties.getAccessTokenUrl(), requestEntity, String.class);
		pars = tokenRes.split("&");
		parameters = new HashMap<>();
		for (String par : pars) {
			String[] str = par.split("=");
			parameters.put(str[0], str[1]);
		}
		accessToken = parameters.get("access_token");
        Long expiresIn = Long.parseLong(parameters.get("expires_in"));
        tokenExpiryTime = System.currentTimeMillis() + expiresIn * 1000;
	}
	
	private HttpHeaders getHeader() {
		HttpHeaders headers = new HttpHeaders();
		if(oauthProperties.isEnabled()) {
			headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken());
		}
		return headers;
	}
	
	private ResponseEntity<String> formBodyRequest(String url, HttpMethod method, Map<String, ?> params, HttpHeaders requestHeaders) {
		if(params == null) {params = new HashMap<>();}
		String httpBody = null;
		Set<String> keys = params.keySet();
		for (String key : keys) {
			if(httpBody == null) {
				httpBody = key + "=" + params.get(key);
			} else {
				httpBody += "&" + key + "=" + params.get(key);
			}
		}
		requestHeaders.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        HttpEntity<String> requestEntity = new HttpEntity<String>(httpBody, requestHeaders);
        ResponseEntity<String> rss = restTemplate.exchange(url, method, requestEntity, String.class, params);
        return rss;
    }
	
	private ResponseEntity<String> jsonBodyRequest(String url, HttpMethod method, Map<String, ?> params, HttpHeaders requestHeaders) {
		if(params == null) {params = new HashMap<>();}
		requestHeaders.add("Content-Type", "application/json; charset=UTF-8");
		String httpBody = JSON.toJSONString(params);
        HttpEntity<String> requestEntity = new HttpEntity<String>(httpBody, requestHeaders);
        ResponseEntity<String> rss = restTemplate.exchange(url, method, requestEntity, String.class, params);
        return rss;
    }
}
