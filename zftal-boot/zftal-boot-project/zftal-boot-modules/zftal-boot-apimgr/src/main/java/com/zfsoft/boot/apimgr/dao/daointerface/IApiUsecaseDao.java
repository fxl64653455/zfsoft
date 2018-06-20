package com.zfsoft.boot.apimgr.dao.daointerface;


import org.apache.ibatis.annotations.Mapper;

import com.zfsoft.api.dao.BaseDao;
import com.zfsoft.boot.apimgr.dao.entities.ApiUsecaseModel;

@Mapper
public interface IApiUsecaseDao extends BaseDao<ApiUsecaseModel> {
	
	
	
}
