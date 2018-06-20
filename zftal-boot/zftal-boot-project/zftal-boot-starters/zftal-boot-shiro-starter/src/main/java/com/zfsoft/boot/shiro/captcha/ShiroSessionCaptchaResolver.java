package com.zfsoft.boot.shiro.captcha;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.zfsoft.boot.kaptcha.ext.CaptchaResolver;
import com.zfsoft.boot.kaptcha.ext.util.ExtConfig;
import com.zfsoft.shiro.SubjectUtils;

public class ShiroSessionCaptchaResolver implements CaptchaResolver {

	/**
	 * Name of the session attribute that holds the Captcha name. Only used
	 * internally by this implementation.
	 */
	public static final String Captcha_SESSION_ATTRIBUTE_NAME = ShiroSessionCaptchaResolver.class.getName() + ".Captcha";
	public static final String Captcha_DATE_SESSION_ATTRIBUTE_NAME = ShiroSessionCaptchaResolver.class.getName() + ".Captcha_DATE";

	private String sessionKeyValue = Captcha_SESSION_ATTRIBUTE_NAME;
	private String sessionKeyDateValue = Captcha_DATE_SESSION_ATTRIBUTE_NAME;

	@Override
	public void init(ExtConfig config) {
		this.sessionKeyValue = config.getSessionKey();
		this.sessionKeyDateValue = config.getSessionDate();
	}

	@Override
	public boolean validCaptcha(HttpServletRequest request, String capText) {
		if (StringUtils.isEmpty(capText)) {
			return false;
		}
		String sessionCapText = (String) SubjectUtils.getSession().getAttribute(this.sessionKeyValue);
		// String sessionCapDate = (String) SubjectUtils.getSession().getAttribute(this.sessionKeyDateValue);
		if (sessionCapText != null) {
			return StringUtils.equalsIgnoreCase(sessionCapText, capText);
		}
		return false;
	}

	@Override
	public void setCaptcha(HttpServletRequest request, HttpServletResponse response, String capText, Date capDate) {

		// store the text in the session
		SubjectUtils.getSession().setAttribute(this.sessionKeyValue, (StringUtils.isNotEmpty(capText) ? capText : null));

		// store the date in the session so that it can be compared
		// against to make sure someone hasn't taken too long to enter
		// their kaptcha
		SubjectUtils.getSession().setAttribute( this.sessionKeyDateValue, (capDate != null ? capDate : new Date()));

	}

}
