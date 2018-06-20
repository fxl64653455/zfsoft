package com.zfsoft.boot.authz.service.svcinterface;

import java.util.List;
import java.util.Set;

import com.zfsoft.api.service.BaseService;
import com.zfsoft.boot.authz.dao.entities.RoleModel;
import com.zfsoft.boot.authz.dao.entities.UserModel;

/**
 * 
 * @className	： IAuthzUserService
 * @description	： 用户管理Service接口
 * @author 		：万大龙（743）
 * @date		： 2018年4月20日 下午3:57:24
 * @version 	V1.0
 */
public interface IAuthzUserService extends BaseService<UserModel> {
	
	/**
	 * @description	： 角色未分配用户列表
	 * @author 		： 万大龙（743）
	 * @date 		：2018年4月16日 下午3:35:36
	 * @param jsdm 角色代码
	 * @return
	 */
    public List<UserModel> getPagedWfpyhList(RoleModel model);
    
    /**
     * 
     * @description	： 角色已分配用户列表
     * @author 		： 万大龙（743）
     * @date 		：2018年4月16日 下午3:35:28
     * @param jsdm 角色代码
     * @return
     */
    public List<UserModel> getPagedYfpyhList(RoleModel model);
    
    /**
     * 查询用户列表
     * @author 		： vindell（001）
     * @param jsdm 角色代码
     * @param yhz 用户组代码
     * @param gwId 岗位ID
     * @return 用户对象集合
     */
    public List<UserModel> getPagedList( String jsdm, String yhz, String gwId);
    
    /**
  	 * 
  	 * @description	： 给用户分配角色
  	 * @author 		： 万大龙（743）
  	 * @date 		：2018年4月20日 下午3:53:33
  	 * @param yhms 用户名集合	
  	 * @param jsdms 角色代码集合
  	 * @return 变更记录数
  	 */
  	public int setRoles(List<String> yhms , List<String> roleIds);
	
	/**
  	 * 
  	 * @description	： 获取用户已分配角色ID
  	 * @author 		： 万大龙（743）
  	 * @date 		：2018年4月20日 下午3:51:48
  	 * @param yhm 用户名
  	 * @return
  	 */
  	public List<String> getRoles(String yhm);
  	
  	/**
  	 * 
  	 * @description	： 给用户分配用户组
  	 * @author 		： 万大龙（743）
  	 * @date 		：2018年4月20日 下午3:53:13
  	 * @param yhms 用户名集合	
  	 * @param yhzs 用户组代码集合
  	 * @return 变更记录数
  	 */
  	public int setGroups(List<String> yhms , List<String> yhzs);
  	
  	/**
  	 * 
  	 * @description	： 获取用户已分配用户组ID
  	 * @author 		： 万大龙（743）
  	 * @date 		：2018年4月20日 下午3:51:22
  	 * @param yhm 用户名
  	 * @return
  	 */
  	public List<String> getGroups(String yhm);
	
	/**
	 * @description	： 批量修改用户密码
	 * @author 		： 万大龙（743）
	 * @date 		：2018年4月20日 下午4:08:03
	  * @param yhms 用户名集合	
	 * @param password 新密码
	 * @return 变更结果
	 */
	public boolean updatePwd(List<String> yhms , String password);
	 
	/**
	 * @description	： 根据[用户组功能模块操作表]数据查询用户（关联的用户组）具备的权限信息
	 * @author 		： 万大龙（743）
	 * @date 		：2018年4月13日 下午4:24:37
	 * @param yhm 用户名
	 * @return 用户具备的权限信息
	 */
	public Set<String> getPermissions( String jsdm);
	
	/**
	 * @description	： 增加用户并分配角色
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月6日 下午5:00:15
	 * @param model
	 * @param jsdms
	 * @return
	 */
	public boolean insert(UserModel model ,String[] jsdms);

	/**
	 * @description	： 修改用户信息及分配的角色信息
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月7日 上午9:16:49
	 * @param model
	 * @param jsdmArr
	 * @return
	 */
	public boolean update(UserModel model, String[] jsdmArr);
	
	/**
	 * @description	： 删除用户信息
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月7日 上午9:37:00
	 * @param yhm
	 * @return
	 */
	public boolean scYhxx(String[] yhm);
	
	/**
	 * @description	： 批量修改用户密码
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月7日 上午10:29:01
	 * @param yhmArr
	 * @param password
	 * @return
	 */
	public boolean updateYhmm(String[] yhmArr , String password);
}
