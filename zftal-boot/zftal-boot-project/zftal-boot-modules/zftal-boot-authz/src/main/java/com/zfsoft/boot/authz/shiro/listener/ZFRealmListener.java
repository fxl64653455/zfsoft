package com.zfsoft.boot.authz.shiro.listener;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;

import com.zfsoft.api.web.WebContext;
import com.zfsoft.api.web.session.User;
import com.zfsoft.shiro.realm.RealmListener;

/**
 * 
 * @className	： ZFRealmListener
 * @description	： 登录成功后的监听，方便设置相关参数
 * @author 		：万大龙（743）
 * @date		： 2017年10月10日 下午4:09:27
 * @version 	V1.0
 */
public class ZFRealmListener implements RealmListener {

	@Override
	public void onAuthenticationSuccess(AuthenticationInfo info, Session session) {
		User user = (User) info.getPrincipals().getPrimaryPrincipal();
		List<String> jsdms = user.getJsdms();
		if (jsdms != null && !jsdms.isEmpty()){
			user.setJsdm(jsdms.get(0));
		}
		session.setAttribute(WebContext.SESSION_USER_KEY, user);
        session.setAttribute(user.getYhm(), user.getJsdms());
	}

	@Override
	public void onAuthenticationFail(AuthenticationToken token, AuthenticationException ex) {
		// TODO Auto-generated method stub
		
	}

}
