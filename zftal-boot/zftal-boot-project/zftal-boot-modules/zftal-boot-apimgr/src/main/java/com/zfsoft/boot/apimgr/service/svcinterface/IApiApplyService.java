/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.service.svcinterface;


import java.util.List;
import com.zfsoft.api.service.BaseService;
import com.zfsoft.boot.apimgr.dao.entities.ApiApplyModel;
import com.zfsoft.boot.apimgr.dao.entities.ApplyCombo;
import com.zfsoft.boot.apimgr.web.dto.ApiApplyDto;

/**
 * 
 * @className	： IApiApplyService
 * @description	： TODO(描述这个类的作用)
 * @author 		：樊新亮（1505）
 * @date		： 2017年9月28日 下午3:43:39
 * @version 	V1.0
 */
public interface IApiApplyService extends BaseService<ApiApplyModel>{
	
	/**
	 * 
	 * @description	： 保存申请记录
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月10日 下午12:20:06
	 * @param apply
	 * @param user
	 * @return
	 */
	public int saveApplys(ApiApplyDto apply, String user);
	
	/**
	 * 查询审核信息列表
	 * @description	： TODO
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月11日 上午10:55:10
	 * @param apply
	 * @return
	 */
	public List<ApiApplyModel> getPagedAuditList(ApiApplyModel apply);
	/**
	 * @description	： 根据用户查询接口申请下拉框数据
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年1月17日 下午2:53:02
	 * @return
	 */
	public List<ApplyCombo> comboListByOwner(String owner);
}
