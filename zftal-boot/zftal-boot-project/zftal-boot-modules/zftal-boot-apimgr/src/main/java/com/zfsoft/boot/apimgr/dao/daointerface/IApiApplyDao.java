/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.dao.daointerface;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zfsoft.api.dao.BaseDao;
import com.zfsoft.boot.apimgr.dao.entities.ApiApplyModel;
import com.zfsoft.boot.apimgr.dao.entities.ApplyCombo;

/**
 * 
 * @className	： IApiApply
 * @description	： TODO(描述这个类的作用)
 * @author 		：樊新亮（1505）
 * @date		： 2017年9月28日 下午3:29:47
 * @version 	V1.0
 */
@Mapper
public interface IApiApplyDao extends BaseDao<ApiApplyModel>{
	/**
	 * 查询审核信息列表
	 * @description	： TODO
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月11日 上午10:52:50
	 * @param apply
	 * @return
	 */
	public List<ApiApplyModel> getPagedAuditList(ApiApplyModel apply);
	/**
	 * @description	： 根据用户查询接口申请下拉框数据
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年1月17日 下午2:54:46
	 * @param owner
	 * @return
	 */
	public List<ApplyCombo> comboListByOwner(@Param("owner") String owner);
}
