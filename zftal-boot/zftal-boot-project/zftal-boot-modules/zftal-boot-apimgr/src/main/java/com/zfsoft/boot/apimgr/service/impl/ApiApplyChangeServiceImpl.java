/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zfsoft.api.service.BaseServiceImpl;
import com.zfsoft.boot.apimgr.dao.daointerface.IApiApplyChangeDao;
import com.zfsoft.boot.apimgr.dao.daointerface.IApiApplyDao;
import com.zfsoft.boot.apimgr.dao.entities.ApiApplyChangeModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiApplyModel;
import com.zfsoft.boot.apimgr.dao.entities.ApiApplyModel.ApplyStatus;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiApplyChangeService;
import com.zfsoft.boot.apimgr.web.dto.ApiApplyDto;

@Service
public class ApiApplyChangeServiceImpl extends BaseServiceImpl<ApiApplyChangeModel, IApiApplyChangeDao> implements IApiApplyChangeService{

	@Autowired
	private IApiApplyDao applyDao;
	
	@Override
	public int saveApplyChange(ApiApplyDto par, String user) {
		
		ApiApplyModel apply = applyDao.getModel(par.getApplyId());
		ApiApplyChangeModel change = new ApiApplyChangeModel();
		change.setApplyId(apply.getApplyId());change.setChangeUser(user);
		change.setAuditStatus(ApplyStatus.wait.ordinal() + "");
		
		JSONObject src = new JSONObject();src.put("invokeIp", apply.getInvokeIp());src.put("invokeFrequency", apply.getInvokeFrequency());
		JSONObject target = new JSONObject();target.put("invokeIp", par.getInvokeIp());target.put("invokeFrequency", par.getFrequency());
		JSONObject changeContent = new JSONObject();changeContent.put("src", src);changeContent.put("target", target);
		change.setChangeContent(changeContent.toJSONString());
		
		try {
			change.setChangeFile(par.getApplyFile().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return getDao().insert(change);
	}

	@Override
	public List<ApiApplyChangeModel> getPagedAuditList(ApiApplyChangeModel change) {
		return getDao().getPagedAuditList(change);
	}
	
}
