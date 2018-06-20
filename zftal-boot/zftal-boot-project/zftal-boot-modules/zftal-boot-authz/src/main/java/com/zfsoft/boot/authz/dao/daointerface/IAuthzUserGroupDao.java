package com.zfsoft.boot.authz.dao.daointerface;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zfsoft.api.dao.BaseDao;
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
public interface IAuthzUserGroupDao extends BaseDao<UserModel>{
	
	/**
	 * 
	 * @description	： 用户组内新增用户
	 * @author 		： 万大龙（743）
	 * @date 		：2018年4月13日 下午6:01:17
	 * @param yhz 用户组Id
	 * @param yhmList 用户名集合
	 * @return 变更记录数
	 */
	public int zjYhxx(@Param(value = "yhz") String yhz, @Param(value = "yhmList") List<String> yhmList);
	
	/**
	 * @description	： 删除用户组内用户
	 * @author 		： 万大龙（743）
	 * @date 		：2018年4月13日 下午6:00:54
	 * @param yhz 用户组Id
	 * @param yhmList 用户名集合
	 * @return 变更记录数
	 */
	public int scYhxx(@Param(value = "yhz") String yhz, @Param(value = "yhmList") List<String> yhmList);
	
	/**
	 * 
	 * @description	： 设置用户组权限
	 * @author 		： 万大龙（743）
	 * @date 		：2018年4月13日 下午4:28:43
	 * @param yhz 用户组Id
	 * @param gnczIds 功能操作ID集合
	 * @return 数据成功变更数
	 */
	public int setPerms(@Param(value = "yhz") String yhz,  @Param(value = "gnczIds") List<String> gnczIds);
	
	/**
	 * @description	： 删除用户组权限
	 * @author 		： 万大龙（743）
	 * @date 		：2018年4月13日 下午4:24:19
	 * @param yhz 用户组Id
	 * @param gnczIds 功能操作ID集合
	 * @return 数据成功变更数
	 */
	public int deletePerms(@Param(value = "yhz") String yhz, @Param(value = "gnczIds") List<String> gnczIds);
	
}
