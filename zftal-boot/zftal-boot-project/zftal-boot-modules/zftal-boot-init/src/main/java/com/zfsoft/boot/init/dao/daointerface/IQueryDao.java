package com.zfsoft.boot.init.dao.daointerface;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zfsoft.api.dao.BaseDao;
import com.zfsoft.basemodel.BaseMap;
import com.zfsoft.basemodel.BaseModel;

/**
 * 
 * @className	： IQueryDao
 * @description	： 与业务无关的公共基础查询Dao接口
 * @author 		：万大龙（743）
 * @date		： 2017年9月30日 下午2:14:51
 * @version 	V1.0
 */
@Mapper
public interface IQueryDao extends BaseDao<BaseModel> {

	/**
	 * 
	 * @description: 生成guid
	 * @author : wandalong
	 * @date : 2014-4-3
	 * @time : 下午06:49:49 
	 * @return
	 */
	public String getSysGuid();
	
	/**
	 * 
	 *@描述：根据传入格式返回数据库相应格式的时间值
	 *@创建人:wandalong
	 *@创建时间:2015-1-26下午07:23:39
	 *@修改人:
	 *@修改时间:
	 *@修改描述:
	 *@param dataFormat : (如：yyyy-MM-dd HH24:mi:ss)
	 *@return
	 */
	public String getDatabaseTime(String dataFormat);

	public List<Map<String,String>> getResultList(@Param(value="tableName") String tableName,@Param(value="tabCol")String tabCol,@Param(value="tabColV")String tabColV);
	
	public List<BaseMap> getSelectList(@Param(value="tableName") String tableName,@Param(value="tabCol")String tabCol,@Param(value="tabColV")String tabColV);
	
	public List<BaseMap> getTableList(Map<String,Object> map);
	
	public List<BaseMap> getPagedSelectList(@Param(value="tableName") String tableName,@Param(value="tabCol")String tabCol,@Param(value="tabColV")String tabColV);
	
	public List<BaseMap> getPagedTableList(Map<String,Object> map);


}
