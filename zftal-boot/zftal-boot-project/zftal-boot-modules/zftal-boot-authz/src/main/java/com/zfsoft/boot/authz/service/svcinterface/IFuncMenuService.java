package com.zfsoft.boot.authz.service.svcinterface;

import java.util.List;

import com.zfsoft.api.service.BaseService;
import com.zfsoft.basemodel.BaseMap;
import com.zfsoft.boot.authz.dao.entities.AncdModel;

/**
 * 
 *@类名称	: IFuncMenuService.java
 *@类描述	：功能菜单Service接口
 *@创建人	：wandalong
 *@创建时间	：2017年7月17日 下午4:00:54
 *@修改人	：
 *@修改时间	：
 *@版本号	:v1.0
 */
public interface IFuncMenuService extends BaseService<AncdModel>{

	public List<BaseMap> getTopNavMenuList(String yhm, String jsdm, String localeKey);
	
	public List<BaseMap> getChildNavMenuList(String yhm, String jsdm,String parentGnmkdm, String localeKey);
	
	public List<BaseMap> getChildNavMenuTreeList(String yhm, String jsdm,String parentGnmkdm, String localeKey);
	
	public List<BaseMap> getNavMenuTreeList(String yhm, String jsdm, String localeKey);
	
}
