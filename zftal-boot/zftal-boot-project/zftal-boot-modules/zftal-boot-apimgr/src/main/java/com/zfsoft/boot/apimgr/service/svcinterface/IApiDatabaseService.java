/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.service.svcinterface;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.zfsoft.api.service.BaseService;
import com.zfsoft.boot.apimgr.dao.entities.ApiDatabaseModel;
import com.zfsoft.boot.apimgr.web.dto.InputColumn;

public interface IApiDatabaseService extends BaseService<ApiDatabaseModel> {

	/**
	 * @description	： 根据名称返回数据库信息
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年5月15日 上午11:29:12
	 * @param dbname
	 * @return
	 */
	ApiDatabaseModel getModelByDbName(String dbname);
	
	/**
	 * 
	 * @description ： 查询用户的数据库列表
	 * @author ： 万大龙（743）
	 * @date ：2017年10月17日 下午4:20:52
	 * @param owner
	 * @return
	 */
	List<ApiDatabaseModel> getDatabaseList(String owner);

	/**
	 * 
	 * @description ： 获取所有的表名与注释
	 * @author ： 万大龙（743）
	 * @date ：2017年9月21日 上午11:41:48
	 * @param dbname
	 * @param dbtype
	 * @return
	 */
	List<Map<String, String>> getTableNameList(String dbname, String dbtype);

	/**
	 * 
	 * @description ： 获取表中对应的字段与注释
	 * @author ： 万大龙（743）
	 * @date ：2017年9月21日 上午11:42:01
	 * @param dbname
	 * @param table
	 * @param dbtype
	 * @return
	 */
	List<Map<String, String>> getTableColumnList(String dbname, String table, String dbtype);

	/**
	 * 
	 * @description ： 根据解析出的SQL中字段信息
	 * @author ： 万大龙（743）
	 * @date ：2018年1月2日 下午5:55:47
	 * @param dbname
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public InputColumn getSQLParserInputColumnList(String dbname, String sql, String dbtype) throws Exception;

	public List<Map<String, String>> getSQLParserOutputColumnList(String dbname, String sql, String dbtype) throws Exception;

	public List<Map<String, String>> getSQLParserUpdateColumnList(String dbname, String sql, String dbtype) throws Exception;
	

	/**
	 * 
	 * @description ： 动态添加一个新的数据源
	 * @author ： 万大龙（743）
	 * @date ：2017年10月17日 下午4:57:24
	 * @param model
	 */
	void setNewDatasource(ApiDatabaseModel model);

	/**
	 * 
	 * @description ： 移除数据源
	 * @author ： 万大龙（743）
	 * @date ：2017年10月17日 下午4:57:35
	 * @param model
	 */
	void removeDatasource(ApiDatabaseModel model);

	/**
	 * 
	 * @description ： 获取指定ID关联的信息，以便进行删除前的逻辑检查
	 * @author ： 万大龙（743）
	 * @date ：2017年10月20日 下午12:53:15
	 * @param list
	 * @return
	 */
	List<Map<String, String>> getDependencies(List<String> list);
	
	/**
	 * @description	： 获取数据源
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年5月9日 上午10:36:39
	 * @return
	 */
	Map<Object, DataSource> getResolvedDataSources();
}
