package com.zfsoft.boot.authz.dao.entities;

import org.apache.ibatis.type.Alias;

import com.zfsoft.basemodel.BaseModel;

/**
 * 
 * @className ： UserGroupModel
 * @description ： 用户组信息对象模型
 * @author ：万大龙（743）
 * @date ： 2018年4月13日 下午5:31:29
 * @version V1.0
 */
@Alias(value = "UserGroupModel")
@SuppressWarnings("serial")
public class UserGroupModel extends BaseModel {

	/**
	 * 用户组信息ID
	 */
	private String yhz;
	/**
	 * 用户组名称
	 */
	private String yhzmc;
	/**
	 * 用户组创建时间
	 */
	private String cjsj;

	public String getYhz() {
		return yhz;
	}

	public void setYhz(String yhz) {
		this.yhz = yhz;
	}

	public String getYhzmc() {
		return yhzmc;
	}

	public void setYhzmc(String yhzmc) {
		this.yhzmc = yhzmc;
	}

	public String getCjsj() {
		return cjsj;
	}

	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}

}
