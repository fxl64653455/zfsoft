/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.zfsoft.boot.apimgr.web.validator.ApplyFile;
import com.zfsoft.boot.apimgr.web.validator.IpChain;

public class ApiApplyDto {
	
	private String appIds;
	private String applyId;
	@NotBlank(message = "接口ID不能为空")
	private String deployId;
	@IpChain(message = "IP格式错误")
	private String invokeIp;
	@NotNull(message = "调用频率不能为空")
	private Integer frequency;
	@ApplyFile(message = "只能上传word文件")
	private MultipartFile applyFile;
	
	public String getAppIds() {
		return appIds;
	}
	public void setAppIds(String appIds) {
		this.appIds = appIds;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public String getDeployId() {
		return deployId;
	}
	public void setDeployId(String deployId) {
		this.deployId = deployId;
	}
	public String getInvokeIp() {
		return invokeIp;
	}
	public void setInvokeIp(String invokeIp) {
		this.invokeIp = invokeIp;
	}
	public Integer getFrequency() {
		return frequency;
	}
	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}
	public MultipartFile getApplyFile() {
		return applyFile;
	}
	public void setApplyFile(MultipartFile applyFile) {
		this.applyFile = applyFile;
	}
}
