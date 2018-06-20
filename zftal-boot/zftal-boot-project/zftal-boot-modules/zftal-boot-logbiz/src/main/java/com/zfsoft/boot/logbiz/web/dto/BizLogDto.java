/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.logbiz.web.dto;

import com.zfsoft.basemodel.BaseModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "BizLogDto", description = "业务日志对象")
public class BizLogDto extends BaseModel{

	private static final long serialVersionUID = -4505465576295612306L;
	/**
	 * 操作人
	 */
	@ApiModelProperty(value = "操作人")
	private String czr;
	/**
	 * 业务名称
	 */
	@ApiModelProperty(value = "业务名称")
	private String ywmc;
	/**
	 * 模块名称
	 */
	@ApiModelProperty(value = "模块名称")
	private String mkmc;
	/**
	 * 操作类型
	 */
	@ApiModelProperty(value = "操作类型")
	private String czlx;
	/**
	 * 操作描述
	 */
	@ApiModelProperty(value = "操作描述")
	private String czms;
	/**
	 * 操作日期
	 */
	@ApiModelProperty(value = "操作日期")
	private String czrq;
	/**
	 * 客户端IP地址
	 */
	@ApiModelProperty(value = "客户端IP地址")
	private String remote;
	/**
	 * 应用服务IP地址
	 */
	@ApiModelProperty(value = "应用服务IP地址")
	private String host;
	/**开始时间*/
	@ApiModelProperty(value = "开始时间")
	private String beginTime;
	/**结束时间*/
	@ApiModelProperty(value = "结束时间")
	private String endTime;
	
	public String getCzr() {
		return czr;
	}

	public void setCzr(String czr) {
		this.czr = czr;
	}

	public String getYwmc() {
		return ywmc;
	}

	public void setYwmc(String ywmc) {
		this.ywmc = ywmc;
	}

	public String getMkmc() {
		return mkmc;
	}

	public void setMkmc(String mkmc) {
		this.mkmc = mkmc;
	}

	public String getCzlx() {
		return czlx;
	}

	public void setCzlx(String czlx) {
		this.czlx = czlx;
	}

	public String getCzms() {
		return czms;
	}

	public void setCzms(String czms) {
		this.czms = czms;
	}

	public String getCzrq() {
		return czrq;
	}

	public void setCzrq(String czrq) {
		this.czrq = czrq;
	}

	public String getRemote() {
		return remote;
	}

	public void setRemote(String remote) {
		this.remote = remote;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
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
