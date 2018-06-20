package com.zfsoft.boot.apimgr.dao.daointerface;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zfsoft.api.dao.BaseDao;
import com.zfsoft.boot.apimgr.dao.entities.ApiInfoModel;

@Mapper
public interface IApiInfoDao extends BaseDao<ApiInfoModel> {
	
	/**
	 * 
	 * @description	： TODO
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月9日 下午4:19:18
	 * @param apiInfo
	 * @return
	 */
	@Deprecated
	List<ApiInfoModel> getPagedSearchList(ApiInfoModel apiInfo);
	
	/**
	 *@description	：新增信息
	 * @author 		：杨碧琳(1422)
     * @date		： 2017年9月30日 
	 */
	public int insertInfo(ApiInfoModel infomodel);

	
	/**
	 * 
	 * @description	： 获取指定ID关联的信息，以便进行删除前的逻辑检查
	 * @author 		： 万大龙（743）
	 * @date 		：2017年10月20日 下午12:53:15
	 * @param list
	 * @return
	 */
	List<Map<String,String>> getDependencies(List<String> list);
	
}
