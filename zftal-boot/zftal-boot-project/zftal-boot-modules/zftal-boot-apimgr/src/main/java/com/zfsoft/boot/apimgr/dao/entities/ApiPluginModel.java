/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.dao.entities;

import java.util.HashMap;
import java.util.Map;

import com.zfsoft.basemodel.BaseModel;

/**
 * 
 * @className	： ApiPluginModel
 * @description	： TODO(描述这个类的作用)
 * @author 		：万大龙（743）
 * @date		： 2017年10月31日 下午7:19:19
 * @version 	V1.0
 */
@SuppressWarnings("serial")
public class ApiPluginModel extends BaseModel {

	/**
	 * 接口ID编号
	 */
	private String id;
	/**
	 * 接口图标
	 */
	private byte[] iconbyte;
	/**
	 * 接口状态：0:不可用、1：可用
	 */
	private String status;
	/**
	 * 接口描述信息：JSON格式的对象，用于存储不同接口的描述信息
	 */
	private String desc;
	/**
	 * 接口发布类型： com.zfsoft.boot.dsb.util.DeployTypeEnum
	 */
	private String type;
	/**
	 * 接口请求方式：com.zfsoft.boot.dsb.util.RequestMethodEnum
	 */
	private String method;
	/**
	 * 接口发布URL:指对外暴露的访问地址
	 */
	private String addr;
	/**
	 * 接口发布版本：自动生成
	 */
	private String ver;
	/**
	 * 接口信息id
	 */
	private String apiid;
	/** 接口名称 */
	private String apiName;
	/** 接口简单描述信息 */
	private String apiInfo;
	/**
	 * 接口发布人ID
	 */
	private String owner;
	/**
	 * 接口发布时间：数据库时间
	 */
	private String time;
	/**
	 * 原始接口描述信息
	 */
	private ApiInfoModel api;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte[] getIconbyte() {
		return iconbyte;
	}

	public void setIconbyte(byte[] iconbyte) {
		this.iconbyte = iconbyte;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
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

	public ApiInfoModel getApi() {
		return api;
	}

	public void setApi(ApiInfoModel api) {
		this.api = api;
	}

	public String getApiid() {
		return apiid;
	}

	public void setApiid(String apiid) {
		this.apiid = apiid;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getApiInfo() {
		return apiInfo;
	}

	public void setApiInfo(String apiInfo) {
		this.apiInfo = apiInfo;
	}
	
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("apiName", apiName);
		map.put("time", time);
		map.put("type", type);
		map.put("addr", addr);
		map.put("apiInfo", apiInfo);
		map.put("desc", desc);
		map.put("ver", ver);
		map.put("owner", owner);
		return map;
	}
	
}
