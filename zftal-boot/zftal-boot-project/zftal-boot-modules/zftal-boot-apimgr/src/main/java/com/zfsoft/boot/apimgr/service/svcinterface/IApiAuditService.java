/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.service.svcinterface;

import com.zfsoft.api.service.BaseService;
import com.zfsoft.boot.apimgr.dao.entities.ApiAuditModel;

/**
 * 
 * @className	： IApiAuditService
 * @description	： TODO(描述这个类的作用)
 * @author 		：樊新亮（1505）
 * @date		： 2017年9月28日 下午3:43:53
 * @version 	V1.0
 */
public interface IApiAuditService extends BaseService<ApiAuditModel>{
	
	/**
	 * 
	 * @description	： 审核一个接口
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月10日 下午3:59:11
	 * @param audit 审核信息
	 * @param appId 应用ID
	 * @param deployId 接口发布ID
	 * @return
	 */
	public int audit(ApiAuditModel audit,String appId,String deployId);
	
	/**
	 * @description	： 接口变更审核
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年1月31日 下午3:00:33
	 * @param audit
	 * @return
	 */
	public int changeAudit(ApiAuditModel audit);
}
