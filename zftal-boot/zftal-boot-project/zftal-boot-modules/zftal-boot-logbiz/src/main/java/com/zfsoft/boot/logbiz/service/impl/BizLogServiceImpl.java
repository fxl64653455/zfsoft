package com.zfsoft.boot.logbiz.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zfsoft.api.service.BaseServiceImpl;
import com.zfsoft.boot.logbiz.dao.daointerface.IBizLogDao;
import com.zfsoft.boot.logbiz.dao.entities.BizLogModel;
import com.zfsoft.boot.logbiz.service.svcinterface.IBizLogService;
import com.zfsoft.boot.logbiz.web.dto.BizLogDto;

@Service
public class BizLogServiceImpl extends BaseServiceImpl<BizLogModel, IBizLogDao> implements IBizLogService {

	@Override
	public List<BizLogModel> getPagedSearchList(BizLogDto par) {
		return getDao().getPagedSearchList(par);
	}

	 
}
