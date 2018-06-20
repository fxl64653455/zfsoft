package com.zfsoft.boot.authz.service.svcinterface;

import com.zfsoft.api.service.BaseService;
import com.zfsoft.api.web.session.User;
import com.zfsoft.boot.authz.dao.entities.LoginModel;

/**
 * 
 * <p>
 *   <h3>zftal框架<h3>
 *   说明：用户登录
 * <p>
 * @author <a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a>
 * @version 2017年3月17日下午2:36:39
 */
public interface IAuthzLoginService extends BaseService<LoginModel>{
	
	/**
	 * 
	 * <p>方法说明：查询用户信息<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2017年3月17日下午2:36:50<p>
	 * @param model
	 * @return
	 */
	public User cxYhxx(LoginModel model) ;
	
	
}
