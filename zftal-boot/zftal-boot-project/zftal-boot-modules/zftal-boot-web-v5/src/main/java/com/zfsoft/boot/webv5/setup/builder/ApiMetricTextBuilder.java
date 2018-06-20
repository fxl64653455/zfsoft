/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.webv5.setup.builder;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.Builder;

import com.alibaba.fastjson.JSONObject;

public class ApiMetricTextBuilder implements Builder<String> {

	/**
	 * 接口数据描述信息结构如下：
	 * 
	 * {
	 * 	  "appkey" 		: "xadasdasdasdasd",
	 * 	  "addr"		: "addr",
	 * 	  "bizName"		: "bizName",
	 * 	  "uri"			: "test/xxx",
	 * 	  "userAgent"	: "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:57.0) Gecko/20100101 Firefox/57.0"
	 * }
	 * 
	 */
	private JSONObject body;

	public ApiMetricTextBuilder() {
		body = new JSONObject();
	}
 
	public ApiMetricTextBuilder input(final String appkey,final String addr, final String bizName, final String uri, final String userAgent) {
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("appkey", appkey);
		input.put("addr", addr);
		input.put("bizName", bizName);
		input.put("uri", uri);
		input.put("userAgent", userAgent);
		body.putAll(input);
		return this;
	}
	
	@Override
	public String build() {
		return body.toJSONString();
	}
	
	public static ApiMetricTextBuilder get() {
		return new ApiMetricTextBuilder();
	}


}
