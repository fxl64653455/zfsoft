/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.service.svcinterface;

import java.util.List;
import java.util.Map;

import com.zfsoft.api.service.BaseService;
import com.zfsoft.boot.apimgr.dao.entities.ApiDeployModel;
import com.zfsoft.boot.apimgr.web.dto.ApiDeployDto;

public interface IApiDataService extends BaseService<ApiDeployModel> {
	
	public Object doApiInsert(ApiDeployDto dto, Map<String, Object> paramMap) throws Exception;
	
	public Object doApiDelete(ApiDeployDto dto, Map<String, Object> paramMap) throws Exception;
	
	public Object doApiUpdate(ApiDeployDto dto, Map<String, Object> paramMap) throws Exception;
	
	public List<Map<String,String>> getApiDataList(ApiDeployDto dto, Map<String, Object> paramMap) throws Exception;
	
}
