package com.zfsoft.boot.authz.dao.daointerface;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zfsoft.api.dao.BaseDao;
import com.zfsoft.basemodel.BaseMap;
import com.zfsoft.boot.authz.dao.entities.AncdModel;

@Mapper
public interface IFuncMenuDao extends BaseDao<AncdModel> {

	public List<BaseMap> getTopNavMenuList(@Param(value = "yhm") String yhm, @Param(value = "jsdm") String jsdm,
			@Param(value = "localeKey") String localeKey);

	public List<BaseMap> getChildNavMenuList(@Param(value = "yhm") String yhm, @Param(value = "jsdm") String jsdm,
			@Param(value = "gnmkdm") String parentGnmkdm, @Param(value = "localeKey") String localeKey);
	
	public List<BaseMap> getNavMenuTreeList(@Param(value = "yhm") String yhm, @Param(value = "jsdm") String jsdm,
			@Param(value = "gnmkdm") String parentGnmkdm, @Param(value = "localeKey") String localeKey);

}
