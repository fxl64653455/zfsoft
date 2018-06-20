/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

public class ApiInvokeDto {
	/**是否启用连续调用配置*/
	private boolean invokeEnable;
	/**接口发布ID*/
	@NotBlank(message = "接口ID不能为空")
	private String apiId;
	/**被调用接口的ID*/
	private String invokeDeployId;
	/**
	 * 参数对应关系
	 * apiParam.key 对应接口输入参数key
	 * apiParam.name 对应被调用接口的输入参数key
	 * apiParam.desc 对应被调用接口的输入参数name
	 */
	private List<ApiParam> apiParams = new ArrayList<>();
	
	public boolean isInvokeEnable() {
		return invokeEnable;
	}
	public void setInvokeEnable(boolean invokeEnable) {
		this.invokeEnable = invokeEnable;
	}
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
	public List<ApiParam> getApiParams() {
		return apiParams;
	}
	public void setApiParams(List<ApiParam> apiParams) {
		this.apiParams = apiParams;
	}
}
