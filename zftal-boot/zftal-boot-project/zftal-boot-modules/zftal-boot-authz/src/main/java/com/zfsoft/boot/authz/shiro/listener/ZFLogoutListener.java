/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.authz.shiro.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;

import com.zfsoft.api.utils.BizLogUtils;
import com.zfsoft.api.web.session.User;
import com.zfsoft.shiro.filter.impl.DefaultLogoutListener;

/**
 * 
 * @className	： ZFLogoutListener
 * @description	： 账号注销监听：记录日志
 * @author 		：万大龙（743）
 * @date		： 2017年10月10日 下午4:10:01
 * @version 	V1.0
 */
public class ZFLogoutListener extends DefaultLogoutListener {

	@Override
	public void beforeLogout(Subject subject, ServletRequest request, ServletResponse response) {
		if(!subject.isAuthenticated()){
			return;
		}
		User loginUser = (User) subject.getPrincipals().getPrimaryPrincipal();
		BizLogUtils.logout(loginUser, "登录注销", "权限控制", loginUser.toString() + "注销登录状态.");
	}
	
}
