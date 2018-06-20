package com.zfsoft.boot.authz.dao.daointerface;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zfsoft.api.dao.BaseDao;
import com.zfsoft.boot.authz.dao.entities.RoleModel;
import com.zfsoft.boot.authz.dao.entities.UserModel;

/**
 * 
 * @className	： IAuthzUserDao
 * @description	： 用户管理DAO
 * @author 		：万大龙（743）
 * @date		： 2018年4月13日 下午4:23:36
 * @version 	V1.0
 */
@Mapper
public interface IAuthzUserDao extends BaseDao<UserModel>{
	
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
    public List<UserModel> getPagedUserList(@Param(value = "jsdm") String jsdm,
    		@Param(value = "yhz") String yhz,@Param(value = "gwId") String gwId);
    
  	/**
  	 * 
  	 * @description	： 给用户分配角色
  	 * @author 		： 万大龙（743）
  	 * @date 		：2018年4月20日 下午3:53:33
  	 * @param yhms 用户名集合	
  	 * @param jsdms 角色代码集合
  	 * @return 变更记录数
  	 */
  	public int setRoles(@Param(value = "yhms") List<String> yhms , @Param(value = "jsdms") List<String> roleIds);
  	
  	/**
  	 * 
  	 * @description	： 获取用户已分配角色ID
  	 * @author 		： 万大龙（743）
  	 * @date 		：2018年4月20日 下午3:51:48
  	 * @param yhm 用户名
  	 * @return
  	 */
  	public List<String> getRoles(@Param(value="yhm") String yhm);
  	
  	/**
  	 * 
  	 * @description	： 给用户分配用户组
  	 * @author 		： 万大龙（743）
  	 * @date 		：2018年4月20日 下午3:53:13
  	 * @param yhms 用户名集合	
  	 * @param yhzs 用户组代码集合
  	 * @return 变更记录数
  	 */
  	public int setGroups(@Param(value = "yhms") List<String> yhms , @Param(value = "yhzs") List<String> yhzs);
  	
  	/**
  	 * 
  	 * @description	： 获取用户已分配用户组ID
  	 * @author 		： 万大龙（743）
  	 * @date 		：2018年4月20日 下午3:51:22
  	 * @param yhm 用户名
  	 * @return
  	 */
  	public List<String> getGroups(@Param(value="yhm") String yhm);
    
	/**
	 * 
	 * @description	： 批量修改用户密码
	 * @author 		： 万大龙（743）
	 * @date 		：2018年4月13日 下午4:19:19
	 * @param yhms 用户名集合	
	 * @param password 新密码
	 * @return 变更记录数
	 */
	public int updatePwd(@Param(value = "yhms") List<String> yhms , @Param(value = "password") String password);
	
	/**
	 * @description	： 根据[用户组功能模块操作表]数据查询用户（关联的用户组）具备的权限信息
	 * @author 		： 万大龙（743）
	 * @date 		：2018年4月13日 下午4:24:37
	 * @param yhm 用户名
	 * @return 用户具备的权限信息
	 */
	public Set<String> getPermissions(@Param(value="yhm") String yhm);
	
	/**
	 * @description	： 增加用户角色
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月6日 下午5:04:27
	 * @param yhm 用户名
	 * @param jsdms 角色代码
	 * @return 
	 */
	public int zjYhjsxx(@Param(value = "yhm") String yhm,@Param(value = "jsdms") String[] jsdms);
	
	/**
	 * @description	： 删除用户角色
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月7日 上午9:23:29
	 * @param yhm
	 * @return
	 */
	public int scYhjsxx(@Param(value = "yhm") String[] yhm);
	
	/**
	 * @description	： 删除用户
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月7日 上午9:38:31
	 * @param yhm
	 * @return
	 */
	public int scYhxx(@Param(value = "yhm") String[] yhm);
	
	/**
	 * @description	： 批量修改用户密码
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月7日 上午10:30:53
	 * @param yhms
	 * @param password
	 * @return
	 */
	public int updateYhmm(@Param(value = "yhms") String[] yhms , @Param(value = "password")String password);
}
