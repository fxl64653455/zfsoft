package com.zfsoft.boot.logbiz.dao.daointerface;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zfsoft.api.dao.BaseDao;
import com.zfsoft.boot.logbiz.dao.entities.BizLogModel;
import com.zfsoft.boot.logbiz.web.dto.BizLogDto;

@Mapper
public interface IBizLogDao extends BaseDao<BizLogModel> {
	
	public List<BizLogModel> getPagedSearchList(BizLogDto par);
	
}
