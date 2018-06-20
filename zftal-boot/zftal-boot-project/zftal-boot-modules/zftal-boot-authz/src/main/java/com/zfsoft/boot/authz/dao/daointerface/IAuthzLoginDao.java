package com.zfsoft.boot.authz.dao.daointerface;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zfsoft.api.dao.BaseDao;
import com.zfsoft.boot.authz.dao.entities.LoginModel;

/**
 * 
 * @className	： IAuthzLoginDao
 * @description	： 登录查询Dao
 * @author 		：万大龙（743）
 * @date		： 2017年11月8日 下午3:24:52
 * @version 	V1.0
 */
@Mapper
public interface IAuthzLoginDao extends BaseDao<LoginModel>{

	/**
	 * 
	 * @description	： 根据用户ID和密码查询用户可否登录，角色数量等信息
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月8日 下午2:07:29
	 * @param yhm	: 用户名
	 * @param mm	: 密码，可不填
	 * @return
	 */
	public Map<String, String> getAccountStatus(@Param(value="yhm")String yhm,@Param(value="mm")String mm);
	
	/***
	 * 
	 * @description	： 根据用户ID和密码查询用户信息
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月8日 下午2:07:12
	 * @param yhm
	 * @param mm
	 * @return
	 */
	public LoginModel getAccount(@Param(value="yhm")String yhm,@Param(value="mm") String mm);
	
	/***
	 * 
	 * @description	： 根据用户ID无密码查询用户信息；用于单点登录
	 * @author 		： 万大龙（743）
	 * @date 		：2017年11月8日 下午2:06:53
	 * @param yhm
	 * @return
	 */
	public LoginModel getAccountWithoutPwd(@Param(value="yhm") String yhm);
	
}
