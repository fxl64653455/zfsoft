package com.zfsoft.boot.authz.web.dto;

import java.io.Serializable;

import com.zfsoft.basemodel.QueryModel;
import com.zfsoft.search.core.SearchModel;

public class UserDto implements Serializable {

	private String uid;
	private String username;
	private String password;

	private Integer sex;
	private Integer age;
	private String token;
	
	
	private String yhm;//用户名
	private String zgh;//职工号
	private String xm;//姓名
	private String mm;//密码
	private String lxdh;//联系电话
	private String dzyx;//电子邮箱
	private String sfqy;//是否启用
	private String yhlx;//用户类型
	private String jgdm;//机构代码
	private String jgmc;//机构名称(部门名称)
	private String jsxx;//角色信息
	
	private String ejsq;//二级授权
	
	private SearchModel searchModel;
	private QueryModel queryModel = new QueryModel();
	
	
	public String getJgmc() {
		return jgmc;
	}
	public void setJgmc(String jgmc) {
		this.jgmc = jgmc;
	}
	public String getEjsq() {
		return ejsq;
	}
	public void setEjsq(String ejsq) {
		this.ejsq = ejsq;
	}
	public String getYhm() {
		return yhm;
	}
	public void setYhm(String yhm) {
		this.yhm = yhm;
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
	public String getMm() {
		return mm;
	}
	public void setMm(String mm) {
		this.mm = mm;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getDzyx() {
		return dzyx;
	}
	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}
	public String getYhlx() {
		return yhlx;
	}
	public void setYhlx(String yhlx) {
		this.yhlx = yhlx;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public String getJgdm() {
		return jgdm;
	}
	public void setJgdm(String jgdm) {
		this.jgdm = jgdm;
	}
	public QueryModel getQueryModel() {
		return queryModel;
	}
	public void setQueryModel(QueryModel queryModel) {
		this.queryModel = queryModel;
	}
	public String getJsxx() {
		return jsxx;
	}
	public void setJsxx(String jsxx) {
		this.jsxx = jsxx;
	}
	
}
