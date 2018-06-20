/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.dao.entities;

import java.util.HashMap;
import java.util.Map;

import com.zfsoft.basemodel.BaseModel;

/**
 * 
 * @className ： ApiInfoModel
 * @description ： 接口信息描述表Model
 * @author ：万大龙（743）
 * @date ： 2017年9月19日 下午4:46:14
 * @version V1.0
 */
public class ApiInfoModel extends BaseModel {

	private static final long serialVersionUID = 5094568306833226198L;

	/**
	 * 接口ID编号
	 */
	private String id;
	/**
	 * 接口名称
	 */
	private String name;
	/**
	 * 接口类型：biz、data；用于区别业务接口和数据源接口，不显示
	 */
	private String type;
	/**
	 * 数据库ID编号
	 */
	private String dbid;
	/**
	 * 接口用途描述
	 */
	private String info;
	/**
	 * 接口详细说明
	 */
	private String detail;
	/**
	 * 接口描述信息：JSON格式的对象，用于存储不同接口的描述信息
	 */
	private String desc;
	/**
	 * 接口创建用户
	 */
	private String owner;
	/**
	 * 接口创建时间
	 */
	private String time;
	/**
	 * 接口发布次数
	 */
	private String deploynum;
	/**是否启用连续调用配置*/
	private String invokeEnable;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getDbid() {
		return dbid;
	}

	public void setDbid(String dbid) {
		this.dbid = dbid;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDeploynum() {
		return deploynum;
	}

	public void setDeploynum(String deploynum) {
		this.deploynum = deploynum;
	}
	
	public String getInvokeEnable() {
		return invokeEnable;
	}

	public void setInvokeEnable(String invokeEnable) {
		this.invokeEnable = invokeEnable;
	}

	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("name", name);
		map.put("time", time);
		map.put("type", type);
		//map.put("url", url);
		map.put("info", info);
		map.put("desc", desc);
		return map;
	}

}
