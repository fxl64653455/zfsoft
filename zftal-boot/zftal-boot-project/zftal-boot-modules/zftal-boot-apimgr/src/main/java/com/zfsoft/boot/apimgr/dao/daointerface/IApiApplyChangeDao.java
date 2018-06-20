/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.dao.daointerface;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zfsoft.api.dao.BaseDao;
import com.zfsoft.boot.apimgr.dao.entities.ApiApplyChangeModel;

@Mapper
public interface IApiApplyChangeDao extends BaseDao<ApiApplyChangeModel>{
	
	/**
	 * @description	： 查询变更审核信息列表
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年1月25日 下午12:20:43
	 * @param change
	 * @return
	 */
	public List<ApiApplyChangeModel> getPagedAuditList(ApiApplyChangeModel change);
	
	/**
	 * @description	： 根据申请ID查询对应的变更记录
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年5月31日 下午3:09:40
	 * @param applyId
	 * @return
	 */
	public List<ApiApplyChangeModel> getApplyChangeList(@Param("applyId") String applyId);
}
