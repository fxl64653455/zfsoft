/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.setup.builder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.Builder;

import com.alibaba.fastjson.JSONObject;
import com.zfsoft.boot.apimgr.web.dto.ApiError;
import com.zfsoft.boot.apimgr.web.dto.ApiField;
import com.zfsoft.boot.apimgr.web.dto.ApiParam;

@SuppressWarnings("unchecked")
public class BizApiDescBuilder implements Builder<String> {

	/**
	 * 接口数据描述信息结构如下：
	 * {
	 * 		desc  : {
	 * 			...
	 * 		},
	 * 		bind  : {
	 * 			rocketmq :  {
	 * 				topic : tag
	 * 			}
	 * 		},
	 * 		input : {
	 * 			type 	:  Axis / Axis2/ CXF / HTTP,
	 * 			url		:  API URL,
	 * 			params 	: [
	 * 				{ key:"参数编码", name:"参数名称", type : "字段类型",  required : 是否必填,  desc : "参数描述"},
	 * 				{ key:"参数编码", name:"参数名称", type : "字段类型",  required : 是否必填,  desc : "参数描述"},
	 * 				...
	 * 			],
	 * 			authz	: {
	 * 				plugin 		: pluginId,
	 * 				extension	: extensionId
	 * 			}
	 * 		},
	 * 		output: {
	 * 			type 		: JSON / XML/ String,
	 * 			outcase 	: '{"error_code": 0, "reason": "成功", "result": 2}' ,
	 * 			fields		: [
	 * 				{ key:"字段编码", name:"字段名称", type : "字段类型",  desc : "字段描述"},
	 * 				{ key:"字段编码", name:"字段名称", type : "字段类型",  desc : "字段描述"},
	 * 				...
	 * 			],
	 * 			errors		: [
	 * 				{ key:"错误编码", desc : "错误描述"},
	 * 				{ key:"错误编码", desc : "错误描述"},
	 * 				...
	 * 			],
	 * 			namespace	: WS接口需要指定命名空间,通常指您的接口所在域名的倒叙，如：zfsot.com,
	 * 			tags		: ["数据接口","用户查询",...]
	 * 			...
	 * 		},
	 * 		usecase : [Java,JavaScript,Python,...]
	 * }
	 * 
	 */
	private JSONObject desc;

	public BizApiDescBuilder() {
		desc = new JSONObject();
	}

	/**
	 * 
	 * @description ： 描述信息
	 * @author ： 万大龙（743）
	 * @date ：2017年9月26日 上午9:19:42
	 * @param descMap
	 * @return
	 */
	public BizApiDescBuilder desc(final Map<String, String> descMap) {
		desc.put("desc", descMap);
		return this;
	}
	
	public BizApiDescBuilder rocketmq(String topic,String tag) {
		Map<String, Object> bind = this.bind();
		Map<String, Object> mq = new HashMap<String, Object>();
		mq.put("topic", topic);
		mq.put("tag", tag);
		bind.put("rocketmq", mq);
		return this;
	}

	public BizApiDescBuilder invoke(String apiId,String invokeDeployId,String paramRelation) {
		Map<String, Object> invoke = this.invoke();
		invoke.put("apiId", apiId);invoke.put("invokeDeployId", invokeDeployId);invoke.put("paramRelation", paramRelation);
		return this;
	}
	
	public BizApiDescBuilder input(final String type,final String method, final String url,final String namespace, final String operName, final List<ApiParam> params) {
		Map<String, Object> input = this.input();
		input.put("type", type);
		input.put("method", method);
		input.put("url", url);
		input.put("namespace", namespace);
		input.put("operName", operName);
		input.put("params", params);
		return this;
	}
	
	public BizApiDescBuilder authz(final String pluginId,final String extensionId) {
		Map<String, Object> input = this.input();
		Map<String, Object> authz = new HashMap<String, Object>();
		authz.put("plugin", pluginId);
		authz.put("extension", extensionId);
		input.put("authz", authz);
		return this;
	}

	public BizApiDescBuilder output(final String type, final String outcase, final List<ApiField> fields) {
		Map<String, Object> output = this.output();
		output.put("type", type);
		output.put("outcase", outcase);
		output.put("fields", fields);
		return this;
	}
	
	public BizApiDescBuilder errors(final List<ApiError> errors) {
		Map<String, Object> output = this.output();
		output.put("errors", errors);
		return this;
	}
	
	public BizApiDescBuilder namespace(String namespace) {
		Map<String, Object> output = this.output();
		output.put("namespace", namespace);
		return this;
	}
	
	public BizApiDescBuilder tags(final String... tags) {
		Map<String, Object> output = this.output();
		output.put("tags", tags);
		return this;
	}

	public BizApiDescBuilder usecases(final String... usecases) {
		Map<String, Object> output = this.output();
		output.put("usecases", usecases);
		return this;
	}

	private Map<String, Object> input() {
		Object input = desc.get("input");
		if (null == input) {
			input = new HashMap<String, Object>();
		}
		desc.put("input", input);
		return (Map<String, Object>) input;
	}

	private Map<String, Object> output() {
		Object output = desc.get("output");
		if (null == output) {
			output = new HashMap<String, Object>();
		}
		desc.put("output", output);
		return (Map<String, Object>) output;
	}
	
	private Map<String, Object> bind() {
		Object bind = desc.get("bind");
		if (null == bind) {
			bind = new HashMap<String, Object>();
		}
		desc.put("bind", bind);
		return (Map<String, Object>) bind;
	}
	
	private Map<String, Object> invoke() {
		Object invoke = desc.get("invoke");
		if (null == invoke) {
			invoke = new HashMap<String, Object>();
		}
		desc.put("invoke", invoke);
		return (Map<String, Object>) invoke;
	}
	
	@Override
	public String build() {
		return desc.toJSONString();
	}
	
	public static BizApiDescBuilder get() {
		return new BizApiDescBuilder();
	}


}
