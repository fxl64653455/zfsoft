package com.zfsoft.boot.apimgr.service.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.enhanced.utils.AopTargetUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import com.zfsoft.api.service.BaseServiceImpl;
import com.zfsoft.basemodel.PairModel;
import com.zfsoft.basicutils.StringEscapeUtils;
import com.zfsoft.basicutils.StringUtils;
import com.zfsoft.beanutils.reflection.ReflectionUtils;
import com.zfsoft.boot.druid.DruidProperties;
import com.zfsoft.boot.druid.ds.DataSourceContextHolder;
import com.zfsoft.boot.druid.ds.DynamicDataSource;
import com.zfsoft.boot.druid.ds.JDBCDriverEnum;
import com.zfsoft.boot.druid.ds.JDBCUtils;
import com.zfsoft.boot.apimgr.dao.daointerface.IApiDatabaseDao;
import com.zfsoft.boot.apimgr.dao.entities.ApiDatabaseModel;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiDatabaseService;
import com.zfsoft.boot.apimgr.util.JSQLParserUtils;
import com.zfsoft.boot.apimgr.util.SQLDataUtils;
import com.zfsoft.boot.apimgr.util.enums.DataExchangeTypeEnum;
import com.zfsoft.boot.apimgr.web.dto.ApiDatasourceDto;
import com.zfsoft.boot.apimgr.web.dto.InputColumn;
import com.zfsoft.db.core.datasource.NewDruidDataSource;
import com.zfsoft.freemarker.utils.FormatUtils;
import com.zfsoft.security.algorithm.DesBase64Codec;

import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;

@Service
public class ApiDatabaseServiceImpl extends BaseServiceImpl<ApiDatabaseModel, IApiDatabaseDao>
		implements IApiDatabaseService {

	private static final Logger logger = LoggerFactory.getLogger(ApiDatabaseServiceImpl.class);

	@Autowired
	private DesBase64Codec dbencrypt;
	@Autowired
	private DataSource dataSource;
	@Autowired
	private DataSourceProperties properties;
	@Autowired
	private DruidProperties druidProperties;
	// 条件参数正则表达式: 
	private Pattern pattern_if = Pattern.compile("(?:\\[#if\\s+(\\w+)\\?\\?\\]\\s*)+?");
	private Pattern pattern_noparse = Pattern.compile("(?:\\[#noparse\\]\\s*(\\s*#\\{\\w+\\}\\s*)\\[/#noparse\\])+?");
	// insert 参数正则表达式: (?:#\{\s*(\w+)(?:\s*,\s*jdbcType\s*=\s*\w+\s*)*\})+?
	private Pattern pattern_param = Pattern.compile("(?:#\\{\\s*(\\w+)(?:\\s*,\\s*jdbcType\\s*=\\s*\\w+\\s*)*\\})+?");

	
	@Override
	public List<ApiDatabaseModel> getDatabaseList(String owner) {
		return getDao().getDatabaseList(owner);
	}

	@Override
	public List<Map<String, String>> getTableNameList(String dbname,String dbtype) {
		try {
			if (StringUtils.isEmpty(dbname)) {
				dbname = ApiDatasourceDto.DEFAULT_DATASOURCE;
			}
			
			DataSourceContextHolder.setDatabaseName(dbname);
			
			if(JDBCDriverEnum.ORACLE == JDBCDriverEnum.driver(dbtype) ||
					JDBCDriverEnum.ORACLE12C == JDBCDriverEnum.driver(dbtype)) {
				return getDao().getTableNameList();
			}else if(JDBCDriverEnum.MARIADB == JDBCDriverEnum.driver(dbtype) || 
					JDBCDriverEnum.MYSQL == JDBCDriverEnum.driver(dbtype)) {
				return getDao().getTableNameListForMysql(dbname);
			}else if(JDBCDriverEnum.POSTGRESQL == JDBCDriverEnum.driver(dbtype)) {
				return getDao().getTableNameListForPostgresql();
			}else if(JDBCDriverEnum.SQLSERVER_2000 == JDBCDriverEnum.driver(dbtype) ||
					JDBCDriverEnum.SQLSERVER_2005 == JDBCDriverEnum.driver(dbtype)) {
				return getDao().getTableNameListForSqlServer();
			}
			
			return new ArrayList<Map<String, String>>();
		} finally {
			DataSourceContextHolder.setDatabaseName(ApiDatasourceDto.DEFAULT_DATASOURCE);
		}
	}

	@Override
	public List<Map<String, String>> getTableColumnList(String dbname, String table, String dbtype) {
		try {
			if (StringUtils.isEmpty(dbname)) {
				dbname = ApiDatasourceDto.DEFAULT_DATASOURCE;
			}
			if (StringUtils.isEmpty(table)) {
				logger.warn("param table is Empty.");
				return new ArrayList<Map<String, String>>();
			}
			
			DataSourceContextHolder.setDatabaseName(dbname);
			if(JDBCDriverEnum.ORACLE == JDBCDriverEnum.driver(dbtype) ||
					JDBCDriverEnum.ORACLE12C == JDBCDriverEnum.driver(dbtype)) {
				return getDao().getTableColumnList(table);
			}else if(JDBCDriverEnum.MARIADB == JDBCDriverEnum.driver(dbtype) || 
					JDBCDriverEnum.MYSQL == JDBCDriverEnum.driver(dbtype)) {
				return getDao().getTableColumnListForMysql(dbname,table);
			}else if(JDBCDriverEnum.POSTGRESQL == JDBCDriverEnum.driver(dbtype)) {
				return getDao().getTableColumnListForPostgresql(table);
			}else if(JDBCDriverEnum.SQLSERVER_2000 == JDBCDriverEnum.driver(dbtype) ||
					JDBCDriverEnum.SQLSERVER_2005 == JDBCDriverEnum.driver(dbtype)) {
				return getDao().getTableColumnListForSqlServer(table);
			}
			
			return new ArrayList<Map<String, String>>();
		} finally {
			DataSourceContextHolder.setDatabaseName(ApiDatasourceDto.DEFAULT_DATASOURCE);
		}
	}

	@Override
	public InputColumn getSQLParserInputColumnList(String dbname, String sql, String dbtype) throws Exception {
		InputColumn inputColumn = new InputColumn();
		// 如果组件SQL不为空，则使用Freemarker进行去除条件处理
		if (StringUtils.isNotEmpty(sql)) {
			try {
				
				Map<String, Object> rootMap = new HashMap<String, Object>();
				Map<String, Object> paramMap = new HashMap<String, Object>();
				rootMap.put("paramMap", paramMap);
				
				List<String> columnList = new ArrayList<String>();
				// 根据正则解析出参数
				Matcher matcher = pattern_if.matcher(sql);
				boolean useFreemarker = false;
				// 查找匹配的 [#if 参数名??] 片段
				while (matcher.find()) {
					 useFreemarker = true;
					// 获取匹配的[]的内容
					//String full_segment = matcher.group(0);
					// 参数名称
					String columnName = matcher.group(1);
					columnList.add(columnName);
				}
				useFreemarker = !useFreemarker ? pattern_noparse.matcher(sql).find() : useFreemarker;
				// 如果使用了参数标签，则进行Freemarker解析
				String data_sql = useFreemarker ? FormatUtils.toTextStatic(rootMap, DigestUtils.md5DigestAsHex(sql.getBytes()), sql) : sql;
				// 根据正则解析出参数
				Matcher matcher2 = pattern_param.matcher(sql);
				// 查找匹配的 #{字段名} 或 #{字段名,jdbcType=VARCHAR} 片段
				while (matcher2.find()) {
					data_sql = data_sql.replace(matcher2.group(0), matcher2.group(1));
				}
				Statement statement = JSQLParserUtils.getStatement(data_sql);
				// insert case
				if (statement instanceof Insert) {
					// 获取新增字段列名称
					columnList.addAll( JSQLParserUtils.getColumnNames((Insert) statement));
					inputColumn.setExchType( DataExchangeTypeEnum.INSERT);
				} 
				// delete case
				else if (statement instanceof Delete) {
					// 获得where条件中的列
					columnList.addAll( JSQLParserUtils.getConditionColumnNames(statement));
					inputColumn.setExchType( DataExchangeTypeEnum.DELETE);
				}
				// update case
				else if (statement instanceof Update) {
					// 获得where条件中的列名称
					columnList.addAll( JSQLParserUtils.getConditionColumnNames(statement));
					inputColumn.setExchType( DataExchangeTypeEnum.UPDATE);
				} 
				// select case
				else if (statement instanceof Select) {
					// 获得where条件中的列名称
					columnList.addAll( JSQLParserUtils.getConditionColumnNames(statement));
					inputColumn.setExchType( DataExchangeTypeEnum.QUERY);
				}
				// 解析查询表名称
				List<String> selectTables = JSQLParserUtils.getTables(data_sql);
				DataSourceContextHolder.setDatabaseName(dbname);
				ApiDatabaseModel model = new ApiDatabaseModel();
				model.setQueryList(selectTables);
				
				// 根据解析出的SQL中使用的查询表名称，得到不重复的字段描述信息
				List<Map<String, String>> tableColumnList = null;
				if(JDBCDriverEnum.ORACLE == JDBCDriverEnum.driver(dbtype) ||
						JDBCDriverEnum.ORACLE12C == JDBCDriverEnum.driver(dbtype)) {
					tableColumnList = getDao().getSQLParserColumnList(model);
				} else if(JDBCDriverEnum.MARIADB == JDBCDriverEnum.driver(dbtype) || 
						JDBCDriverEnum.MYSQL == JDBCDriverEnum.driver(dbtype)) {
					tableColumnList = getDao().getSQLParserColumnListForMysql(model.getQueryList(), dbname);
				}else if(JDBCDriverEnum.POSTGRESQL == JDBCDriverEnum.driver(dbtype)) {
					tableColumnList = getDao().getSQLParserColumnListForPostgresql(model.getQueryList());
				}else if(JDBCDriverEnum.SQLSERVER_2000 == JDBCDriverEnum.driver(dbtype) ||
						JDBCDriverEnum.SQLSERVER_2005 == JDBCDriverEnum.driver(dbtype)) {
					tableColumnList = getDao().getSQLParserColumnListForSqlServer(model.getQueryList());
				}
				
				inputColumn.setInput( SQLDataUtils.getDataColumnList(columnList, tableColumnList));
			} finally {
				DataSourceContextHolder.setDatabaseName(ApiDatasourceDto.DEFAULT_DATASOURCE);
			}
		} else {
			inputColumn.setInput( new ArrayList<Map<String, String>>());
		}
		return inputColumn;
	}
	
	@Override
	public List<Map<String, String>> getSQLParserOutputColumnList(String dbname, String sql, String dbtype) throws Exception {
		// 如果组件SQL不为空，则使用Freemarker进行去除条件处理
		if (StringUtils.isNotEmpty(sql)) {
			try {

				Map<String, Object> rootMap = new HashMap<String, Object>();
				Map<String, Object> paramMap = new HashMap<String, Object>();
				rootMap.put("paramMap", paramMap);
				
				List<String> columnList = new ArrayList<String>();
				// 根据正则解析出参数
				boolean useFreemarker = pattern_if.matcher(sql).find() ?  true : pattern_noparse.matcher(sql).find();
				// 如果使用了参数标签，则进行Freemarker解析
				String data_sql = useFreemarker ? FormatUtils.toTextStatic(rootMap, DigestUtils.md5DigestAsHex(sql.getBytes()), sql) : sql;
				// 根据正则解析出参数
				Matcher matcher2 = pattern_param.matcher(sql);
				// 查找匹配的 #{字段名} 或 #{字段名,jdbcType=VARCHAR} 片段
				while (matcher2.find()) {
					data_sql = data_sql.replace(matcher2.group(0), matcher2.group(1));
				}
				Statement statement = JSQLParserUtils.getStatement(data_sql);
				// select case
				if (statement instanceof Select) {
					// 解析查询列
					columnList = JSQLParserUtils.getColumnNames((Select) statement);
				}
				
				// 解析查询表名称
				List<String> selectTables = JSQLParserUtils.getTables(data_sql);
				DataSourceContextHolder.setDatabaseName(dbname);
				ApiDatabaseModel model = new ApiDatabaseModel();
				model.setQueryList(selectTables);
				
				// 根据解析出的SQL中使用的查询表名称，得到不重复的字段描述信息
				List<Map<String, String>> tableColumnList = null;
				if(JDBCDriverEnum.ORACLE == JDBCDriverEnum.driver(dbtype) ||
						JDBCDriverEnum.ORACLE12C == JDBCDriverEnum.driver(dbtype)) {
					tableColumnList = getDao().getSQLParserColumnList(model);
				}else if(JDBCDriverEnum.MARIADB == JDBCDriverEnum.driver(dbtype) || 
						JDBCDriverEnum.MYSQL == JDBCDriverEnum.driver(dbtype)) {
					tableColumnList = getDao().getSQLParserColumnListForMysql(model.getQueryList(), dbname);
				}else if(JDBCDriverEnum.POSTGRESQL == JDBCDriverEnum.driver(dbtype)) {
					tableColumnList = getDao().getSQLParserColumnListForPostgresql(model.getQueryList());
				}else if(JDBCDriverEnum.SQLSERVER_2000 == JDBCDriverEnum.driver(dbtype) ||
						JDBCDriverEnum.SQLSERVER_2005 == JDBCDriverEnum.driver(dbtype)) {
					tableColumnList = getDao().getSQLParserColumnListForSqlServer(model.getQueryList());
				}
				
				return SQLDataUtils.getDataColumnList(columnList, tableColumnList);
				
			} finally {
				DataSourceContextHolder.setDatabaseName(ApiDatasourceDto.DEFAULT_DATASOURCE);
			}
		} else {
			return new ArrayList<Map<String, String>>();
		}
	}

	@Override
	public List<Map<String, String>> getSQLParserUpdateColumnList(String dbname, String sql, String dbtype) throws Exception {
		// 如果组件SQL不为空，则使用Freemarker进行去除条件处理
		if (StringUtils.isNotEmpty(sql)) {
			try {
				Map<String, Object> rootMap = new HashMap<String, Object>();
				Map<String, Object> paramMap = new HashMap<String, Object>();
				rootMap.put("paramMap", paramMap);
				List<String> columnList = new ArrayList<String>();
				// 根据正则解析出参数
				Matcher matcher = pattern_if.matcher(sql);
				boolean useFreemarker = false;
				// 查找匹配的 [#if 参数名??] 片段
				while (matcher.find()) {
					 useFreemarker = true;
					// 获取匹配的[]的内容
					//String full_segment = matcher.group(0);
					// 参数名称
					String columnName = matcher.group(1);
					columnList.add(columnName);
				}
				useFreemarker = !useFreemarker ? pattern_noparse.matcher(sql).find() : useFreemarker;
				// 如果使用了参数标签，则进行Freemarker解析
				String data_sql = useFreemarker ? FormatUtils.toTextStatic(rootMap, DigestUtils.md5DigestAsHex(sql.getBytes()), sql) : sql;
				// 根据正则解析出参数
				Matcher matcher2 = pattern_param.matcher(sql);
				// 查找匹配的 #{字段名} 或 #{字段名,jdbcType=VARCHAR} 片段
				while (matcher2.find()) {
					data_sql = data_sql.replace(matcher2.group(0), matcher2.group(1));
				}
				Statement statement = JSQLParserUtils.getStatement(data_sql);
				// update case
				if (statement instanceof Update) {
					// 获取变更字段列名称
					columnList.addAll(JSQLParserUtils.getColumnNames((Update) statement));
				}
				// 解析查询表名称
				List<String> selectTables = JSQLParserUtils.getTables(data_sql);
				DataSourceContextHolder.setDatabaseName(dbname);
				ApiDatabaseModel model = new ApiDatabaseModel();
				model.setQueryList(selectTables);
				
				// 根据解析出的SQL中使用的查询表名称，得到不重复的字段描述信息
				List<Map<String, String>> tableColumnList = null;
				if(JDBCDriverEnum.ORACLE == JDBCDriverEnum.driver(dbtype) ||
						JDBCDriverEnum.ORACLE12C == JDBCDriverEnum.driver(dbtype)) {
					tableColumnList = getDao().getSQLParserColumnList(model);
				}else if(JDBCDriverEnum.MARIADB == JDBCDriverEnum.driver(dbtype) || 
						JDBCDriverEnum.MYSQL == JDBCDriverEnum.driver(dbtype)) {
					tableColumnList = getDao().getSQLParserColumnListForMysql(model.getQueryList(), dbname);
				}else if(JDBCDriverEnum.POSTGRESQL == JDBCDriverEnum.driver(dbtype)) {
					tableColumnList = getDao().getSQLParserColumnListForPostgresql(model.getQueryList());
				}else if(JDBCDriverEnum.SQLSERVER_2000 == JDBCDriverEnum.driver(dbtype) ||
						JDBCDriverEnum.SQLSERVER_2005 == JDBCDriverEnum.driver(dbtype)) {
					tableColumnList = getDao().getSQLParserColumnListForSqlServer(model.getQueryList());
				}
				
				return SQLDataUtils.getDataColumnList(columnList, tableColumnList);
				
			} finally {
				DataSourceContextHolder.setDatabaseName(ApiDatasourceDto.DEFAULT_DATASOURCE);
			}
		} else {
			return new ArrayList<Map<String, String>>();
		}
	}
	
	@Override
	public boolean insert(ApiDatabaseModel dbModel) {
		// 新增一条数据库配置记录
		getDao().insert(dbModel);
		// 发布该数据库的数据源对象
		setNewDatasource(dbModel);
		return true;
	}

	@Override
	public boolean update(ApiDatabaseModel dbModel) {
		// 修改前查询出历史数据
		ApiDatabaseModel oldDbMopdel = getDao().getModel(dbModel);
		// 修改一条数据库配置记录
		getDao().update(dbModel);
		// 移除数据源对象
		removeDatasource(oldDbMopdel);
		// 发布该数据库的数据源对象
		setNewDatasource(dbModel);
		return true;
	}

	@Override
	public List<Map<String, String>> getDependencies(List<String> list) {
		return getDao().getDependencies(list);
	}

	@Override
	public boolean batchDelete(List<?> list) {

		// 先查询出要删除的记录
		List<PairModel> listToDel = getDao().getDatabaseListByIds(list);
		// 执行批量数据删除
		getDao().batchDelete(list);

		// 从动态数据源集合中移除已被删除的数据源对象
		if (dataSource != null && !CollectionUtils.isEmpty(listToDel)) {
			for (PairModel pairModel : listToDel) {
				// 移除数据源对象（兼容JavaMelody监控的对象代理）
				try {
					Object target = AopTargetUtils.getTarget(dataSource);
					if (Proxy.isProxyClass(dataSource.getClass())) {
						InvocationHandler handler = Proxy.getInvocationHandler(dataSource);
						Method method = ReflectionUtils.getMethod(DynamicDataSource.class,
								"removeTargetDataSource", String.class);
						handler.invoke(dataSource, method, new Object[] { pairModel.getKey() });
						logger.debug("DataSource {} removed .", pairModel.getKey());
					} else if (target instanceof DynamicDataSource) {
						((DynamicDataSource) target).removeTargetDataSource(pairModel.getKey());
						logger.debug("DataSource {} removed .", pairModel.getKey());
					}
				} catch (Exception e) {
					e.printStackTrace();
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		}

		return true;
	}

	@Override
	public void setNewDatasource(ApiDatabaseModel dbModel) {

		if (dataSource != null) {

			// 使用数据库URL生成Base64秘钥
			String encryptKeyText = Base64.encodeBase64String(dbModel.getUrl().getBytes());
			// 进行数据库账号密码解密
			dbModel.setUsername(dbencrypt.decrypt(dbModel.getUsername().getBytes(), encryptKeyText));
			dbModel.setPassword(dbencrypt.decrypt(dbModel.getPassword().getBytes(), encryptKeyText));
			// 首先对数据源配置进行连接测试，已保证数据源驱动、连接都可用
			String url = StringEscapeUtils.unescapeHtml(dbModel.getUrl());
			boolean ok = JDBCUtils.testConnection(JDBCUtils.getDriverClass(dbModel.getDbtype()), url,
					dbModel.getUsername(), dbModel.getPassword());
			// 测试通过，则创建该数据源的配置信息
			if (ok) {

				// 自定义加密数据源：需要对参数进行加密
				if (properties.getType().equals(NewDruidDataSource.class)) {
					// 进行数据库URl、账号、密码加密
					url = dbencrypt.encrypt(url);
					dbModel.setUsername(dbencrypt.encrypt(dbModel.getUsername()));
					dbModel.setPassword(dbencrypt.encrypt(dbModel.getPassword()));
				}

				// 动态创建Druid数据源（兼容JavaMelody监控的对象代理）
				try {
					Object target = AopTargetUtils.getTarget(dataSource);
					
					properties.setDriverClassName(JDBCDriverEnum.driver(dbModel.getDbtype()).getDriverClass());
					if(JDBCDriverEnum.driver(dbModel.getDbtype()) == JDBCDriverEnum.ORACLE ||
							JDBCDriverEnum.driver(dbModel.getDbtype()) == JDBCDriverEnum.ORACLE12C) {
						druidProperties.setValidationQuery("select 1 from dual");
					}else if(JDBCDriverEnum.driver(dbModel.getDbtype()) == JDBCDriverEnum.POSTGRESQL ||
							JDBCDriverEnum.driver(dbModel.getDbtype()) == JDBCDriverEnum.MYSQL ||
							JDBCDriverEnum.driver(dbModel.getDbtype()) == JDBCDriverEnum.MARIADB ||
							JDBCDriverEnum.driver(dbModel.getDbtype()) == JDBCDriverEnum.SQLSERVER_2000 ||
							JDBCDriverEnum.driver(dbModel.getDbtype()) == JDBCDriverEnum.SQLSERVER_2005) {
						druidProperties.setValidationQuery("select 1");
					}
					
					if (Proxy.isProxyClass(dataSource.getClass())) {

						InvocationHandler handler = Proxy.getInvocationHandler(dataSource);
						Method method = ReflectionUtils.getMethod(DynamicDataSource.class,
								"setTargetDataSource", DataSourceProperties.class, DruidProperties.class, String.class,
								String.class, String.class, String.class);
						handler.invoke(dataSource, method, new Object[] { properties, druidProperties,
								dbModel.getName(), url, dbModel.getUsername(), dbModel.getPassword() });

					} else if (target instanceof DynamicDataSource) {
						((DynamicDataSource) target).setTargetDataSource(properties, druidProperties, dbModel.getName(),
								url, dbModel.getUsername(), dbModel.getPassword());
					}
					
					logger.debug("DataSource {} Created .", dbModel.getName());
					
				} catch (Exception e) {
					e.printStackTrace();
				} catch (Throwable e) {
					e.printStackTrace();
				}

			}
		}

	}

	@Override
	public void removeDatasource(ApiDatabaseModel dbModel) {
		if (dataSource != null) {
			// 移除数据源对象（兼容JavaMelody监控的对象代理）
			try {
				Object target = AopTargetUtils.getTarget(dataSource);
				if (Proxy.isProxyClass(dataSource.getClass())) {

					InvocationHandler handler = Proxy.getInvocationHandler(dataSource);
					Method method = ReflectionUtils.getMethod(DynamicDataSource.class,
							"removeTargetDataSource", String.class);
					handler.invoke(dataSource, method, new Object[] { dbModel.getName() });

				} else if (target instanceof DynamicDataSource) {
					((DynamicDataSource) target).removeTargetDataSource(dbModel.getName());
				}
			} catch (Exception e) {
				e.printStackTrace();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Object, DataSource> getResolvedDataSources() {
		Map<Object, DataSource> res = null;
		try {
			Object target = AopTargetUtils.getTarget(dataSource);
			if (Proxy.isProxyClass(dataSource.getClass())) {
				InvocationHandler handler = Proxy.getInvocationHandler(dataSource);
				Method method = ReflectionUtils.getMethod(DynamicDataSource.class,"getResolvedDataSources");
				res = (Map<Object, DataSource>) handler.invoke(dataSource, method, new Object[] {});
			} else if (target instanceof DynamicDataSource) {
				res = ((DynamicDataSource) target).getResolvedDataSources();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public ApiDatabaseModel getModelByDbName(String dbname) {
		return getDao().getModelByDbName(dbname);
	}

}
