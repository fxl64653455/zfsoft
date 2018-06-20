package com.zfsoft.boot.init.dao.daointerface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zfsoft.api.dao.BaseDao;
import com.zfsoft.boot.init.dao.entities.JcsjModel;

/**
 * 
* 
* 类名称：JcsjDao 
* 类描述：基础数据DAO
* 创建人：xucy 
* 创建时间：2012-4-13 下午01:45:13 
* 修改人：xucy 
* 修改时间：2012-4-13 下午01:45:13 
* 修改备注： 
* @version 
*
 */
@Mapper
public interface IJcsjDao extends BaseDao<JcsjModel>{

	/**
	 * 
	* 方法描述: 查询基础数据列表(不分页)
	* 参数 @return
	* 参数 @throws Exception 参数说明
	* 返回类型 List<JcsjModel> 返回类型
	* @throws
	 */
	public List<JcsjModel> cxJcsjList(JcsjModel model);
	
	
	/**
	 * 
	* 方法描述:基础数据列表
	* 参数 @param array 参数说明
	* 返回类型  List<JcsjModel> 返回类型
	* @throws
	 */
	public  List<JcsjModel> getJcsjList(String lxdm);

	/**
	 * 
	* 方法描述:基础数据列表
	* 参数 @param array 参数说明
	* 返回类型  List<HashMap<String, String>> 返回类型
	* @throws
	 */
	public List<HashMap<String, String>> getJcsjHashMap(String lxdm);
	
	/**
	 * 
	 * <p>方法说明：检查是否可删除<p>
	 * <p>作者：a href="#">Zhangxiaobin[1036]<a><p>
	 * <p>时间：2016年12月26日下午8:29:40<p>
	 */
	public int checkDeletable(Map<String,String> params);
	
}
