package com.zfsoft.boot.authz.dao.daointerface;

import java.awt.Menu;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zfsoft.api.dao.BaseDao;
import com.zfsoft.boot.authz.dao.entities.AncdModel;
import com.zfsoft.boot.authz.dao.entities.RoleModel;
import com.zfsoft.boot.authz.dao.entities.UserModel;

/**
 * 
 * <p>
 *   <h3>zftal框架<h3>
 *   说明：角色管理DAO
 * <p>
 * @author <a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a>
 * @version 2017年3月15日下午3:30:12
 */
@Mapper
public interface IAuthzRolePermsDao extends BaseDao<RoleModel>{
	
	/**
	 * 
	 * <p>方法说明：查询角色功能权限<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2017年3月15日下午4:04:24<p>
	 * @param jsdm 角色代码
	 * @return 菜单列表
	 */
	public List<Menu> getGnqxByJsdm(String jsdm);
	
	
	
	/**
	 * 
	 * <p>方法说明：二级角色功能菜单<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2017年3月27日下午5:36:51<p>
	 * @param jsdm 角色代码
	 * @param zgh 职工号
	 * @return 菜单列表
	 */
	public List<Menu> getGnqxByEjjs(@Param(value = "jsdm")String jsdm,@Param(value = "sxz") String zgh);
	
	
	
	/**
	 * 
	 * <p>方法说明：查询所有的功能权限<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2017年3月24日上午10:05:22<p>
	 * @return 功能权限列表
	 */
	public List<Menu> getAllGnqxList();
	
	
	
	/**
	 * 
	 * <p>方法说明：查询角色功能按钮列表<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2017年3月15日下午5:52:43<p>
	 * @param jsdm 角色代码
	 * @param gnmkdm 功能模块代码
	 * @return 功能按钮
	 */
	public List<AncdModel> getButtonList(@Param(value = "jsdm") String jsdm,@Param(value = "gnmkdm") String gnmkdm);
	
	
	
	/**
	 * 
	 * <p>方法说明：二级授权的按钮列表<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2017年3月28日上午9:42:45<p>
	 * @param jsdm 角色代码
	 * @param gnmkdm 功能模块代码
	 * @param zgh 工号
	 * @return 功能按钮
	 */
	public List<AncdModel> getEjqxButtonList(@Param(value = "jsdm") String jsdm,@Param(value = "gnmkdm") String gnmkdm, @Param(value = "sxz") String zgh);
	
	
	
	/**
	 * 
	 * <p>方法说明：查询用户角色列表<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2017年3月16日上午8:53:56<p>
	 * @param zgh 职工号
	 * @return 角色列表
	 */
	public List<RoleModel> getJsxxListByZgh(String zgh);
	
	
	
	/**
	 * 
	 * <p>方法说明：根据ID删除角色（系统内置角色不受影响）<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2017年3月16日上午9:05:14<p>
	 * @param jsdm 角色代码
	 * @return 影响数据条数
	 */
	public int deleteJsxx(String jsdm);
	
	
	
	/**
	 * 
	 * <p>方法说明：删除角色用户<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2017年3月16日上午9:09:49<p>
	 * @param jsdm 角色代码
	 * @return 影响数据条数
	 */
	public int deleteJsyh(String jsdm);
	
	
	
	/**
	 * 
	 * <p>方法说明：删除角色功能权限<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2017年3月16日上午9:08:17<p>
	 * @param jsdm 角色代码
	 * @return 影响数据条数
	 */
	public int deleteJsgn(String jsdm);
	
	
	
	
	
	/**
	 * 
	 * <p>方法说明：角色未分配用户列表<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2017年3月16日下午4:42:13<p>
	 * @param model
	 * @return
	 */
	//@DataRange(info = "{TEACHER_SZBM:'JGDM'}", dataIds = { "teacher" } )
    public List<UserModel> getPagedWfpyhList(RoleModel model);
    
    
    
    /**
     * 
     * <p>方法说明：角色已分配用户列表<p>
     * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
     * <p>时间：2017年3月16日下午4:42:33<p>
     * @param model
     * @return
     */
	//@DataRange(info = "{TEACHER_SZBM:'JGDM'}", dataIds = { "teacher" } )
    public List<UserModel> getPagedYfpyhList(RoleModel model);
	
	
    
    /**
     * 
     * <p>方法说明：保存用户分配<p>
     * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
     * <p>时间：2017年3月17日上午10:05:28<p>
     * @param jsdm 角色代码
     * @param zgh 职工号数组
     * @return int
     */
	public  int insertYhfp(@Param(value = "jsdm") String jsdm , @Param(value = "zghs") String[] zghs);
    
	
	
	/**
	 * 
	 * <p>方法说明：取消用户分配<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2017年3月17日上午10:28:59<p>
	 * @param jsdm 角色代码
     * @param zgh 职工号数组
     * @return int
	 */
	public  int deleteYhfp(@Param(value = "jsdm") String jsdm , @Param(value = "zghs") String[] zghs);
	
	
	/**
	 * 
	 * <p>方法说明：保存角色功能权限<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2017年3月23日下午4:34:32<p>
	 * @param jsdm 角色代码
	 * @param gnczids 功能操作代码
	 * @return int
	 */
	public int insertGnqx(@Param(value = "jsdm") String jsdm,@Param(value = "gnczids") String[] gnczids);
	
	
	/**
	 * 
	 * <p>方法说明：删除角色功能权限<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2017年3月23日下午4:35:14<p>
	 * @param jsdm 角色代码
	 * @return int
	 */
	public int deleteGnqx(String jsdm);
	/*
	
	
	*//**
	 * 
	 * <p>方法说明：二级授权用户信息<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2017年3月27日上午9:55:52<p>
	 * @param model jsglModel
	 * @return list
	 *//*
	//@DataRange(info = "{TEACHER_SZBM:'JGDM'}", dataIds = { "teacher" } )
	public List<YhglModel> getPagedEjsqList(EjqxModel model);
	
	
	
	*//**
	 * 
	 * <p>方法说明：删除二级权限<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2017年3月27日下午3:14:35<p>
	 * @param ejqxModel 二级授权信息
	 * @return int
	 *//*
	public int deleteEjqx(EjqxModel ejqxModel);
	
	
	
	*//**
	 * 
	 * <p>方法说明：保存二级授权<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2017年3月27日下午3:26:12<p>
	 * @param ejqxModel 二级授权信息
	 * @return int
	 *//*
	public int insertEjqx(EjqxModel ejqxModel);
	
	
	
	*//**
	 * 
	 * <p>方法说明：二级授权详细<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2017年3月27日下午3:59:09<p>
	 * @param ejqxModel  二级授权信息
	 * @return List
	 *//*
	public List<AncdModel> getEjqxList(EjqxModel ejqxModel);*/
	
	
	
	/**
	 * 
	 * <p>方法说明：查询二级授权角色<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2017年3月27日下午4:58:06<p>
	 * @param zgh 职工号
	 * @return list
	 */
	public List<RoleModel> getEjsqJsxxList(String zgh);
	
	
	
	/**
	 * 
	 * <p>方法说明：二级角色权限<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2017年3月27日下午5:11:35<p>
	 * @param jsdm 角色代码
	 * @param zgh 职工号
	 * @return set
	 */
	public Set<String> getUserEjqxList(@Param(value = "jsdm") String jsdm ,@Param(value = "sxz") String zgh);


	
	/**
	 * 
	 * <p>方法说明：保存数据权限<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2017年3月28日下午3:17:13<p>
	 * @param jsdm 角色代码
	 * @param gzids 资源IDs
	 * @return int
	 */
	public int insertSjqx(@Param(value = "jsdm")String jsdm , @Param(value = "gzids") String[] gzids);

	

	/**
	 * 
	 * <p>方法说明：删除角色数据权限<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2017年3月28日下午3:17:41<p>
	 * @param jsdm 角色代码
	 * @return int
	 */
	public int deleteSjqx(String jsdm);



	/**
	 * 
	 * <p>方法说明：查询角色数据权限<p>
	 * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	 * <p>时间：2017年3月28日下午3:22:58<p>
	 * @param jsdm 角色代码
	 * @return list
	 */
	public List<String> getSjqxByJsdm(String jsdm);
	
	
	public Set<String> getRoleList(@Param(value="yhm")String yhm);
	
	/**
	 * 根据[角色功能模块操作表]数据查询角色具备的权限信息
	 * @param jsdm 角色代码
	 * @return 角色具备的权限信息
	 */
	public Set<String> getPermissions(@Param(value="jsdm")String jsdm);

}
