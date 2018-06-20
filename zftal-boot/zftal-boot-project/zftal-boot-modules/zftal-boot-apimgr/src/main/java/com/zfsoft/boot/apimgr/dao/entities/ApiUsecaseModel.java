/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.dao.entities;

import com.zfsoft.basemodel.BaseModel;

/**
 * 
 * @className ： ApiUsecaseModel
 * @description ： 接口使用示例表(用于关联发布的接口与代码示例模板关系)Model
 * @author ：万大龙（743）
 * @date ： 2017年10月26日 上午9:42:55
 * @version V1.0
 */
@SuppressWarnings("serial")
public class ApiUsecaseModel extends BaseModel {

	/**
	 * 接口使用示例表ID编号
	 */
	private String id;
	/**
	 * 接口发布信息表ID编号
	 */
	private String deployId;
	/**
	 * 语言版本：Java、JavaScript、PHP、Python...等 ；
	 * com.zfsoft.boot.dsb.util.LanguageTypeEnum
	 */
	private String language;

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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
