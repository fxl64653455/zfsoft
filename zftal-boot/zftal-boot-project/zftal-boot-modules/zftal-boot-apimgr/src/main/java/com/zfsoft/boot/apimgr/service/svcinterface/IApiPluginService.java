/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.service.svcinterface;

import java.util.List;

import com.zfsoft.api.service.BaseService;
import com.zfsoft.basemodel.PairModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiPluginModel;

public interface IApiPluginService extends BaseService<ApiPluginModel> {
	
	public List<PairModel> getPlugins();
	
	public List<PairModel> getAuthzExtensions(String pluginId);
	
	public List<PairModel> getApiExtensions(String pluginId);
	
}
