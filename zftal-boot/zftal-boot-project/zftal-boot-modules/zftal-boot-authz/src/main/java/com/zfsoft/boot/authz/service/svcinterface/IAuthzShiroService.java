package com.zfsoft.boot.authz.service.svcinterface;

import com.zfsoft.api.web.session.User;
import com.zfsoft.shiro.service.AccountService;

/**
 * 
 * @className	： IAuthzShiroService
 * @description	： Shiro权限管理数据查询接口
 * @author 		：万大龙（743）
 * @date		： 2017年11月8日 下午1:50:19
 * @version 	V1.0
 */
public interface IAuthzShiroService extends AccountService {
	
	/**
	 * 
	 * @description	： 查询用户信息，[参数：用户名和密码]
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月8日 下午3:20:14
	 * @param userName
	 * @param password
	 * @return
	 */
	User getAuthzAccount(String userName, String password);
	
}
