package com.zfsoft.boot.apimgr.dao.daointerface;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zfsoft.api.dao.BaseDao;
import com.zfsoft.basemodel.PairModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiDatabaseModel;
import com.zfsoft.boot.apimgr.setup.mybatis.APIPagination;

@Mapper
public interface IApiDatabaseDao extends BaseDao<ApiDatabaseModel> {
	
	/**
	 * @description	： 根据名称返回数据库信息
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年5月15日 上午11:30:29
	 * @param dbname
	 * @return
	 */
	ApiDatabaseModel getModelByDbName(@Param("dbname") String dbname);
	
	/**
	 * 
	 * @description	： 查询用户的数据库列表
	 * @author 		： 万大龙（743）
	 * @date 		：2017年10月17日 下午4:20:52
	 * @param owner
	 * @return
	 */
	List<ApiDatabaseModel> getDatabaseList(@Param("owner") String owner);
	
	/**
	 * 
	 * @description	： 查询ID集合对应的的数据库列表
	 * @author 		： 万大龙（743）
	 * @date 		：2017年10月17日 下午5:10:29
	 * @param list
	 * @return
	 */
	List<PairModel> getDatabaseListByIds(List<?> list);
	
	/**
	 * 
	 * @description	： 获取所有的表名与注释
	 * 	Oracle实现
	 * @author 		： 万大龙（743）
	 * @date 		：2017年9月21日 上午11:41:48
	 * @return
	 */
	List<Map<String,String>> getTableNameList();
	
	/**
	 * @description	： 获取所有的表名与注释
	 * 	MySql实现
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年5月15日 上午10:13:35
	 * @param dbname
	 * @return
	 */
	List<Map<String,String>> getTableNameListForMysql(@Param("dbName") String dbName);
	
	/**
	 * @description	： 获取所有的表名与注释
	 * 	PostgreSql实现
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年5月16日 上午9:11:13
	 * @return
	 */
	List<Map<String,String>> getTableNameListForPostgresql();
	
	/**
	 * @description	： 获取所有的表名与注释
	 * 	SqlServer实现
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年5月16日 下午3:40:19
	 * @return
	 */
	List<Map<String,String>> getTableNameListForSqlServer();
	
	/**
	 * @description	： 获取表中对应的字段与注释
	 * 	Oracle实现
	 * @author 		： 万大龙（743）
	 * @date 		：2017年9月21日 上午11:42:01
	 * @param table
	 * @return
	 */
	List<Map<String,String>> getTableColumnList(@Param("tableName") String table);
	
	/**
	 * @description	： 获取表中对应的字段与注释
	 * 	MySql实现
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年5月15日 上午10:49:39
	 * @param dbName
	 * @param tableName
	 * @return
	 */
	List<Map<String,String>> getTableColumnListForMysql(@Param("dbName") String dbName, @Param("tableName") String tableName);
	
	/**
	 * @description	： 获取表中对应的字段与注释
	 * 	PostgreSql实现
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年5月16日 上午9:16:36
	 * @param tableName
	 * @return
	 */
	List<Map<String,String>> getTableColumnListForPostgresql(@Param("tableName") String tableName);
	
	/**
	 * @description	： 获取表中对应的字段与注释
	 * 	SqlServer实现
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年5月16日 下午3:42:01
	 * @param tableName
	 * @return
	 */
	List<Map<String,String>> getTableColumnListForSqlServer(@Param("tableName") String tableName);
	
	/**
	 * @description	： 根据解析出的SQL中使用的查询表名称，得到不重复的字段描述信息
	 * 	Oracle实现
	 * @author 		： 万大龙（743）
	 * @date 		：2017年9月26日 上午9:51:20
	 * @param model
	 * @return
	 */
	List<Map<String,String>> getSQLParserColumnList(ApiDatabaseModel model);
	
	/**
	 * @description	： 根据解析出的SQL中使用的查询表名称，得到不重复的字段描述信息
	 * 	MySql实现
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年5月15日 下午3:40:17
	 * @param model
	 * @param dbName
	 * @return
	 */
	List<Map<String,String>> getSQLParserColumnListForMysql(@Param("queryList") List<?> queryList, @Param("dbName") String dbName);
	
	/**
	 * @description	： 根据解析出的SQL中使用的查询表名称，得到不重复的字段描述信息
	 * 	PostgreSql实现
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年5月16日 上午9:20:13
	 * @param queryList
	 * @return
	 */
	List<Map<String,String>> getSQLParserColumnListForPostgresql(@Param("queryList") List<?> queryList);
	
	/**
	 * @description	： 根据解析出的SQL中使用的查询表名称，得到不重复的字段描述信息
	 * 	SqlServer实现
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年5月16日 下午3:42:50
	 * @param queryList
	 * @return
	 */
	List<Map<String,String>> getSQLParserColumnListForSqlServer(@Param("queryList") List<?> queryList);
	
	/**
	 * @description	： 获取指定ID关联的信息，以便进行删除前的逻辑检查
	 * @author 		： 万大龙（743）
	 * @date 		：2017年10月20日 下午12:53:15
	 * @param list
	 * @return
	 */
	List<Map<String,String>> getDependencies(List<String> list);
	
	/**
	 * 
	 * @description	： 根据Table构造数据增加SQL并执行
	 * @author 		： 万大龙（743）
	 * @date 		：2018年1月4日 上午11:12:14
	 * @param map
	 * @return
	 */
	int insertData(Map<String,Object> map);
	
	/**
	 * 
	 * @description	： 根据SQL执行增加操作
	 * @author 		： 万大龙（743）
	 * @date 		：2018年1月4日 上午11:12:59
	 * @param map
	 * @return
	 */
	int insertDataBySQL(Map<String,Object> map);
	
	/**
	 * 
	 * @description	： 根据Table构造数据删除SQL并执行
	 * @author 		： 万大龙（743）
	 * @date 		：2018年1月4日 上午11:12:14
	 * @param map
	 * @return
	 */
	int deleteData(Map<String,Object> map);
	
	/**
	 * 
	 * @description	： 根据SQL执行删除操作
	 * @author 		： 万大龙（743）
	 * @date 		：2018年1月4日 上午11:12:59
	 * @param map
	 * @return
	 */
	int deleteDataBySQL(Map<String,Object> map);
	
	/**
	 * 
	 * @description	： 根据Table构造数据更新SQL并执行
	 * @author 		： 万大龙（743）
	 * @date 		：2018年1月4日 上午11:12:14
	 * @param map
	 * @return
	 */
	int updateData(Map<String,Object> map);
	
	/**
	 * 
	 * @description	： 根据SQL执行更新操作
	 * @author 		： 万大龙（743）
	 * @date 		：2018年1月4日 上午11:12:59
	 * @param map
	 * @return
	 */
	int updateDataBySQL(Map<String,Object> map);
	
	/**
	 * 
	 * @description	： 根据Table查询【接口数据】信息
	 * @author 		： 万大龙（743）
	 * @date 		：2017年10月24日 下午8:48:38
	 * @param map
	 * @return
	 */
	@APIPagination
	List<Map<String,String>> getDataList(Map<String,Object> map);
	
	/**
	 * 
	 * @description	： 根据SQL分页查询【接口数据】信息
	 * @author 		： 万大龙（743）
	 * @date 		：2017年10月24日 下午8:47:35
	 * @param sql
	 * @return
	 */
	@APIPagination
	List<Map<String,String>> getDataListBySQL(Map<String,Object> map);
	
}
