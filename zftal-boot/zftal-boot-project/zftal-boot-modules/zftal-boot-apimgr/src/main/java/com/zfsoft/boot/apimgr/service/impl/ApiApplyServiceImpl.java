/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zfsoft.api.service.BaseServiceImpl;
import com.zfsoft.boot.apimgr.dao.daointerface.IApiApplyDao;
import com.zfsoft.boot.apimgr.dao.entities.ApiApplyModel;
import com.zfsoft.boot.apimgr.dao.entities.ApplyCombo;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiApplyService;
import com.zfsoft.boot.apimgr.setup.oauth.OauthProperties;
import com.zfsoft.boot.apimgr.setup.oauth.OauthProperties.AuthType;
import com.zfsoft.boot.apimgr.web.dto.ApiApplyDto;

/**
 * 
 * @className	： ApiApplyServiceImpl
 * @description	： TODO(描述这个类的作用)
 * @author 		：樊新亮（1505）
 * @date		： 2017年10月16日 下午1:31:22
 * @version 	V1.0
 */
@Service
public class ApiApplyServiceImpl extends BaseServiceImpl<ApiApplyModel, IApiApplyDao> implements IApiApplyService{
	
	@Autowired
	private OauthProperties oauth;
	
	@Override
	@Transactional(rollbackFor = {Exception.class})
	public int saveApplys(ApiApplyDto apply, String user) {
		int a = 0;
		if(oauth.getAuthType() == AuthType.app) {
			String[] ids = apply.getAppIds().split(",");
			for (String appId : ids) {
				ApiApplyModel applyModel = new ApiApplyModel();
				applyModel.setApplyUser(user);applyModel.setApplyFor(apply.getDeployId());applyModel.setApplyTo(appId);
				applyModel.setApplyStatus("0");applyModel.setInvokeIp(apply.getInvokeIp());applyModel.setInvokeFrequency(apply.getFrequency());
				try {
					applyModel.setApplyFile(apply.getApplyFile().getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
				a += getDao().insert(applyModel);
			}
		}else if(oauth.getAuthType() == AuthType.api) {
			ApiApplyModel applyModel = new ApiApplyModel();
			applyModel.setApplyUser(user);applyModel.setApplyFor(apply.getDeployId());
			applyModel.setApplyStatus("0");applyModel.setInvokeIp(apply.getInvokeIp());applyModel.setInvokeFrequency(apply.getFrequency());
			try {
				applyModel.setApplyFile(apply.getApplyFile().getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			a = getDao().insert(applyModel);
		}
		return a;
	}

	@Override
	public List<ApiApplyModel> getPagedAuditList(ApiApplyModel apply) {
		return getDao().getPagedAuditList(apply);
	}

	@Override
	public List<ApplyCombo> comboListByOwner(String owner) {
		return getDao().comboListByOwner(owner);
	}
	
}
