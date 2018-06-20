/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.dao.entities;

import com.zfsoft.basemodel.BaseModel;

public class ApiInvokeModel extends BaseModel{
	private static final long serialVersionUID = -6879018188251608602L;
	private String apiId;
	private String invokeDeployId;
	private String paramRelation;
	public String getApiId() {
		return apiId;
	}
	public void setApiId(String apiId) {
		this.apiId = apiId;
	}
	public String getInvokeDeployId() {
		return invokeDeployId;
	}
	public void setInvokeDeployId(String invokeDeployId) {
		this.invokeDeployId = invokeDeployId;
	}
	public String getParamRelation() {
		return paramRelation;
	}
	public void setParamRelation(String paramRelation) {
		this.paramRelation = paramRelation;
	}
}
