package com.zfsoft.boot.authz.service.svcinterface;


import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.zfsoft.api.service.BaseService;
import com.zfsoft.boot.authz.dao.entities.AncdModel;
import com.zfsoft.boot.authz.dao.entities.Menu;
import com.zfsoft.boot.authz.dao.entities.RoleModel;
import com.zfsoft.boot.authz.dao.entities.SjzyModel;

/**
 * 
 * @className	： IAuthzRoleService
 * @description	： 角色管理Service
 * @author 		：万大龙（743）
 * @date		： 2018年4月16日 下午3:40:05
 * @version 	V1.0
 */
public interface IAuthzRoleService extends BaseService<RoleModel>{

	
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
	public int scJsyhxx(@Param(value = "jsdm") String jsdm , @Param(value = "yhms") String[] yhms);
	
	/**
	 * @description	： 给角色分配功能权限
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月8日 下午12:16:52
	 * @param jsdm
	 * @param gnczids
	 * @return
	 */
	public boolean saveGnqx(@Param(value = "jsdm") String jsdm,@Param(value = "gnczids") String[] gnczids);
	
	/**
	 * @description	： 根据[角色功能模块操作表]数据查询角色具备的权限信息
	 * @author 		： 万大龙（743）
	 * @date 		：2018年4月16日 下午3:24:28
	 * @param jsdm 角色代码
	 * @return 角色具备的权限信息
	 */
	public Set<String> getPermissions(@Param(value="jsdm")String jsdm);

	/**
	 * 
	 * <p>方法说明：查询角色功能按钮列表<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2017年3月15日下午5:52:43<p>
	 * @param jsdm 角色代码
	 * @param zgh 职工号
	 * @param gnmkdm 功能模块代码
	 * @return 功能菜单按钮
	 */
	public List<AncdModel> getButtonList(String jsdm,String zgh ,String gnmkdm);

	/**
	 * @description	： 根据用户名查询用户角色
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月6日 下午6:24:30
	 * @param yhm 用户名
	 * @return 角色列表
	 */
	public List<RoleModel> getJsxxListByZgh(String yhm);
	
	/**
	 * @description	： 查询所有的功能权限
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月7日 下午3:44:07
	 * @return
	 */
	public List<Menu> getAllGnqxList();
	
	/**
	 * @description	： 查询角色按钮列表
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月7日 下午3:55:51
	 * @param jsdm
	 * @return
	 */
	public List<AncdModel> getButtonList(String jsdm);
	
	/**
	 * @description	： 获取数据资源列表
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月7日 下午4:27:31
	 * @return
	 */
	public List<SjzyModel> getSjzyList();
	
	/**
	 * @description	： 查询角色数据权限
	 * @author 		： 樊新亮（1505）
	 * @date 		：2018年6月7日 下午4:41:04
	 * @param jsdm
	 * @return
	 */
	public List<String> getSjqxByJsdm(String jsdm);
}
