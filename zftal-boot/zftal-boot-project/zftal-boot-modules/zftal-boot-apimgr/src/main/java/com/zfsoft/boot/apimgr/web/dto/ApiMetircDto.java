/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.web.dto;

/**
 * 
 * @className ： ApiMetircDto
 * @description ： 接口监控Dto对象
 * @author ：万大龙（743）
 * @date ： 2017年11月28日 上午10:36:21
 * @version V1.0
 */
public class ApiMetircDto {

	/** ID编号 */
	private String id;
	/** 业务主键ID;用于作为附加参数提取该业务相关的请求记录 */
	private String bizId;
	private String bizName;
	/** 应用Key;记录该次记录产生源应用 */
	private String appKey;
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

}
