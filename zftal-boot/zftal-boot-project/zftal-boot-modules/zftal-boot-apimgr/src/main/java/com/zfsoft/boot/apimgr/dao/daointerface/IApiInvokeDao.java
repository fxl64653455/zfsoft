/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.dao.daointerface;

import org.apache.ibatis.annotations.Mapper;

import com.zfsoft.api.dao.BaseDao;
import com.zfsoft.boot.apimgr.dao.entities.ApiInvokeModel;

@Mapper
public interface IApiInvokeDao extends BaseDao<ApiInvokeModel>{
	/***
	 * @description	： 根据apiId和invokeDeployId判断是否存在
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年4月19日 上午10:02:58
	 * @param obj
	 * @return
	 */
	public int exists(ApiInvokeModel obj);
}
