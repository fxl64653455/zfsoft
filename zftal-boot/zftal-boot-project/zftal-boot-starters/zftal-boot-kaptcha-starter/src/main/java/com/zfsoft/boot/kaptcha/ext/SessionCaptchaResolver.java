package com.zfsoft.boot.kaptcha.ext;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.WebUtils;

import com.zfsoft.boot.kaptcha.ext.util.ExtConfig;

public class SessionCaptchaResolver implements CaptchaResolver {

	/**
	 * Name of the session attribute that holds the Captcha name. Only used
	 * internally by this implementation.
	 */
	public static final String Captcha_SESSION_ATTRIBUTE_NAME = SessionCaptchaResolver.class.getName() + ".Captcha";
	public static final String Captcha_DATE_SESSION_ATTRIBUTE_NAME = SessionCaptchaResolver.class.getName() + ".Captcha_DATE";

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
		String sessionCapText = (String) WebUtils.getSessionAttribute(request, this.sessionKeyValue);
		// String sessionCapDate = (String) WebUtils.getSessionAttribute(request,
		// this.sessionKeyDateValue);
		if (sessionCapText != null) {
			return StringUtils.equalsIgnoreCase(sessionCapText, capText);
		}
		return false;
	}

	@Override
	public void setCaptcha(HttpServletRequest request, HttpServletResponse response, String capText, Date capDate) {

		// store the text in the session
		WebUtils.setSessionAttribute(request, this.sessionKeyValue, (StringUtils.isNotEmpty(capText) ? capText : null));

		// store the date in the session so that it can be compared
		// against to make sure someone hasn't taken too long to enter
		// their kaptcha
		WebUtils.setSessionAttribute(request, this.sessionKeyDateValue, (capDate != null ? capDate : new Date()));

	}

}
