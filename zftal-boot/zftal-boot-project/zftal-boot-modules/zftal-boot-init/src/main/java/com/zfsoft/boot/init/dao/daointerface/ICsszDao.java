package com.zfsoft.boot.init.dao.daointerface;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zfsoft.api.dao.BaseDao;
import com.zfsoft.basemodel.PairModel;
import com.zfsoft.boot.init.dao.entities.CsszModel;

/**
 * @className	： ICsszDao
 * @description	： TODO(描述这个类的作用)
 * @author 		：万大龙（743）
 * @date		： 2017年10月16日 下午12:08:57
 * @version 	V1.0
 */
@Mapper
public interface ICsszDao extends BaseDao<CsszModel> {

	/**
	 * @description	： 根据字段来源字段值的SQL查询数据
	 * @author 		： 万大龙（743）
	 * @date 		：2017年10月16日 下午12:08:22
	 * @param zdlySQL
	 * @return
	 */
	public List<PairModel> getZdsjList(@Param(value = "zdlySQL") String zdlySQL);

}