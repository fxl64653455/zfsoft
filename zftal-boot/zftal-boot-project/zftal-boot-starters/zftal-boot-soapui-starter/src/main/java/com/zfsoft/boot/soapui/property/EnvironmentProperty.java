/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.soapui.property;


import java.util.Properties;

public class EnvironmentProperty {

	/**
	 * Environment Name
	 */
	private String name;
	/**
	 * Environment Settings
	 */
	private Properties settings = new Properties();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Properties getSettings() {
		return settings;
	}

	public void setSettings(Properties settings) {
		this.settings = settings;
	}

}
