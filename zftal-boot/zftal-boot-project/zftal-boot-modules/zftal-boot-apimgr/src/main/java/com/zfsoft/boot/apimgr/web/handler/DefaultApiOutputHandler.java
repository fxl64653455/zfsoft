/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.handler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zfsoft.basicutils.StringUtils;
import com.zfsoft.boot.apimgr.dao.entities.ApiDeployModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiInfoModel;
import com.zfsoft.boot.apimgr.util.enums.ApiTypeEnum;
import com.zfsoft.boot.apimgr.web.dto.ApiDeployDto;
import com.zfsoft.boot.apimgr.web.dto.ApiError;
import com.zfsoft.boot.apimgr.web.dto.ApiField;
import com.zfsoft.boot.apimgr.web.dto.ApiInfoBizDto;
import com.zfsoft.boot.apimgr.web.dto.ApiInfoDataDto;
import com.zfsoft.boot.apimgr.web.dto.ApiParam;

@Component
public class DefaultApiOutputHandler implements ApiOutputHandler {

	/**
	 * 
	 * @description	： ApiDataModel解析
	 * @author 		：万大龙（743）
	 * @date 		：2017年10月20日 上午10:33:32
	 * @param rtModel
	 * @return
	 * @throws Exception
	 * @see com.zfsoft.boot.apimgr.setup.builder.ApiDataDescBuilder
	
	@Override
	public ApiDatasourceDto output(ApiDataSourceModel rtModel) {
		
		ApiDatasourceDto rtDto = new ApiDatasourceDto();
		
		rtDto.setId(rtModel.getId());
		rtDto.setName(rtModel.getName());
		rtDto.setType(rtModel.getType());
		rtDto.setMethod(rtModel.getMethod());
		rtDto.setInfo(rtModel.getInfo());
		rtDto.setDbid(rtModel.getDbid());
		
		//处理JSON格式的描述信息
		JSONObject desc = JSONObject.parseObject(rtModel.getDesc());
		
		//解析输入数据
		JSONObject input = desc.getJSONObject("input");
		
		rtDto.setTable(input.getString("table"));
		rtDto.setSql(StringEscapeUtils.unescapeHtml(input.getString("sql")));
		
		//解析输出数据
		JSONObject output = desc.getJSONObject("output");

		//接口数据来源表列名
		Map<String, String> columnMap = new LinkedHashMap<String, String>();
		//接口数据来源表列别名
		Map<String, String> aliasMap = new LinkedHashMap<String, String>();
		
		JSONArray resultMap = output.getJSONArray("resultMap");
		for (int i = 0; i < resultMap.size(); i++) {
			// { column : "响应字段名称", alias:"字段别名" }
			JSONObject item = resultMap.getJSONObject(i);
			String alias = item.getString("alias");
			String column = item.getString("column");
			columnMap.put(column, column);
			aliasMap.put(column, alias);
		}
		
		rtDto.setAlias(aliasMap);
		rtDto.setColumns(columnMap);
		
		return rtDto;
	}
	 */
	@Override
	public ApiInfoBizDto outputBiz(ApiInfoModel rtModel) {
		
		ApiInfoBizDto infoDto = new ApiInfoBizDto();
		
		infoDto.setId(rtModel.getId());
		infoDto.setName(rtModel.getName());
		infoDto.setInfo(rtModel.getInfo());
		infoDto.setDetail(rtModel.getDetail());
		infoDto.setOwner(rtModel.getOwner());
		infoDto.setDeploynum(rtModel.getDeploynum());
		infoDto.setTime(rtModel.getTime());
		infoDto.setInvokeEnable(rtModel.getInvokeEnable());
		
		//处理JSON格式的描述信息
		JSONObject desc = JSONObject.parseObject(rtModel.getDesc());
		
		//解析输入数据
		JSONObject input = desc.getJSONObject("input");
		
		infoDto.setType(input.getString("type"));
		infoDto.setMethod(input.getString("method"));
		infoDto.setNamespace(input.getString("namespace"));
		infoDto.setOperName(input.getString("operName"));
		infoDto.setUrl(input.getString("url"));
		
		//接口认证信息
		JSONObject authz = input.getJSONObject("authz");
		if(authz != null) {
			infoDto.setPluginId(authz.getString("plugin"));
			infoDto.setExtensionId(authz.getString("extension"));
		}
		
		//解析绑定数据
		JSONObject bind = desc.getJSONObject("bind");
		/*消息主题相关*/
		JSONObject rocketmq = bind.getJSONObject("rocketmq");
		if(rocketmq != null) {
			infoDto.setTopic(rocketmq.getString("topic"));
			infoDto.setTag(rocketmq.getString("tag"));
		}
		
		/*
		 * 输入参数
		 */
		List<ApiParam> paramList = new LinkedList<ApiParam>();
		JSONArray params = input.getJSONArray("params");
		if(params != null) {
			for (int i = 0; i < params.size(); i++) {
				// { key:"参数编码", name:"参数名称",  type : "字段类型",  required : 是否必填,  desc : "参数描述"},
				JSONObject item = params.getJSONObject(i);
				paramList.add(new ApiParam(item.getString("key"), item.getString("name"), item.getString("type"),item.getString("required") , item.getString("desc")));
			}
		}
		infoDto.setParamList(paramList);
		
		//解析输出数据
		JSONObject output = desc.getJSONObject("output");
		infoDto.setOutType(output.getString("type"));
		infoDto.setOutCase(output.getString("outcase"));
		
		/*
		 * 输出参数
		 */
		JSONArray fields = output.getJSONArray("fields");
		List<ApiField> fieldList = new ArrayList<ApiField>();
		if(fields != null) {
			for (int i = 0; i < fields.size(); i++) {
				// { key:"字段编码", name:"字段名称", type : "字段类型",  desc : "字段描述"},
				JSONObject item = fields.getJSONObject(i);
				fieldList.add(new ApiField(item.getString("key"), item.getString("name"), item.getString("type"), item.getString("desc")));
			} 
		}
		infoDto.setFieldList(fieldList);
		
		/**
		 * 接口输出错误
		 */
		JSONArray errors = output.getJSONArray("errors");
		List<ApiError> errorList = new ArrayList<ApiError>();
		if(errors != null) {
			for (int i = 0; i < errors.size(); i++) {
				// { key:"错误编码", desc : "错误描述"},
				JSONObject item = errors.getJSONObject(i);
				errorList.add(new ApiError(item.getString("key"), item.getString("desc")));
			} 
		}
		infoDto.setErrorList(errorList);
		
		return infoDto;
	}

	@Override
	public ApiInfoDataDto outputData(ApiInfoModel rtModel) {
		
		ApiInfoDataDto infoDto = new ApiInfoDataDto();

		infoDto.setId(rtModel.getId());
		infoDto.setName(rtModel.getName());
		infoDto.setInfo(rtModel.getInfo());
		infoDto.setDetail(rtModel.getDetail());
		infoDto.setOwner(rtModel.getOwner());
		infoDto.setDeploynum(rtModel.getDeploynum());
		infoDto.setInvokeEnable(rtModel.getInvokeEnable());
		infoDto.setTime(rtModel.getTime());
		
		//处理JSON格式的描述信息
		JSONObject desc = JSONObject.parseObject(rtModel.getDesc());
		
		//解析绑定数据
		JSONObject bind = desc.getJSONObject("bind");
				
		/*
		 * 数据交换类型：com.zfsoft.boot.apimgr.util.DataExchangeTypeEnum
		 * 数据交换方式：com.zfsoft.boot.apimgr.util.DataExchangeMethodEnum
		 */
		JSONObject data = bind.getJSONObject("data");
		if(data != null) {
			infoDto.setDbid(data.getString("dbid"));
			infoDto.setExchType(data.getString("exchType"));
			infoDto.setExchMethod(data.getString("exchMethod"));
			infoDto.setTable(data.getString("table"));
			infoDto.setSql(data.getString("sql"));
		}
		
		/*消息主题相关*/
		JSONObject rocketmq = bind.getJSONObject("rocketmq");
		if(rocketmq != null) {
			infoDto.setTopic(rocketmq.getString("topic"));
			infoDto.setTag(rocketmq.getString("tag"));
		}
		
		//解析输入数据
		JSONObject input = desc.getJSONObject("input");
		infoDto.setType(input.getString("type"));
		/*
		 * 输入参数
		 */
		List<ApiParam> paramList = new LinkedList<ApiParam>();
		JSONArray params = input.getJSONArray("params");
		if(params != null) {
			for (int i = 0; i < params.size(); i++) {
				// { key:"参数编码", name:"参数名称",  type : "字段类型",  required : 是否必填,  desc : "参数描述"},
				JSONObject item = params.getJSONObject(i);
				paramList.add(new ApiParam(item.getString("key"), item.getString("name"), item.getString("type"),item.getString("required") , item.getString("desc")));
			}
			infoDto.setParamList(paramList);
		}
		
		/*
		 * 变更字段
		 */
		List<ApiParam> updateList = new LinkedList<ApiParam>();
		JSONArray updates = input.getJSONArray("updates");
		if(updates != null) {
			for (int i = 0; i < updates.size(); i++) {
				// { key:"参数编码", name:"参数名称",  type : "字段类型",  required : 是否必填,  desc : "参数描述"},
				JSONObject item = updates.getJSONObject(i);
				updateList.add(new ApiParam(item.getString("key"), item.getString("name"), item.getString("type"), item.getString("desc")));
			}
			infoDto.setUpdateList(updateList);
		}
		
		//解析输出数据
		JSONObject output = desc.getJSONObject("output");
		infoDto.setOutType(output.getString("type"));
		infoDto.setOutCase(output.getString("outcase"));
		
		/*
		 * 输出参数
		 */
		JSONArray fields = output.getJSONArray("fields");
		List<ApiField> fieldList = new ArrayList<ApiField>();
		if(fields != null) {
			for (int i = 0; i < fields.size(); i++) {
				// { key:"字段编码", name:"字段名称", type : "字段类型",  desc : "字段描述"},
				JSONObject item = fields.getJSONObject(i);
				fieldList.add(new ApiField(item.getString("key"), item.getString("name"), item.getString("type"), item.getString("alias"), item.getString("desc")));
			}
		}
		infoDto.setFieldList(fieldList);
		
		return infoDto;
	}
	
	@Override
	public ApiDeployDto output(ApiDeployModel rtModel) {

		ApiDeployDto infoDto = new ApiDeployDto();
		
		infoDto.setId(rtModel.getId());
		infoDto.setStatus(rtModel.getStatus());
		infoDto.setType(rtModel.getType());
		infoDto.setMethod(rtModel.getMethod());
		infoDto.setAddr(rtModel.getAddr());
		infoDto.setApiId(rtModel.getApiId());
		ApiInfoModel info = rtModel.getApi();
		if(info != null ) {
			infoDto.setApiName(info.getName());
			infoDto.setApiInfo(info.getInfo());
			infoDto.setApiDetail(info.getDetail());
		}
		
		//处理JSON格式的描述信息
		JSONObject desc = JSONObject.parseObject(rtModel.getDesc());
		
		//解析绑定数据
		JSONObject bind = desc.getJSONObject("bind");
				
		/*
		 * 数据交换类型：com.zfsoft.boot.apimgr.util.DataExchangeTypeEnum
		 * 数据交换方式：com.zfsoft.boot.apimgr.util.DataExchangeMethodEnum
		 */
		JSONObject data = bind.getJSONObject("data");
		if(data != null) {
			infoDto.setDbid(data.getString("dbid"));
			infoDto.setExchType(data.getString("exchType"));
			infoDto.setExchMethod(data.getString("exchMethod"));
			infoDto.setTable(data.getString("table"));
			infoDto.setSql(data.getString("sql"));
		}
		
		/*消息主题相关*/
		JSONObject rocketmq = bind.getJSONObject("rocketmq");
		if(rocketmq != null) {
			infoDto.setTopic(rocketmq.getString("topic"));
			infoDto.setTag(rocketmq.getString("tag"));
		}
		
		/*
		 * 输入参数
		 */
		JSONObject input = desc.getJSONObject("input");
		
		infoDto.setSrcApiType(input.getString("type"));
		infoDto.setSrcMethod(input.getString("method"));
		infoDto.setSrcNamespace(input.getString("namespace"));
		infoDto.setSrcOperName(input.getString("operName"));
		infoDto.setApiUrl(input.getString("url"));
		//如果dbid字段不为空，表示是数据源接口
		if(StringUtils.isNotEmpty(infoDto.getDbid())) {
			// 接口类型：biz、data；用于区别业务接口和数据源接口，不显示
			infoDto.setApiType(ApiTypeEnum.DATA.getKey());
		} else {
			infoDto.setApiType(ApiTypeEnum.BIZ.getKey());
		}
		
		//接口认证信息
		JSONObject authz = input.getJSONObject("authz");
		if(authz != null) {
			infoDto.setPluginId(authz.getString("plugin"));
			infoDto.setExtensionId(authz.getString("extension"));
		}
		
		//解析输入数据
		List<ApiParam> paramList = new LinkedList<ApiParam>();
		JSONArray params = input.getJSONArray("params");
		for (int i = 0; i < params.size(); i++) {
			// { key:"参数编码", name:"参数名称",  type : "字段类型",  required : 是否必填,  desc : "参数描述"},
			JSONObject item = params.getJSONObject(i);
			paramList.add(new ApiParam(item.getString("key"),item.getString("name"), item.getString("type"),item.getString("required") , item.getString("desc")));
		}
		infoDto.setParamList(paramList);
		
		/*
		 * 变更字段
		 */
		List<ApiParam> updateList = new LinkedList<ApiParam>();
		JSONArray updates = input.getJSONArray("updates");
		if(updates != null) {
			for (int i = 0; i < updates.size(); i++) {
				// { key:"参数编码", name:"参数名称",  type : "字段类型",  required : 是否必填,  desc : "参数描述"},
				JSONObject item = updates.getJSONObject(i);
				updateList.add(new ApiParam(item.getString("key"), item.getString("name"), item.getString("type"), item.getString("desc")));
			}
		}
		infoDto.setUpdateList(updateList);
		
		
		/*
		 * 输出参数
		 */
		JSONObject output = desc.getJSONObject("output");
		
		infoDto.setOutType(output.getString("type"));
		infoDto.setOutCase(output.getString("outcase"));
		infoDto.setPageable(BooleanUtils.toBoolean(output.getString("pageable")));
		infoDto.setNamespace(output.getString("namespace"));
		
		//接口个性标签
		JSONArray tagsArr = output.getJSONArray("tags");
		if(tagsArr != null) {
			String[] tags = new String[tagsArr.size()];
			for (int i = 0; i < tagsArr.size(); i++) {
				tags[i] = tagsArr.getString(i); 
			}
			infoDto.setTags(tags);
		} else {
			infoDto.setTags(new String[] {});
		}
		
		//语言版本：Java、JavaScript、PHP、Python...等 ； com.zfsoft.boot.apimgr.util.LanguageTypeEnum
		JSONArray usecases = output.getJSONArray("usecases");
		if(usecases != null) {
			String[] languages = new String[usecases.size()];
			for (int i = 0; i < usecases.size(); i++) {
				languages[i] = usecases.getString(i); 
			}
			infoDto.setLanguage(languages);
		} else {
			infoDto.setLanguage(new String[] {});
		}
		
		//解析输出数据
		JSONArray fields = output.getJSONArray("fields");
		List<ApiField> fieldList = new ArrayList<ApiField>();
		if(fields != null) {
			for (int i = 0; i < fields.size(); i++) {
				/* { key:"字段编码", name:"字段名称",  type : "字段类型",   desc : "字段描述"}
				 * 或者
				 * { key:"字段编码", name:"字段名称", alias:"字段别名", type : "字段类型",   desc : "字段描述"},
				 */
				JSONObject item = fields.getJSONObject(i);
				fieldList.add(new ApiField(item.getString("key"), item.getString("name"), item.getString("type"), item.getString("alias"),  item.getString("desc")));
			}
		}
		infoDto.setFieldList(fieldList);
		
		/**
		 * 接口输出错误
		 */
		JSONArray errors = output.getJSONArray("errors");
		List<ApiError> errorList = new ArrayList<ApiError>();
		if(errors != null) {
			for (int i = 0; i < errors.size(); i++) {
				// { key:"错误编码", desc : "错误描述"},
				JSONObject item = errors.getJSONObject(i);
				errorList.add(new ApiError(item.getString("key"), item.getString("desc")));
			} 
		}
		
		infoDto.setErrorList(errorList);
		
		return infoDto;
		
	}

	
}
