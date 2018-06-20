package com.zfsoft.boot.authz.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfsoft.api.web.session.User;
import com.zfsoft.basicutils.StringUtils;
import com.zfsoft.boot.authz.dao.daointerface.IAuthzRoleDao;
import com.zfsoft.boot.authz.dao.daointerface.IAuthzLoginDao;
import com.zfsoft.boot.authz.dao.daointerface.IAuthzUserDao;
import com.zfsoft.boot.authz.dao.entities.LoginModel;
import com.zfsoft.boot.authz.service.svcinterface.IAuthzShiroService;
import com.zfsoft.security.algorithm.MD5Codec;
import com.zfsoft.shiro.InvalidAccountException;
import com.zfsoft.shiro.NoneRoleException;
import com.zfsoft.shiro.authc.DefaultDelegateAuthenticationInfo;
import com.zfsoft.shiro.authc.DelegateAuthenticationInfo;
import com.zfsoft.shiro.token.DelegateAuthenticationToken;

/**
 * 
 * @className	： AuthzShiroServiceImpl
 * @description	： Shiro认证信息获取接口实现：实现与系统业务数据对接
 * @author 		：万大龙（743）
 * @date		： 2017年11月8日 下午1:52:49
 * @version 	V1.0
 */
@Service("defaultShiroService")
public class AuthzShiroServiceImpl implements IAuthzShiroService {

	@Autowired
	private IAuthzRoleDao roleDao;
	@Autowired
	private IAuthzUserDao userDao;	
	@Autowired
	private IAuthzLoginDao loginDao;

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
		LoginModel model = getLoginDao().getAccount(userName, password);
		
		User user = new User();
		
		user.setYhm(model.getYhm());
		user.setXm(model.getXm());
		user.setSfqy(model.getSfqy());
		user.setJgdm(model.getJgdm());
		user.setYhlx(model.getYhlx());
		
		List<String> jsdms = new ArrayList<String>();
		for (String jsdm : getUserDao().getRoles(model.getYhm())) {
			jsdms.add(jsdm);
		}
		user.setJsdm(jsdms.get(0));
		user.setJsdms(jsdms);
		
		return user;
	}
	
	
	@Override
	public DelegateAuthenticationInfo getAuthenticationInfo(DelegateAuthenticationToken token) {
		
		if( !StringUtils.hasText(token.getUsername()) || token.getPassword() == null ){
			throw new InvalidAccountException("Username or password is incorrect.");
		}
		
		//密码加密
		String pwd = MD5Codec.encrypt(new String(token.getPassword()));
		//账号状态
		Map<String, String> statusMap = getLoginDao().getAccountStatus(token.getUsername(), pwd);
   		//账号不存在 或 用户名或密码不正确
   		if("0".equals(statusMap.get("NUM_1")) || "0".equals(statusMap.get("NUM_2"))){
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
   		 
		return new DefaultDelegateAuthenticationInfo(this.getAuthzAccount(token.getUsername(), pwd), token.getPassword());
	}

	@Override
	public Set<String> getPermissionsInfo(Object principal) {
		User user = (User) principal;
		
		//权限代码
		Set<String> permissions = new HashSet<String>();
		
		//角色 + 用户名拥有的权限代码: 需要个人授权支持，目前未开发
		permissions.addAll(getUserDao().getPermissions(user.getYhm()));
		
		//角色拥有的权限代码
		permissions.addAll(getRoleDao().getPermissions(user.getJsdm()));
		
		return permissions;
	}

	@Override
	public Set<String> getPermissionsInfo(Set<Object> principals) {
		return null;
	}

	@Override
	public Set<String> getRolesInfo(Object principal) {
		User user = (User) principal;
		return new HashSet<>(user.getJsdms());
	}

	@Override
	public Set<String> getRolesInfo(Set<Object> principals) {
		return null;
	}
	

	public IAuthzRoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(IAuthzRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public IAuthzUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IAuthzUserDao userDao) {
		this.userDao = userDao;
	}


	public IAuthzLoginDao getLoginDao() {
		return loginDao;
	}


	public void setLoginDao(IAuthzLoginDao loginDao) {
		this.loginDao = loginDao;
	}
	
}
