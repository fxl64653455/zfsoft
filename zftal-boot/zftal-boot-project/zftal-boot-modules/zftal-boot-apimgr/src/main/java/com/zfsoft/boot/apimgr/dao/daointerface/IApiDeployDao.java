package com.zfsoft.boot.apimgr.dao.daointerface;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zfsoft.api.dao.BaseDao;
import com.zfsoft.boot.apimgr.dao.entities.ApiDeployModel;

@Mapper
public interface IApiDeployDao extends BaseDao<ApiDeployModel> {
	
	/**
	  *@description:计算该业务系统接口发布的版本信息 
	  *@author ：杨碧琳(1422)
	  *@date   ： 2017年10月13日 
	  */
	public ApiDeployModel getVersions(String id);
	/**
	  *@description:判断发布接口地址是否已经存在
	  *@author 杨碧琳(1422)
	  *@date   ： 2017年10月13日 
	  */
	public int checkAddr(String addr);
	/***
	 * @description	： 查询已发布的接口信息
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月16日 上午9:59:34
	 * @param apiInfoModel
	 * @return
	 */
	public List<ApiDeployModel> getPagedSearchList(ApiDeployModel apiInfoModel);
	/***
	 * 
	 * @description	： 查询图标数据
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月24日 下午5:29:12
	 * @param deployId
	 * @return
	 */
	public ApiDeployModel getIcon(@Param("deployId") String deployId);
	/**
	 * @description	： 查询下拉框数据
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年11月18日 下午4:06:13
	 * @param deployModel
	 * @return
	 */
	public List<ApiDeployModel> getComboList(ApiDeployModel deployModel);
	/**
	 * @description	： 根据接口ID查询发布记录
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年5月31日 下午5:29:21
	 * @return
	 */
	public List<ApiDeployModel> getModelListByApiInfoId(@Param("apiId") String apiId);
}
