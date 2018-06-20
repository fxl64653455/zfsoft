/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.zfsoft.api.service.BaseServiceImpl;
import com.zfsoft.boot.apimgr.dao.daointerface.IApiAppDao;
import com.zfsoft.boot.apimgr.dao.daointerface.IApiApplyChangeDao;
import com.zfsoft.boot.apimgr.dao.daointerface.IApiApplyDao;
import com.zfsoft.boot.apimgr.dao.daointerface.IApiAuditDao;
import com.zfsoft.boot.apimgr.dao.daointerface.IApiMetircDao;
import com.zfsoft.boot.apimgr.dao.entities.ApiAppModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiApplyChangeModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiApplyModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiAuditModel;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiAppService;
import com.zfsoft.boot.apimgr.setup.oauth.OauthProperties;
import com.zfsoft.boot.apimgr.setup.oauth.RestTemplateUtils;
import com.zfsoft.boot.apimgr.setup.oauth.OauthProperties.AuthType;

/**
 * 
 * @className	： ApiAppServiceImpl
 * @description	： TODO(描述这个类的作用)
 * @author 		：樊新亮（1505）
 * @date		： 2017年10月16日 下午1:34:33
 * @version 	V1.0
 */
@Service
public class ApiAppServiceImpl extends BaseServiceImpl<ApiAppModel, IApiAppDao> implements IApiAppService{

	@Autowired
	private OauthProperties oauth;
	@Autowired
	private RestTemplateUtils rest;
	@Autowired
	private IApiApplyDao applyDao;
	@Autowired
	private IApiAuditDao auditDao;
	@Autowired
	private IApiMetircDao apiMetircDao;
	@Autowired
	private IApiApplyChangeDao apiApplyChangeDao;
	
	@Override
	@Transactional(rollbackFor = {Exception.class})
	public ApiAppModel saveApp(ApiAppModel app) {

		if(app.getAppId() != null && !app.getAppId().equals("")) {
			getDao().update(app);
		} else {
			if(oauth.isEnabled() && oauth.getAuthType() == AuthType.app) {
				Map<String, Object> params = new HashMap<>();
				params.put("clientType", "api");params.put("clientName", app.getAppName());
				JSONObject res = JSONObject.parseObject(rest.post(oauth.getSaveClientUrl(), params, "json"));
				if(res.getInteger("code") == 200) {
					JSONObject obj = res.getJSONObject("data");
					app.setAppKey(obj.getString("clientId"));app.setAppSecret(obj.getString("clientSecret"));
					try {
						getDao().insert(app);
					} catch (Exception e) {
						String deleteUrl = oauth.getDeleteClientUrl().replaceAll("\\{clientId\\}", app.getAppKey()).replaceAll("\\{relation\\}", "true");
						rest.post(deleteUrl, null, "json");
					}
				}
			} else {
				getDao().insert(app);
			}
		}
		
		return app;
	}

	@Override
	public List<ApiAppModel> getPagedApplyTableData(ApiAppModel app) {
		
		return getDao().getPagedApplyTableData(app);
	}

	@Override
	@Transactional(rollbackFor = {Exception.class})
	public int deleteApp(String appId) {
		
		/**删除相关数据*/
		ApiAppModel app = getModel(appId);
		ApiApplyModel apply = new ApiApplyModel();apply.setApplyTo(app.getAppId());
		List<ApiApplyModel> applys = applyDao.getModelList(apply);
		List<ApiAuditModel> allAudits = new ArrayList<>();
		List<ApiApplyChangeModel> allApplyChanges = new ArrayList<>();
		int a = 0;
		for (ApiApplyModel apiApply : applys) {
			ApiAuditModel audit = new ApiAuditModel();audit.setAuditFor(apiApply.getApplyId());
			for (ApiAuditModel apiAudit : auditDao.getModelList(audit)) {
				allAudits.add(apiAudit);
				/**删除接口申请审核记录*/
				a += auditDao.delete(apiAudit);
			}
			for(ApiApplyChangeModel applyChange : apiApplyChangeDao.getApplyChangeList(apiApply.getApplyId())) {
				allApplyChanges.add(applyChange);
				ApiAuditModel auditChange = new ApiAuditModel();audit.setAuditFor(applyChange.getApplyChangeId());
				for (ApiAuditModel apiChangeAudit : auditDao.getModelList(auditChange)) {
					allAudits.add(apiChangeAudit);
					/**删除接口变更申请审核记录*/
					a += auditDao.delete(apiChangeAudit);
				}
				/**删除接口变更申请记录*/
				a += apiApplyChangeDao.delete(applyChange);
			}
			/**删除接口申请记录*/
			a += applyDao.delete(apiApply);
		}
		/**删除相关的调用记录*/
		a += apiMetircDao.deleteByAppKey(app.getAppKey());
		/**删除应用*/
		a += getDao().delete(app);
		
		/**如果未开启则不去同步*/
		if(!oauth.isEnabled()) {return a;}
		
		/**同步认证服务器*/
		String deleteUrl = oauth.getDeleteClientUrl().replaceAll("\\{clientId\\}", app.getAppKey()).replaceAll("\\{relation\\}", "true");
		String str = rest.post(deleteUrl, null, "json");
		JSONObject res = JSONObject.parseObject(str);
		
		if(res.getInteger("code") != 200) {
			/**还原删除操作*/
			for (ApiApplyModel apiApply : applys) {
				applyDao.insert(apiApply);
			}
			for (ApiApplyChangeModel applyChange : allApplyChanges) {
				apiApplyChangeDao.insert(applyChange);
			}
			for (ApiAuditModel apiAudit : allAudits) {
				auditDao.insert(apiAudit);
			}
			getDao().insert(app);a = 0;
		}
		return a;
	}

	@Override
	public int deleteAppMenu() {
		return getDao().deleteMenu("N060501");
	}

	@Override
	public int addAppMenu() {
		if(getDao().hasMenu("N060501") <= 0) {
			return getDao().addAppMenu();
		}
		return 0;
	}

}
