package com.zfsoft.boot.authz.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Service;

import com.zfsoft.api.web.session.User;
import com.zfsoft.boot.authz.dao.entities.LoginModel;
import com.zfsoft.shiro.NoneRoleException;
import com.zfsoft.shiro.authc.DefaultDelegateAuthenticationInfo;
import com.zfsoft.shiro.authc.DelegateAuthenticationInfo;
import com.zfsoft.shiro.token.DelegateAuthenticationToken;

/**
 * 
 * @className	： AuthzSsoShiroServiceImpl
 * @description	： 单点登录接口实现
 * @author 		：万大龙（743）
 * @date		： 2017年11月8日 下午3:18:01
 * @version 	V1.0
 */
@Service("ssoAccountServiceImpl")
public class AuthzSsoShiroServiceImpl extends AuthzShiroServiceImpl {

	@Override
	public User getAuthzAccount(String userName, String password) {
	
		/*select a.yhm,
	       a.xm,
	       nvl(a.sfqy, '0') sfqy,
	       a.sjly,
	       a.jgdm,
	       a.yhlx,
	       a.dzyx,
	       a.sjhm
		  from zftal_xtgl_yhb a*/
		LoginModel model = getLoginDao().getAccountWithoutPwd(userName);
		
		User userInfo = new User();
		
		userInfo.setYhm(model.getYhm());
		userInfo.setXm(model.getXm());
		userInfo.setSfqy(model.getSfqy());
		userInfo.setJgdm(model.getJgdm());
		userInfo.setYhlx(model.getYhlx());
		
		List<String> jsdms = new ArrayList<String>();
		for (String jsdm : getUserDao().getRoles(model.getYhm())) {
			jsdms.add(jsdm);
		}
		userInfo.setJsdm(jsdms.get(0));
		userInfo.setJsdms(jsdms);
		
		return userInfo;
	}
	
	@Override
	public DelegateAuthenticationInfo getAuthenticationInfo(DelegateAuthenticationToken token) {
		
		//账号状态
		Map<String, String> statusMap = getLoginDao().getAccountStatus(token.getUsername(), null);
   		//账号不存在 或 用户名或密码不正确
   		if("0".equals(statusMap.get("NUM_1"))){
   			throw new UnknownAccountException();
   		}
   		// 账号被禁用
		else if ("0".equals(statusMap.get("NUM_4"))) {
			throw new DisabledAccountException();
		}
   		//用户无所属角色
   		else if("0".equals(statusMap.get("NUM_3"))){
            throw new NoneRoleException();
   		}
   		 
		return new DefaultDelegateAuthenticationInfo(this.getAuthzAccount(token.getUsername(), null), "0");
	}
	

}
