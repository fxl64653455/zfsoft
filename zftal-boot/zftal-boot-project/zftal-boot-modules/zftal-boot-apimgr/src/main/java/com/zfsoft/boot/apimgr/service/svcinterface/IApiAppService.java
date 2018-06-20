/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.service.svcinterface;

import java.util.List;
import com.zfsoft.api.service.BaseService;
import com.zfsoft.boot.apimgr.dao.entities.ApiAppModel;

/**
 * 
 * @className	： IApiAppService
 * @description	： TODO(描述这个类的作用)
 * @author 		：樊新亮（1505）
 * @date		： 2017年9月28日 下午3:43:44
 * @version 	V1.0
 */
public interface IApiAppService extends BaseService<ApiAppModel>{
	
	/**
	 * 
	 * @description	： TODO
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年9月29日 下午1:51:00
	 * @param app
	 * @return
	 */
	public ApiAppModel saveApp(ApiAppModel app);
	
	/**
	 * 
	 * @description	： TODO
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月9日 下午5:45:23
	 * @param app
	 * @return
	 */
	public List<ApiAppModel> getPagedApplyTableData(ApiAppModel app);
	
	/**
	 * 
	 * @description	： TODO
	 * @author 		： 樊新亮（1505）
	 * @date 		：2017年10月11日 下午8:50:10
	 * @param appId
	 * @return
	 */
	public int deleteApp(String appId);
	
	/**
	 * @description	： 删除应用菜单
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年1月16日 下午4:14:47
	 * @param dm
	 * @return
	 */
	int deleteAppMenu();
	
	/**
	 * @description	： 添加应用菜单
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年1月16日 下午4:20:06
	 * @return
	 */
	int addAppMenu();
}
