package com.zfsoft.boot.kaptcha.ext.util;

import java.util.Properties;

import com.google.code.kaptcha.util.Config;
import com.zfsoft.boot.kaptcha.ext.Constants;

public class ExtConfig extends Config {

	public ExtConfig(Properties properties) {
		super(properties);
	}
	
	/**
	 * Allows one to override the key name which is stored in the users Cookie.
	 */
	public String getCookieKey(String def) {
		return this.getProperties().getProperty(Constants.KAPTCHA_COOKIE_CONFIG_KEY, def);
	}

	/**
	 * Allows one to override the date name which is stored in the users Cookie.
	 */
	public String getCookieDate(String def)
	{
		return this.getProperties().getProperty(Constants.KAPTCHA_COOKIE_CONFIG_DATE, def);
	}

}
