package com.zfsoft.boot.authz.dao.entities;

import org.apache.ibatis.type.Alias;

import com.zfsoft.basemodel.BaseModel;

@Alias(value = "LoginModel")
public class LoginModel extends BaseModel {

	private static final long serialVersionUID = 1L;
	private String yhm; // 用户名
	private String mm; // 密码
	private String yzm; // 验证码
	private String yhlybid; // 用户id
	private String jg_id; // 部门代码
	private String jgdm;// 机构代码
	private String sfqy;// 是否启用
	private String zgh;
	private String xm;
	private String bmdm_id; // 部门代码
	private String lxdh; // 联系电话
	private String ylzd1;
	private String ylzd2;
	private String dzyx; // 电子邮箱
	private String[] cbv;
	private String pkValue;
	private String yhlx; // 用户类型，xs,js：方便区别用户角色
	private String fjgndm;
	private String switchRole;

	public String getYhm() {
		return yhm;
	}

	public void setYhm(String yhm) {
		this.yhm = yhm;
	}

	public String getMm() {
		return mm;
	}

	public void setMm(String mm) {
		this.mm = mm;
	}

	public String getYzm() {
		return yzm;
	}

	public void setYzm(String yzm) {
		this.yzm = yzm;
	}

	public String getYhlybid() {
		return yhlybid;
	}

	public void setYhlybid(String yhlybid) {
		this.yhlybid = yhlybid;
	}

	public String getJg_id() {
		return jg_id;
	}

	public void setJg_id(String jg_id) {
		this.jg_id = jg_id;
	}

	public String getJgdm() {
		return jgdm;
	}

	public void setJgdm(String jgdm) {
		this.jgdm = jgdm;
	}

	public String getSfqy() {
		return sfqy;
	}

	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getBmdm_id() {
		return bmdm_id;
	}

	public void setBmdm_id(String bmdm_id) {
		this.bmdm_id = bmdm_id;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getYlzd1() {
		return ylzd1;
	}

	public void setYlzd1(String ylzd1) {
		this.ylzd1 = ylzd1;
	}

	public String getYlzd2() {
		return ylzd2;
	}

	public void setYlzd2(String ylzd2) {
		this.ylzd2 = ylzd2;
	}

	public String getDzyx() {
		return dzyx;
	}

	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}

	public String[] getCbv() {
		return cbv;
	}

	public void setCbv(String[] cbv) {
		this.cbv = cbv;
	}

	public String getPkValue() {
		return pkValue;
	}

	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}

	public String getYhlx() {
		return yhlx;
	}

	public void setYhlx(String yhlx) {
		this.yhlx = yhlx;
	}

	public String getFjgndm() {
		return fjgndm;
	}

	public void setFjgndm(String fjgndm) {
		this.fjgndm = fjgndm;
	}

	public String getSwitchRole() {
		return switchRole;
	}

	public void setSwitchRole(String switchRole) {
		this.switchRole = switchRole;
	}

}
