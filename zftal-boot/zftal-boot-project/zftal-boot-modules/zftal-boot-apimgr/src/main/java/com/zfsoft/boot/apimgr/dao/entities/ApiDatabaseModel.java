/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.dao.entities;

import com.zfsoft.basemodel.BaseModel;

/**
 * 
 * @className ： ApiDatabaseModel
 * @description ： 接口数据库信息表（存储接口数据来源的数据库连接信息）Model
 * @author ：万大龙（743）
 * @date ： 2017年10月14日 下午4:45:53
 * @version V1.0
 */
public class ApiDatabaseModel extends BaseModel {

	private static final long serialVersionUID = -6407909688636036868L;

	/**
	 * 数据库ID编号
	 */
	private String id;
	/**
	 * 数据库类型
	 */
	private String dbtype;
	/**数据库类型别名*/
	private String dbTypeDesc;
	/**
	 * 数据库名称
	 */
	private String name;
	/**
	 * 数据库中文名称
	 */
	private String cnname;
	/**
	 * 数据库连接地址
	 */
	private String url;
	/**
	 * 数据库账号（已加密）
	 */
	private String username;
	/**
	 * 数据库密码（已加密）
	 */
	private String password;
	/**
	 * 数据库描述：该数据库相关简述
	 */
	private String desc;
	/**
	 * 数据库信息创建人ID
	 */
	private String owner;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDbtype() {
		return dbtype;
	}

	public void setDbtype(String dbtype) {
		this.dbtype = dbtype;
	}
	
	public String getDbTypeDesc() {
		return dbTypeDesc;
	}

	public void setDbTypeDesc(String dbTypeDesc) {
		this.dbTypeDesc = dbTypeDesc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCnname() {
		return cnname;
	}

	public void setCnname(String cnname) {
		this.cnname = cnname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

}
