/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.dto;

import javax.validation.constraints.NotBlank;

/**
 * 
 * @className	： ApiUsecaseDto
 * @description	： TODO(描述这个类的作用)
 * @author 		：万大龙（743）
 * @date		： 2017年10月26日 上午10:11:07
 * @version 	V1.0
 */
public class ApiUsecaseDto {

	/**
	 * 接口使用示例表ID编号
	 */
	private String id;
	/**
	 * 接口发布信息表ID编号
	 */
	@NotBlank(message="请选择发布的接口记录")
	private String deployId;
	/**
	 * 语言版本：Java、JavaScript、PHP、Python...等 ；
	 * com.zfsoft.boot.dsb.util.LanguageTypeEnum
	 */
	@NotBlank(message="语音版本不能为空")
	private String[] language;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeployId() {
		return deployId;
	}

	public void setDeployId(String deployId) {
		this.deployId = deployId;
	}

	public String[] getLanguage() {
		return language;
	}

	public void setLanguage(String[] language) {
		this.language = language;
	}

}
