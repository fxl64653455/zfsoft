package com.zfsoft.boot.init.service.svcinterface;

import com.zfsoft.boot.init.dao.entities.ValidationModel;

/**
 * 
 *@类名称:CommonValidationService.java
 *@类描述：公共验证方法service接口
 *@创建人：zhangxiaobin
 *@创建时间：2014-6-17 下午08:28:40
 *@版本号:v1.0
 */
public interface IValidationService {
	
	
	/**
	 * 
	 *@描述：唯一约束验证
	 *@创建人:zhangxiaobin
	 *@创建时间:2014-6-17下午08:29:39
	 *@修改人:
	 *@修改时间:
	 *@修改描述:
	 *@param map
	 *@return
	 */
	public boolean unique(ValidationModel model) ;
}
