/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.kaptcha.ext;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.boot.kaptcha.ext.util.ExtConfig;

public interface CaptchaResolver {

	public void init(ExtConfig config );
	
	/**
	 * Valid the current captcha via the given request.
	 * @param request request to be used for resolution
	 * @return the result
	 */
	boolean validCaptcha(HttpServletRequest request, String capText);

	/**
	 * Set the current captcha to the given one.
	 * @param request request to be used for captcha modification
	 * @param response response to be used for captcha modification
	 * @param capText the new captcha value
	 * @throws UnsupportedOperationException if the CaptchaResolver implementation does not support dynamic changing of the captcha
	 */
	void setCaptcha(HttpServletRequest request, HttpServletResponse response, String capText, Date capDate);
	
}
