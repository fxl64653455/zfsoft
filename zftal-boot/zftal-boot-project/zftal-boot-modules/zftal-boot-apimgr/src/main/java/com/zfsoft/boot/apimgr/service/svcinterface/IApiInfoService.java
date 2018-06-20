package com.zfsoft.boot.apimgr.service.svcinterface;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.zfsoft.api.service.BaseService;
import com.zfsoft.basemodel.QueryModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiInfoModel;
import com.zfsoft.boot.apimgr.web.exceptions.DeleteApiInfoException;

public interface IApiInfoService extends BaseService<ApiInfoModel> {
	
	/**
	 * @description	：新增信息
	 * @author 		：杨碧琳(1422)
     * @date		： 2017年9月30日 
	 */
	public int insertInfo(ApiInfoModel infomodel);
	
	/***
	 * 
	 * @description	： TODO
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年9月30日 上午9:16:14
	 * @param apiInfo
	 * @return
	 */
	@Deprecated
	public QueryModel getPagedSearchList(ApiInfoModel apiInfo);
	
	/**
	 * 
	 * @description	： 获取指定ID关联的信息，以便进行修改、删除前的逻辑检查
	 * @author 		： 万大龙（743）
	 * @date 		：2017年10月23日 下午2:44:52
	 * @param list
	 * @return
	 */
	List<Map<String,String>> getDependencies(List<String> list);
	
	/**
	 * @description	： 根据ID串删除业务接口及所有关联数据
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年5月31日 下午4:45:26
	 * @param ids
	 */
	public void deleteApiInfo(Set<String> ids) throws DeleteApiInfoException;
}
