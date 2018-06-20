/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.setup.builder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.builder.Builder;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@SuppressWarnings("unchecked")
public class ApiDataDescBuilder implements Builder<String> {

	/**
	 * 接口数据描述信息结构如下：
	 * {
	 * 		desc  : {
	 * 			
	 * 		},
	 * 		input : {
	 * 			type 		: Table / SQL,
	 * 			table		: Table Name
	 * 			sql			: SQL
	 * 		},
	 * 		output: {
	 * 			type : Map/List
	 * 			resultMap 	: [
	 * 				{ column : "响应字段名称", alias:"字段别名" },
	 * 				{ column : "响应字段名称", alias:"字段别名" },
	 * 				...
	 * 			]
	 * 		}
	 * }
	 * 
	 */
	private JSONObject desc;
	
	public ApiDataDescBuilder() {
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
	public ApiDataDescBuilder desc(Map<String,String> descMap){
		desc.put("desc", descMap);
		return this;
	}
	
	/**
	 * @description	： 数据中心标准表
	 * @author 		： 万大龙（743）
	 * @date 		：2017年9月26日 上午9:19:52
	 * @param table
	 * @return
	 */
	public ApiDataDescBuilder tableInput(final String table) {
		
		Map<String, Object> input = this.input();
		
		input.put("type", "table");
		input.put("table", table);
		
		return this;
	}
	
	/**
	 * 
	 * @description	： SQL语句
	 * @author 		： 万大龙（743）
	 * @date 		：2017年9月26日 上午9:20:50
	 * @param sql
	 * @return
	 */
	public ApiDataDescBuilder sqlInput(final String sql) {
		
		Map<String, Object> input = this.input();
		
		input.put("type", "sql");
		input.put("sql", sql);
		
		return this;
	}
	
	public ApiDataDescBuilder outputMap(final Map<String,String> columnMap) {
		return this.output("Map", columnMap);
	}
	
	public ApiDataDescBuilder outputList(final Map<String,String> columnMap) {
		return this.output("List", columnMap);
	}
	
	protected ApiDataDescBuilder output(final String type,final Map<String,String> columnMap) {
		
		Map<String, Object> output = this.output();
		
		output.put("type", type);
		
		JSONArray resultMap = new JSONArray();
		
		Iterator<Entry<String, String>> ite = columnMap.entrySet().iterator();
		while (ite.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) ite.next();
			
			Map<String,String> column = new HashMap<String,String>();
			//字段名称
			column.put("column", entry.getKey());
			//字段别名
			column.put("alias", entry.getValue());
			
			resultMap.add(column);
		}
		
		output.put("resultMap", resultMap);
		
		
		return this;
	}
	
	public static ApiDataDescBuilder get() {
		return new ApiDataDescBuilder();
	}
	
	
	@Override
	public String build() {
		return desc.toJSONString();
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
}
