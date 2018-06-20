package com.zfsoft.boot.authz.dao.entities;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

/**
 * 功能按钮
 * @className	： Button
 * @description	： TODO(描述这个类的作用)
 * @author 		：樊新亮（1505）
 * @date		： 2018年6月7日 下午3:46:22
 * @version 	V1.0
 */
@Alias(value="button")
public class Button implements Serializable {

	private static final long serialVersionUID = -7721627859928970573L;

	private String gnczid;
	private String czdm;
	private String czmc;
	
	public String getGnczid() {
		return gnczid;
	}
	public void setGnczid(String gnczid) {
		this.gnczid = gnczid;
	}
	public String getCzdm() {
		return czdm;
	}
	public void setCzdm(String czdm) {
		this.czdm = czdm;
	}
	public String getCzmc() {
		return czmc;
	}
	public void setCzmc(String czmc) {
		this.czmc = czmc;
	}
	
	
}
