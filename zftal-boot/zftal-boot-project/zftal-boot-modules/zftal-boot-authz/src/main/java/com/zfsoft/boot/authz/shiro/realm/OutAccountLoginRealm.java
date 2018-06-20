/**
 * 
 */
package com.zfsoft.boot.authz.shiro.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;

import com.zfsoft.api.web.WebContext;
import com.zfsoft.api.web.session.User;
import com.zfsoft.boot.authz.service.svcinterface.IAuthzShiroService;
import com.zfsoft.boot.authz.shiro.token.OutAccountLoginToken;

/**
 * <p>
 *   <h3>zftal框架<h3>
 *   说明：外部系统登录Realm
 * <p>
 * @author <a href="#">Zhangxiaobin[1036]<a>
 * @version 2016年7月29日下午3:59:02
 */
public class OutAccountLoginRealm extends AuthorizingRealm{

	private IAuthzShiroService shiroService;
	
	public OutAccountLoginRealm() {
		 setAuthenticationTokenClass(OutAccountLoginToken.class);
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		if(token instanceof OutAccountLoginToken){
			OutAccountLoginToken castToken = (OutAccountLoginToken)token;
			String userName = castToken.getUserName();
			User userInfo = shiroService.getAuthzAccount(userName, null);
			
			if(userInfo == null){
				throw new AccountException("查询不到用户信息，用户名：[" + castToken.getUserName()  + "]。");
			}
			
			SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userInfo, "0", userInfo.getYhm());
			Session session = SecurityUtils.getSubject().getSession();
			session.setAttribute(WebContext.SESSION_USER_KEY, userInfo);
	        return simpleAuthenticationInfo;
		}
		return null;
	}

//	@Override
//	public void onInit(){
//		super.onInit();
//		setAuthenticationTokenClass(org.apache.shiro.authc.AuthenticationToken.class);
//	}
	
	//============================//
	public IAuthzShiroService getShiroService() {
		return shiroService;
	}

	public void setShiroService(IAuthzShiroService shiroService) {
		this.shiroService = shiroService;
	}

	
	
}
