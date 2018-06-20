package com.zfsoft.boot.authz.dao.entities;

import org.apache.ibatis.type.Alias;

import com.zfsoft.basemodel.BaseModel;
import com.zfsoft.search.core.SearchModel;

/**
 * 
 * @className ： RoleModel
 * @description ： 角色信息表Model
 * @author ：万大龙（743）
 * @date ： 2018年4月12日 下午2:49:01
 * @version V1.0
 */
@Alias(value = "RoleModel")
public class RoleModel extends BaseModel {

	private static final long serialVersionUID = 6583251406158603349L;
	/**	
	 * 角色代码
	 */
	private String jsdm;
	/**
	 * 角色名称
	 */
	private String jsmc;
	/**
	 * 角色说明
	 */
	private String jssm;
	/**
	 * 角色类型(1:原生|2:继承|3:复制)
	 */
	private String jslx;
	/**
	 * 角色状态(0:不可用|1:正常)
	 */
	private String jszt;
	/**
	 * 角色创建人ID
	 */
	private String yhm;
	/**
	 * 角色已分配用户量
	 */
	private int yhs;
	/**
	 * 角色已分配用户组名称，多个以,连接
	 */
	private String yhz;

	private SearchModel searchModel = new SearchModel();
	
	public String getJsdm() {
		return jsdm;
	}

	public void setJsdm(String jsdm) {
		this.jsdm = jsdm;
	}

	public String getJsmc() {
		return jsmc;
	}

	public void setJsmc(String jsmc) {
		this.jsmc = jsmc;
	}

	public String getJssm() {
		return jssm;
	}

	public void setJssm(String jssm) {
		this.jssm = jssm;
	}

	public String getJslx() {
		return jslx;
	}

	public void setJslx(String jslx) {
		this.jslx = jslx;
	}

	public String getJszt() {
		return jszt;
	}

	public void setJszt(String jszt) {
		this.jszt = jszt;
	}

	public String getYhm() {
		return yhm;
	}

	public void setYhm(String yhm) {
		this.yhm = yhm;
	}

	public int getYhs() {
		return yhs;
	}

	public void setYhs(int yhs) {
		this.yhs = yhs;
	}

	public String getYhz() {
		return yhz;
	}

	public void setYhz(String yhz) {
		this.yhz = yhz;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

}
