package com.zfsoft.boot.authz.dao.entities;

import org.apache.ibatis.type.Alias;

import com.zfsoft.basemodel.BaseModel;
import com.zfsoft.basemodel.QueryModel;
import com.zfsoft.search.core.SearchModel;

/**
 * 
 * @className	： UserModel
 * @description	：User对象模型
 * @author 		：万大龙（743）
 * @date		： 2018年4月11日 上午11:21:11
 * @version 	V1.0
 */
@Alias(value="UserModel")
@SuppressWarnings("serial")
public class UserModel extends BaseModel {

	/**
	 * 用户名
	 */
	private String yhm;
	/**
	 * 姓名
	 */
	private String xm;
	/**
	 * 密码
	 */
	private String mm;
	/**
	 * 手机号码
	 */
	private String sjhm;
	/**
	 * 邮箱地址
	 */
	private String dzyx;
	/**
	 * 用户状态：（0:不可用|1:正常|2:锁定）
	 */
	private String yhzt;
	/**角色信息*/
	private String jsxx;
	
	private SearchModel searchModel = new SearchModel();
	private QueryModel queryModel = new QueryModel();


	public String getYhm() {
		return yhm;
	}

	public void setYhm(String yhm) {
		this.yhm = yhm;
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

	public String getSjhm() {
		return sjhm;
	}

	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}

	public String getDzyx() {
		return dzyx;
	}

	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}

	public String getYhzt() {
		return yhzt;
	}

	public void setYhzt(String yhzt) {
		this.yhzt = yhzt;
	}

	public String getJsxx() {
		return jsxx;
	}

	public void setJsxx(String jsxx) {
		this.jsxx = jsxx;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public QueryModel getQueryModel() {
		return queryModel;
	}

	public void setQueryModel(QueryModel queryModel) {
		this.queryModel = queryModel;
	}
	
}
