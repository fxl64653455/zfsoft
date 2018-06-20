package com.zfsoft.boot.authz.dao.daointerface;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zfsoft.api.dao.BaseDao;
import com.zfsoft.boot.authz.dao.entities.AncdModel;
import com.zfsoft.boot.authz.dao.entities.Menu;
import com.zfsoft.boot.authz.dao.entities.RoleModel;
import com.zfsoft.boot.authz.dao.entities.SjzyModel;

/**
 * 
 * @className	： IAuthzRoleDao
 * @description	： 角色管理DAO
 * @author 		：万大龙（743）
 * @date		： 2018年4月16日 下午3:19:41
 * @version 	V1.0
 */
@Mapper
public interface IAuthzRoleDao extends BaseDao<RoleModel>{
	
    /**
     * 
     * @description	： 给角色分配用户
     * @author 		： 万大龙（743）
     * @date 		：2018年4月16日 下午3:29:06
     * @param jsdm 角色代码
	 * @param yhms 用户名集合
	 * @return 变更记录数
     */
	public int zjJsyhxx(@Param(value = "jsdm") String jsdm , @Param(value = "yhms") String[] yhms);
	
	/**
	 * 
	 * @description	： 删除角色已分配的用户
	 * @author 		： 万大龙（743）
	 * @date 		：2018年4月16日 下午3:29:25
	 * @param jsdm 角色代码
	 * @param yhms 用户名集合
	 * @return 变更记录数
	 */
	public  int scJsyhxx(@Param(value = "jsdm") String jsdm , @Param(value = "yhms") String[] yhms);
	
	/**
	 * @description	： 新增角色功能权限
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月8日 下午12:15:33
	 * @param jsdm
	 * @param gnczids
	 * @return
	 */
	public int insertGnqx(@Param(value = "jsdm") String jsdm,@Param(value = "gnczids") String[] gnczids);
	
	/**
	 * @description	： 删除角色功能权限
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月8日 下午12:15:05
	 * @param jsdm
	 * @return
	 */
	public int deleteGnqx(@Param(value = "jsdm") String jsdm);
	
	/**
	 * @description	： 根据[角色功能模块操作表]数据查询角色具备的权限信息
	 * @author 		： 万大龙（743）
	 * @date 		：2018年4月16日 下午3:24:28
	 * @param jsdm 角色代码
	 * @return 角色具备的权限信息
	 */
	public Set<String> getPermissions(@Param(value="jsdm")String jsdm);
	
	/**
	 * @description	： 查询角色功能按钮列表
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月6日 下午5:03:15
	 * @param jsdm 角色代码
	 * @param gnmkdm 功能模块代码
	 * @return 功能按钮
	 */
	public List<AncdModel> getButtonList(@Param(value = "jsdm") String jsdm,@Param(value = "gnmkdm") String gnmkdm);

	/**
	 * @description	： 查询用户角色列表
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月6日 下午6:29:40
	 * @param yhm 用户名
	 * @return 角色列表
	 */
	public List<RoleModel> getJsxxListByZgh(String yhm);
	
	/**
	 * @description	： 查询所有的功能权限
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月7日 下午3:47:57
	 * @return
	 */
	public List<Menu> getAllGnqxList();
	
	/**
	 * @description	： 获取数据资源列表
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月7日 下午4:28:42
	 * @return
	 */
	public List<SjzyModel> getSjzyList();
	
	/**
	 * @description	： 查询角色数据权限
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月7日 下午4:43:41
	 * @param jsdm
	 * @return
	 */
	public List<String> getSjqxByJsdm(String jsdm);
	
	/**
	 * @description	： 查询用户角色
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月8日 下午3:38:44
	 * @param yhm
	 * @return
	 */
	public List<String> getJsdmByYhm(@Param("yhm") String yhm);
}
