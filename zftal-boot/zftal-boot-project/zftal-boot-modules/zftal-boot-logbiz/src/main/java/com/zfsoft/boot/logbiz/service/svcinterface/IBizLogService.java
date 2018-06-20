/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.logbiz.service.svcinterface;

import java.util.List;

import com.zfsoft.api.service.BaseService;
import com.zfsoft.boot.logbiz.dao.entities.BizLogModel;
import com.zfsoft.boot.logbiz.web.dto.BizLogDto;

public interface IBizLogService extends BaseService<BizLogModel> {
	
	public List<BizLogModel> getPagedSearchList(BizLogDto par);
	
}

