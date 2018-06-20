/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.dao.daointerface;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zfsoft.api.dao.BaseDao;
import com.zfsoft.boot.apimgr.dao.entities.ApiMetircModel;

/**
 * 
 * @className	： IApiMetircDao
 * @description	： TODO(描述这个类的作用)
 * @author 		：万大龙（743）
 * @date		： 2017年11月28日 下午5:20:43
 * @version 	V1.0
 */
@Mapper
public interface IApiMetircDao extends BaseDao<ApiMetircModel>{
	
	List<ApiMetircModel> countByAppKey();
	
	int deleteByAppKey(@Param("appKey") String appkey);
	
}
