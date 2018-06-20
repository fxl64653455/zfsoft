/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.service.svcinterface;

import java.util.List;

import com.zfsoft.api.service.BaseService;
import com.zfsoft.boot.apimgr.dao.entities.ApiApplyChangeModel;
import com.zfsoft.boot.apimgr.web.dto.ApiApplyDto;

public interface IApiApplyChangeService extends BaseService<ApiApplyChangeModel>{
	
	/**
	 * @description	： 保存申请变更信息
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年1月25日 下午1:28:44
	 * @param par
	 * @param user
	 * @return
	 */
	public int saveApplyChange(ApiApplyDto par,String user);
	
	/**
	 * @description	： 查询变更审核信息
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年1月25日 下午1:29:04
	 * @param change
	 * @return
	 */
	public List<ApiApplyChangeModel> getPagedAuditList(ApiApplyChangeModel change);
}
