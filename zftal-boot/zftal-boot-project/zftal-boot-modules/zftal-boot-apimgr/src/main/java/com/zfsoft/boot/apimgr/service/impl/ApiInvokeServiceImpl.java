/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.zfsoft.api.service.BaseServiceImpl;
import com.zfsoft.basicutils.StringUtils;
import com.zfsoft.boot.apimgr.dao.daointerface.IApiInvokeDao;
import com.zfsoft.boot.apimgr.dao.entities.ApiInvokeModel;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiInvokeService;
import com.zfsoft.boot.apimgr.web.dto.ApiInvokeDto;
import com.zfsoft.boot.apimgr.web.dto.ApiParam;

@Service
public class ApiInvokeServiceImpl extends BaseServiceImpl<ApiInvokeModel, IApiInvokeDao> implements IApiInvokeService{

	@Override
	public int save(ApiInvokeDto par) {
		int a = 0;
		ApiInvokeModel obj = new ApiInvokeModel();
		obj.setApiId(par.getApiId());obj.setInvokeDeployId(par.getInvokeDeployId());
		JSONArray arr = new JSONArray();
		for(ApiParam p : par.getApiParams()) {
			if(StringUtils.isNotEmpty(p.getName())) {
				arr.add(p);
			}
		}
		obj.setParamRelation(arr.toJSONString());
		if(par.isInvokeEnable()) {
			if(getDao().exists(obj) > 0) {
				a = getDao().update(obj);
			}else {
				a = getDao().insert(obj);
			}
		}else{
			a = getDao().delete(obj);
		}
		return a;
	}
	
}
