/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.handler;

import com.zfsoft.boot.apimgr.dao.entities.ApiDeployModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiInfoModel;
import com.zfsoft.boot.apimgr.web.dto.ApiDeployDto;
import com.zfsoft.boot.apimgr.web.dto.ApiInfoBizDto;
import com.zfsoft.boot.apimgr.web.dto.ApiInfoDataDto;

public interface ApiOutputHandler {

	ApiInfoBizDto outputBiz(ApiInfoModel rtModel);
	
	ApiInfoDataDto outputData(ApiInfoModel rtModel);
	
	ApiDeployDto output(ApiDeployModel rtModel);
	
}
