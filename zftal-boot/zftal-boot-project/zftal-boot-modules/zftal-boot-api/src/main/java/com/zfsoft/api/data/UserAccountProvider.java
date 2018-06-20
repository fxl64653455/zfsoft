package com.zfsoft.api.data;

import java.util.Map;

import com.zfsoft.basemodel.BaseMap;

public interface UserAccountProvider {

	public Map<String, String> getAccountStatus(String username);
	
	/**
	 * 
	 *@描述		：通过页面绑定的参数查询用户信息
	 *@创建人		: wandalong
	 *@创建时间	: 2017年4月13日下午2:14:21
	 *@param data
	 *@return
	 *@修改人		: 
	 *@修改时间	: 
	 *@修改描述	:
	 */
	public BaseMap getUserAccount(Map<String, Object> map);
	
	public int updateAccount(Map<String, Object> map);
	
}
