package com.zfsoft.boot.apimgr.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zfsoft.api.service.BaseServiceImpl;
import com.zfsoft.basemodel.QueryModel;
import com.zfsoft.boot.apimgr.dao.daointerface.IApiDeployDao;
import com.zfsoft.boot.apimgr.dao.daointerface.IApiInfoDao;
import com.zfsoft.boot.apimgr.dao.daointerface.IApiInvokeDao;
import com.zfsoft.boot.apimgr.dao.entities.ApiDeployModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiInfoModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiInvokeModel;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiInfoService;
import com.zfsoft.boot.apimgr.web.exceptions.DeleteApiInfoException;

@Service
public class ApiInfoServiceImpl extends BaseServiceImpl<ApiInfoModel, IApiInfoDao> implements IApiInfoService {

	@Autowired
	private IApiInvokeDao invokeDao;
	@Autowired
	private IApiDeployDao deployDao;
	
	@Override
	public int insertInfo(ApiInfoModel infomodel) {
		return dao.insertInfo(infomodel);
	}

	@Override
	@Deprecated
	public QueryModel getPagedSearchList(ApiInfoModel apiInfo) {
		List<ApiInfoModel> items = dao.getPagedSearchList(apiInfo);
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		for (ApiInfoModel apiApp : items) {
			mapList.add(apiApp.toMap());
		}
		QueryModel queryModel = apiInfo.getQueryModel();
		queryModel.setItems(mapList);
		return queryModel;
	}

	@Override
	public List<Map<String, String>> getDependencies(List<String> list) {
		return getDao().getDependencies(list);
	}

	@Override
	@Transactional(rollbackFor = {Exception.class})
	public void deleteApiInfo(Set<String> ids) throws DeleteApiInfoException{
		for (String id : ids) {
			ApiInvokeModel invokeModel = invokeDao.getModel(id);
			if(invokeModel != null) {
				throw new DeleteApiInfoException("I00010");
			}
			/**删除发布记录*/
			List<ApiDeployModel> deploys = deployDao.getModelListByApiInfoId(id);
			for (ApiDeployModel deploy : deploys) {
				if("1".equals(deploy.getStatus())) {
					throw new DeleteApiInfoException("I00011");
				}
				deployDao.delete(deploy);
			}
			/**删除接口*/
			getDao().delete(id);
		}
	}
	
}
