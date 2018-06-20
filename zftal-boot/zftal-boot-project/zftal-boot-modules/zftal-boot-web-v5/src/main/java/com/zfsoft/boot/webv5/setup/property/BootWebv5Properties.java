package com.zfsoft.boot.webv5.setup.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("zftal.http")
public class BootWebv5Properties {

	/**
	 * 前端首页地址
	 */
	protected String frontIndex = "/index";
	/**
	 * 后端首页地址
	 */
	protected String adminIndex = "/index";
	/**
	 * 菜单类型:top/left
	 */
	protected String menuType = "top";
	

	public String getFrontIndex() {
		return frontIndex;
	}

	public void setFrontIndex(String frontIndex) {
		this.frontIndex = frontIndex;
	}

	public String getAdminIndex() {
		return adminIndex;
	}

	public void setAdminIndex(String adminIndex) {
		this.adminIndex = adminIndex;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

}
