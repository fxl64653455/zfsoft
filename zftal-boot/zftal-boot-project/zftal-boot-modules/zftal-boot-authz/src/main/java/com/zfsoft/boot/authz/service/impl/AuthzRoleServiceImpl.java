package com.zfsoft.boot.authz.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.zfsoft.api.service.BaseServiceImpl;
import com.zfsoft.boot.authz.dao.daointerface.IAuthzRoleDao;
import com.zfsoft.boot.authz.dao.entities.AncdModel;
import com.zfsoft.boot.authz.dao.entities.Menu;
import com.zfsoft.boot.authz.dao.entities.RoleModel;
import com.zfsoft.boot.authz.dao.entities.SjzyModel;
import com.zfsoft.boot.authz.service.svcinterface.IAuthzRoleService;


/**
 * @className	： AuthzRoleServiceImpl
 * @description	：  角色管理Service实现
 * @author 		：万大龙（743）
 * @date		： 2018年4月16日 下午3:41:16
 * @version 	V1.0
 */
@Service
public class AuthzRoleServiceImpl extends BaseServiceImpl<RoleModel, IAuthzRoleDao>
		implements IAuthzRoleService {

	@Override
	public int zjJsyhxx(String jsdm, String[] yhms) {
		return getDao().zjJsyhxx(jsdm, yhms);
	}

	@Override
	public int scJsyhxx(String jsdm, String[] yhms) {
		return getDao().scJsyhxx(jsdm, yhms);
	}

	@Override
	public boolean saveGnqx(String jsdm, String[] gnczids) {
		/**删除原来的功能权限*/
		dao.deleteGnqx(jsdm);
		
		if (gnczids != null && gnczids.length > 0){
			/**插入功能权限*/
			int c = dao.insertGnqx(jsdm, gnczids);
			return c > 0;
		}else {
			/**未指定任何功能权限*/
			return true;			
		}
	}

	@Override
	public Set<String> getPermissions(String jsdm) {
		return getDao().getPermissions(jsdm);
	}

	@Override
	public List<AncdModel> getButtonList(String jsdm, String yhm, String gnmkdm) {
		
		List<String> jsdms = dao.getJsdmByYhm(yhm);
		
		if (jsdms.contains(jsdm)){
			return dao.getButtonList(jsdm, gnmkdm);
		}
		
		return new ArrayList<>();
	}

	@Override
	public List<RoleModel> getJsxxListByZgh(String yhm) {
		List<RoleModel> yhjs = dao.getJsxxListByZgh(yhm);
		Set<String> jsdms = new HashSet<String>();
		for (RoleModel jsxx : yhjs){
			jsdms.add(jsxx.getJsdm());
		}
		return yhjs;
	}
	
	@Override
	public List<Menu> getAllGnqxList() {
		return dao.getAllGnqxList();
	}
	
	@Override
	public List<AncdModel> getButtonList(String jsdm) {
		return dao.getButtonList(jsdm, null);
	}
	
	@Override
	public List<SjzyModel> getSjzyList() {
		return dao.getSjzyList();
	}
	
	@Override
	public List<String> getSjqxByJsdm(String jsdm) {
		return dao.getSjqxByJsdm(jsdm);
	}
}
