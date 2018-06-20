/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.service.svcinterface;

import com.zfsoft.api.service.BaseService;
import com.zfsoft.boot.apimgr.dao.entities.ApiInvokeModel;
import com.zfsoft.boot.apimgr.web.dto.ApiInvokeDto;

public interface IApiInvokeService extends BaseService<ApiInvokeModel>{
	/**
	 * @description	： 保存接口连续调用信息
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年4月19日 上午9:56:08
	 * @param par
	 * @return
	 */
	public int save(ApiInvokeDto par);
}
