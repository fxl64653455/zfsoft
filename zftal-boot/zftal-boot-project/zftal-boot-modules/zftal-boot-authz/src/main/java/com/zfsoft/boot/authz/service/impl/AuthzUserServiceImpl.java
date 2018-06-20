package com.zfsoft.boot.authz.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.zfsoft.api.service.BaseServiceImpl;
import com.zfsoft.boot.authz.dao.daointerface.IAuthzUserDao;
import com.zfsoft.boot.authz.dao.entities.RoleModel;
import com.zfsoft.boot.authz.dao.entities.UserModel;
import com.zfsoft.boot.authz.service.svcinterface.IAuthzUserService;

/**
 * 
 * @className	： AuthzUserServiceImpl
 * @description	： 用户管理Service接口实现
 * @author 		：万大龙（743）
 * @date		： 2018年4月20日 下午3:58:46
 * @version 	V1.0
 */
@Service
public class AuthzUserServiceImpl extends BaseServiceImpl<UserModel, IAuthzUserDao> implements IAuthzUserService {
	
	@Override
	public List<UserModel> getPagedWfpyhList(RoleModel model) {
		return getDao().getPagedWfpyhList(model);
	}

	@Override
	public List<UserModel> getPagedYfpyhList(RoleModel model) {
		return getDao().getPagedYfpyhList(model);
	}
	
	@Override
	public int setRoles(List<String> users,  List<String> roleIds) {
		return getDao().setRoles(users, roleIds);
	}

	@Override
	public List<String> getRoles(String userId) {
		return getDao().getRoles(userId);
	}
	
	@Override
	public int setGroups(List<String> users,  List<String> groupIds) {
		return getDao().setGroups(users, groupIds);
	}
	
	@Override
	public List<String> getGroups(String userId) {
		return getDao().getGroups(userId);
	}
	
	@Override
	public boolean updatePwd(List<String> users, String password) {
		return getDao().updatePwd(users, password) > 0;
	}

	@Override
	public Set<String> getPermissions(String userId) {
		return getDao().getPermissions(userId);
	}

	@Override
	public List<UserModel> getPagedList(String jsdm, String yhz, String gwId) {
		return  getDao().getPagedUserList(jsdm, yhz, gwId);
	}

	@Override
	public boolean insert(UserModel model, String[] jsdms) {
		
		int count = getDao().insert(model);
		
		if (jsdms != null && jsdms.length > 0){
			getDao().zjYhjsxx(model.getYhm(), jsdms);
		}
		
		return count > 0;
	}

	@Override
	public boolean update(UserModel model, String[] jsdms) {
		int count = dao.update(model);
		
		String yhm = model.getYhm();
		dao.scYhjsxx(new String[]{yhm});
		
		if (jsdms != null && jsdms.length > 0){
			dao.zjYhjsxx(model.getYhm(), jsdms);
		}
		return count > 0;
	}
	
	@Override
	public boolean scYhxx(String[] yhm) {
		if (yhm == null || yhm.length == 0) return false;
		
		int count = dao.scYhxx(yhm);
	
		if (count > 0){
			dao.scYhjsxx(yhm);
		}
		return count  > 0;
	}
	
	@Override
	public boolean updateYhmm(String[] yhmArr, String password) {
		if (yhmArr == null || yhmArr.length == 0) return false;
		return dao.updateYhmm(yhmArr, password)  > 0;
	}
}
