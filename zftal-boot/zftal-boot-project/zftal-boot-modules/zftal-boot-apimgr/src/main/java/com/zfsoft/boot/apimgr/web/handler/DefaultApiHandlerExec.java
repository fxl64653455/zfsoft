/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.handler;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.encoding.XMLType;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.alibaba.fastjson.JSON;
import com.zfsoft.api.utils.MessageUtil;
import com.zfsoft.api.utils.ResultUtils;
import com.zfsoft.api.utils.WebUtils;
import com.zfsoft.api.web.WebContext;
import com.zfsoft.basicutils.CollectionUtils;
import com.zfsoft.basicutils.StringUtils;
import com.zfsoft.boot.apimgr.dao.entities.ApiDeployModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiInfoModel;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiDataService;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiDeployService;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiInfoService;
import com.zfsoft.boot.apimgr.setup.builder.BizApiDescBuilder;
import com.zfsoft.boot.apimgr.setup.builder.DataApiDescBuilder;
import com.zfsoft.boot.apimgr.util.AxisFaultUtils;
import com.zfsoft.boot.apimgr.util.enums.ApiTypeEnum;
import com.zfsoft.boot.apimgr.util.enums.BizApiTypeEnum;
import com.zfsoft.boot.apimgr.util.enums.DataExchangeTypeEnum;
import com.zfsoft.boot.apimgr.util.enums.RequestMethodEnum;
import com.zfsoft.boot.apimgr.web.dto.ApiDeployDto;
import com.zfsoft.boot.apimgr.web.dto.ApiInfoBizDto;
import com.zfsoft.boot.apimgr.web.dto.ApiInfoDataDto;
import com.zfsoft.boot.apimgr.web.dto.ApiParam;
import com.zfsoft.boot.webv5.setup.constant.RequestAttributes;
import com.zfsoft.httpclient.ResponseContent;
import com.zfsoft.httpclient.utils.HttpClientUtils;
import com.zfsoft.httpclient.utils.HttpsClientUtils;
import com.zfsoft.plugin.api.ApiAuthExtensionPoint;
import com.zfsoft.plugin.api.annotation.ExtensionMapping;
import com.zfsoft.plugin.api.exception.PluginInvokeException;
import com.zfsoft.ws.axis.client.Param;
import com.zfsoft.ws.axis.client.utils.AxisClientUtils;

import ro.fortsoft.pf4j.PluginManager;

@Component
public class DefaultApiHandlerExec implements ApiHandlerExec {

	public static final Logger logger = LoggerFactory.getLogger(DefaultApiHandlerExec.class);
	protected static final String STATUS_SUCCESS = "success";
	protected static final String STATUS_FAIL = "fail";
	protected static final String STATUS_ERROR = "error";

	@Autowired
	private PluginManager pluginManager;
	@Autowired
	private IApiDeployService apiDeployService;
	@Autowired
	private IApiDataService apiDataService;
	@Autowired
	private ApiOutputHandler apiOutputHandler;
	@Resource(name = "messageSource")
	protected MessageSource messageSource;
	@Autowired
	private IApiInfoService apiInfoService;

	private JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();

	protected String getMessage(String key, Object[] params) {
		try {
			return getMessageSource().getMessage(key, params, WebContext.getLocale());
		} catch (Exception e) {
			return MessageUtil.getText(key, params);
		}
	}

	protected String getMessage(String key) {
		try {
			return getMessageSource().getMessage(key, null, WebContext.getLocale());
		} catch (Exception e) {
			return MessageUtil.getText(key);
		}
	}

	/**
	 * @description	： 根据插件参数执行相应的插件
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年11月13日 下午3:22:00
	 * @param dto
	 * @param paramMap 请求参数
	 * @param headers 请求头参数
	 * @param res 接口返回结果
	 * @return
	 * @throws PluginInvokeException
	 */
	private Object httpAuthPlugin(ApiDeployDto dto, Map<String, Object> paramMap,Map<String, String> headers, Object res) throws PluginInvokeException {

		if (StringUtils.hasText(dto.getPluginId()) && StringUtils.hasText(dto.getExtensionId())) {
			
			try {
				List<ApiAuthExtensionPoint> extensions = pluginManager.getExtensions(ApiAuthExtensionPoint.class, dto.getPluginId());
				for (ApiAuthExtensionPoint extension : extensions) {
					ExtensionMapping em = extension.getClass().getAnnotation(ExtensionMapping.class);
					if(StringUtils.hasText(em.id()) && em.id().equals(dto.getExtensionId())) {
						if(headers != null && headers.size() > 0) {
							extension.handleHeaderParams(paramMap, WebUtils.toHttp(WebContext.getRequest()));
						}
						if(paramMap != null) {
							extension.handleRequestParams(paramMap, WebUtils.toHttp(WebContext.getRequest()));
						}
						if(res != null) {
							return extension.handleResult(res);
						}
						break;
					}
				}
			} catch (Exception e) {
				throw new PluginInvokeException(dto.getPluginId(), dto.getExtensionId(), e);
			}
			
		}
		
		return res;
	}
	
	private Object exec(ApiDeployModel deploy, Map<String, Object> requestParams) throws Exception {
		
		Object res = null;

		// 解析出最终发布的接口信息
		ApiDeployDto dto = getApiOutputHandler().output(deploy);

		// 接口数据来源表列名
		Map<String, Object> paramMap = new LinkedHashMap<String, Object>();

		/*
		 * 接口输入参数检查和参数组装
		 */
		List<ApiParam> paramList = dto.getParamList();
		if (!CollectionUtils.isEmpty(paramList)) {
			// 检查查询是否必填
			for (ApiParam apiParam : paramList) {
				// 如果必填，则检查是否传递参数
				if (apiParam.getRequired().equals("1") && ObjectUtils.isEmpty(requestParams.get(apiParam.getKey()))) {
					// 返回异常信息
					return ResultUtils.status(ApiExceptinHandler.STATUS_FAIL, getMessage("API-I100002", new Object[] { apiParam.getKey() }));
				}
				// 如果是path参数则替换源接口URL
				String url = dto.getApiUrl();
				if(url != null && url.indexOf("{" + apiParam.getKey() + "}") >= 0) {
					url = url.replaceAll("\\{" + apiParam.getKey() + "\\}", requestParams.get(apiParam.getKey()).toString());
					dto.setApiUrl(url);
				} else {
					// 组装新的请求参数集合
					paramMap.put(apiParam.getKey(), requestParams.get(apiParam.getKey()));
				}
			}
		}
		
		// 业务型接口（第三方系统接口）
		if (ApiTypeEnum.BIZ.getKey().equalsIgnoreCase(dto.getApiType())) {

			Set<String> keys = paramMap.keySet();
			
			/* 源接口类型为HTTP */
			if (BizApiTypeEnum.HTTP.getKey().equalsIgnoreCase(dto.getSrcApiType())) {

				Map<String, String> headers = new HashMap<>();
				/** 插件处理请求参数 */
				httpAuthPlugin(dto, paramMap, headers, null);

				try {
					if (RequestMethodEnum.GET.getKey().equalsIgnoreCase(dto.getSrcMethod())) {
						ResponseContent rc = HttpClientUtils.httpRequestWithGet(dto.getApiUrl(), paramMap,CharEncoding.UTF_8, headers, HttpClientUtils.DEFAULT_CONTENT_HANDLER);
						res = rc.getContentText();
					} else if (RequestMethodEnum.POST.getKey().equalsIgnoreCase(dto.getSrcMethod())) {
						ResponseContent rc = HttpClientUtils.httpRequestWithPost(dto.getApiUrl(), paramMap,CharEncoding.UTF_8, null, headers, HttpClientUtils.DEFAULT_CONTENT_HANDLER);
						res = rc.getContentText();
					}
					
					/**插件处理返回结果*/
					res = httpAuthPlugin(dto, null, null, res);
					
				} catch (Exception e) {
					logger.error(e.getMessage());
					return ResultUtils.status(ApiExceptinHandler.STATUS_FAIL, getMessage("API-I100007"));
				}

			}
			/* 源接口类型为HTTPS */
			else if (BizApiTypeEnum.HTTPS.getKey().equalsIgnoreCase(dto.getSrcApiType())) {

				Map<String, String> headers = new HashMap<>();
				/** 插件处理请求参数 */
				httpAuthPlugin(dto, paramMap, headers, null);
				
				try {
					if (RequestMethodEnum.GET.getKey().equalsIgnoreCase(dto.getSrcMethod())) {
						ResponseContent rc = HttpsClientUtils.httpRequestWithGet(dto.getApiUrl(), paramMap,CharEncoding.UTF_8, headers, HttpClientUtils.DEFAULT_CONTENT_HANDLER);
						res = rc.getContentText();
					} else if (RequestMethodEnum.POST.getKey().equalsIgnoreCase(dto.getSrcMethod())) {
						ResponseContent rc = HttpsClientUtils.httpRequestWithPost(dto.getApiUrl(), paramMap,CharEncoding.UTF_8, null, headers, HttpClientUtils.DEFAULT_CONTENT_HANDLER);
						res = rc.getContentText();
					}
					
					/**插件处理返回结果*/
					res = httpAuthPlugin(dto, null, null, res);
					
				} catch (Exception e) {
					logger.error(e.getMessage());
					return ResultUtils.status(ApiExceptinHandler.STATUS_FAIL, getMessage("API-I100007"));
				}
				
			}
			/* 源接口类型为AXIS */
			else if (BizApiTypeEnum.AXIS.getKey().equalsIgnoreCase(dto.getSrcApiType())) {

				Param[] pars = new Param[keys.size()];
				int i = 0;
				for (String key : keys) {
					pars[i] = new Param(key, XMLType.XSD_STRING, paramMap.get(key), ParameterMode.IN);
					i++;
				}

				try {
					res = AxisClientUtils.invoke(dto.getApiUrl(), new QName(dto.getSrcNamespace(), dto.getSrcOperName()), true, XMLType.XSD_STRING, pars);
				} catch (org.apache.axis.AxisFault e) {
					// 处理异常
					throw AxisFaultUtils.handleFault(e);
				}
				
			}
//			/* 源接口类型为AXIS2 */
//			else if (BizApiTypeEnum.AXIS2.getKey().equalsIgnoreCase(dto.getSrcApiType())) {
//
//			}
			/* 源接口类型为CXF */
			else if (BizApiTypeEnum.CXF.getKey().equalsIgnoreCase(dto.getSrcApiType())) {

				Object[] params = new Object[keys.size()];
				int i = 0;
				for (String key : keys) {
					params[i] = paramMap.get(key);
					i++;
				}
				Client client = dcf.createClient(dto.getApiUrl());
				Object[] objs = client.invoke(new QName(dto.getSrcNamespace(), dto.getSrcOperName()), params);
				res = objs[0];
				
			}

		}
		// 数据源接口（基于创建的数据源连接的接口）
		else if (ApiTypeEnum.DATA.getKey().equalsIgnoreCase(dto.getApiType())) {
			
			// 增加
			if(DataExchangeTypeEnum.INSERT.equals(dto.getExchType())) {
				return getApiDataService().doApiInsert(dto, paramMap);
			}
			// 删除
			else if(DataExchangeTypeEnum.DELETE.equals(dto.getExchType())) {
				return getApiDataService().doApiDelete(dto, paramMap);
			}
			// 修改
			else if(DataExchangeTypeEnum.UPDATE.equals(dto.getExchType())) {
				
				/*
				 * 接口输入参数检查和参数组装
				 */
				List<ApiParam> updateList = dto.getUpdateList();
				if (!CollectionUtils.isEmpty(updateList)) {
					// 检查查询是否必填
					for (ApiParam update : updateList) {
						// 如果必填，则检查是否传递参数
						if (ObjectUtils.isEmpty(requestParams.get(update.getKey()))) {
							// 返回异常信息
							return ResultUtils.status(ApiExceptinHandler.STATUS_FAIL, getMessage("API-I100002", new Object[] { update.getKey() }));
						}
						// 如果是path参数则替换源接口URL
						String url = dto.getApiUrl();
						if(url != null && url.indexOf("{" + update.getKey() + "}") >= 0) {
							url = url.replaceAll("\\{" + update.getKey() + "\\}", requestParams.get(update.getKey()).toString());
							dto.setApiUrl(url);
						} else {
							// 组装新的请求参数集合
							paramMap.put(update.getKey(), requestParams.get(update.getKey()));
						}
					}
				}
				
				return getApiDataService().doApiUpdate(dto, paramMap);
				
			}
			// 查询
			else if(DataExchangeTypeEnum.QUERY.equals(dto.getExchType())) {
				// 检查分页参数
				if(dto.isPageable()) {
					
					if ( !requestParams.containsKey(RequestAttributes.REQUEST_OFFSET)) {
						// 返回异常信息
						return ResultUtils.status(ApiExceptinHandler.STATUS_FAIL, getMessage("API-I100002", new Object[] { RequestAttributes.REQUEST_OFFSET }));
					}
					// 进行数字类型验证
					if ( !NumberUtils.isDigits(requestParams.get(RequestAttributes.REQUEST_OFFSET).toString())) {
						// 返回异常信息
						
						return ResultUtils.status(ApiExceptinHandler.STATUS_FAIL, getMessage("API-I100003", new Object[] { RequestAttributes.REQUEST_OFFSET, "Integer" }));
					}
					
					if ( !requestParams.containsKey(RequestAttributes.REQUEST_LIMIT)) {
						// 返回异常信息
						return ResultUtils.status(ApiExceptinHandler.STATUS_FAIL, getMessage("API-I100002", new Object[] { RequestAttributes.REQUEST_LIMIT }));
					}
					// 进行数字类型验证
					if ( !NumberUtils.isDigits(requestParams.get(RequestAttributes.REQUEST_LIMIT).toString())) {
						// 返回异常信息
						return ResultUtils.status(ApiExceptinHandler.STATUS_FAIL, getMessage("API-I100003", new Object[] { RequestAttributes.REQUEST_LIMIT , "Integer"}));
					}
					
					paramMap.put(RequestAttributes.REQUEST_OFFSET, requestParams.get(RequestAttributes.REQUEST_OFFSET));
					paramMap.put(RequestAttributes.REQUEST_LIMIT, requestParams.get(RequestAttributes.REQUEST_LIMIT));
					
				}
				// 查询数据
				res = JSON.toJSONString(getApiDataService().getApiDataList(dto, paramMap));
			}

		} else {
			res = JSON.toJSONString(CollectionUtils.EMPTY_COLLECTION);
		}

		return res;
	}
	
	@Override
	public Object exec(String deployId, Map<String, Object> requestParams) throws Exception {

		ApiDeployModel deploy = getApiDeployService().findDeployById(deployId, true);

		if (deploy == null) {
			// 返回异常信息
			return ResultUtils.status(ApiExceptinHandler.STATUS_FAIL, getMessage("API-I100001"));
		}

		return exec(deploy, requestParams);
	}

	@Override
	public Object debugExec(String apiId, Map<String, Object> requestParams){
		try {
			ApiInfoModel infoModel = apiInfoService.getModel(apiId);
			ApiDeployModel deployModel = new ApiDeployModel();
			deployModel.setApi(infoModel);
			if (ApiTypeEnum.BIZ.getKey().equalsIgnoreCase(infoModel.getType())) {
				ApiInfoBizDto infoDto = getApiOutputHandler().outputBiz(infoModel);
				BizApiDescBuilder builder =  BizApiDescBuilder.get()
						.input(infoDto.getType(), infoDto.getMethod(), infoDto.getUrl(), infoDto.getNamespace(), infoDto.getOperName(), infoDto.getParamList())
						.output(infoDto.getOutType(), infoDto.getOutCase(), infoDto.getFieldList())
						.namespace(infoDto.getNamespace())
						.rocketmq(infoDto.getTopic(), infoDto.getTag());
				if(StringUtils.hasText(infoDto.getPluginId()) && StringUtils.hasText(infoDto.getExtensionId())) {
					builder.authz(infoDto.getPluginId(), infoDto.getExtensionId());
				}
				builder.errors(infoDto.getErrorList());
				deployModel.setDesc(builder.build());
			} else if (ApiTypeEnum.DATA.getKey().equalsIgnoreCase(infoModel.getType())) {
				ApiInfoDataDto infoDto = getApiOutputHandler().outputData(infoModel);
				String desc = DataApiDescBuilder.get()
						.data(infoDto.getDbid(), infoDto.getExchType(), infoDto.getExchMethod(), infoDto.getTable(), infoDto.getSql())
						.input(infoDto.getParamList(), infoDto.getUpdateList())
						.output(infoDto.getOutType(), infoDto.getOutCase(), infoDto.getFieldList())
						.rocketmq(infoDto.getTopic(), infoDto.getTag())
						.pageable(requestParams.containsKey("pageable") ? true : false)
						.build();
				deployModel.setDesc(desc);
				deployModel.setId(UUID.randomUUID().toString());
			}
			return exec(deployModel, requestParams);
		} catch (Exception e) {
			return ResultUtils.status(ApiExceptinHandler.STATUS_FAIL, e.getMessage());
		}
	}
	
	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	private IApiDataService getApiDataService() {
		return apiDataService;
	}

	private ApiOutputHandler getApiOutputHandler() {
		return apiOutputHandler;
	}

	public IApiDeployService getApiDeployService() {
		return apiDeployService;
	}

	public void setApiDeployService(IApiDeployService apiDeployService) {
		this.apiDeployService = apiDeployService;
	}

}
