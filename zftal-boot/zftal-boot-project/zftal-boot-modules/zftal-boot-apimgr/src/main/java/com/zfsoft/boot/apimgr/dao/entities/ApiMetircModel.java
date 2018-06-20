/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.dao.entities;

import com.zfsoft.basemodel.BaseModel;

/**
 * 
 * @className	： ApiMetircModel
 * @description	： 服务接口客户端请求记录Model
 * @author 		：万大龙（743）
 * @date		： 2017年11月28日 下午4:56:32
 * @version 	V1.0
 */
@SuppressWarnings("serial")
public class ApiMetircModel extends BaseModel {

	/**
	 * top记录数
	 */
	private int top = 0;
	
	/** ID编号 */
	private String id;
	/** 业务主键ID;用于作为附加参数提取该业务相关的请求记录 */
	private String bizId;
	private String bizName;
	/** 应用Key;记录该次记录产生源应用 */
	private String appKey;
	private String appName;
	/** 客户端IP地址 */
	private String addr;
	/** 接口路径 */
	private String uri;
	/** 客户端设备名称 */
	private String deviceName;
	/** 客户端操作系统名称 */
	private String osName;
	/** 客户端操作系统版本 */
	private String osVer;
	/** 客户端操作系统制造商 */
	private String osMfr;
	/** 客户端浏览器名称 */
	private String browserName;
	/** 客户端浏览器版本 */
	private String browserVer;
	/** 客户端浏览器类型 */
	private String browserType;
	/** 客户端浏览器内核 */
	private String browserKernel;
	/** 客户端User-Agent请求头原始信息 */
	private String userAgent;
	/** 客户端请求发送时间 */
	private String time;
	private String beginTime;
	private String endTime;

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	
	public String getBizName() {
		return bizName;
	}

	public void setBizName(String bizName) {
		this.bizName = bizName;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getOsVer() {
		return osVer;
	}

	public void setOsVer(String osVer) {
		this.osVer = osVer;
	}

	public String getOsMfr() {
		return osMfr;
	}

	public void setOsMfr(String osMfr) {
		this.osMfr = osMfr;
	}

	public String getBrowserName() {
		return browserName;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	public String getBrowserVer() {
		return browserVer;
	}

	public void setBrowserVer(String browserVer) {
		this.browserVer = browserVer;
	}

	public String getBrowserType() {
		return browserType;
	}

	public void setBrowserType(String browserType) {
		this.browserType = browserType;
	}

	public String getBrowserKernel() {
		return browserKernel;
	}

	public void setBrowserKernel(String browserKernel) {
		this.browserKernel = browserKernel;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	
}
