/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.zfsoft.api.service.BaseServiceImpl;
import com.zfsoft.boot.apimgr.dao.daointerface.IApiAppDao;
import com.zfsoft.boot.apimgr.dao.daointerface.IApiApplyChangeDao;
import com.zfsoft.boot.apimgr.dao.daointerface.IApiApplyDao;
import com.zfsoft.boot.apimgr.dao.daointerface.IApiAuditDao;
import com.zfsoft.boot.apimgr.dao.daointerface.IApiDeployDao;
import com.zfsoft.boot.apimgr.dao.daointerface.IApiInfoDao;
import com.zfsoft.boot.apimgr.dao.entities.ApiAppModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiApplyChangeModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiApplyModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiApplyModel.ApplyStatus;
import com.zfsoft.boot.apimgr.dao.entities.ApiAuditModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiDeployModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiInfoModel;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiAuditService;
import com.zfsoft.boot.apimgr.setup.event.ApiAuditEvent;
import com.zfsoft.boot.apimgr.setup.event.bo.ApiAudit;
import com.zfsoft.boot.apimgr.setup.event.bo.ApiAudit.AuditType;
import com.zfsoft.boot.apimgr.setup.oauth.OauthProperties;
import com.zfsoft.boot.apimgr.setup.oauth.RestTemplateUtils;
import com.zfsoft.boot.apimgr.setup.oauth.OauthProperties.AuthType;
import com.zfsoft.boot.apimgr.util.enums.AuditStatus;
import com.zfsoft.boot.apimgr.web.dto.Client;
import com.zfsoft.boot.apimgr.web.dto.Resources;

/**
 * 
 * @className	： ApiAuditServiceImpl
 * @description	： TODO(描述这个类的作用)
 * @author 		：樊新亮（1505）
 * @date		： 2017年10月16日 下午1:38:43
 * @version 	V1.0
 */
@Service
public class ApiAuditServiceImpl extends BaseServiceImpl<ApiAuditModel, IApiAuditDao> implements IApiAuditService{

	@Autowired
	private OauthProperties oauth;
	@Autowired
	private RestTemplateUtils rest;
	@Autowired
	private IApiApplyDao apiApplyDao;
	@Autowired
	private IApiAppDao apiAppDao;
	@Autowired
	private IApiInfoDao apiInfoDao;
	@Autowired
	private IApiDeployDao apiDeployDao;
	@Autowired
	private IApiApplyChangeDao changeDao;
	
	@Override
	@Transactional(rollbackFor = {Exception.class})
	public int audit(ApiAuditModel audit, String appId, String deployId) {
		ApiDeployModel apiDeploy = apiDeployDao.getModel(deployId);
		ApiInfoModel apiModel = apiInfoDao.getModel(apiDeploy.getApiId());
		if(apiDeploy == null || apiModel == null) {return 0;}
		ApiApplyModel apply = new ApiApplyModel();
		if(audit.getAuditStatus() == AuditStatus.yes) {
			apply.setApplyStatus(ApplyStatus.pass.ordinal() + "");
		} else {
			apply.setApplyStatus(ApplyStatus.unpass.ordinal() + "");
		}
		/**更新接口申请状态*/
		apply.setApplyId(audit.getAuditFor());apiApplyDao.update(apply);
		/**插入审核信息*/
		int a = getDao().insert(audit);
		/**如果未开启则不去更新权限*/
		if(!oauth.isEnabled()) {return a;}
		/**如果同意则更新接口权限*/
		ApiApplyModel apiApply = apiApplyDao.getModel(audit.getAuditFor());
		if(audit.getAuditStatus() == AuditStatus.yes) {
			Map<String, Object> client = new HashMap<>();
			if(oauth.getAuthType() == AuthType.app) {
				ApiAppModel app = apiAppDao.getModel(appId);
				client.put("clientId", app.getAppKey());
			}
			client.put("clientType", "api");
			List<Map<String, Object>> resources = new ArrayList<Map<String,Object>>();
			Map<String, Object> resource = new HashMap<>();
			resource.put("resourceName", apiModel.getName());resource.put("requestUrl", apiDeploy.getAddr());
			resource.put("resourceType", "api");resource.put("description", apiModel.getInfo());
			resource.put("invokeIp", apiApply.getInvokeIp());resource.put("invokeFrequency", apiApply.getInvokeFrequency());
			resources.add(resource);
			client.put("resources", resources);
			String str = rest.post(oauth.getSaveClientUrl(), client, "json");
			JSONObject res = JSONObject.parseObject(str);
			if(oauth.getAuthType() == AuthType.api) {
				JSONObject obj = res.getJSONObject("data");
				apiApply.setApiKey(obj.getString("clientId"));apiApply.setApiSecret(obj.getString("clientSecret"));
				apiApplyDao.update(apiApply);
			}
			if(res.getInteger("code") != 200) {
				apply.setApplyStatus(ApplyStatus.wait.ordinal() + "");
				apiApplyDao.update(apply);
				getDao().delete(audit);
				return 0;
			}
		}
		/**审核事件*/
		ApiAudit obj = new ApiAudit();
		try {
			PropertyUtils.copyProperties(obj, audit);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		obj.setInvokeIp(apiApply.getInvokeIp());obj.setInvokeFrequency(apiApply.getInvokeFrequency());
		obj.setDeployId(deployId);obj.setApiName(apiModel.getName());obj.setApiKey(apiApply.getApiKey());
		obj.setAuditType(AuditType.apply);
		getContext().publishEvent(new ApiAuditEvent(obj));
		return a;
	}

	@Override
	@Transactional(rollbackFor = {Exception.class})
	public int changeAudit(ApiAuditModel audit) {
		ApiApplyChangeModel changeModel = new ApiApplyChangeModel();
		changeModel.setApplyChangeId(audit.getAuditFor());
		changeModel = changeDao.getModel(changeModel);
		if(audit.getAuditStatus() == AuditStatus.yes) {
			changeModel.setAuditStatus(ApplyStatus.pass.ordinal() + "");
		} else {
			changeModel.setAuditStatus(ApplyStatus.unpass.ordinal() + "");
		}
		/**更新变更申请状态*/
		changeDao.update(changeModel);
		/**插入审核信息*/
		int a = getDao().insert(audit);
		/**如果未开启则不去更新权限*/
		if(!oauth.isEnabled()) {return a;}
		/**如果同意则更新接口权限*/
		if(audit.getAuditStatus() == AuditStatus.yes) {
			if(oauth.getAuthType() == AuthType.api) {
				/**认证类型为API*/
				String url = oauth.getFindByClientIdUrl().replaceAll("\\{relation\\}", "true").replaceAll("\\{clientId\\}", changeModel.getApiKey());
				JSONObject res = JSONObject.parseObject(rest.get(url, null, "json"));
				Client c = res.getObject("data", Client.class);
				Map<String, Object> client = new HashMap<>();
				client.put("clientId", changeModel.getApiKey());client.put("clientType", "api");
				List<Map<String, Object>> resources = new ArrayList<Map<String,Object>>();
				Map<String, Object> resource = new HashMap<>();
				Map<String, String> cmap = changeModel.toMap();
				resource.put("resourceId", c.getResources().stream().findFirst().get().getResourceId());
				resource.put("invokeIp", cmap.get("targetInvokeIp"));resource.put("invokeFrequency", cmap.get("targetInvokeFrequency"));
				resources.add(resource);
				client.put("resources", resources);
				JSONObject robj = JSONObject.parseObject(rest.post(oauth.getSaveClientUrl(), client, "json"));
				if(robj.getInteger("code") != 200) {
					changeModel.setAuditStatus(ApplyStatus.wait.ordinal() + "");
					changeDao.update(changeModel);
					getDao().delete(audit);
					return 0;
				}
			}else if(oauth.getAuthType() == AuthType.app) {
				/**认证类型为APP*/
				ApiApplyModel apply = apiApplyDao.getModel(changeModel.getApplyId());
				ApiAppModel app = apiAppDao.getModel(apply.getApplyTo());
				ApiDeployModel deploy = apiDeployDao.getModel(apply.getApplyFor());
				String url = oauth.getFindByClientIdUrl().replaceAll("\\{relation\\}", "true").replaceAll("\\{clientId\\}", app.getAppKey());
				JSONObject res = JSONObject.parseObject(rest.get(url, null, "json"));
				Client c = res.getObject("data", Client.class);
				for (Resources r : c.getResources()) {
					if(r.getRequestUrl().equals(deploy.getAddr())) {
						Map<String, String> cmap = changeModel.toMap();
						Map<String, Object> client = new HashMap<>();
						client.put("clientId", app.getAppKey());client.put("clientType", "api");
						List<Map<String, Object>> resources = new ArrayList<Map<String,Object>>();
						Map<String, Object> resource = new HashMap<>();
						resource.put("resourceId", r.getResourceId());
						resource.put("invokeIp", cmap.get("targetInvokeIp"));resource.put("invokeFrequency", cmap.get("targetInvokeFrequency"));
						resources.add(resource);
						client.put("resources", resources);
						JSONObject robj = JSONObject.parseObject(rest.post(oauth.getSaveClientUrl(), client, "json"));
						if(robj.getInteger("code") != 200) {
							changeModel.setAuditStatus(ApplyStatus.wait.ordinal() + "");
							changeDao.update(changeModel);
							getDao().delete(audit);
							return 0;
						}
						break;
					}
				}
			}
		}
		/**审核事件*/
		ApiAudit obj = new ApiAudit();
		try {
			PropertyUtils.copyProperties(obj, audit);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		Map<String, String> cmap = changeModel.toMap();
		obj.setInvokeIp(cmap.get("targetInvokeIp"));obj.setInvokeFrequency(Integer.parseInt(cmap.get("targetInvokeFrequency")));
		obj.setApiName(changeModel.getApiName());obj.setApiKey(changeModel.getApiKey());
		obj.setAuditType(AuditType.change);
		getContext().publishEvent(new ApiAuditEvent(obj));
		return a;
	}

}
