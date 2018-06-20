/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ApiDataBaseDto", description = "数据库配置数据传输对象")
public class ApiDataBaseDto {
	
	/**
	 * 数据库ID编号
	 */
	private String id;
	/**
	 * 数据库名称
	 */
	@ApiModelProperty(value = "数据库名称")
	@NotBlank(message="数据库名称必填")  
	private String name;
	/**
	 * 数据库中文名称
	 */
	@ApiModelProperty(value = "数据库中文名")
	@NotBlank(message="数据库中文名称必填")  
	private String cnname;
	/**
	 * 数据库类型
	 */
	@ApiModelProperty(value = "数据库类型")
	@NotBlank(message="数据库类型必填")
	private String dbtype;
	/**
	 * 数据库连接地址
	 */
	@ApiModelProperty(value = "数据库连接地址")
	@NotBlank(message="数据库连接地址必填")
	private String url;
	/**
	 * 数据库账号（已加密）
	 */
	@ApiModelProperty(value = "数据库账号")
	@NotBlank(message="数据库账号必填")
	private String username;
	/**
	 * 数据库密码（已加密）
	 */
	@ApiModelProperty(value = "数据库密码")
	@NotBlank(message="数据库密码必填")
	private String password;
	/**
	 * 数据库描述：该数据库相关简述
	 */
	@ApiModelProperty(value = "数据库描述")
	@NotBlank(message="数据库描述必填")
	private String desc;
	/**
	 * 接口数据库信息创建人ID
	 */
	private String owner;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getDbtype() {
		return dbtype;
	}

	public void setDbtype(String dbtype) {
		this.dbtype = dbtype;
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
