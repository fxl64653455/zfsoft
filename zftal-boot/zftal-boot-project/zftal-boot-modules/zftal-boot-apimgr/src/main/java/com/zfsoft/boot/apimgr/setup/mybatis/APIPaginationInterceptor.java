/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.setup.mybatis;


import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import com.zfsoft.beanutils.reflection.AnnotationUtils;
import com.zfsoft.boot.druid.ds.JDBCDriverEnum;
import com.zfsoft.boot.apimgr.util.enums.DataExchangeMethodEnum;
import com.zfsoft.boot.webv5.setup.constant.RequestAttributes;
import com.zfsoft.db.core.dialect.DialectFactory;
import com.zfsoft.orm.mybatis.cache.BeanMethodDefinitionFactory;
import com.zfsoft.orm.mybatis.interceptor.AbstractPaginationInterceptor;
import com.zfsoft.orm.mybatis.interceptor.meta.MetaStatementHandler;
import com.zfsoft.orm.mybatis.utils.MetaObjectUtils;

/**
 * 
 *@类名称		: PaginationInterceptor.java
 *@类描述		： 通过拦截<code>StatementHandler</code>的<code>prepare</code>方法， 重写mybatis的SQL语句，实现物理分页
 *@创建人		：wandalong
 *@创建时间	：Jun 16, 2016 7:24:23 PM
 *@修改人		：
 *@修改时间	：
 *@版本号	:v1.0
 */
@Intercepts( { 
	@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }), 
	@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) 
})
public class APIPaginationInterceptor extends AbstractPaginationInterceptor {

	protected static Logger LOG = LoggerFactory.getLogger(APIPaginationInterceptor.class);

	@Override
	protected boolean isRequireIntercept(Invocation invocation,StatementHandler statementHandler,MetaStatementHandler metaStatementHandler) {
		// 通过反射获取到当前MappedStatement
		MappedStatement mappedStatement = metaStatementHandler.getMappedStatement();
		// 获取对应的BoundSql，这个BoundSql其实跟我们利用StatementHandler获取到的BoundSql是同一个对象。
		BoundSql boundSql = metaStatementHandler.getBoundSql();
		Object paramObject = boundSql.getParameterObject();
		//提取方法
		Method method = BeanMethodDefinitionFactory.getMethodDefinition(mappedStatement.getId(), paramObject != null ? new Class<?>[] {paramObject.getClass()} : null);
		// 拦截需要分页的SQL语句。通过MappedStatement的ID匹配，默认重写以包含Paged字符的ID
		return (method != null && AnnotationUtils.hasAnnotation(method, APIPagination.class));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object doStatementIntercept(Invocation invocation,StatementHandler statementHandler,MetaStatementHandler metaStatementHandler) throws Throwable {
		//检查是否需要进行拦截处理
		if (isRequireIntercept(invocation, statementHandler, metaStatementHandler)) {
			
			// 获取对应的BoundSql，这个BoundSql其实跟我们利用StatementHandler获取到的BoundSql是同一个对象。
			BoundSql boundSql = metaStatementHandler.getBoundSql();
			MetaObject metaBoundSql = MetaObjectUtils.forObject(boundSql);
			
			// 参数对象parameterObject的一个属性  
			Map<String, Object> queryMap = (Map<String, Object>) boundSql.getParameterObject();
			
			// 如果不分页，则不做事情
			if(!BooleanUtils.toBoolean(queryMap.get("pageable").toString())) {
				// 将执行权交给下一个拦截器  
				return invocation.proceed();
			}
						
			String exchMethod = String.valueOf(queryMap.get("exchMethod"));
			String dbtype = ObjectUtils.isEmpty(queryMap.get("dbtype")) ? JDBCDriverEnum.ORACLE.getKey() : String.valueOf(queryMap.get("dbtype"));
			long offset = Long.parseLong(queryMap.get(RequestAttributes.REQUEST_OFFSET).toString()); 
			long limit = Long.parseLong(queryMap.get(RequestAttributes.REQUEST_LIMIT).toString()); 
			
			// 接口交换方式：com.zfsoft.boot.apimgr.util.DataExchangeEnum
			if(DataExchangeMethodEnum.TABLE.getKey().equalsIgnoreCase(exchMethod)) {
				// 获取到我们自己写在Mapper映射语句中对应的Sql语句
				String originalSQL = (String) metaBoundSql.getValue("sql");
				// 处理后的SQL
				String finalSQL = DialectFactory.getDialectByType(dbtype).getLimitSQL(originalSQL, offset, limit);
				if (LOG.isDebugEnabled()) {
					LOG.debug(" Pagination SQL : "+ finalSQL);
				}
				// 将处理后的物理分页sql重新写入作为执行SQL
				metaBoundSql.setValue("sql", finalSQL);
				
			}  else if(DataExchangeMethodEnum.SQL.getKey().equalsIgnoreCase(exchMethod)) {
				// 处理前SQL
				String data_sql = String.valueOf(queryMap.get("data_sql"));
				// 处理后SQL
				String finalSQL = DialectFactory.getDialectByType(dbtype).getLimitSQL(data_sql, offset, limit);
				if (LOG.isDebugEnabled()) {
					LOG.debug(" Pagination SQL : "+ finalSQL);
				}
				// 替换参数中的SQL
				queryMap.put("data_sql", finalSQL);
			}
			
			RowBounds rowBounds = metaStatementHandler.getRowBounds();
			MetaObject metaRowBounds = MetaObjectUtils.forObject(rowBounds);
			metaRowBounds.setValue("offset",Integer.parseInt(offset + ""));
			metaRowBounds.setValue("limit",Integer.parseInt(limit + ""));
		}
		// 将执行权交给下一个拦截器  
		return invocation.proceed();
	}
	
	@Override
	public Object plugin(Object target) {
       return Plugin.wrap(target, this);  
	}
}
