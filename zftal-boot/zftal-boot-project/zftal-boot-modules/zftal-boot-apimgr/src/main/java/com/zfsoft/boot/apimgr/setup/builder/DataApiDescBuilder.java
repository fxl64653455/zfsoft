/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.setup.builder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.Builder;

import com.alibaba.fastjson.JSONObject;
import com.zfsoft.boot.apimgr.web.dto.ApiField;
import com.zfsoft.boot.apimgr.web.dto.ApiParam;
@SuppressWarnings("unchecked")
public class DataApiDescBuilder implements Builder<String> {
	
	/**
	 * 接口数据描述信息结构如下：
	 * {
	 * 		desc  : {
	 * 			...
	 * 		},
	 * 		bind  : {
	 * 			data 	:  {
	 * 				dbid		: Data Source ID,
	 * 				exchType	: 数据交换类型：0: 新增数据 、1： 删除数据、2： 更新数据、3：查询数据,
	 * 				exchMethod	: 数据交换方式：0: 数据表 、1： 自定义SQL语句,
	 * 				table		: 数据表,
	 * 				sql			: 自定义SQL
	 * 			},
	 * 			rocketmq :  {
	 * 				topic : tag
	 * 			}
	 * 		},
	 * 		input : {
	 * 			type 	:  Data,
	 * 			params 	: [
	 * 				{ key:"参数编码", name:"参数名称",  type : "字段类型",  required : 是否必填,  desc : "参数描述"},
	 * 				{ key:"参数编码", name:"参数名称",  type : "字段类型",  required : 是否必填,  desc : "参数描述"},
	 * 				...
	 * 			]
	 * 		},
	 * 		output: {
	 * 			type 		: JSON / XML/ String,
	 * 			outcase 	: '{"error_code": 0, "reason": "成功", "result": 2}' ,
	 * 			fields		: [
	 * 				{ key:"字段编码", name:"字段名称", alias:"字段别名", type : "字段类型",   desc : "字段描述"},
	 * 				{ key:"字段编码", name:"字段名称", alias:"字段别名", type : "字段类型",   desc : "字段描述"},
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
	
	public DataApiDescBuilder() {
		desc = new JSONObject();
	}
	
	/**
	 * 
	 * @description	： 描述信息
	 * @author 		： 万大龙（743）
	 * @date 		：2017年9月26日 上午9:19:42
	 * @param descMap
	 * @return
	 */
	public DataApiDescBuilder desc(Map<String,String> descMap){
		desc.put("desc", descMap);
		return this;
	}

	public DataApiDescBuilder data(final String dbid,final String exchType,final String exchMethod,
			final String table,
			final String sql) {
		Map<String, Object> bind = this.bind();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("dbid", dbid);
		data.put("exchType", exchType);
		data.put("exchMethod", exchMethod);
		data.put("table", table);
		data.put("sql", sql);
		bind.put("data", data);
		return this;
	}
	
	public DataApiDescBuilder rocketmq(String topic,String tag) {
		Map<String, Object> bind = this.bind();
		Map<String, Object> mq = new HashMap<String, Object>();
		mq.put("topic", topic);
		mq.put("tag", tag);
		bind.put("rocketmq", mq);
		return this;
	}
	
	/**
	 * 
	 * @description	： TODO
	 * @author 		： 万大龙（743）
	 * @date 		：2017年10月24日 上午10:21:44
	 * @param params : 输入参数
	 * @return
	 */
	public DataApiDescBuilder input(final List<ApiParam> params,final List<ApiParam> updates) {
		Map<String, Object> input = this.input();
		input.put("type", "Data");
		input.put("params", params);
		input.put("updates", updates);
		return this;
	}
	
	/**
	 * 
	 * @description	： TODO
	 * @author 		： 万大龙（743）
	 * @date 		：2017年10月24日 上午10:22:24
	 * @param type		: 输出类型 JSON / XML/ String
	 * @param outcase	: 输出示例
	 * @param fields	: 输出字段
	 * @return
	 */
	public DataApiDescBuilder output(final String type,final String outcase,final List<ApiField> fields) {
		Map<String, Object> output = this.output();
		output.put("type", type);
		output.put("outcase", outcase);
		output.put("fields", fields);
		return this;
	}
	
	public DataApiDescBuilder pageable(final boolean pageable) {
		Map<String, Object> output = this.output();
		output.put("pageable", pageable);
		return this;
	}
	
	public DataApiDescBuilder namespace(final String namespace) {
		Map<String, Object> output = this.output();
		output.put("namespace", namespace);
		return this;
	}
	
	public DataApiDescBuilder tags(final String ...tags) {
		Map<String, Object> output = this.output();
		output.put("tags", tags);
		return this;
	}
	
	public DataApiDescBuilder usecases(final String ...usecases) {
		Map<String, Object> output = this.output();
		output.put("usecases", usecases);
		return this;
	}
	
	public DataApiDescBuilder invoke(String apiId,String invokeDeployId,String paramRelation) {
		Map<String, Object> invoke = this.invoke();
		invoke.put("apiId", apiId);invoke.put("invokeDeployId", invokeDeployId);invoke.put("paramRelation", paramRelation);
		return this;
	}
	
	private Map<String, Object> input() {
		Object input = desc.get("input");
		if(null == input) {
			input = new HashMap<String, Object>();
		}
		desc.put("input", input);
		return (Map<String, Object>) input;
	}
	
	private Map<String, Object> output() {
		Object output = desc.get("output");
		if(null == output) {
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
	
	public static DataApiDescBuilder get() {
		return new DataApiDescBuilder();
	}


}
