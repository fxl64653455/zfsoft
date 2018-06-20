package com.zfsoft.boot.apimgr.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.zfsoft.api.service.BaseServiceImpl;
import com.zfsoft.api.utils.MessageUtil;
import com.zfsoft.api.utils.ResultUtils;
import com.zfsoft.api.web.WebContext;
import com.zfsoft.basemodel.PairModel;
import com.zfsoft.basicutils.CollectionUtils;
import com.zfsoft.boot.druid.ds.DataSourceContextHolder;
import com.zfsoft.boot.druid.ds.JDBCDriverEnum;
import com.zfsoft.boot.webv5.setup.constant.RequestAttributes;
import com.zfsoft.boot.apimgr.dao.daointerface.IApiDatabaseDao;
import com.zfsoft.boot.apimgr.dao.daointerface.IApiDeployDao;
import com.zfsoft.boot.apimgr.dao.entities.ApiDatabaseModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiDeployModel;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiDataService;
import com.zfsoft.boot.apimgr.util.enums.DataExchangeMethodEnum;
import com.zfsoft.boot.apimgr.web.dto.ApiDatasourceDto;
import com.zfsoft.boot.apimgr.web.dto.ApiDeployDto;
import com.zfsoft.boot.apimgr.web.dto.ApiParam;
import com.zfsoft.boot.apimgr.web.handler.ApiOutputHandler;
import com.zfsoft.freemarker.utils.FormatUtils;

@Service
public class ApiDataServiceImpl extends BaseServiceImpl<ApiDeployModel, IApiDeployDao> implements IApiDataService {

	private static final Logger logger = LoggerFactory.getLogger(ApiDataServiceImpl.class);
	
	@Autowired
	private IApiDatabaseDao apiDatabaseDao;
	
	@Autowired
	private ApiOutputHandler apiOutputHandler;
	@Resource(name = "messageSource")
	protected MessageSource messageSource;
	
	// 条件参数正则表达式: 
	private Pattern pattern_if = Pattern.compile("(?:\\[#if\\s+(\\w+)\\?\\?\\]\\s*)+?");
	private Pattern pattern_noparse = Pattern.compile("(?:\\[#noparse\\]\\s*(\\s*#\\{\\w+\\}\\s*)\\[/#noparse\\])+?");
	protected static final String STATUS_SUCCESS = "success";

	@Override
	public Object doApiInsert(ApiDeployDto dto, Map<String, Object> paramMap) throws Exception {
		
		try {
			
			/*
			 * 查询接口数据源
			 */
			String dbname = DataSourceContextHolder.DEFAULT_DATASOURCE;
			if(!ApiDatasourceDto.DEFAULT_DATASOURCE.equalsIgnoreCase(dto.getDbid())) {
				ApiDatabaseModel dbModel = getApiDatabaseDao().getModel(dto.getDbid());
				dbname = dbModel.getName();
			}
			//切换数据源
			DataSourceContextHolder.setDatabaseName(dbname);
			logger.debug("DataSource Switch To {} .", dbname);
			// 数据表
			if(DataExchangeMethodEnum.TABLE.getKey().equalsIgnoreCase(dto.getExchMethod())) {

				Map<String, Object> queryMap = new HashMap<String, Object>();
				// 表名称
				queryMap.put("tableName", dto.getTable());
				// 条件参数
				List<PairModel> paramList = new ArrayList<PairModel>();
				if(!ObjectUtils.isEmpty(paramMap) && !ObjectUtils.isEmpty(dto.getParamList())) {
					for (ApiParam param : dto.getParamList()) {
						Object val = paramMap.get(param.getKey());
						if (!ObjectUtils.isEmpty(val) ) {
							paramList.add(new PairModel(param.getKey(), val.toString()));
						}
					}
				}
				queryMap.put("paramList", paramList);
				getApiDatabaseDao().insertData(queryMap);
				
			} 
			// 自定义SQL语句
			else if(DataExchangeMethodEnum.SQL.getKey().equalsIgnoreCase(dto.getExchMethod())) {
				
				Map<String,Object> queryMap = paramMap == null ? new HashMap<String, Object>() : paramMap ;
				// 检查是否需要进Freemarker处理
				boolean useFreemarker = pattern_if.matcher(dto.getSql()).find() ? true : pattern_noparse.matcher(dto.getSql()).find();
				// 如果使用了参数标签，则进行Freemarker解析
				String data_sql = useFreemarker ? FormatUtils.toTextStatic(queryMap, dto.getId(), dto.getSql()) : dto.getSql();
				// 原始SQL语句
				queryMap.put("data_sql", data_sql);
				getApiDatabaseDao().insertDataBySQL(queryMap);
				
			}
			
			return ResultUtils.status( STATUS_SUCCESS, getMessage("API-I100011"));
			
		} finally {
			DataSourceContextHolder.setDatabaseName(ApiDatasourceDto.DEFAULT_DATASOURCE);
			logger.debug("DataSource Reset To {} .", ApiDatasourceDto.DEFAULT_DATASOURCE);
		}
		
	}

	@Override
	public Object doApiDelete(ApiDeployDto dto, Map<String, Object> paramMap) throws Exception {
		
		try {
			
			/*
			 * 查询接口数据源
			 */
			String dbname = DataSourceContextHolder.DEFAULT_DATASOURCE;
			if(!ApiDatasourceDto.DEFAULT_DATASOURCE.equalsIgnoreCase(dto.getDbid())) {
				ApiDatabaseModel dbModel = getApiDatabaseDao().getModel(dto.getDbid());
				dbname = dbModel.getName();
			}
			//切换数据源
			DataSourceContextHolder.setDatabaseName(dbname);
			logger.debug("DataSource Switch To {} .", dbname);
			// 数据表
			if(DataExchangeMethodEnum.TABLE.getKey().equalsIgnoreCase(dto.getExchMethod())) {

				Map<String, Object> queryMap = new HashMap<String, Object>();
				// 表名称
				queryMap.put("tableName", dto.getTable());
				// 条件参数
				List<PairModel> paramList = new ArrayList<PairModel>();
				if(!ObjectUtils.isEmpty(paramMap) && !ObjectUtils.isEmpty(dto.getParamList())) {
					for (ApiParam param : dto.getParamList()) {
						Object val = paramMap.get(param.getKey());
						if (!ObjectUtils.isEmpty(val) ) {
							paramList.add(new PairModel(param.getKey(), val.toString()));
						}
					}
				}
				queryMap.put("paramList", paramList);
				getApiDatabaseDao().deleteData(queryMap);
				
			} 
			// 自定义SQL语句
			else if(DataExchangeMethodEnum.SQL.getKey().equalsIgnoreCase(dto.getExchMethod())) {
				
				Map<String,Object> queryMap = paramMap == null ? new HashMap<String, Object>() : paramMap ;
				// 检查是否需要进Freemarker处理
				boolean useFreemarker = pattern_if.matcher(dto.getSql()).find() ? true : pattern_noparse.matcher(dto.getSql()).find();
				// 如果使用了参数标签，则进行Freemarker解析
				String data_sql = useFreemarker ? FormatUtils.toTextStatic(queryMap, dto.getId(), dto.getSql()) : dto.getSql();
				// 原始SQL语句
				queryMap.put("data_sql", data_sql);
				getApiDatabaseDao().deleteDataBySQL(queryMap);
				
			}
			
			return ResultUtils.status( STATUS_SUCCESS, getMessage("API-I100012"));
			
		} finally {
			DataSourceContextHolder.setDatabaseName(DataSourceContextHolder.DEFAULT_DATASOURCE);
			logger.debug("DataSource Reset To {} .", DataSourceContextHolder.DEFAULT_DATASOURCE);
		}
		
	}

	@Override
	public Object doApiUpdate(ApiDeployDto dto, Map<String, Object> paramMap) throws Exception {
		
		try {
			
			/*
			 * 查询接口数据源
			 */
			String dbname = DataSourceContextHolder.DEFAULT_DATASOURCE;
			if(!ApiDatasourceDto.DEFAULT_DATASOURCE.equalsIgnoreCase(dto.getDbid())) {
				ApiDatabaseModel dbModel = getApiDatabaseDao().getModel(dto.getDbid());
				dbname = dbModel.getName();
			}
			//切换数据源
			DataSourceContextHolder.setDatabaseName(dbname);
			logger.debug("DataSource Switch To {} .", dbname);
			// 数据表
			if(DataExchangeMethodEnum.TABLE.getKey().equalsIgnoreCase(dto.getExchMethod())) {

				Map<String, Object> queryMap = new HashMap<String, Object>();
				// 表名称
				queryMap.put("tableName", dto.getTable());
				// 变更字段
				List<PairModel> updateList = new ArrayList<PairModel>();
				if(!ObjectUtils.isEmpty(paramMap) && !ObjectUtils.isEmpty(dto.getUpdateList())) {
					for (ApiParam param : dto.getUpdateList()) {
						Object val = paramMap.get(param.getKey());
						if (!ObjectUtils.isEmpty(val) ) {
							updateList.add(new PairModel(param.getKey(), val.toString()));
						} else {
							updateList.add(new PairModel(param.getKey(), ""));
						}
					}
				}
				queryMap.put("updateList", updateList);
				// 条件参数
				List<PairModel> paramList = new ArrayList<PairModel>();
				if(!ObjectUtils.isEmpty(paramMap) && !ObjectUtils.isEmpty(dto.getParamList())) {
					for (ApiParam param : dto.getParamList()) {
						Object val = paramMap.get(param.getKey());
						if (!ObjectUtils.isEmpty(val) ) {
							paramList.add(new PairModel(param.getKey(), val.toString()));
						}
					}
				}
				queryMap.put("paramList", paramList);
				getApiDatabaseDao().updateData(queryMap);
				
			} 
			// 自定义SQL语句
			else if(DataExchangeMethodEnum.SQL.getKey().equalsIgnoreCase(dto.getExchMethod())) {
				
				Map<String,Object> queryMap = paramMap == null ? new HashMap<String, Object>() : paramMap ;
				// 检查是否需要进Freemarker处理
				boolean useFreemarker = pattern_if.matcher(dto.getSql()).find() ? true : pattern_noparse.matcher(dto.getSql()).find();
				// 如果使用了参数标签，则进行Freemarker解析
				String data_sql = useFreemarker ? FormatUtils.toTextStatic(queryMap, dto.getId(), dto.getSql()) : dto.getSql();
				// 原始SQL语句
				queryMap.put("data_sql", data_sql);
				getApiDatabaseDao().updateDataBySQL(queryMap);
				
			}
			
			return ResultUtils.status( STATUS_SUCCESS, getMessage("API-I100013"));
			
		} finally {
			DataSourceContextHolder.setDatabaseName(DataSourceContextHolder.DEFAULT_DATASOURCE);
			logger.debug("DataSource Reset To {} .", DataSourceContextHolder.DEFAULT_DATASOURCE);
		}
		
	}
	
	@Override
	public List<Map<String,String>> getApiDataList(ApiDeployDto dto, Map<String, Object> paramMap) throws Exception {
		
		try {
			
			/*
			 * 接口输出参数
			 */
			if( CollectionUtils.isEmpty(dto.getFieldList())) {
				return null;
			}
			
			/*
			 * 查询接口数据源
			 */
			String dbname = DataSourceContextHolder.DEFAULT_DATASOURCE;
			String dbtype = JDBCDriverEnum.ORACLE.getKey();
			if(!ApiDatasourceDto.DEFAULT_DATASOURCE.equalsIgnoreCase(dto.getDbid())) {
				ApiDatabaseModel dbModel = getApiDatabaseDao().getModel(dto.getDbid());
				dbname = dbModel.getName();
				dbtype = dbModel.getDbtype();
			}
					
			//切换数据源
			DataSourceContextHolder.setDatabaseName(dbname);
			logger.debug("DataSource Switch To {} .", dbname);
			
			// 数据交换方式：com.zfsoft.boot.apimgr.util.DataExchangeEnum
			if(DataExchangeMethodEnum.TABLE.getKey().equalsIgnoreCase(dto.getExchMethod())) {
				
				Map<String, Object> queryMap = new HashMap<String, Object>();
				// 表名称
				queryMap.put("tableName", dto.getTable());
				// 输出字段
				queryMap.put("fieldList", dto.getFieldList());
				// 条件参数
				List<PairModel> paramList = new ArrayList<PairModel>();
				if(!ObjectUtils.isEmpty(paramMap) && !ObjectUtils.isEmpty(dto.getParamList())) {
					for (ApiParam param : dto.getParamList()) {
						Object val = paramMap.get(param.getKey());
						if (!ObjectUtils.isEmpty(val) ) {
							paramList.add(new PairModel(param.getKey(), val.toString()));
						}
					}
				}
				queryMap.put("paramList", paramList);
				
				/* APIPaginationInterceptor 需要判断该参数 */
				
				// 是否分页标记
				queryMap.put("pageable", dto.isPageable());
				// 数据交换方式
				queryMap.put("exchMethod", dto.getExchMethod());
				// 数据库类型：用于判断数据库构造不同数据库的分页查询SQL
				queryMap.put("dbtype", dbtype);
				// 分页起始位置
				queryMap.put(RequestAttributes.REQUEST_OFFSET, paramMap.get(RequestAttributes.REQUEST_OFFSET));
				// 分页每页数据量
				queryMap.put(RequestAttributes.REQUEST_LIMIT, paramMap.get(RequestAttributes.REQUEST_LIMIT));
				
				return getApiDatabaseDao().getDataList(queryMap);
				
			} 
			
			else if(DataExchangeMethodEnum.SQL.getKey().equalsIgnoreCase(dto.getExchMethod())) {
				
				Map<String,Object> queryMap = paramMap == null ? new HashMap<String, Object>() : paramMap ;
				//数据查询SQL
				String data_sql = FormatUtils.toTextStatic( queryMap, dto.getId(), dto.getSql());
				
				/* APIPaginationInterceptor 需要判断该参数 */
				
				// 是否分页标记
				queryMap.put("pageable", dto.isPageable());
				// 数据交换方式
				queryMap.put("exchMethod", dto.getExchMethod());
				// 数据库类型：用于判断数据库构造不同数据库的分页查询SQL
				queryMap.put("dbtype", dbtype);
				// 分页起始位置
				queryMap.put(RequestAttributes.REQUEST_OFFSET, paramMap.get(RequestAttributes.REQUEST_OFFSET));
				// 分页每页数据量
				queryMap.put(RequestAttributes.REQUEST_LIMIT, paramMap.get(RequestAttributes.REQUEST_LIMIT));
				// 原始SQL语句
				queryMap.put("data_sql", data_sql);
				
				return getApiDatabaseDao().getDataListBySQL(queryMap);
				
			}
			
			return null;
			
		} finally {
			DataSourceContextHolder.setDatabaseName(DataSourceContextHolder.DEFAULT_DATASOURCE);
			logger.debug("DataSource Reset To {} .", DataSourceContextHolder.DEFAULT_DATASOURCE);
		}
		
		
	}
	
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

	public IApiDatabaseDao getApiDatabaseDao() {
		return apiDatabaseDao;
	}

	public void setApiDatabaseDao(IApiDatabaseDao apiDatabaseDao) {
		this.apiDatabaseDao = apiDatabaseDao;
	}

	public ApiOutputHandler getApiOutputHandler() {
		return apiOutputHandler;
	}

	public void setApiOutputHandler(ApiOutputHandler apiOutputHandler) {
		this.apiOutputHandler = apiOutputHandler;
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
}
